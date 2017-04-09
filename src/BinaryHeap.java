import java.util.ArrayList;

public class BinaryHeap {

	public static ArrayList<Node> makeHeap(ArrayList<Node> node_array) {
		for (int i = node_array.size() / 2 - 1; i >= 0; i--) {
			minHeapify(node_array, i);
		}
		return node_array;
	}

	public static void minHeapify(ArrayList<Node> node_array, int i) {
		int l = i * 2 + 1;
		int r = i * 2 + 2;
		int small;
		if (l <= node_array.size() - 1 && node_array.get(l).compareTo(node_array.get(i)) == -1) {
			small = l;
		} else {
			small = i;
		}
		if (r <= node_array.size() - 1 && node_array.get(r).compareTo(node_array.get(small)) == -1) {
			small = r;
		}
		if (small != i) {
			Node temp = node_array.get(i);
			node_array.set(i, node_array.get(small));
			node_array.set(small, temp);
			minHeapify(node_array, small);
		}
	}

	public static Node RemoveMin(ArrayList<Node> node_array) {
		if (node_array.size() < 0)
			return null;

		Node min = node_array.get(0);
		node_array.set(0, node_array.get(node_array.size() - 1));
		node_array.remove(node_array.size() - 1);

		minHeapify(node_array, 0);
		return min;
	}

	public static void Insert(ArrayList<Node> node_array, Node newNode) {
		node_array.add(node_array.size(), newNode);
		binaryHeapIncreaseKey(node_array, newNode);
	}

	public static void binaryHeapIncreaseKey(ArrayList<Node> node_array, Node newNode) {
		int i = node_array.size() - 1;
		while (i > 0 && node_array.get(Parent(i)).compareTo(node_array.get(i)) == 1) {
			Node temp = node_array.get(i);
			node_array.set(i, node_array.get(Parent(i)));
			node_array.set(Parent(i), temp);
			i = Parent(i);
		}
	}

	public static int Parent(int i) {
		return (i - 1) / 2;
	}

	public static int left(int i) {
		return 2 * i + 1;
	}

	public static int right(int i) {
		return 2 * i + 2;
	}

}