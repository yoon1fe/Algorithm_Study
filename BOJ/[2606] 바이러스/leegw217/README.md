# [2606] 바이러스 - Java

###  :octocat: 분류

> 그래프
>
> BFS

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
public class week15_바이러스 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		List<Integer>[] adjList = new ArrayList[V+1];
		for(int v=1; v<=V; v++) adjList[v] = new ArrayList<Integer>();
		for(int e=0; e<E; e++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			adjList[a].add(b);
			adjList[b].add(a);
		}
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] v = new boolean[V+1];
		for(int e : adjList[1]) q.offer(e);
		v[1] = true;
		int ans = 0;
		
		while(!q.isEmpty()) {
			int node = q.poll();
			if(v[node]) continue;
			v[node] = true;
			ans++;
			for(int e : adjList[node]) 
				if(!v[e]) q.offer(e);
		}
		System.out.println(ans);
	}
}
```

### :octocat: 풀이 방법

1. 인접리스트를 이용해 그래프를 만든다.
2. BFS를 이용해 1번과 연결된 컴퓨터 수를 구한다.

### :octocat: 후기

힐링 문제..
