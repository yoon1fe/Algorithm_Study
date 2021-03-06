import java.util.HashMap;

public class week03_가사검색 {
	class Node{
		String character;
		Node[] children;
		HashMap<Integer, Integer> childHash;
		
		public Node(String character) {
			this.character = character;
			this.children = new Node[26];
			childHash = new HashMap<Integer, Integer>();
		}
		
		public void addChild(int len) {
			if(childHash.containsKey(len))
				childHash.put(len, childHash.get(len)+1);
			else
				childHash.put(len, 1);
		}
		
		public int getChildCnt(int len) {
			return childHash.containsKey(len) ? childHash.get(len) : 0;
		}
	}
	
	class Trie{
		Node root;
		int index;
		
		public Trie() {
			this.root = new Node("");
		}
		
		public void insert(String key) {
			Node temp = root;
			
			for(int i=0; i<key.length(); i++) {
				char c = key.charAt(i);
				int ascii = c-'a';
				
				if(temp.children[ascii] == null) {
					Node node = new Node(String.valueOf(c));
					temp.children[ascii] = node;
					temp.addChild(key.length());
					temp = node;
				} else {
					temp.addChild(key.length());
					temp = temp.children[ascii];
				}
			}
		}
		
		public int search(String key) {
			int cnt = 0;
			Node trie = root;
			for(int i=0; i<key.length(); i++) {
				if(key.charAt(i) == '?') {
					cnt = trie.getChildCnt(key.length());
					break;
				}
				int ascii = key.charAt(i) - 'a';
				trie = trie.children[ascii];
				if(trie == null) return 0;
				
			}
			return cnt;
		}
	}
	
	public int[] solution(String[] words, String[] queries) {
        Trie trie = new Trie();
        int[] answer = new int[queries.length];
		for(int i=0; i<words.length; i++) 
        	trie.insert(words[i]);
		for(int i=0; i<queries.length; i++) {
			if(queries[i].charAt(0) == '?') continue;
			answer[i] = trie.search(queries[i]);
		}
		
		Trie trieRev = new Trie();
		for(int i=0; i<words.length; i++) {
			StringBuilder sb = new StringBuilder(words[i]);
			sb.reverse();
			trieRev.insert(sb.toString());
		}
		for(int i=0; i<queries.length; i++) {
			if(queries[i].charAt(0) != '?') continue;
			StringBuilder sb = new StringBuilder(queries[i]);
			sb.reverse();
			answer[i] = trieRev.search(sb.toString());
		}
        return answer;
    }
}