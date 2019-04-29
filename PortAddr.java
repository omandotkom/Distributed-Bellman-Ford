/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribusi.proses;

/**
 *
 * @author omandotkom
 */
public class PortAddr {

    String hostaddr;
    int portnum;

    public PortAddr(String s, int i) {
        hostaddr = new String(s);
        portnum = i;
    }

    public int getPort() {
        return portnum;
    }

    public String getHostAddr() {
        return hostaddr;
    }
}
