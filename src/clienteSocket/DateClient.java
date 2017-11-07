package clienteSocket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DateClient {
	
    public static void main(String[] args) throws IOException {
    	
    	Socket s = new Socket("172.16.3.94", 17500);  
    	//Socket s = new Socket("172.16.3.54",17000);  
        	
    	DataOutputStream dout = new DataOutputStream(s.getOutputStream());  
    					 //dout.writeUTF("1;fpsantos;eyvcco");
    					 dout.writeUTF("testando... 2");
    					 dout.flush();  
    					 dout.close();  
    					 s.close(); 
    
    }
    
}
