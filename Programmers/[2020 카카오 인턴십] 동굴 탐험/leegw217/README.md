# [2020 카카오 인턴십] 동굴 탐험 - Java

###  :octocat: 분류

> BFS
>
> 위상정렬

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class week05_동굴탐험 {
	ArrayList<Integer>[] adjList;
	ArrayList<Integer> st = new ArrayList<Integer>();
	int[] indegree;
	
	void makeGraph(int n, int[][] order) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[n];
		q.offer(0);
		visited[0] = true;
		
		while(!q.isEmpty()) {
			int p = q.poll();
			
			for(int i=adjList[p].size()-1; i>=0; i--) { 
				if(!visited[adjList[p].get(i)]) {
					visited[adjList[p].get(i)] = true;
					q.offer(adjList[p].get(i));
					adjList[p].remove(i);
				}
			}
		}
		
		for(int i=0; i<order.length; i++)
			adjList[order[i][1]].add(order[i][0]);
	}
	
	public boolean solution(int n, int[][] path, int[][] order) {
        adjList = new ArrayList[n];
        for(int i=0; i<n; i++) adjList[i] = new ArrayList<Integer>();
        for(int i=0; i<path.length; i++) {
        	adjList[path[i][0]].add(path[i][1]);
        	adjList[path[i][1]].add(path[i][0]);
        }
        makeGraph(n, order);
        indegree = new int[n];
        
        for(int i=0; i<n; i++)
        	for(int j=0; j<adjList[i].size(); j++)
        		indegree[adjList[i].get(j)]++;
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i=0; i<n; i++)
        	if(indegree[i] == 0) q.offer(i);
        int cnt = 0;
        while(!q.isEmpty()) {
        	int p = q.poll();
        	cnt++;
        	for(int i=0; i<adjList[p].size(); i++) {
        		indegree[adjList[p].get(i)]--;
        		if(indegree[adjList[p].get(i)] == 0)
        			q.offer(adjList[p].get(i));
        	}
        }
        if(cnt != n) return false;
        return true;
    }
}
```

### :octocat: 풀이 방법

1. 입력받은 path를 가지고 양방향 그래프를 만든다.
2. 각 노드별로 해당 노드에 방문하기 위해 꼭 방문해야하는 부모노드가 있으니
부모노드에서 해당 노드로 가는 방향을 지운다.
3. 어떤 노드 X에서 출발해 먼저 방문해야하는 노드를 순서대로 따라가다가
다시 자기자신을 만나면 cycle이 존재한다는 뜻이고 해당 노드로 진입이 불가능하다는 뜻이된다.
4. 위상정렬을 통해 cycle이 존재하는지 확인하고 있으면 false 없으면 true를 출력한다.

### :octocat: 후기

보자마자 못풀거같아서 바로 카카오테크 블로그를 봤다. 보고 대충 이해해서 코드를 짰는데
cycle 검사하는 부분에서 dfs를 이용해서 했더니 효율성에서 시간초과가 3개가 나왔다...
아마 뭔가 가지치기를 했어야 했는데 안했나보다하고 원철이가 가르쳐준 위상정렬 정리사이트를
정독하고 위상정렬을 사용해 쉽게 해결했다!!
이거 비슷한 문제 또 나오면 효율성 100점은 어려울거같다 ㅜㅜ
