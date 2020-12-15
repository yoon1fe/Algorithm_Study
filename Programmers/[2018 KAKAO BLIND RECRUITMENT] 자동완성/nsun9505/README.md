# 

## 분류
> 트라이

## 코드
```java
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
```

## 문제풀이
트라이를 사용해서 푼 첫 문제!

그래서 구글링~
[참고](https://velog.io/@jkh2801/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%9E%90%EB%8F%99%EC%99%84%EC%84%B1)

로직은 간단합니다!
1. 문자열을 트라이에 저장!
   - 저장할 때는 해당 노드에 중첩되는? 단어의 수를 표현하기 위해 cnt를 사용했습니다.
1. 트라이에서 문자열을 몇 번째에 찾는지 find를 통해서 구현하면 됩니다.
   - cnt값이 1이면 해당 문자열을 찾습니다.
   - cnt 값이 1이라는 것은 이후에 중첩되는 것이 없고, 해당 레벨까지만 가도 다음 레벨은 탐색하지 않아도 된다는 것을 의미합니다.
   - 물론, 문자열 끝부분까지 가서 cnt가 1인 부분을 만날 수도 있습니다.
   - 또한, 어떤 문자열은 긴 문자열에 포함되어서 cnt가 1인 부분을 못 만날 수도 있으므로 return 값은 해당 문자열의 길이값으로 하면 됩니다!



## 후기
처음에는 HashMap으로 풀 수 있을까 했지만, 지금 생각해보니 트라이처럼 한 노드에 문자값 하나를 담는 것이 내가 구현했던 한 노드에 0~i번째 문자열을 저장하는 것보다 메모리를 덜 쓰는 것을 구현하면서 깨닫게 되었습니다.

오오 트라이를 직접 배워서 써보는 첫 경험!

덕분에 트라이 공부도 하고 트라이 사용하는 방법도 익혀서 갑니다!

굿굿!!