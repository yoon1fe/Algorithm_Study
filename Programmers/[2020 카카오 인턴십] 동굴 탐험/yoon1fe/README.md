## [2020 카카오 인턴십] 동굴 탐험 - Java

###    :man_cartwheeling: ​분류

> BFS
>
> 위상 정렬



###  :man_cartwheeling: 코드

```java
import java.util.*;

class Solution {
    public static boolean solution(int n, int[][] path, int[][] order) {
        List<Integer>[] graph = new ArrayList[n];
        
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        for(int i = 0; i < path.length; i++) {
        	graph[path[i][0]].add(path[i][1]);
        	graph[path[i][1]].add(path[i][0]);
        }    
        
        return topological_sort(bfs(graph), order);
    }
	
	public static List<Integer>[] bfs(List<Integer>[] graph) {
		Queue<Integer> q = new LinkedList<>();
		List<Integer>[] directedGraph = new ArrayList[graph.length];
		for(int i = 0; i < directedGraph.length; i++) directedGraph[i] = new ArrayList<>();
		boolean[] v = new boolean[directedGraph.length];
		
		q.offer(0);
		v[0] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i = 0; i < graph[cur].size(); i++) {
				int next = graph[cur].get(i);
				if(v[next]) continue;
				
				directedGraph[cur].add(next);
				v[next] = true;
				q.offer(next);
			}
		}
		
		return directedGraph;
	}
	
	
	public static boolean topological_sort(List<Integer>[] graph, int[][] order) {
        Queue<Integer> q = new LinkedList<>();
        int[] inDegree = new int [graph.length];
        
        for(int i = 0; i < graph.length; i++) {
        	for(Integer next : graph[i]) {
        		inDegree[next]++;
        	}
        }
        
		for (int i = 0; i < order.length; i++) {
			graph[order[i][0]].add(order[i][1]);
			inDegree[order[i][1]]++;
		}
        
        for(int i = 0; i < graph.length; i++) {
        	if(inDegree[i] == 0) q.offer(i);
        }
        
        int cnt = 0;
        while(!q.isEmpty()) {
        	int cur = q.poll();
        	
//        	System.out.print(cur + " ");
        	cnt++;
        	
        	for(int i = 0; i < graph[cur].size(); i++) {
        		int next = graph[cur].get(i);
        		
        		if(--inDegree[next] == 0) {
        			q.offer(next);
        		}
        	}
        }
        
        return cnt == graph.length ? true : false;
	}
}
```



### :man_cartwheeling: 풀이 방법

그래프에서.. 순서가 있다???

위상 정렬이 생각이 났습니다..

하지만 어떻게 구현하는지 잘 몰랐으므로 공부하고 풀었습니다.



위상 정렬에 대해선 따로 정리해서 올려야겠습니다..

먼저 인풋으로 들어오는 path 이 친구로는 우리가 필요한 방향있는 그래프를 만들 수가 없습니다.

따라서 양방향 그래프로 만들고 이를 **BFS** 돌려서 **방향있는 그래프**로 만들어 주었습니다.

 

그렇게 만들어진 그래프에 order의 값들을 갖다 붙이고 위상 정렬을 돌리면 끝입니다.

만약 싸이클이 생성되지 않는다면**(순서대로 이동할 수 있다면)** 방의 개수와 cnt 값이 같아야 되겠져.



###  :man_cartwheeling: 후기

위상 정렬은 큐를 사용하는 방법 말고 dfs로도 만들 수 있습니다.

이 말인즉슨, 방향있는 그래프를 만들고 얘를 dfs 돌려서 싸이클이 있는지 찾을 수도 있단 말씀!

하지만 귀찮으니깐 다시 풀어보진 않을껍니다.

 

코테 화이팅!!!!!!!