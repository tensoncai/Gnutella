import java.util.ArrayList;
import java.util.List;

// do not extend Thread
public class Node {
	String ip;
	List<Node> neighbors;
	int listeningPort;
	int numFilesToShare;
	
	public Node(String ip, int lp, int num) {
		this.ip = ip;
		neighbors = new ArrayList<Node>();
		listeningPort = lp;
		numFilesToShare = num;
	}
	
	public void pingNeighbors() {
		DescriptorHeader pingHeader = new DescriptorHeader();
		byte[] header = pingHeader.createHeader("ping");
		
		Ping ping = new Ping(neighbors, header);
		ping.start();
		
		
	}
	
	
	
}
