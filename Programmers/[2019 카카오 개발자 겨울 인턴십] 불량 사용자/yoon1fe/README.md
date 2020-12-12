## [2019 카카오 개발자 겨울 인턴십] 불량 사용자 - Java

### :man_cartwheeling: 분류

> 문자열 처리
>
> DFS

​

:man_cartwheeling: 코드

```java
import java.util.*;
import java.util.regex.*;

class Solution {
    Set<Set<Integer>> answerSet = new HashSet<>();
	List<String> list = new ArrayList<>();
	boolean[] v;
	
	public int solution(String[] user_id, String[] banned_id) {
		v = new boolean[user_id.length];
		
		for(int i = 0; i < banned_id.length; i++) {
			banned_id[i] = banned_id[i].replace("*", ".");
		}
		
        dfs(user_id, banned_id, 0, new HashSet<>());
        
        return answerSet.size();
    }

	public void dfs(String[] user_id, String[] banned_id, int idx, Set<Integer> set) {
		if(idx == banned_id.length) {
			answerSet.add(set);
			return;
		}
		
		for(int i = 0; i < user_id.length; i++) {
			if(Pattern.compile(banned_id[idx]).matcher(user_id[i]).matches() && !v[i]) {
				v[i] = true;
				set.add(i);
				
				dfs(user_id, banned_id, idx+1, new HashSet<>(set));
				
				set.remove(i);
				v[i] = false;
			}
		}
	}
}
```



### :man_cartwheeling: 풀이 방법

생각보다 좀 까다로웠던 문제입니다..

정규 표현식을 잘 써야할텐데 ㅜ



먼저 정규 표현식에서 "."은 모든 문자를 나타내기 때문에 *을 .으로 바꾸고 시작했습니다. Pattern 요쪽을 한번 봅시다.

```
Pattern.compile(String regex)		// 정규 표현식을 패턴으로 컴파일
Pattern.matcher(Charsequence input)	// 패턴과 매치할 matcher 생성
Matcher.matches()					// 패턴과 matcher 매치 여부 리턴 (boolean)
```

이렇게 불량 사용자인지 여부를 쉽게 판단할 수 있습니다.

 

다음은 문제의 풀이입니다.

먼저 큰 틀은 DFS 입니다. 유저 목록 중에서 불량 사용자와 매칭되는 애들의 인덱스를 DFS로 찾아가면서 Set에 넣어 관리합니다. 다만 중복된 애들을 제거해야 하기 때문에 최종적으로 만들어진 Set들을 관리하는 Set을 만들어서 중복 제거를 해줍시다.

 



###  :man_cartwheeling: 후기

찾아보고 도움을 많이 받고 풀었습니다...

아직 멀어쑴니다 ㅜㅜ

감사합니다!!