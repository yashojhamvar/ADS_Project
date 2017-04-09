import java.util.ArrayList;
import java.util.Iterator;

public class Node implements Comparable<Node> {
	int frequency;
	int key;
	Node leftChild;
	Node rightChild;
	Node parent;
	StringBuilder path;

	public Node(int frequency, int key) {
		this.leftChild = null;
		this.rightChild = null;
		this.frequency = frequency;
		this.key = key;
		this.parent = null;
		this.path = new StringBuilder();
	}

	public int compareTo(Node n) {
		if (this.frequency > n.frequency)
			return 1;
		else if (this.frequency < n.frequency)
			return -1;
		else
			return 0;
	}

	public String toString() {
		return Integer.toString(frequency) + " " + Integer.toString(key);
	}

}