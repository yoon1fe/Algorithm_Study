# [1005] ACM Craft - Java

###  :octocat: 분류

> 그래프
> 
> 위상정렬

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
public class week13_ACMCraft {
	static int N, K, W;
	static int[] time;
	static List<Integer>[] adjList;
	static int[] indegree;
	
	static void topologicalSort() {
		Queue<Integer> q = new LinkedList<Integer>();
		int[] result = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			result[i] = time[i];
			if(indegree[i] == 0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int p = q.poll();
			
			for(int i=0; i<adjList[p].size(); i++) {
				int node = adjList[p].get(i);
				result[node] = Math.max(result[node], result[p] + time[node]);
				indegree[node]--;
				if(indegree[node] == 0) q.offer(node);
			}
		}
		System.out.println(result[W]);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			time = new int[N+1];
			indegree = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) 
				time[i] = Integer.parseInt(st.nextToken());
			adjList = new ArrayList[N+1];
			for(int i=0; i<=N; i++) adjList[i] = new ArrayList<Integer>();
			for(int k=0; k<K; k++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				adjList[x].add(y);
				indegree[y]++;
			}
			W = Integer.parseInt(br.readLine());
			topologicalSort();
		}
	}
}
```

### :octocat: 풀이 방법

1. 방향이 있는 그래프를 만든다.
2. 위상정렬을 하면서 각 건물을 건설하는데 걸리는 시간을 구한다.

### :octocat: 후기

처음에 디피로 풀어볼려고 생고생고생을 했는데
오랜만에 알고리즘 풀어봐서 그런가 머리가 너무 안돌아가서 포기했다..ㅜㅜ
결국 위상정렬 써서 푸는 방법 찾아서 그걸로 풀었다 흑흑
