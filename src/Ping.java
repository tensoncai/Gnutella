import java.io.IOException;
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
			for (Node neighbor : neighbors) {
				ping(neighbor.ip, neighbor.listeningPort);
			}
			
			try {
				Thread.sleep(3000);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void ping(String IP, int port) {
		try {
			CreateSocket s = new CreateSocket(IP, port);
			s.sendPacket(header, header.length);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}