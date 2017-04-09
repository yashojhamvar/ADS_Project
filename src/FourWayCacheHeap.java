import java.util.ArrayList;

public class FourWayCacheHeap {

	private ArrayList<FourWayCacheHeapNode> fourWayNodeList;
	private int size;
	private int capacity;
	private static final int FRONT = 3;

	public FourWayCacheHeap(ArrayList<FourWayCacheHeapNode> fourwaycacheheap2) {
		this.fourWayNodeList = fourwaycacheheap2;
		fourWayNodeList.add(0, null);
		fourWayNodeList.add(1, null);
		fourWayNodeList.add(2, null);
		this.size = fourwaycacheheap2.size();
		capacity = size;
		buildHeap(size - 1);
	}

	private void buildHeap(int asize) {
		for (int i = getParent(asize - 1); i >= FRONT; i--) {
			heapify(i);
		}
	}

	public FourWayCacheHeapNode extractMin() {
		FourWayCacheHeapNode result = fourWayNodeList.get(FRONT);
		fourWayNodeList.set(FRONT, fourWayNodeList.get(size - 1));
		fourWayNodeList.remove(size - 1);
		size--;
		heapify(FRONT);
		return result;
	}

	public void insertItem(FourWayCacheHeapNode item) {

		fourWayNodeList.add(item);
		size++;
		int current = size - 1;
		while (isValid(current) && isValid(getParent(current)) && fourWayNodeList.get(getParent(current))
				.getFrequency() > fourWayNodeList.get(current).getFrequency()) {
			swapp(getParent(current), current);
			current = (current - 1) / 2;
		}

	}

	private void heapify(int index) {
		int first = getChild(index, 1);
		int smallestIdx = first;

		for (int i = 2; i <= 4; i++) {
			if (isValid(index) && isValid(getChild(index, i)) && isValid(smallestIdx)) {
				if (fourWayNodeList.get(getChild(index, i)).getFrequency() < fourWayNodeList.get(smallestIdx)
						.getFrequency()) {
					smallestIdx = getChild(index, i);
				}
			}
		}

		if (isValid(smallestIdx)
				&& fourWayNodeList.get(smallestIdx).getFrequency() < fourWayNodeList.get(index).getFrequency()) {
			swapp(smallestIdx, index);
			if (smallestIdx != index)
				heapify(smallestIdx);
		}

	}

	private void swapp(int smallestIdx, int index) {
		FourWayCacheHeapNode temmp = fourWayNodeList.get(smallestIdx);
		fourWayNodeList.set(smallestIdx, fourWayNodeList.get(index));
		fourWayNodeList.set(index, temmp);
	}

	private boolean isValid(int index) {
		return index >= FRONT && index < size;
	}

	private int getParent(int index) {
		return (index - 1) / 4 + FRONT;
	}

	private int getChild(int index, int i) {
		return 4 * (index - FRONT) + FRONT + i;
	}

	public int getSize() {
		return size;
	}

}
