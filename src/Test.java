import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
	public static void main(String[] args) throws IOException {
		Node A = new Node("127.0.0.1", 4000, 1, 1);
		Node B = new Node("127.0.0.1", 5000, 2, 2);
		Node C = new Node("127.0.0.1", 6000, 3, 3);
		Node D = new Node("127.0.0.1", 7000, 4, 4);
		
		A.listenForIncoming();
		B.listenForIncoming();
//		C.listenForIncoming();
		
		List<Node> neighbors = new ArrayList<Node>();
		neighbors.add(B);
//		neighbors.add(C);
//		neighbors.add(D);
		A.neighbors = neighbors;
		A.pingNeighbors();

	}
}
