import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HuffmanTree {

	static HashMap<Integer, String> fourWayHeapCodeMap = new HashMap<Integer, String>();
	static HashMap<Integer, String> codeTable = new HashMap<Integer, String>();
	private static FourWayCacheHeapNode fourWayRoot = null;

	public static ArrayList<Node> createHuffmanTreeFromBinaryHeap(ArrayList<Node> binaryHeap) {
		while (binaryHeap.size() > 1) {
			Node element1 = BinaryHeap.RemoveMin(binaryHeap);
			Node element2 = BinaryHeap.RemoveMin(binaryHeap);
			Node element = new Node(element1.frequency + element2.frequency, -1);
			element.leftChild = element1;
			element1.parent = element;
			element.rightChild = element2;
			element2.parent = element;
			BinaryHeap.Insert(binaryHeap, element);
		}
		return binaryHeap;
	}

	public static void assignCodes(Node root) {
		if (root.parent != null && root == root.parent.leftChild) {
			root.path.append(root.parent.path);
			root.path.append("0");
		}
		if (root.parent != null && root == root.parent.rightChild) {
			root.path.append(root.parent.path);
			root.path.append("1");
		}
		if (root.leftChild != null)
			assignCodes(root.leftChild);
		if (root.rightChild != null)
			assignCodes(root.rightChild);
	}

	public static HashMap<Integer, String> buildCodeTable(Node root) {
		if (root.leftChild != null)
			buildCodeTable(root.leftChild);
		if (root.key != -1) {
			codeTable.put(root.key, root.path.toString());
		}
		if (root.rightChild != null)
			buildCodeTable(root.rightChild);

		return codeTable;
	}

	public static void writeCodeTable(HashMap<Integer, String> hmap) {
		StringBuilder sb = new StringBuilder("");
		Set temp = hmap.entrySet();
		Iterator j = temp.iterator();
		while (j.hasNext()) {
			Map.Entry mapentry = (Map.Entry) j.next();
			sb.append((int) mapentry.getKey() + " " + hmap.get((int) mapentry.getKey()) + "\n");
		}

		String fileName = "code_table.txt";
		try {
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(sb.toString());
			bw.close();
			sb = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static HashMap<Integer, String> createHuffmanTreeFromFourWayHeap(ArrayList<FourWayCacheHeapNode> frequencyTable) {
		FourWayCacheHeap heap = new FourWayCacheHeap(frequencyTable);

		while (heap.getSize() != 4) {
			FourWayCacheHeapNode left = heap.extractMin();
			FourWayCacheHeapNode right = heap.extractMin();
			FourWayCacheHeapNode top = new FourWayCacheHeapNode(-1, left.getFrequency() + right.getFrequency());
			top.setfirst(left);
			left.parent = top;
			top.setsecond(right);
			right.parent = top;
			top.setthird(null);
			top.setfourth(null);
			heap.insertItem(top);
		}
		fourWayRoot = heap.extractMin();
		assignCodesFourwayHeap(fourWayRoot);
		buildCodeTableFourwayHeap(fourWayRoot);
		return fourWayHeapCodeMap;
	}

	public static void assignCodesFourwayHeap(FourWayCacheHeapNode root) {
		if (root.parent != null && root == root.parent.getfirst()) {
			root.path.append(root.parent.path);
			root.path.append("0");
		}
		if (root.parent != null && root == root.parent.getsecond()) {
			root.path.append(root.parent.path);
			root.path.append("1");
		}
		if (root.getfirst() != null)
			assignCodesFourwayHeap(root.getfirst());
		if (root.getsecond() != null)
			assignCodesFourwayHeap(root.getsecond());
	}

	private static void buildCodeTableFourwayHeap(FourWayCacheHeapNode root) {
		if (root.getfirst() != null)
			buildCodeTableFourwayHeap(root.getfirst());
		if (root.key != -1) {
			fourWayHeapCodeMap.put(root.key, root.path.toString());
		}
		if (root.getsecond() != null)
			buildCodeTableFourwayHeap(root.getsecond());
	}

	public static void createHuffmanTreeFromPairingHeap() {
		while (!PairingHeap.isSizeOne()) {
			PairingNode element1 = PairingHeap.RemoveMin();
			PairingNode element2 = PairingHeap.RemoveMin();
			PairingNode element = new PairingNode(element1.frequency + element2.frequency, -1);
			element.leftchild = element1;
			element1.parent = element;
			element.rightchild = element2;
			element2.parent = element;
			PairingHeap.insert(element);
		}
	}

	public static void assignPairingHeapHuffmanCodes(PairingNode root) {
		if (root.parent != null && root == root.parent.leftchild) {
			root.path.append(root.parent.path);
			root.path.append("0");
		}
		if (root.parent != null && root == root.parent.rightchild) {
			root.path.append(root.parent.path);
			root.path.append("1");
		}
		if (root.leftchild != null)
			assignPairingHeapHuffmanCodes(root.leftchild);
		if (root.rightchild != null)
			assignPairingHeapHuffmanCodes(root.rightchild);
	}

	public static HashMap<Integer, String> buildCodeTablePairingHeap(PairingNode root) {
		if (root.leftchild != null)
			buildCodeTablePairingHeap(root.leftchild);
		if (root.key != -1) {
			codeTable.put(root.key, root.path.toString());
		}
		if (root.rightchild != null)
			buildCodeTablePairingHeap(root.rightchild);
		return codeTable;
	}

}
