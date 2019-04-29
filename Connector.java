/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribusi.proses;

import java.util.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author omandotkom
 */
public class Connector {

    ServerSocket listener;
    Socket[] link;

    public void closeSockets() {
        try {
            listener.close();
            for (int i = 0; i < link.length; i++) {
                link[i].close();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void Connect(String basename, int myId, int nProc, BufferedReader[] dataIn,
            PrintWriter[] dataOut) throws Exception {
        NameClient c = new NameClient();
        link = new Socket[nProc];
        final int localport = 1031;
        listener = new ServerSocket(localport);
//registrasi ke nameserver
        c.insertName(basename + myId, (InetAddress.getLocalHost()).getHostAddress(),
                localport);
//untuk suatu proses Pi, terima koneksi dari P0,P1...Pi-1
        for (int i = 0; i < myId; i++) {
            Socket s = listener.accept();
            BufferedReader dIn = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
            String getline = dIn.readLine();
            StringTokenizer st = new StringTokenizer(getline);
            int srcId = Integer.parseInt(st.nextToken());
            int destId = Integer.parseInt(st.nextToken());
            String tag = st.nextToken();
            if (tag.equals("hello")) {
                link[srcId] = s;
                dataIn[srcId] = dIn;
                dataOut[srcId] = new PrintWriter(s.getOutputStream());
            }
        }
        //buat koneksi ke Pi+1, Pi+2,... Pnproc (nProc = jumlah total proses)
        for (int i = myId + 1; i < nProc; i++) {
            PortAddr addr;
            do {
                addr = c.searchName(basename + i);
                Thread.sleep(100);
            } while (addr.getPort() == -1);
            link[i] = new Socket(addr.getHostAddr(), addr.getPort());
            dataOut[i] = new PrintWriter(link[i].getOutputStream());
            dataIn[i] = new BufferedReader(
                    new InputStreamReader(link[i].getInputStream()));
//kirim pesan "hello" untuk membuat koneksi
            dataOut[i].println(myId + " " + i + " hello null");
            dataOut[i].flush();
        }

    }
}
