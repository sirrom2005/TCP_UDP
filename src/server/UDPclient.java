package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPclient {
    public static void main(String[] args) {
        new Client();
    }
    
    
    static class Client{
        byte[] buf = new byte[1024];
        DatagramSocket socket;
        InetAddress address;
        
        public Client(){
            try {
                this.address = InetAddress.getByName("10.101.101.102");
                socket = new DatagramSocket();
                System.out.println("Client ready");
            } catch (UnknownHostException ex) {
                Logger.getLogger(UDPclient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(UDPclient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for(int i=1; i<=1E3; i++) {                
                try {
                    buf = String.format("%s - [%d]", "The cow jum over the moon", i).getBytes();
                    
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 1234);

                    System.out.println("Sending message >> " + i);
                    socket.send(packet);
                }  catch (IOException ex) {
                    Logger.getLogger(UDP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
            System.out.println("Client closed");
        }
    }
}
