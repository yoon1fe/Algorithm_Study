# [2606] 바이러스 - Java

###  :computer: 분류

> BFS



### :computer: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] graph;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		input();
		System.out.println(bfs(1));
	}
	
	public static int bfs(int s) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] v = new boolean[N + 1];
		int answer = 0;
		
		q.offer(s);
		v[s] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i = 0; i < graph[cur].size(); i++) {
				int next = graph[cur].get(i);
				if(v[next] == false) {
					answer++;
					v[next] = true;
					q.offer(next);
				}
			}
		}
		
		return answer;
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); M = Integer.parseInt(br.readLine());
		
		graph = new List[N + 1];
		for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			graph[to].add(from);
		}
	}
}

```



### :computer: 풀이 방법

1번 컴퓨터덕분에 바이러스에 걸리는 애들의 수를 구하는 문제입니다.

아아아주 기본적인 BFS 문젭니당.

금요일이고 해서 알고리즘 스터디 문제를 쉬울걸로 넣어놔서 풀어봐씀니다..ㅎ

 

인풋으로 그래프를 이쁘게 만들어 줍니다. 방향이 없는 그래프이기 때문에 양방향으로 넣어 줍니다.

그리고 시작점은 항상 1번 컴퓨터입니다. 그렇기 때문에 1번에서 BFS를 돌려서 방문하는 친구들의 개수를 구하면 됩니당.

 

### :computer: 후기

올해 초까지만 해도 2차원 배열아니면 BFS도 잘 못돌렸는데 역시 해서 안되는건 없나 봅니다.

감사합니다~!~!