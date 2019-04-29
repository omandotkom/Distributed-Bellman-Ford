/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribusi.proses;

import java.util.*;
public class Msg {
int srcId, destId;
String tag;
String msgBuf;
public Msg(int s, int t, String msgType, String buf) {
srcId = s;
destId = t;
tag = msgType;
msgBuf = buf;
}
public int getSrcId() {
return srcId;
}
public int getDestId() {
return destId;
}
public String getTag() {
return tag;
}
public String getMessage() {
return msgBuf;
}
public int getMessageInt() {
StringTokenizer st = new StringTokenizer(msgBuf);
return Integer.parseInt(st.nextToken());
}
//
}