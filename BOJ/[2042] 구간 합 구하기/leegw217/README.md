# [2042] 구간 합 구하기 - Java

###  :octocat: 분류

> 세그먼트 트리

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class week21_구간합구하기 {
	static long[] number;
	static long[] tree;
	
	static long init(int node, int start, int end) {
		if(start == end) return tree[node] = number[start];
		else return tree[node] = init(node*2,start,(start+end)/2) + init(node*2+1,(start+end)/2+1,end);
	}
	
	static long sum(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 0;
		if(left <= start && end <= right) return tree[node];
		return sum(node*2, start, (start+end)/2, left, right) + sum(node*2+1, (start+end)/2+1, end, left, right);
	}
	
	static void update(int node, int start, int end, int index, long diff) {
		if(index < start || index > end) return;
		tree[node] = tree[node] + diff;
		if(start != end) {
			update(node*2, start, (start+end)/2, index, diff);
			update(node*2+1, (start+end)/2+1, end, index, diff);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		number = new long[N+1];
		tree = new long[20000000];
		for(int i=1; i<=N; i++) number[i] = Long.parseLong(br.readLine());
		init(1, 1, N);
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if(a == 1) {
				long diff = c - number[b];
				number[b] = c;
				update(1, 1, N, b, diff);
			}
			else System.out.println(sum(1, 1, N, b, (int)c));
		}
	}
}
```

### :octocat: 풀이 방법

1. 세그먼트 트리를 만든다.
2. 숫자를 바꿔야하면 바꾸고..
3. 구간합을 구해야하면 구한다..

### :octocat: 후기

태어나서 세그먼트 트리라는거 처음봄.. 백준 블로그에 정리 잘돼있어서 init, update, sum 함수 그대로 따라 쳤다..ㅜ
