## [1261] 알고스팟 - Java

### :video_game: 분류

> BFS



### :video_game: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static int N, M;
	static int[][] map;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception {
		input();
		
		System.out.println(solve());
	}
	
	public static int solve() {
		Queue<Dir> q = new LinkedList<>();
		int[][] v = new int[N][M];
		
		for(int i = 0; i < N; i++) Arrays.fill(v[i], Integer.MAX_VALUE);
		
		q.offer(new Dir(0, 0));
		v[0][0] = 0;
		
		while(!q.isEmpty()) {
			Dir cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
				if(!isIn(next)) continue;
				if(map[next.y][next.x] == 0 && v[next.y][next.x] > v[cur.y][cur.x] ) {
					v[next.y][next.x] = v[cur.y][cur.x];
					q.offer(next);
				}else if(map[next.y][next.x] == 1 && v[next.y][next.x] > v[cur.y][cur.x] + 1){
					v[next.y][next.x] = v[cur.y][cur.x] + 1;
					q.offer(next);
				}
			}
		}

		return v[N - 1][M - 1];
	}
	
	public static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < N && 0 <= c.x && c.x < M) return true;
		else return false;
	}
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}
	}
	
}
```



### :video_game: 풀이 방법

미로를 탈출시키랩니다.

BFS를 써서 간단하게 풀었는데 풀고 나서 알고리즘 분류를 보니 다익스트라로도 풀 수 있댑니다..

난 못풀겠습니다.



BFS를 써서 간단하게 풀었습니다. 시작점(0, 0)에서부터 출발하는데 v[][] 배열로 방문 여부를 따지지 않고 뿌순 벽의 수를 저장해가면서 도착점까지 갔습니다.

가장 적은 경우를 찾아야 하기 때문에 v[][] 배열을 먼저 엄청 큰 수로 초기화를 해줍니다. 그리고 시작점에서는 부순 벽이 없으니깐 0으로 주고 시작합니다.

 

그 뒤로도 간단합니당. 사방 탐색을 하는데,

1) 이동한 곳이 벽이 아니면 v[next.y][next.x] 배열의 값보다 v[cur.y][cur.x] 배열의 값이 작은 경우에만 고걸로 갱신하고 큐에 넣어주는 겁니당. 가지치기를 하는 셈이지여.

2) 이동한 곳이 벽이면 v[cur.y][cur.x] 배열의 값 + 1이 될테니깐 얘랑 v[next.y][next.x] 의 값을 비교해서 갱신해주면 끝입니다..



### :video_game: 후기

조만간 다익스트라로도 꼭 한번 풀어봐야게씀당...^^

감사합니다!!