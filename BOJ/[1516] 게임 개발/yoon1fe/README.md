## [1516] 게임 개발 - Java

### :video_game: 분류

> 위상 정렬



### :video_game: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer>[] graph;
	static int[] time;
	static int[] inDegree;
	
	public static void main(String[] args) throws Exception {
		input();

		solve();
	}
	
	public static void solve() {
		int[] answer = new int[N + 1];
		
		// 위상 정렬
		List<Integer> topol = topologicalSort();
		
		// 더해가기
		for(int i = 0; i < topol.size(); i++) {
			int cur = topol.get(i);
			answer[cur] += time[cur];
			
			for(int j = 0; j < graph[cur].size(); j++) {
				int next = graph[cur].get(j);
				
				if(answer[next] < answer[cur]) answer[next] = answer[cur];
			}
		}
		
		for(int i = 1; i <= N; i++) System.out.println(answer[i]);
	}
	
	public static List<Integer> topologicalSort() {
		List<Integer> ret = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) {
			if(inDegree[i] == 0) q.offer(i); 
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			ret.add(cur);
			
			for(int i = 0; i < graph[cur].size(); i++) {
				int next = graph[cur].get(i);
				if(--inDegree[next] == 0) q.offer(next);
			}
		}
		
		return ret;
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		graph = new List[N + 1];
		time = new int[N + 1];
		inDegree = new int[N + 1];
		
		for(int i = 1; i <= N; i++)
			graph[i] = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			time[i] = Integer.parseInt(st.nextToken());
			
			while(true) {
				int from = Integer.parseInt(st.nextToken());
				if(from == -1) break;
				graph[from].add(i);
				inDegree[i]++;
			}
		}
	}
}
```



### :video_game: 풀이 방법

ACM Craft 문제랑 비슷합니다. 위상 정렬을 해서 풀면 편하답니다.

먼저 위상 정렬을 합니다.

요 문제는 인풋이 좀 희한해서 주의해야 합니다. -1이 나올 때까지 그래프에 추가해주어야 합니다. from 과 to 를 헷갈리지 맙시다.!

 

인풋을 이쁘게 잘 받았으면 그래프를 토대로 위상 정렬을 합니다. 위상 정렬을 한 값으로 그리디하게 해당 건물을 지을 수 있을 때까지 걸리는 시간을 구하면 됩니다. 코드를 보니 다익스트라 느낌이 물씬 납니다. 요것도 다익스트라로도 풀 수 있을 것만 같은 느낌이 듭니다.

 

answer에 차곡차곡 더해주고 출력하면 끝입니당.



### :video_game: 후기

재미난 위상 정렬..

하지만 이를 활용해서 문제 푸는데는 좀 더 인사이트를 키워야겠슴니당....

감사합니다!!