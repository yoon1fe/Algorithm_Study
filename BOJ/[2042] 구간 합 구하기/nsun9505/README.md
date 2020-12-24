# [2042] 구간 합 구하기 - JAVA

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
	static long[] arr;
	static long[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		arr = new long[N+1];
		
		for(int i=1; i<=N; i++)
			arr[i] = Long.parseLong(br.readLine());
		
		int height = (int)Math.ceil(Math.log(N)/Math.log(2)) + 1;
		int size = (int)Math.pow(2, height);
		tree = new long[size];
		
		makeSegTree(1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int cond = Integer.parseInt(st.nextToken());
			
			if(cond == 1) {
				int index = Integer.parseInt(st.nextToken());
				long newValue = Long.parseLong(st.nextToken());
				long diff = newValue - arr[index];
				arr[index] = newValue;
				update(index, 1, 1, N, diff);
			} else {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				sb.append(find(1, 1, N, left, right) + "\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	
	public static long find(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
			return 0;
		if(left <= start && end <= right)
			return tree[node];
		int mid = (start+end)/2;
		return find(node*2, start, mid, left, right)
				+ find((node*2)+1, mid+1, end, left, right);
	}
	
	public static void update(int index, int node, int start, int end, long diff) {
		if(index < start || index > end)
			return;
		
		tree[node] += diff;
		if(start == end)
			return;
		
		int mid = (start+end)/2;
		update(index, node*2, start, mid, diff);
		update(index, node*2 + 1, mid + 1, end, diff);
	}
	
	public static long makeSegTree(int node, int start, int end) {
		if(start == end)
			return tree[node] = arr[start];
		int mid = (start+end)/2;
		return tree[node] = makeSegTree(node * 2, start, mid) 
				+ makeSegTree(node*2 + 1, mid + 1, end);
	}
}
```

## 문제 풀이
문제를 풀기 위해서 고민하다가 완전탐색으로는 무리라고 생각해서 세그먼트 트리를 찾아서 공부하고 풀었습니다.

[여기](https://www.acmicpc.net/blog/view/9)를 보고 풀었습니다.
   - 자료구조를 설명하는 글인줄 알았는데 전체 코드를 보니 답이였네요..ㅎ

### makeSegTree
   - 세그먼트 트리를 구성하는 메서드입니다.
   - node는 트리의 몇 번째 노드인지, start와 end는 node가 담당하는 범위입니다.
   - 리프노드는 입력 받은 배열 값이 들어가고, 내부 노드에는 i~j번째까지의 합이 들어가게 됩니다!
   - 그래서 start와 end가 같으면 세그먼트 트리에 arr[start]를 저장합니다.
   - start != end라면 아직 내부 노드이므로 tree[node]에 왼쪽 자식 노드와 오른쪽 자식 노드를 더해서 값을 저장합니다.
   - 세그먼트 트리는 Full Binary Tree(자식이 없거나, 둘 이거나)이므로 왼/오른쪽 노드의 값을 가져오는데 문제가 없습니다.

### find : left ~ right 구간의 합을 출력합니다.
   - start, end : node가 담당하는 범위
   - left, right : 찾고자 하는 구간
   - (left > end || right < start)에서 left > end인 경우 [start, end] [left, right]이고, right < start인 경우 [left, start] [start, end]인 경우입니다.
       - 즉, 범위 안에 들지 않으므로 0을 리턴해서 결과에 영향을 주지 않도록 합니다.
   - left <= start && end <= right인 경우는 현재 node가 찾고자 하는 [left, right] 구간 안에 포함되는 것이므로 더 밑으로 내려갈 필요 없이 tree[node]를 리턴하면 됩니다.
   - 만약 위 두 경우도 아니라면 [left, right] 구간이 start, end 구간에 걸쳐있거나 start, end 구간에 완전히 포함된 경우 즉, [start, end] 구간이 더 큰 경우이므로 범위를 좀 더 줄여서 찾아보면 됩니다.

### update : index가 속하는 구간의 합을 변경합니다.
   - index가 현재 노드의 구간인 [start, end]에 속하지 않는다면 영향을 받지 않으므로 바로 종료합니다.
   - 영향을 받는다면, 이전에 계산한 newValue - oldValue(diff)를 현재 노드에 더해줍니다.
   - 만약 현재 노드가 리프노드가 아니라면 자식 노드들에게도 영향을 주기 위해 두 개로 나누어서 호출하면 됩니다.
   - 그리고 원래 숫자를 저장하던 배열도 새로운 값으로 변경해줍니다. arr[index] = newValue

## 후기
사람들이 어렵다 어렵다 했는데, 기본적인 문제라서 자료구조를 배우고 사용해보는 좋은 문제 였습니다.