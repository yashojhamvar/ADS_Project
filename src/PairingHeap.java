import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class PairingHeap {

	static PairingNode root;
	static int size = 0;

	public PairingHeap() {
		root = null;
	}

	public static void buildPairHeap(ArrayList<PairingNode> freqTable) {
		Iterator<PairingNode> it = freqTable.iterator();
		while (it.hasNext()) {
			PairingHeap.insert(it.next());
		}
	}

	public static PairingNode Meldsubtrees(PairingNode first, PairingNode second) {
		if (first.compareTo(second) == 1) {

			second.leftsibling = first.leftsibling;
			first.leftsibling = second;
			first.rightsibling = second.child;
			if (first.rightsibling != null)
				first.rightsibling.leftsibling = first;
			second.child = first;
			return second;
		} else {

			second.leftsibling = first;
			first.rightsibling = second.rightsibling;
			if (first.rightsibling != null)
				first.rightsibling.leftsibling = first;
			second.rightsibling = first.child;
			if (second.rightsibling != null)
				second.rightsibling.leftsibling = second;
			first.child = second;
			return first;

		}

	}

	private static PairingNode combineSiblings(PairingNode firstSibling) {
		if (firstSibling.rightsibling == null)
			return firstSibling;
		PairingNode ptr = firstSibling;
		PairingNode ptr2 = firstSibling.rightsibling;

		LinkedList<PairingNode> siblingList = new LinkedList<PairingNode>();
		LinkedList<PairingNode> pass1List = new LinkedList<PairingNode>();
		while (ptr != null) {

			siblingList.addLast(ptr);
			ptr2 = ptr;
			ptr = ptr.rightsibling;
			ptr2.rightsibling = null;
		}

		ptr = siblingList.removeFirst();
		ptr2 = siblingList.removeFirst();
		while (ptr != null) {
			ptr = Meldsubtrees(ptr, ptr2);
			pass1List.addLast(ptr);
			if (!siblingList.isEmpty()) {
				ptr = siblingList.removeFirst();
				if (siblingList.isEmpty()) {
					ptr2 = pass1List.removeLast();
					pass1List.addLast(Meldsubtrees(ptr2, ptr));
					break;
				} else
					ptr2 = siblingList.removeFirst();
			} else
				ptr = null;
		}

		PairingNode root = pass1List.removeLast();
		while (!pass1List.isEmpty()) {
			ptr = pass1List.removeLast();
			root = Meldsubtrees(root, ptr);
		}

		return root;
	}

	public static PairingNode RemoveMin() {
		if (isEmpty())
			return null;
		PairingNode node = root;
		if (root.child == null)
			root = null;
		else
			root = combineSiblings(root.child);
		size--;
		return node;
	}

	public static void inorder() {
		inorder(root);
	}

	public int getsize() {
		return size;
	}

	private static void inorder(PairingNode r) {

		if (r != null) {
			inorder(r.child);
			System.out.print(r.frequency + " ");
			inorder(r.rightsibling);
		}
	}

	public static void insert(PairingNode newNode) {
		if (root == null) {
			root = newNode;
		} else
			root = Meldsubtrees(root, newNode);
	}

	public static boolean isEmpty() {
		return root == null;
	}

	public static boolean isSizeOne() {
		if (root.child == null)
			return true;
		else
			return false;
	}

	public static void cleanGarbage() {
		root = null;
	}
}
