package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FileExteratorImpl {

	static class Node implements Comparable<Node> {
		Character ch;
		Integer freq;

		Node left = null;
		Node right = null;

		public Node(Character ch, Integer freq) {
			this.ch = ch;
			this.freq = freq;
		}

		public Node(Character ch, Integer freq, Node left, Node right) {
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}

		@Override
		public int compareTo(Node o) {
			return this.freq - o.freq;
		}

	}

	public String fileExteratorImpl(String fileURL) {
		Map<Character, String> map = new HashMap<Character, String>();
		String result = "";
		try {
			File myObj = new File(fileURL);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
				map = createHuffmanTrees(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		for (Map.Entry<Character, String> entry : map.entrySet()) {
			result += entry.getValue();
		}
		return result;
	}

	private Map<Character, String> createHuffmanTrees(String data) {
		if (data == null || data.length() == 0) {
			return null;
		}

		Map<Character, Integer> map = new HashMap<Character, Integer>();

		for (char c : data.toCharArray()) {
			Integer temp = 0;
			if (map.get(c) != null) {
				temp = map.get(c);
			}
			map.put(c, temp + 1);
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (var entry : map.entrySet()) {
			pq.add(new Node(entry.getKey(), entry.getValue()));
		}

		while (pq.size() != 1) {
			Node left = pq.poll();
			Node right = pq.poll();

			int sum = left.freq + right.freq;

			pq.add(new Node(null, sum, left, right));
		}

		Node root = pq.peek();

		Map<Character, String> huffmanCode = new HashMap<>();
		encodeData(root, "", huffmanCode);

		System.out.println("Huffman Codes of the characters are: " + huffmanCode);
		return huffmanCode;
	}

	private void encodeData(Node root, String str, Map<Character, String> huffmanCode) {
		if (root == null) {
			return;
		}

		if (isLeaf(root)) {
			huffmanCode.put(root.ch, str.length() > 0 ? str : "1");
		}

		encodeData(root.left, str + '0', huffmanCode);
		encodeData(root.right, str + '1', huffmanCode);
	}

	public boolean isLeaf(Node root) {
		return root.left == null && root.right == null;
	}

}
