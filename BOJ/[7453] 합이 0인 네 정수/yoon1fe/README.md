## [7453] 합이 0인 네 정수 - Java

### :video_game: 분류

> 이분 탐색



### :video_game: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] A, B, C, D;
	static int[] AB, CD;
	
	public static void main(String[] args) throws Exception {
		input();

		System.out.println(solve());
	}
	
	public static long solve() {
		long answer = 0;
		AB = new int[N*N];
		CD = new int[N*N];
		
		int idx = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				AB[idx] = (A[i] + B[j]);
				CD[idx++] = (C[i] + D[j]);
			}
		}
		
		Arrays.sort(CD);
		
		for(int i = 0; i < N * N; i++) {
			answer += (getUpperBound(0, N*N, -AB[i]) - getLowerBound(0, N*N, -AB[i]));
		}
		
		return answer;
	}
	
	public static int getLowerBound(int left, int right, int target) {
		while(left < right) {
			int mid = (left + right) / 2;
			if(CD[mid] < target) left = mid + 1;
			else right = mid;
		}
		
		return right;
	}
	
	public static int getUpperBound(int left, int right, int target) {
		while(left < right) {
			int mid = (left + right) / 2;
			if(CD[mid] <= target) left = mid + 1;
			else right = mid;
		}
		
		return right;
	}
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		A = new int[N]; B = new int[N];
		C = new int[N]; D = new int[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			A[i] = Integer.parseInt(st.nextToken()); B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken()); D[i] = Integer.parseInt(st.nextToken());
		}
	}
}
```



### :video_game: 풀이 방법

이해가 아직 잘 안된다 ;;;

상윤이꺼 고대로 했는데ㅔ... 물어봐야겠따;;



### :video_game: 후기

없다;