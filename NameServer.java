/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribusi.proses;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author omandotkom
 */
/* server melayani request utk insert atau search
* ke struktur data NameTable */
public class NameServer {

    NameTable table;
    static final int port = 1024;
//konstruktor default memanggil NameTable

    public NameServer() {
        table = new NameTable();
    }

    //membaca data dari socket client yang terkoneksi
//lalu di parsing (apakah search atau insert)
    void handleclient(Socket client) {
        try {
            BufferedReader din = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter pout = new PrintWriter(client.getOutputStream());
            String getline = din.readLine();
            StringTokenizer st = new StringTokenizer(getline);
            String tag = st.nextToken();
            if (tag.equals("search")) {
                int index = table.search(st.nextToken());
                if (index == -1) // not found
                {
                    pout.println(-1 + " nullhost");
                } else {
                    pout.println(table.getPort(index) + " "
                            + table.getHostName(index));
                }
            } else if (tag.equals("insert")) {
                String name = st.nextToken();
                String host = st.nextToken();
                int port = Integer.parseInt(st.nextToken());
                int retVal = table.insert(name, host, port);
                pout.println(retVal);
            }
            pout.flush();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        NameServer ns = new NameServer();
        System.out.println("NameServer started:");
        
        try {
            ServerSocket listener = new ServerSocket(port);
            while (true) {
                Socket aClient = listener.accept();
                ns.handleclient(aClient);
                  aClient.close();
                
            }
        } catch (IOException e) {
            System.err.println("Server aborted:" + e);
        }
    }
}
