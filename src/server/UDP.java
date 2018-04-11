package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UDP {
    public static void main(String[] args) {
        new Server();
    }
    
    static class Server{
        byte[] buf = new byte[1024];
        String msg;
        DatagramSocket socket;
        DatagramPacket packet;
        
        public Server(){
            try {
                socket = new DatagramSocket(1234,InetAddress.getByName("10.101.101.102"));
                System.out.println("Server ready");                
                packet = new DatagramPacket(buf, buf.length);
            } catch (SocketException ex) {
                Logger.getLogger(UDP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(UDP.class.getName()).log(Level.SEVERE, null, ex);
            }

            while (true) {                
                try {   
                    
                    System.out.println("Waiting for message");
                    socket.receive(packet);
                    
                    msg = new String(packet.getData(), 0, packet.getLength());

                    System.out.println(msg);
                    
                    if(msg.equals("exit")){
                        break;
                    }
                }catch (IOException ex) {
                    Logger.getLogger(UDP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            socket.close();
            System.out.println("Server closed");
        }
    }
    
}
