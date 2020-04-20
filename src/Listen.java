import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Listen {
	int listeningPort;
	Socket socket = null;
	
	public Listen(int listeningPort) {
		this.listeningPort = listeningPort;
	}
	
	public void listenAtPort() throws IOException {
		ServerSocket serverSocket = new ServerSocket(listeningPort);
		socket = serverSocket.accept(); 
		
		InputStream in = socket.getInputStream();
	    DataInputStream dis = new DataInputStream(in);
		
		DataInputStream in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
		
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte buffer[] = new byte[1024];
		for(int s; (s=in.read(buffer)) != -1; )
		{
		  baos.write(buffer, 0, s);
		}
		byte result[] = baos.toByteArray();
	}
}
