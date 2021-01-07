# [2019 카카오 개발자 겨울 인턴십] 징검다리 건너기 - Java

###  :octocat: 분류

> 이분탐색

### :octocat: 코드

```java
import java.util.Arrays;
public class week20_징검다리건너기 {
	public int solution(int[] stones, int k) {
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        for(int i=0; i<stones.length; i++) {
        	left = Math.min(left, stones[i]);
        	right = Math.max(right, stones[i]);
        }
        if(k == 1) return left;
        loop:
        while(true) {
        	int mid = (left + right) / 2;
        	if(mid == left) break;
        	int[] temp = Arrays.copyOf(stones, stones.length);
        	for(int i=0; i<temp.length; i++) {
        		if(temp[i] - mid <= 0) temp[i] = 0;
        		else temp[i] = temp[i] - mid;
        	}
        	int kcnt = 0;
        	for(int i=0; i<temp.length; i++) {
        		if(temp[i] == 0) kcnt++;
        		else kcnt = 0;
        		if(kcnt == k) {
        			right = mid;
        			continue loop;
        		}
        	}
        	left = mid;
        }
        return right;
    }
}
```

### :octocat: 풀이 방법

1. 디딤돌에 적힌 수 중 최솟값과 최댓값을 left, right로 설정한다.
2. 중간값인 mid를 구하고 디딤돌에서 mid만큼 뺀 뒤 연속하는 0의 개수를 구한다.
3. 연속하는 0의 개수가 k개 이상이면 못지나간다는 뜻이기 때문에 right를 mid로 바꾼다.
4. 연속하는 0의 개수가 k개 미만이면 지나갈 수 있다는 뜻이기 때문에 left를 mid로 바꾼다.
5. mid값이 left와 같으면 최대로 건널 수 있는 사람 수를 구한것이기 때문에 left를 출력
6. 만약 k가 1인경우는 디딤돌에 적힌 수 중 최솟값이 답이 된다.

### :octocat: 후기

이문제는 보자마자 바로 기술블로그를 봤다. 그래도 생각보다 쉬운 문제였어서 한번에 풀었당 히히
