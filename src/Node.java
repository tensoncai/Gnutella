import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

// do not extend Thread
public class Node {
	String ip;
	InetAddress IP;
	List<Node> neighbors;
	int listeningPort;
	int numFilesToShare;
	
	public Node(String ip, int lp, int num) throws UnknownHostException {
		this.ip = ip;
		neighbors = new ArrayList<Node>();
		listeningPort = lp;
		numFilesToShare = num;
		IP = InetAddress.getByName("localhost");
	}
	
	public void pingNeighbors() {
		DescriptorHeader pingHeader = new DescriptorHeader();
		byte[] header = pingHeader.createHeader("ping");
		
		Ping ping = new Ping(neighbors, header);
		ping.start();
		
	}
}
