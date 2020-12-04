## [2018 KAKAO BLIND RECRUITMENT] 자동완성 - Java

###    :computer: ​분류

> 트라이
>



###  :computer: 코드

```java
import java.util.*;

class Solution {
    static class TrieNode {
		Map<Character, TrieNode> child = new HashMap<>();
		int cnt = 0;
	}

	static class Trie {
		TrieNode root;

		Trie() {
			this.root = new TrieNode();
		}

		void insert(String word) {
			TrieNode cur = this.root;

			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if (cur.child.get(c) == null) {
					cur.child.put(c, new TrieNode());
				}
				cur = cur.child.get(c);
				cur.cnt++;
			}
		}

		int getLength(String word) {
			TrieNode cur = this.root;
			int length = 0;
			
			for(int i = 0; i < word.length(); i++) {
				cur = cur.child.get(word.charAt(i));
				length++;
				
				if(cur.cnt == 1) break;
			}

			return length;
		}
	}

	public int solution(String[] words) {
		int answer = 0;
		Trie trie = new Trie();

		for (String s : words) {
			trie.insert(s);
		}

		for (String s : words) {
			answer += trie.getLength(s);
		}

		return answer;
	}
}
```



### :computer: ​풀이 방법

트라이를 활용하는 문제입니다!!

혼자서 짤 수 있을 것 같았는데 고새 까먹었네여 ㅜ



트라이를 구현하기 위해 TrieNode 클래스와 Trie 클래스를 선언했습니다. 먼저 TrieNode 부터 보시져.

```java
static class TrieNode {
	// 자식 노드
	Map<Character, TrieNode> child = new HashMap<>();
	// 해당 노드의 카운트
	int cnt = 0;
}
```

Map으로 자식 노드들을 저장했고, cnt 로 해당 노드의 카운트입니다. 예를 들어 word, world로 트라이를 만들었을 때 w(2), o(2), r(2), d(1), l(1), d(1) 이런 식으루요. 이렇게 하면 cnt가 1인 친구를 만나면 거기서는 그 단어를 확신할 수 있게 됩니다.

 

요렇게만 구해놓으면 정답은 쉽게 구할 수 있습니다. 위에서 말했듯 cnt가 1일때까지 내려가보면 되는 것이지용.





###  :computer: 후기 

트라이 구현하는 연습!!!

감사합니다!!!