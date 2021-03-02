# [2020 KAKAO BLIND RECRUITMENT] 가사검색 - JAVA

## 분류
> 문자열
>
> 트라이

## 코드
```java
import java.util.HashMap;

public class Solution {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        // 원래 가사 단어 저장
        HashMap<Integer, Trie> trieMap = new HashMap<>();
        // 뒤집은 가사 단어 저장
        HashMap<Integer, Trie> reverseTrieMap = new HashMap<>();
        for(int i=0; i < words.length; i++) {
            if(!trieMap.containsKey(words[i].length())) {
                trieMap.put(words[i].length(), new Trie());
                reverseTrieMap.put(words[i].length(), new Trie());
            }
            Trie trie = trieMap.get(words[i].length());
            Trie reverseTrie = reverseTrieMap.get(words[i].length());
            // 원래 가사 단어 저장
            trie.insert(words[i]);

            // 뒤집은 가사 단어 저장
            reverseTrie.insert(new StringBuilder(words[i]).reverse().toString());
        }
        for(int i=0; i<queries.length; i++){
            if(!trieMap.containsKey(queries[i].length()))
                continue;

            Trie trie = null;
            // 접두사에 ?가 붙은 경우
            if(queries[i].charAt(0) == '?') {
                // 해당 쿼리를 뒤집고
                queries[i] = new StringBuilder(queries[i]).reverse().toString();
                // 가사 단어를 뒤집어서 저장한 트라이에서 찾으면 된다.
                trie = reverseTrieMap.get(queries[i].length());
            } else {
                // 접미사에 ?가 붙은 경우에는 원래 가사 단어를 저장한 트라이에서 찾으면 된다.
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
```

## 문제 풀이
카카오 해설을 보고 풀었습니다!

먼저 트라이는 주어진 각 가사 단어의 길이만큼 필요합니다.
   - 그래서 길이별로 trie를 만들었습니다.

또한, 쿼리 단어 중 접두어에 `?`가 붙은 경우에 해당되는 단어를 찾는 로직이 복잡해지거나 시간이 오래 걸릴 수 있으므로
   - 가사 단어를 뒤집어서 트라이에 저장하면 접두사에 `?`가 붙은 쿼리가 와도 해당 쿼리를 뒤집고 가사 단어를 뒤집어서 저장한 트라이에서 찾으면
   - 접미사에 `?`가 붙은 단어를 찾는 로직과 동일하게 사용할 수 있습니다.

그러면 길이별로 트라이가 필요하고, 각 길이에 따라 원래 가사 단어를 저장하는 트라이, 가사 단어를 뒤집어서 저장한 트라이가 필요합니다.
   - trieMap, reverseTrieMap

단어를 트라이에 저장할 때는, cnt변수를 두어서 임의의 트라이 노드에서 밑에 있는 단어의 수를 카운트하게 하였습니다.

그러면 찾을 때는 먼저 해당 알파벳이 있는지 보고, 없다면 바로 return 0을 하면 됩니다.

알파벳이 있어서 쭉 찾아 들어가다가 `?`를 만나면 멈추고 현재 노드에서의 단어 개수를 리턴하면 됩니다.

## 후기 
트라이 만드는 것까지는 할 수 있었지만, 트라이에 저장할 떄 뒤집어서 저장하는 아이디어는 생각하지 못해서

카카오 해설에서 아이디어를 얻어서 풀 수 있었습니다.

트라이 참 좋네유