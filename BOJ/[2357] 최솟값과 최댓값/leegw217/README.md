# [2357] 최솟값과 최댓값 - Java

###  :octocat: 분류

> 세그먼트 트리

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class week21_최솟값과최댓값 {
	static long[] number;
	static long[] maxtree;
	static long[] mintree;
	
	static long maxinit(int node, int start, int end) {
		if(start == end) return maxtree[node] = number[start];
		else return maxtree[node] = Math.max(maxinit(node*2,start,(start+end)/2), maxinit(node*2+1,(start+end)/2+1,end));
	}
	
	static long mininit(int node, int start, int end) {
		if(start == end) return mintree[node] = number[start];
		else return mintree[node] = Math.min(mininit(node*2,start,(start+end)/2), mininit(node*2+1,(start+end)/2+1,end));
	}
	
	static long findmax(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return Long.MIN_VALUE;
		if(left <= start && end <= right) return maxtree[node];
		return Math.max(findmax(node*2, start, (start+end)/2, left, right), findmax(node*2+1, (start+end)/2+1, end, left, right));
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
		maxtree = new long[20000000];
		mintree = new long[20000000];
		for(int i=1; i<=N; i++) number[i] = Long.parseLong(br.readLine());
		maxinit(1, 1, N);
		mininit(1, 1, N);
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.print(findmin(1, 1, N, a, b)+" ");
			System.out.println(findmax(1, 1, N, a, b));
		}
	}
}
```

### :octocat: 풀이 방법

1. 구간 최댓값을 부모로 가지는 세그먼트 트리와 최솟값을 부모로 가지는 세그먼트 트리를 만든다.
2. 구간 최솟값과 최댓값을 구해서 출력한다.

### :octocat: 후기

세그먼트 트리 만드는 방법을 도저히 못외우겠다;; 코테나오면 골치아프겠다ㅜ
