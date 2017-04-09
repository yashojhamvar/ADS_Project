import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class decoder {
	public static void main(String[] args) {
		decoder.decode(args[0], args[1]);
	}

	public static void decode(String encFilePath, String codeTablefileName) {
		try {
			Node root = new Node(-1, -1);
			String line = null;
			FileReader fr = new FileReader(codeTablefileName);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				String[] splited = line.split("\\s+");
				makeTree(splited[0], splited[1], root);
			}
			br.close();

			// Encoded.bin file read
			File encFile = new File(encFilePath);
			byte[] encoded_bytes = new byte[(int) encFile.length()];
			int totalBytesRead = 0;
			InputStream input = new BufferedInputStream(new FileInputStream(encFile));
			while (totalBytesRead < encoded_bytes.length) {
				int bytesRemaining = encoded_bytes.length - totalBytesRead;
				int bytesRead = input.read(encoded_bytes, totalBytesRead, bytesRemaining);
				if (bytesRead > 0) {
					totalBytesRead = totalBytesRead + bytesRead;
				}
			}
			input.close();

			StringBuilder sb = new StringBuilder();
			FileWriter fileWriter = new FileWriter("decoded.txt");
			BufferedWriter out = new BufferedWriter(fileWriter);
			BitIterator bitIterator = new BitIterator(encoded_bytes);
			Node traversalTree = root;

			while (bitIterator.hasNext()) {
				if (bitIterator.getNextBit()) {
					traversalTree = traversalTree.rightChild;
				} else {
					traversalTree = traversalTree.leftChild;
				}
				if (traversalTree.key != -1) {
					sb.append(traversalTree.key + "\n");
					traversalTree = root;
				}
			}
			out.write(sb.toString());
			out.close();
			sb = null;

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static void makeTree(String string, String string2, Node temproot) {
		StringBuilder path = new StringBuilder(string2);
		int i = 0;
		while (i < path.length() - 1) {
			if (path.charAt(i) == '0' && temproot.leftChild == null) {
				Node temp = new Node(-1, -1);
				temproot.leftChild = temp;
				temproot = temp;
			} else if (path.charAt(i) == '0' && temproot.leftChild != null) {
				temproot = temproot.leftChild;
			} else if (path.charAt(i) == '1' && temproot.rightChild == null) {
				Node temp = new Node(-1, -1);
				temproot.rightChild = temp;
				temproot = temp;
			} else if (path.charAt(i) == '1' && temproot.rightChild != null) {
				temproot = temproot.rightChild;
			}
			i++;
		}

		if (i == path.length() - 1) {
			Node temp = new Node(-1, Integer.parseInt(string));
			if (path.charAt(i) == '0') {
				temproot.leftChild = temp;
			} else {
				temproot.rightChild = temp;
			}
		}

	}
}
