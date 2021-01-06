# [2019 카카오 개발자 겨울 인턴십] 불량 사용자 - Java

###  :octocat: 분류

> 문자열

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class week20_불량사용자 {
	int[] arr;
	int[] result;
	boolean[] visited;
	List<String> answer;
	// userid 중 bannedid 개수만큼 순열뽑기
	void makePerm(int cnt, String[] user_id, String[] banned_id) {
		if(cnt == result.length) {
			if(solve(user_id, banned_id)) {
				// 밴 조건에 만족하면 중복체크해서 정답개수 증가
				int[] temp = Arrays.copyOf(result, result.length);
				Arrays.sort(temp);
				String n = "";
				for(int i=0; i<temp.length; i++) n += String.valueOf(temp[i]);
				if(answer.isEmpty()) answer.add(n);
				else if(!answer.contains(n)) answer.add(n);
			}
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(!visited[i]) {
				result[cnt] = arr[i];
				visited[i] = true;
				makePerm(cnt + 1, user_id, banned_id);
				visited[i] = false;
			}
		}
	}
	// 밴 조건 검사
	boolean solve(String[] user_id, String[] banned_id) {
		for(int i=0; i<result.length; i++) {
			if(user_id[result[i]].length() != banned_id[i].length()) return false;
			for(int j=0; j<banned_id[i].length(); j++) {
				if(banned_id[i].charAt(j) == '*') continue;
				if(user_id[result[i]].charAt(j) != banned_id[i].charAt(j)) return false;
			}
		}
		return true;
	}
	
	public int solution(String[] user_id, String[] banned_id) {
		answer = new ArrayList<String>();
        arr = new int[user_id.length];
        for(int i=0; i<arr.length; i++) arr[i] = i;
        result = new int[banned_id.length];
        visited = new boolean[arr.length];
        makePerm(0, user_id, banned_id);
        return answer.size();
    }
}
```

### :octocat: 풀이 방법

1. user_id N개 중 banned_id K개를 뽑는 모든 순열을 구한다.
2. 각 순열별로 밴 조건을 검사해서 다 만족하면 중복검사 후 정답 개수를 증가시킨다.

### :octocat: 후기

문제 대충보고 trie 써야하나 싶었는데 최대 8개 중 8개 뽑는거니까 몇개 안돼서 모든 경우의 수 계산했다!
