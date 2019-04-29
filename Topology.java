/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribusi.proses;

import java.io.*;
import java.util.*;

public class Topology {
//konfigurasi koneksi dengan tetangga

    public static void readNeighbors(int myId, int N,
            LinkedList<Integer> neighbors) {
        System.out.println("Reading topology...");
        try {
//file berisi list tetangga (process Id)
            BufferedReader dIn = new BufferedReader(
                    new FileReader("topology" + myId));
            StringTokenizer st = new StringTokenizer(dIn.readLine());
            while (st.hasMoreTokens()) {
                int neighbor = Integer.parseInt(st.nextToken());
                neighbors.add(new Integer(neighbor));
            }
        } catch (FileNotFoundException e) { //fully-connected Graph
            for (int j = 0; j < N; j++) {
                if (j != myId) {
                    neighbors.add(new Integer(j));
                }
            }
        } catch (IOException e) { //error lain
            System.err.println(e);
        }
    }
}
