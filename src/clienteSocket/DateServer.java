package clienteSocket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class DateServer {
	
    public static void main(String[] args) throws IOException {
    	
    		ServerSocket ss = new ServerSocket(17000);  
    		
    		Socket s = ss.accept();
    	   		
    		System.out.println("inetAddress: "+ s.getLocalAddress());       	   
    		System.out.println("getTrafficClass: "+ s.getTrafficClass());      	   		
    		System.out.println("getRemoteSocketAddress: "+ s.getRemoteSocketAddress());       	
    		System.out.println("getOutputStream: "+ s.getOutputStream() );          	
    		System.out.println("getChannel: "+ s.getChannel() );      		
    		System.out.println("TcpNoDelay: "+ s.getTcpNoDelay());  
        		    		   		
    		DataInputStream dis = new DataInputStream(s.getInputStream());  
    		
    		String  str = (String) dis.readUTF();  
    		
    		System.out.println("mensagem recebida: "+str);  
    		
    		//ss.close();  
    	
    }
}
