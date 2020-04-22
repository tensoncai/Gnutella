import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

// do not extend Thread
public class Node {
	String ip;
	InetAddress IP;
	List<Node> neighbors;
	int port;
	int numFilesToShare;
	int numKb;
	
	public Node(String ip, int port, int numFilesToShare, int numKb) throws UnknownHostException {
		this.ip = ip;
		neighbors = new ArrayList<Node>();
		this.port = port;
		this.numFilesToShare = numFilesToShare;
		this.numKb = numKb;
		IP = InetAddress.getByName("localhost");
	}
	
	public void pingNeighbors() throws IOException {
		DescriptorHeader pingHeader = new DescriptorHeader();
		byte[] header = pingHeader.createHeader("ping");
		Ping ping = new Ping(port, neighbors, header);
		ping.start();
	}
	
	public void listenForIncoming() throws UnknownHostException {
		Listen listening = new Listen(ip, port, numFilesToShare, numKb, IP, neighbors);
		listening.start();
	}
}
