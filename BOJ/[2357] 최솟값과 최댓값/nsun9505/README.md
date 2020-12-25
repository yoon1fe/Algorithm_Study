# [2357] 최솟값과 최댓값 - JAVA

## 분류
> 세그먼트 트리

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	static int[] maxTree;
	static int[] minTree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		int height = (int)Math.ceil(Math.log(N)/Math.log(2)) + 1;
		int size = (int)Math.pow(2, height);
		maxTree = new int[size];
		minTree = new int[size];
		
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		makeMaxTree(1, 1, N);
		makeMinTree(1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			sb.append(findMin(1, 1, N, left, right) + " " + findMax(1, 1, N, left, right) + "\n");		
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int findMax(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
			return 0;
		if(left <= start && end <= right)
			return maxTree[node];
		int mid = (start+end)/2;
		int leftMax = findMax(node*2, start, mid, left, right);
		int rightMax = findMax(node*2+1, mid+1, end, left, right);
		return leftMax < rightMax ? rightMax : leftMax;
	}
	
	public static int findMin(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
			return Integer.MAX_VALUE;
		if(left <= start && end <= right)
			return minTree[node];
		int mid = (start+end)/2;
		int leftMin = findMin(node*2, start, mid, left, right);
		int rightMin = findMin(node*2+1, mid+1, end, left, right);
		return leftMin < rightMin ? leftMin : rightMin;
	}
	
	public static int makeMaxTree(int node, int start, int end) {
		if(start == end)
			return maxTree[node] = arr[start];
		int mid = (start+end) / 2;
		int left = makeMaxTree(node*2, start, mid);
		int right = makeMaxTree(node*2+1, mid+1, end);
		return maxTree[node] = left < right ? right : left;
	}
	
	public static int makeMinTree(int node, int start, int end) {
		if(start == end)
			return minTree[node] = arr[start];
		int mid = (start+end) / 2;
		int left = makeMinTree(node*2, start, mid);
		int right = makeMinTree(node*2+1, mid+1, end);
		return minTree[node] = left < right ? left : right;
	}
}
```

## 문제 풀이
백준 2042 구간 합 문제에서 사용한 세그먼트 트리를 변경해서 풀었습니다.

find와 makeMin/MaxTree 메서드를 사용했습니다.
   - 변경하는 문제는 없어서 2042번 문제보다는 쉬웠던 것 같습니다.

입력을 받아서 maxTree, minTree를 구성합니다.
   - 약간 패자트리? 그거하고 비슷한거 같기도 하구..
   - 아무튼! 리프 노드에는 배열의 값이 들어갑니다.
   - 내부 노드에는 내부 노드를 기준으로 자식 노드들 중 가장 큰 값 또는 작은 값이 들어갑니다.

left, right를 입력 받아서 해당 구간의 최솟값과 최댓값을 구합니다.
   1. 최솟값과 최댓값을 구할 때는 현재 노드가 담당하는 범위가 넘어서면 min값의 경우 Integer.MAX_VALUE를 리턴, max값은 0을 리턴
   1. 현재 노드가 담당하는 범위를 찾는 구간이 포함한다면 더 이상 내려갈 필요 없이 해당 노드의 값(최솟값 또는 최댓값)을 리턴!
   1. 위 두 경우가 아니라면 왼쪽 자식 서브트리와 오른쪽 자식 서브트리에서 max값 또는 min값을 구해서 리턴하면 됩니다!

## 후기
오우오우 세그먼트 트리 좋은거 같아유!

메리크리스마스~