# [2018 KAKAO BLIND RECRUITMENT] 자동완성 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
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
```

### :octocat: 풀이 방법

1. Trie 객체를 만든다!
2. 학습할 문자열들을 Trie안에 넣는다.
3. 학습된 문자열들을 처음부터 한글자씩 찾아본다.
4. child 개수가 1인경우 자동완성이 가능하다는 뜻이기 때문에 해당 index+1을 answer에 더하기

### :octocat: 후기

Trie 복습해보는 좋은 문제..
