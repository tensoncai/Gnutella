import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

public class Ping extends Thread {
	byte[] header;
	List<Node> neighbors;
	DatagramSocket s;
	
	public Ping(int port, List<Node> neighbors, byte[] header) throws IOException {
		this.neighbors = neighbors;
		this.header = header;
		s = new DatagramSocket(port + 1);
		System.out.println("get local port here = " + s.getLocalPort());
	}
	
	public void run() {
		while (true) {
			for (Node neighbor : neighbors) {
				ping(neighbor.IP, neighbor.port);
				System.out.println("ping sent");
			}
			
			try {
				Thread.sleep(3000);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void ping(InetAddress IP, int port) {
		try {
			DatagramPacket packet = new DatagramPacket(header, header.length, IP, port);
			s.send(packet);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
