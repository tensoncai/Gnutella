import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
	public static void main(String[] args) throws UnknownHostException {
		Node A = new Node("127.0.0.1", 4000, 1);
		Node B = new Node("127.0.0.1", 5000, 2);
		Node C = new Node("127.0.0.1", 6000, 3);
		Node D = new Node("127.0.0.1", 7000, 4);
		
		B.listenForIncoming();
		
		List<Node> neighbors = new ArrayList<Node>();
		neighbors.add(B);
		neighbors.add(C);
		neighbors.add(D);
		A.neighbors = neighbors;
		A.pingNeighbors();
		
	}
}
