import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class encoder {

	public static void main(String[] args) {

		for (int q = 0; q < 1; q++) {
			HashMap<Integer, Integer> freqTable = new HashMap<Integer, Integer>();
			ArrayList<Integer> inputTable = new ArrayList<Integer>();
			String line = null;
			try {
				FileReader fileReader = new FileReader(args[0]);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				while ((line = bufferedReader.readLine()) != null) {
					inputTable.add(Integer.parseInt(line));
					if (freqTable.containsKey(Integer.parseInt(line))) {
						int val = freqTable.get(Integer.parseInt(line));
						freqTable.put(Integer.parseInt(line), ++val);
					} else {
						freqTable.put(Integer.parseInt(line), 1);
					}
				}

				bufferedReader.close();
			} catch (FileNotFoundException ex) {
				System.out.println("Unable to open file '" + args[0]);
			} catch (IOException ex) {
				System.out.println("Error reading file '" + args[0]);
			}

			
			//Binary Heap
/*			Set keyset = freqTable.entrySet();
			ArrayList<Node> frequencyTable = new ArrayList<Node>();
			Iterator i = keyset.iterator();
			while (i.hasNext()) {
				Map.Entry mapentry = (Map.Entry) i.next();
				Node temp = new Node((int) mapentry.getValue(), (int) mapentry.getKey());
				frequencyTable.add(temp);

			}
			callBinaryHeap(inputTable, frequencyTable);
*/
			
			//Four Way Heap
			Set keyset = freqTable.entrySet();
			ArrayList<FourWayCacheHeapNode> frequencyTable2 = new ArrayList<FourWayCacheHeapNode>();
			Iterator i = keyset.iterator();
			while (i.hasNext()) {
				Map.Entry mapentry = (Map.Entry) i.next();
				FourWayCacheHeapNode temp = new FourWayCacheHeapNode((int) mapentry.getKey() ,(int) mapentry.getValue());
				frequencyTable2.add(temp);
			}
			callFourwayHeap(inputTable, frequencyTable2);


			//Pairing Heap
/*			Set keyset = freqTable.entrySet();
			ArrayList<PairingNode> frequencyTable3 = new ArrayList<PairingNode>();
			Iterator i = keyset.iterator();
			while (i.hasNext()) {
				Map.Entry mapentry = (Map.Entry) i.next();
				PairingNode temp2 = new PairingNode((int) mapentry.getValue(), (int) mapentry.getKey());
				frequencyTable3.add(temp2);
			}
			callPairingHeap(inputTable, frequencyTable3);
*/
		}
	}

	public static void callBinaryHeap(ArrayList<Integer> inputTable, ArrayList<Node> frequencyTable) {
		ArrayList<Node> binaryHeapNodeArray = BinaryHeap.makeHeap(frequencyTable);
		ArrayList<Node> hTree = new ArrayList<Node>();
		hTree = HuffmanTree.createHuffmanTreeFromBinaryHeap(binaryHeapNodeArray);
		HuffmanTree.assignCodes(hTree.get(0));
		HashMap<Integer, String> codeTable = new HashMap<Integer, String>();
		codeTable = HuffmanTree.buildCodeTable(hTree.get(0));
		HuffmanTree.writeCodeTable(codeTable);
		encoder.encode(inputTable, codeTable);

	}

	public static void callFourwayHeap(ArrayList<Integer> inputTable, ArrayList<FourWayCacheHeapNode> frequencyTable2) {
		HashMap<Integer, String> codeTable = new HashMap<Integer, String>();
		codeTable = HuffmanTree.createHuffmanTreeFromFourWayHeap(frequencyTable2);
		HuffmanTree.writeCodeTable(codeTable);
		encoder.encode(inputTable, codeTable);

	}

	public static void callPairingHeap(ArrayList<Integer> inputTable, ArrayList<PairingNode> frequencyTable3) {
		PairingHeap.buildPairHeap(frequencyTable3);
		HuffmanTree.createHuffmanTreeFromPairingHeap();
		HuffmanTree.assignPairingHeapHuffmanCodes(PairingHeap.root);
		HashMap<Integer, String> codeTable = new HashMap<Integer, String>();
		codeTable = HuffmanTree.buildCodeTablePairingHeap(PairingHeap.root);
		HuffmanTree.writeCodeTable(codeTable);
		PairingHeap.cleanGarbage();
		encoder.encode(inputTable, codeTable);
	}

	public static void encode(ArrayList<Integer> ipTable, HashMap<Integer, String> cTable) {
		StringBuilder encodedText = new StringBuilder();
		Iterator it = ipTable.iterator();
		while (it.hasNext()) {
			int temp = (int) it.next();
			encodedText.append(cTable.get(temp));
		}

		byte[] barray = new byte[encodedText.length() / 8];
		for (int i = 0; i < (encodedText.length() / 8); i++) {
			barray[i] = (byte) Short.parseShort(encodedText.substring(8 * i, 8 * (i + 1)), 2);
		}

		String fileName = "encoded.bin";
		try {
			OutputStream out = new BufferedOutputStream(new FileOutputStream(fileName));
			out.write(barray);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
