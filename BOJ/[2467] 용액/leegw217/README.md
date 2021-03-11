# [2467] 용액 - Java

###  :octocat: 분류

> 이분탐색

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class week24_용액 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] number = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) number[i] = Integer.parseInt(st.nextToken());
		int l = 0;
		int r = N-1;
		int answer = Integer.MAX_VALUE;
		int left = 0, right = 0;
		while(l < r) {
			int sum = number[l] + number[r];
			if(answer > Math.abs(sum)) {
				answer = Math.abs(sum);
				left = number[l];
				right = number[r];
			}
			if(sum < 0) l++;
			else r--;
		}
		
		System.out.println(left + " " + right);
	}
}
```

### :octocat: 풀이 방법

1. left를 0, right를 N-1로 두고 이분탐색을 한다.
2. number[left]와 nuber[right]를 더한 값의 절대값의 최솟값을 구하고 최소일때 left값과 right값을 저장한다.
3. 더한 값이 음수면 더 큰값을 더하기 위해 left를 증가
4. 더한 값이 양수면 더 작은값을 더하기 위해 right를 감소

### :octocat: 후기

쉽게 생각했는데 시간초과가 나와서 애먹었다.. 너무 오래 쉬었나보다..ㅜ
