/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author omandotkom
 */
public class NameClient {

    BufferedReader din;
    PrintStream pout;
    final String addr = "192.168.100.6"; //address NameServer (Hardcoded!)
    final int port = 5001;//Hardcoded!

    private void getSocket() throws IOException {
        Socket server = new Socket(addr, port);
        System.out.println("Creating socket to connect to " + addr + " : " +port);
        din = new BufferedReader(
                new InputStreamReader(server.getInputStream()));
        pout = new PrintStream(server.getOutputStream());
    }

    public int insertName(String name, String host, int portnum)
            throws IOException {
        getSocket();
        pout.println("insert " + name + " " + host + " " + portnum);
        pout.flush();
        return Integer.parseInt(din.readLine());
    }

    public PortAddr searchName(String name) throws IOException {
        getSocket();
        pout.println("search " + name);
        pout.flush();
        String result = din.readLine();
        StringTokenizer st = new StringTokenizer(result);
        int portnum = Integer.parseInt(st.nextToken());
        String hname = st.nextToken();
        return new PortAddr(hname, portnum);
    }
//
}
