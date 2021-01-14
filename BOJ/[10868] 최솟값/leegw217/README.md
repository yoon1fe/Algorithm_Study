# [10868] 최솟값 - Java

###  :octocat: 분류

> 세그먼트 트리

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class week22_최솟값 {
	static long[] number;
	static long[] mintree;
	
	static long mininit(int node, int start, int end) {
		if(start == end) return mintree[node] = number[start];
		else return mintree[node] = Math.min(mininit(node*2,start,(start+end)/2), mininit(node*2+1,(start+end)/2+1,end));
	}
	
	static long findmin(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return Long.MAX_VALUE;
		if(left <= start && end <= right) return mintree[node];
		return Math.min(findmin(node*2, start, (start+end)/2, left, right), findmin(node*2+1, (start+end)/2+1, end, left, right));
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		number = new long[N+1];
		mintree = new long[20000000];
		for(int i=1; i<=N; i++) number[i] = Long.parseLong(br.readLine());
		mininit(1, 1, N);
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(findmin(1, 1, N, a, b));
		}
	}
}
```

### :octocat: 풀이 방법

1. 세그먼트 트리 만들어서 뽑기

### :octocat: 후기

최솟값과 최댓값문제에서 최솟값 구하는 파트만 쏙 빼서 냄 ㅎㅎ
안보고 짜볼려고했는데 도저히 기억안남 히히
