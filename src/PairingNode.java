public class PairingNode implements Comparable<PairingNode> {

	int frequency;
	int key;
	PairingNode child;
	PairingNode rightsibling;
	PairingNode leftsibling;
	PairingNode leftchild; // needed for Huffman Tree
	PairingNode rightchild; // needed for Huffman Tree
	PairingNode parent;
	StringBuilder path;
	
	public PairingNode(int frequency, int key) {
		this.child = null;
		this.rightsibling = null;
		this.leftsibling = null;
		this.frequency = frequency;
		this.key = key;
		this.leftchild = null;
		this.rightchild = null;
		this.parent = null;
		this.path = new StringBuilder("");
	}

	public int compareTo(PairingNode n) {
		if (this.frequency > n.frequency)
			return 1;
		else if (this.frequency < n.frequency)
			return -1;
		else
			return 0;
	}

}
