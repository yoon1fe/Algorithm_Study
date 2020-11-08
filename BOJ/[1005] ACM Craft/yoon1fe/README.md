## [1005] ACM Craft - Java

### :video_game: 분류

> 위상 정렬



### :video_game: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	
	static int N, K, W;
	static int[] buildTime;
	static List<Integer>[] graph;
	static int[] inDegree;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			input();
			
			System.out.println(solve());
		}
	}
	
	public static int solve() {
		int[] total = Arrays.copyOf(buildTime, N);
		// 위상 정렬
		List<Integer> topol = topologicalSort();
		
		// 시작점에서부터 찾기
		for(int i = 0; i < N; i++) {
			int cur = topol.get(i);
			
			for(int j = 0; j < graph[cur].size(); j++) {
				int next = graph[cur].get(j);
				
				total[next] = Math.max(total[next], total[cur] + buildTime[next]);
			}
		}
		
		return total[W];
	}
	
	public static List<Integer> topologicalSort() {
		Queue<Integer> q = new LinkedList<>();
		List<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			if(inDegree[i] == 0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			list.add(cur);
			
			for(int i = 0; i < graph[cur].size(); i++) {
				int next = graph[cur].get(i);
				if(--inDegree[next] == 0) q.offer(next);
			}
		}
		
		return list;
	}

	public static void input() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
		
		buildTime = new int[N];
		graph = new List[N];
		inDegree = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
			buildTime[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine() , " ");
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			graph[from].add(to);
			inDegree[to]++;
		}
		
		W = Integer.parseInt(br.readLine()) - 1;
	}
	
}
```



### :video_game: 풀이 방법

W번 건물을 짓는데 걸리는 시간을 구하는 문제입니다.

위상 정렬을 해서 쉽게 풀 수 있씁니다.

먼저 큐를 이용해서 위상 정렬 결과를 구했습니다. 인풋을 받을 때 각 노드의 들어오는 노드의 수(inDegree)를 구해놓고 inDegree가 0인 노드들을 큐에 넣고 빼면서 나온 순서가 위상 정렬의 결과가 됩니다. 

자세히 알고 싶으면 [yoon1fe.tistory.com/116](https://yoon1fe.tistory.com/116) 요기로 ㅎㅎ

 

첨에 괜히 어렵게 생각해서 쪼금 헤맸습니다... W부터 봐야하니 위상 정렬 결과 반대로 봐야 하나... 했습니다 ㅜ

생각해보니 위상 정렬을 구해놓고 처음부터 뒤에 오는 애들의 합을 차곡차곡 더해서 total[W]의 값을 바로 출력하면 되는 것이었는디,,,



### :video_game: 후기

위상 정렬 만드는건 참 재밌는것 같습니다. 후후

감사합니다~!~!