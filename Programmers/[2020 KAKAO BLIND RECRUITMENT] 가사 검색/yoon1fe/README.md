## [2020 KAKAO BLIND RECRUITMENT] 가사 검색 - Java

###    :microphone:분류

> Trie
>
> 문자열 처리



###  :microphone: 코드

```java
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    static class TrieNode{
        Map<Character, TrieNode> childNodes = new HashMap<>();
        boolean isLastChar;
        int childNo;
    }

    static class Trie {
        TrieNode rootNode;
        Trie(){
            rootNode = new TrieNode();
        }

        void insert(String word) {
            TrieNode thisNode = this.rootNode;
            for(int i = 0; i < word.length(); i++) {
//                computeIfAbsent(): key를 사용하여 차례대로 value를 반환하는 함수형 인터페이스
//                key(word.charAt(i))가 없는 경우에만 TrieNode 생성
//                thisNode = thisNode.childNodes.computeIfAbsent(word.charAt(i), key -> new TrieNode());
                char c = word.charAt(i);
                if(thisNode.childNodes.get(c) == null) {
                    thisNode.childNodes.put(c, new TrieNode());
                }
                thisNode.childNo++;
                thisNode = thisNode.childNodes.get(c);
            }

            thisNode.isLastChar = true;
        }

        int getCount(String word) {
            TrieNode thisNode = this.rootNode;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode node = thisNode.childNodes.get(c);
                if(node == null) return 0;

                thisNode = node;
            }

            return thisNode.childNo;
        }    
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        Trie[] ascRoots = new Trie[10001];
        Trie[] descRoots = new Trie[10001];

        for(int i = 1; i < 10001; i++) {
            ascRoots[i] = new Trie();
            descRoots[i] = new Trie();
        }

        for(int i = 0; i < words.length; i++) {
            ascRoots[words[i].length()].insert(words[i]);
            descRoots[words[i].length()].insert(reverse(words[i]));
        }

        for(int i = 0; i < queries.length; i++) {
            String subQuery = queries[i].replace("?", "");
            if(queries[i].charAt(0) != '?') {                                                        // ascRoot
                answer[i] = ascRoots[queries[i].length()].getCount(subQuery);
            }else {                                                                                    // descRoot
                answer[i] = descRoots[queries[i].length()].getCount(reverse(subQuery));
            }
        }

        return answer;
    }

    public String reverse(String word) {
        StringBuilder ret = new StringBuilder();
        for(int i = word.length()-1; i>=0; i--) ret.append(word.charAt(i));
        return ret.toString();
    }
}
```



### :microphone:풀이 방법

와 진짜 겨우 풀었네

공부 열심히 해야겠ㅅ브니다

 

문제에서 요구하는 걸 구하는건 크게 어렵지 않은데 효율성 테스트를 보기 때문에 무식하게 N^2 때리면 난리납니다.

Trie라는 자료구조를 사용해서 멋있게 풀어야 합니다...

카카오 블로그 보고 겨우겨우 풀어씀니다.... 그와중에 queries 반대로 볼때는 뒤집어서 넣어야되는데 그거 안해서 식겁했습니다...





카카오 블로그에서 안내해주는 풀이 그대로임니다...

고로 트라이 만드는 코드를 복습해보겠습니다..

```java
static class TrieNode {
    Map<Character, TrieNode> childNodes = new HashMap<>();
    boolean isLastChar;
    int childNo;
}

static class Trie {
    TrieNode rootNode;

    Trie() {
        rootNode = new TrieNode();
    }

    void insert(String word) {
        TrieNode thisNode = this.rootNode;
        for (int i = 0; i < word.length(); i++) {
//            computeIfAbsent(): key를 사용하여 차례대로 value를 반환하는 함수형 인터페이스
//            key(word.charAt(i))가 없는 경우에만 TrieNode 생성
//            thisNode = thisNode.childNodes.computeIfAbsent(word.charAt(i), key -> new TrieNode());
            char c = word.charAt(i);
            if (thisNode.childNodes.get(c) == null) {
                thisNode.childNodes.put(c, new TrieNode());
            }
            thisNode.childNo++;
            thisNode = thisNode.childNodes.get(c);
        }

        thisNode.isLastChar = true;
    }

    int getCount(String word) {
        TrieNode thisNode = this.rootNode;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode node = thisNode.childNodes.get(c);
            if (node == null) return 0;

            thisNode = node;
        }

        return thisNode.childNo;
    }
}
```

먼저 **TrieNode** 클래스 입니다.

기본적으로 자기 자신의 자식 노드들의 목록과 자신이 리프 노드인지 여부를 알려주는 boolean 변수가 필요합니다.

이 문제에서는 query 단어로 시작되는 친구들이 몇 개인지 알아내야 하기 때문에 거쳐갈때다 ++ 해주는 변수를 하나 더 뒀습니다.



그 다음 **Trie** 클래스입니다. 실질적으로 트라이가 있는 클래스져.

루트 노드를 하나 갖고, 트라이를 생성하는 메소드(insert)와 해당되는 단어들의 개수를 리턴하는 getCount 메소드가 있습니다.

insert() 메소드는 computeIfAbsent란 메소드를 활용해서 아주 간단하게 짤 수 있습니다. 하지만 우리는 넣으면서 childNo 를 계속 ++해줘야 하니깐 thisNode를 바꿔가면서 쭉쭉 찾아가줍시다.

넣을 문자(c)를 찾아서 없으면 새로 만들어서 넣어주는 식입니다.

그리고 반복문을 다 돌면? 고 친구는 리프 노드니깐 isLastChar = true 가 되주.

 

getCount() 메소드에서는 query의 길이만큼 쭉쭉 내려가서 다 내려가면 그 노드의 childNo를 바로 리턴해버리면 됩니다. childNo를 열심히 구해놓은 보람이 있는 메소드입니다.

###  :microphone: 후기

아직 트라이가 손에 익숙치 않아서 풀어볼 문제 몇개 올림니다.

https://www.acmicpc.net/problem/13502

https://www.acmicpc.net/problem/5052

https://www.acmicpc.net/problem/14425

https://www.acmicpc.net/problem/4358

https://www.acmicpc.net/problem/14725

 

항상 화이팅!!