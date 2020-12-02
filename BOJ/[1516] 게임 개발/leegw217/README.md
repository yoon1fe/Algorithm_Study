# [1516] 게임 개발 - Java

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
public class week14_게임개발 {
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
		for(int i=1; i<=N; i++) 
			System.out.println(result[i]);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		time = new int[N+1];
		indegree = new int[N+1];
		adjList = new ArrayList[N+1];
		for(int i=0; i<=N; i++) adjList[i] = new ArrayList<Integer>();
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			while(true) {
				int n = Integer.parseInt(st.nextToken());
				if(n == -1) break;
				adjList[n].add(i);
				indegree[i]++;
			}
		}
		topologicalSort();
	}
}
```

### :octocat: 풀이 방법

1. 방향이 있는 그래프를 만든다.
2. 위상정렬을 하면서 각 건물을 건설하는데 걸리는 시간을 구한다.

### :octocat: 후기

ACM Craft 문제랑 똑같은 문제였다.
위상정렬 공부!!
