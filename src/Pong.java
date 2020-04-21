
public class Pong extends Thread {
	
	public Pong() {
		
	}
	
	public void run() {
		DescriptorHeader header = new DescriptorHeader();
		header.createHeader("pong");
	}
}
