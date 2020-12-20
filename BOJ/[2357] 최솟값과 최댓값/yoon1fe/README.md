## [2357] 최솟값과 최댓값 - Java

### :two: 분류

> 세그먼트 트리



### :three: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] list, maxTree, minTree;
	
	public static void main(String[] args) throws Exception {
		input();
	}
	
	public static int initMax(int node, int start, int end) {
		if(start == end) return maxTree[node] = list[start];
		
		int mid = (start + end) / 2;
		
		return maxTree[node] = Math.max(initMax(node*2, start, mid), initMax(node*2 + 1, mid+1, end));
	}
	
	public static int initMin(int node, int start, int end) {
		if(start == end) return minTree[node] = list[start];
		
		int mid = (start + end) / 2;
		
		return minTree[node] = Math.min(initMin(node*2, start, mid), initMin(node*2 + 1, mid+1, end));
	}
	
	public static int findMax(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 0;

		if(left <= start && end <= right) return maxTree[node];
		
		int mid = (start + end) / 2;
		
		return Math.max(findMax(node*2, start, mid, left, right), findMax(node*2 + 1, mid+1, end, left, right));
	}
	
	public static int findMin(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 1000000001;

		if(left <= start && end <= right) return minTree[node];
		
		int mid = (start + end) / 2;
		
		return Math.min(findMin(node*2, start, mid, left, right), findMin(node*2 + 1, mid+1, end, left, right));
	}
		
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new int[N];
		maxTree = new int[N*4];
		minTree = new int[N*4];
		
		for(int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		
		initMax(1, 0, N-1);
		initMin(1, 0, N-1);
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			bw.write(String.valueOf(findMin(1, 0, N-1, a, b)));
			bw.write(" ");
			bw.write(String.valueOf(findMax(1, 0, N-1, a, b)));
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}
}
```



### :five: 풀이 방법

세그먼트 트리를 활용해서 풀 수 있는 문제입니다.

아직 혼자 다 짜는건 어렵네유 ㅜ 여러 번 짜봐야겠슴니다



최솟값, 최댓값을 구하기 위해 세그먼트 트리를 응용해서 두 개의 트리를 만들었습니다. 

minTree와 maxTree는 각각 해당하는 범위의 최솟값, 최댓값을 가집니다. 이렇게 만들어놓으면 마찬가지로 기존의 sum() 메소드를 응용해서 원하는 범위의 최솟값과 최댓값을 쉽게 찾을 수 있겠지용.!!

 

일정 범위의 최솟값, 최댓값을 갖는 트리를 만들어 놓고 자식 노드들로 내려가면서 적절한 값을 찾아내면 되는것입니당!!



### :seven: 후기

세그 트리 연습 많이 해야겠습니다. !!

감사합니다.!!