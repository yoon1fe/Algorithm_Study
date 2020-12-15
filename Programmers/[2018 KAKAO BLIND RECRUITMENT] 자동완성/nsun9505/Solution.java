public class Solution {
	static class TrieNode{
		TrieNode[] children;
		int cnt;
		TrieNode(){
			children = new TrieNode[26];
		}
	}
	
	static class Trie{
		TrieNode root;
		Trie() {
			root = new TrieNode();
		}
		
		void insert(String word) {
			TrieNode cur = this.root;
			cur.cnt+=1;
			
			for(int i=0; i<word.length(); i++) {
				if(cur.children[word.charAt(i)-'a'] == null) {
					cur.children[word.charAt(i) - 'a'] = new TrieNode();
					cur = cur.children[word.charAt(i)-'a'];
					cur.cnt = 1;
				} else {
					cur = cur.children[word.charAt(i)-'a'];
					cur.cnt += 1;
				}
			}
		}
		
		int find(String word) {
			TrieNode search = this.root;
			for(int i=0; i<word.length(); i++) {
				if(search.cnt == 1)
					return i;
				else
					search = search.children[word.charAt(i)-'a'];
			}
			return word.length();
		}
	}
	public int solution(String[] words) {
        int answer = 0;
        Trie trie = new Trie();
        for(String word : words)
        	trie.insert(word);
        
        for(String word : words)
        	answer += trie.find(word);
        return answer;
    }
}
