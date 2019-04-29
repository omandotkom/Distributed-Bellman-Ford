/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribusi.proses;

import java.util.*;

/* name table digunakan oleh NameServer sbg penyimpanan
* -------------------------------------
* | nama | host address/IP | port |
* -------------------------------------
 */
/**
 *
 * @author omandotkom
 */
public class NameTable {

    final int maxSize = 100;
    private String[] names = new String[maxSize];
    private String[] hosts = new String[maxSize];
    private int[] ports = new int[maxSize];
    private int dirsize = 0;

    /* pencarian input nama proses */
    int search(String s) {
        for (int i = 0; i < dirsize; i++) {
            if (names[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    /* insert nama proses, host dan port ke tabel
* return : status 1 :insert, 0 : sdh ada nama di tabel atau
*
tabel penuh */
    int insert(String s, String hostAddr, int port) {
        int i = search(s); // sudah ada?
        if ((i == -1) && (dirsize < maxSize)) {
            names[dirsize] = s;
            hosts[dirsize] = hostAddr;
            ports[dirsize] = port;
            dirsize++;
            return 1;
        } else // nama sudah ada or tabel full
        {
            return 0;
        }
    }

    int getPort(int index) {
        return ports[index];
    }

    String getHostName(int index) {
        return hosts[index];
    }
}
