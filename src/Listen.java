import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class Listen extends Thread {
	int listeningPort;
	Socket socket = null;
	
	public Listen(int listeningPort) {
		this.listeningPort = listeningPort;
	}
	
	public void run() {
		try {
			listenAtPort();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	private void listenAtPort() throws IOException {
		DatagramSocket socket = new DatagramSocket(listeningPort);
		DatagramPacket packet;
		byte[] incoming;
		while (true) {
			incoming = new byte[256];
			packet = new DatagramPacket(incoming, incoming.length);
			socket.receive(packet);
			System.out.println("received");
//			for (int i = 0; i < incoming.length; i++) {
//				System.out.println(incoming[i]);
//			}
			
			// now analyze packet received. Find out which operation pong, query, etc. to do
			// if pong, then make reply object and do pong operation
//			System.out.println("listening");
//			ServerSocket serverSocket = new ServerSocket(listeningPort);
//			socket = serverSocket.accept(); 
//			System.out.println("connected");
//			InputStream in = socket.getInputStream();
//		    DataInputStream dis = new DataInputStream(in);
//		    byte[] incoming = new byte[1024];
//		    int bytesRead = dis.read(incoming);
//		    System.out.println("received");
//		    byte[] b = {incoming[24], incoming[25], incoming[26], incoming[27]};
//		    int payloadLen = Utilities.convertBigEndianByteArrayToInt(b);
//		    int totalLength = 28 + payloadLen;
//		    
//		    if (bytesRead != totalLength) {
//		    		System.out.println("Something wrong. bytesRead != totalLength");
//		    }
//		    
		    // give incoming byte array to another thread for processing
		} 
	}
}
