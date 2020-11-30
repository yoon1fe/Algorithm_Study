# [1300] K번째 수 - Java

###  :octocat: 분류

> 이분탐색

### :octocat: 코드

```java
import java.util.Scanner;
public class week14_K번째수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		long left = 1, right = K;
		long ans = 0;
		
		while(left <= right) {
			long mid = (left + right) / 2;
			long cnt = 0;
			
			for(int i=1; i<=N; i++)
				cnt += Math.min(mid/i, N);
			
			if(cnt < K) left = mid + 1;
			else {
				ans = mid;
				right = mid - 1;
			}
		}
		System.out.println(ans);
	}
}
```

### :octocat: 풀이 방법

1. left=1, right=K로 두고 mid를 선정한다.
2. A행렬의 각 행들 중 mid 값보다 작은 값의 개수를 찾는다.
3. 총 개수가 K보다 작으면 left를 mid+1로 갱신한다.
4. 총 개수가 K보다 크거나 같으면 right를 mid-1로 갱신하고 answer를 mid로 한다.
5. left와 right가 같으면 반복문을 종료하고 그때 answer를 출력한다.

### :octocat: 후기

봐도봐도 이해할 수 없는 문제다. 일단 이해할때까지 계속 본다!
