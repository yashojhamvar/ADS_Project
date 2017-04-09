class BitIterator {
	byte[] byteArray;
	int arrayIndex = 0;
	int bitIndex = 0;
	final static char[] masks = { 0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x01 };

	public BitIterator(byte[] byteArray) {
		this.byteArray = byteArray;
	}

	boolean getNextBit() {
		if (bitIndex == 8) {
			arrayIndex++;
			bitIndex = 0;
		}
		return (byteArray[arrayIndex] & masks[bitIndex++]) > 0;
	}

	boolean hasNext() {
		return !(bitIndex == 8 && arrayIndex == (byteArray.length - 1));
	}
}