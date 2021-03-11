# [Level2] 타겟 넘버 - Java

###  :octocat: 분류

> DFS

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.List;

public class week24_타겟넘버 {
	List<Integer> answer;
	
	void calc(int[] numbers, int a, int b, int idx, int tar) {
		if(idx < numbers.length) {
			calc(numbers, a+b, numbers[idx], idx+1, tar);
			calc(numbers, a-b, numbers[idx], idx+1, tar);
		} else {
			if(a+b == tar) answer.add(a+b);
			else if(a-b == tar) answer.add(a-b);
		}
	}
	
	public int solution(int[] numbers, int target) {
        answer = new ArrayList<Integer>();
        calc(numbers, 0, numbers[0], 1, target);
        return answer.size();
    }
}
```

### :octocat: 풀이 방법

1. 두 수를 더한 경우와 뺀 경우를 모두 고려하는 완전탐색을 DFS를 이용해서 진행한다.
2. target과 같은 경우 정답 배열에 넣는다. 모든 경우를 다 탐색하고 최종 정답 배열의 길이를 리턴한다

### :octocat: 후기

예전에 풀었던 아주 기초적인 DFS 문제!
