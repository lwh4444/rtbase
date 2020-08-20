package cn_rt.idsbase;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SendUtils {
    private static InetAddress mAddress;
    private static DatagramSocket socket = null;
    //	private static String ip = "255.255.255.255";
    private static final int SendPort = 9999;

    public static void send(final String ip, final String content) {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            mAddress = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        new Thread() {
            private byte[] sendBuf;

            public void run() {
                try {
                    sendBuf = content.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                DatagramPacket recvPacket1 = new DatagramPacket(sendBuf, sendBuf.length, mAddress, SendPort);
                try {
                    socket.send(recvPacket1);
                    socket.close();
                    System.out.println("已将内容发送给了服务端" + content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
