# [Lever2] 전화번호 목록 - Java

###  :octocat: 분류

> Trie

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class week24_전화번호목록 {
	class Node {
		String character;
		boolean isfinal;
		List<Node> child;
		
		public Node(String character) {
			this.character = character;
			this.isfinal = false;
			this.child = new ArrayList<Node>();
		}
	}

	class Trie {
		Node root;
		public Trie() {
			this.root = new Node("");
		}
		
		public boolean insert(String key) {
			Node temp = root;
			loop:
			for(int i=0; i<key.length(); i++) {
				char c = key.charAt(i);
				for(int j=0; j<temp.child.size(); j++) {
					if(temp.child.get(j).character.equals(String.valueOf(c))) {
						temp = temp.child.get(j);
						if(temp.isfinal) return false;
						continue loop;
					}
				}
				Node node = new Node(String.valueOf(c));
				temp.child.add(node);
				temp = node;
			}
			temp.isfinal = true;
			return true;
		}
	}
	
	public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}        	
		});
        Trie trie = new Trie();
        for(int i=0; i<phone_book.length; i++)
        	if(!trie.insert(phone_book[i])) return false;
        return answer;
    }
	
	public static void main(String[] args) {
		week24_전화번호목록 m = new week24_전화번호목록();
		String[] phone_book = {"12","123","1235","567","88"};
		System.out.println(m.solution(phone_book));
	}
}
```

### :octocat: 풀이 방법

1. 입력값을 길이 순으로 오름차순 정렬한다.
2. Trie 구조를 구현하고 길이가 짧은 순으로 넣는다.
3. 넣을 때 마지막 문자열일 경우 마지막을 표시한다(isfinal).
4. 넣는 도중 isfinal을 발견하면 앞에 넣은 문자열이 접두어라는 뜻이므로 false

### :octocat: 후기

그냥 완탐돌렸는데 당연히 시간초과가 두개 나와서 연습할겸 Trie로 짜봤다. 한번에 돌아가서 놀랐음...
