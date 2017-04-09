public class FourWayCacheHeapNode {
	public int key;
	public int frequency;
	public FourWayCacheHeapNode first = null;
	public FourWayCacheHeapNode second = null;
	public FourWayCacheHeapNode third = null;
	public FourWayCacheHeapNode fourth = null;
	public FourWayCacheHeapNode parent = null;
	public StringBuilder path = new StringBuilder();
	

	public FourWayCacheHeapNode(int data, int frequency) {
		this.key = data;
		this.frequency = frequency;
	}

	public FourWayCacheHeapNode getfirst() {
		return first;
	}

	public void setfirst(FourWayCacheHeapNode first) {
		this.first = first;
	}

	public FourWayCacheHeapNode getsecond() {
		return second;
	}

	public void setsecond(FourWayCacheHeapNode second) {
		this.second = second;
	}

	public FourWayCacheHeapNode getthird() {
		return third;
	}

	public void setthird(FourWayCacheHeapNode third) {
		this.third = third;
	}

	public FourWayCacheHeapNode getfourth() {
		return fourth;
	}

	public void setfourth(FourWayCacheHeapNode fourth) {
		this.fourth = fourth;
	}

	public int getFrequency() {
		return frequency;
	}

	public int getKey() {
		return key;
	}
}