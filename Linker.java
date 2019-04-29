/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;
import java.net.*;

/**
 *
 * @author omandotkom
 */
public class Linker {

    PrintWriter[] dataOut;
    BufferedReader[] dataIn;
    BufferedReader dIn;
    int myId, N;
    Connector conn;
    public LinkedList<Integer> neighbors = new LinkedList<Integer>();

    public Linker(String basename, int id, int nProc) throws Exception {
        myId = id;
        N = nProc;
        dataIn = new BufferedReader[N];
        dataOut = new PrintWriter[N];
        Topology.readNeighbors(myId, N, neighbors);
        conn = new Connector();
        conn.Connect(basename, myId, N, dataIn, dataOut);
    }

    public void sendMsg(int destId, String tag, String msg) {
        dataOut[destId].println(myId + " " + destId + " " + tag
                + " " + msg + "#");
        dataOut[destId].flush();
    }

    public void sendMsg(int destId, String tag) {
    }

    public void multiCast(LinkedList<Integer> destIds, String tag, String msg) {
        for (int i = 0; i < destIds.size(); i++) {
            sendMsg(destIds.get(i).intValue(), tag, msg);
        }
    }

    public Msg receiveMsg(int fromId) throws IOException {
        String getline = dataIn[fromId].readLine();
        System.out.println(" received message " + getline);
        StringTokenizer st = new StringTokenizer(getline);
        int srcId = Integer.parseInt(st.nextToken());
        int destId = Integer.parseInt(st.nextToken());
        String tag = st.nextToken();
        String msg = st.nextToken();
//dikemas dalam satu kelas Msg
        return new Msg(srcId, destId, tag, msg);
    }
}
