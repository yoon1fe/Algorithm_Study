## [1300] K번째 수 - Java

### :one: 분류

> 이분 탐색



### :one: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static int N, k;
	
	public static void main(String[] args) throws Exception {
		input();

		System.out.println(solve());
	}
	
	public static int solve() {
		int answer = 0;
		int left = 1, right = k;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			long sum = 0;
			for(int i = 1; i <= N; i++) sum += Math.min(N, mid / i);
			
			if(sum >= k) {
				right = mid - 1;
				answer = mid;
			}else 
				left = mid + 1;
		}
		
		return answer;
	}
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); k = Integer.parseInt(br.readLine());
	}
}
```



### :one: 풀이 방법

요 문제는 K- 씨리즈의 끝판왕 K-번째 수입니다.ㅎㅋ

이분 탐색으로 풀면 되는데, 어떻게 N^2를 돌리지 않고 배열에 있는 수를 체크하느냐가 관건입니다.



로직은 다음과 같습니당. mid를 잡고 mid 보다 작은 숫자의 개수를 구해서 K번 째 숫자를 구합니다. 여기서 mid를 이분 탐색을 통해서 구해줍니다.

 

A[i][j]에 들어 있는 값은 i * j 입니다. 그렇기 때문에 j의 값을 구해야겠져. 여기서 무식하게 N*2를 해버리면 안된다 이겁니다. 따라서 mid 보다 작은 숫자는 1 ~ N 까지 반복문을 돌리고 i * j <= mid 이기 때문에 mid / i 가 조건을 만족하는 j의 개수가 됩니다. 

이런 식으로 mid보다 작은 수의 개수를 구해가면서 이분 탐색으로 정답을 구하면 됩니다.



### :one: 후기

감사합니다!