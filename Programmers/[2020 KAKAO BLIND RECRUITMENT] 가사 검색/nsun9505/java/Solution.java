import java.util.HashMap;

public class Solution {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        HashMap<Integer, Trie> trieMap = new HashMap<>();
        HashMap<Integer, Trie> reverseTrieMap = new HashMap<>();
        for(int i=0; i < words.length; i++) {
            if(!trieMap.containsKey(words[i].length())) {
                trieMap.put(words[i].length(), new Trie());
                reverseTrieMap.put(words[i].length(), new Trie());
            }
            Trie trie = trieMap.get(words[i].length());
            Trie reverseTrie = reverseTrieMap.get(words[i].length());
            trie.insert(words[i]);
            reverseTrie.insert(new StringBuilder(words[i]).reverse().toString());
        }
        for(int i=0; i<queries.length; i++){
            if(!trieMap.containsKey(queries[i].length()))
                continue;

            Trie trie = null;
            if(queries[i].charAt(0) == '?') {
                queries[i] = new StringBuilder(queries[i]).reverse().toString();
                trie = reverseTrieMap.get(queries[i].length());
            } else {
                trie = trieMap.get(queries[i].length());
            }
            answer[i] = trie.find(queries[i]);
        }
        return answer;
    }

    static class Trie{
        TrieNode head;

        public Trie() {
            this.head = new TrieNode();
        }

        public void insert(String word){
            TrieNode cur = this.head;
            cur.cnt += 1;
            for(int i=0; i<word.length(); i++){
                cur = cur.getChild().computeIfAbsent(word.charAt(i), c -> new TrieNode());
                cur.cnt += 1;
            }
            cur.setIsLast(true);
        }

        public int find(String word){
            TrieNode cur = this.head;
            for(int i=0; i<word.length(); i++) {
                char ch = word.charAt(i);
                if(ch == '?')
                    break;
                TrieNode node = cur.getChild().get(ch);

                if (node == null)
                    return 0;

                cur = node;

            }
            return cur.cnt;
        }
    }

    static class TrieNode{
        boolean isLast;
        int cnt;
        HashMap<Character, TrieNode> child = new HashMap<>();

        public TrieNode() {
            cnt = 0;
        }

        public HashMap<Character, TrieNode> getChild() {
            return child;
        }

        public boolean isLast() { return this.isLast; }

        public void setIsLast(boolean isLast) { this.isLast = isLast; }
    }
}
