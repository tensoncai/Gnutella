import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class CreateSocket {
	private Socket s;
	
	public CreateSocket(String IP, int port) throws UnknownHostException, IOException {
		s = new Socket(IP, port);
	}
	
	public void sendPacket(byte[] buf, int bufLength) throws IOException {
		OutputStream out = s.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		dos.write(buf, 0, buf.length);
		dos.flush();
	}
	
	public byte[] receivePacket(int bufLength) throws IOException {
		InputStream in = s.getInputStream();
	    DataInputStream dis = new DataInputStream(in);

	    byte[] response = new byte[bufLength];
	    dis.read(response);
	    
	    s.close();
	    
	    return response;
	}
}
