public class week18_자동완성 {
	class Node{
		String character;
		Node[] children;
		int childCnt;
		
		public Node(String character) {
			this.character = character;
			this.children = new Node[26];
			this.childCnt = 0;
		}
		
		public int getChildCnt() {
			return childCnt;
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
					temp = node;
					temp.childCnt++;
				} else {
					temp = temp.children[ascii];
					temp.childCnt++;
				}
			}
		}
		
		public int search(String key) {
			int cnt = 0;
			Node trie = root;
			for(int i=0; i<key.length(); i++) {
				int ascii = key.charAt(i) - 'a';
				trie = trie.children[ascii];
				if(trie == null) return 0;
			}
			cnt = trie.getChildCnt();
			return cnt;
		}
	}
	
	public int solution(String[] words) {
        int answer = 0;
        Trie trie = new Trie();
        for(int i=0; i<words.length; i++) trie.insert(words[i]);
        loop:
        for(int i=0; i<words.length; i++) {
        	String str = "";
        	for(int j=0; j<words[i].length(); j++) {
        		str += String.valueOf(words[i].charAt(j));
        		if(trie.search(str) == 1) {
        			answer += j+1;
        			continue loop;
        		}
        	}
        	answer += words[i].length();
        }
        return answer;
    }
}