import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class Ping extends Thread {
	byte[] header;
	List<Node> neighbors;
	
	public Ping(List<Node> neighbors, byte[] header) {
		this.neighbors = neighbors;
		this.header = header;
	}
	
	public void run() {
		while (true) {
			System.out.println(neighbors.size());
			for (Node neighbor : neighbors) {
				ping(neighbor.IP, neighbor.listeningPort);
				System.out.println("ping sent");
			}
			
			System.out.println("Here");
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
			DatagramSocket s = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(header, header.length, IP, port);
			s.send(packet);
//			CreateSocket s = new CreateSocket(IP, port);
//			s.sendPacket(header, header.length);
			s.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
