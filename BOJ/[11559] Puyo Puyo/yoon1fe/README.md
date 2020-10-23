# [11559] Puyo Puyo - Java

###   :video_game:분류

> 시뮬레이션
>
> BFS

​

###  :video_game:코드

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
	
	static char[][] map = new char[12][6];
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		System.out.println(solve());
		
	}
	
	public static int solve() {
		int cnt = 0;
		
		while(true) {
			int boomCnt = 0;
			
			// 뿌요 터뜨리기
			for(int i = 0; i < 12; i++) {
				for(int j = 0; j < 6; j++) {
					if(map[i][j] == '.') continue;
					boomCnt += boom(new Dir(i, j));
				}
			}
			
			if(boomCnt == 0) break;
			cnt++;
			
			// 뿌요 내리기
			moveDown();
		}
		
		return cnt;
	}	
	
	public static void moveDown() {
		for(int i = 0; i < 6; i++) {
			for(int j = 11; j >= 0; j--) {
				if(map[j][i] == '.') continue;
				Dir cur = new Dir(j, i);
				Dir next = new Dir(cur.y + 1, cur.x);
				if(!isIn(next)) continue;
				
				while(isIn(next) && map[next.y][next.x] == '.') {
					next.y++;
				}
				
				if(j != next.y - 1) {
					map[next.y - 1][next.x] = map[cur.y][cur.x];
					map[cur.y][cur.x] = '.';
				}
			}
		}
	}

	public static int boom(Dir start) {
		// bfs로 터뜨리자
		Queue<Dir> q = new LinkedList<>();
		List<Dir> removeList = new ArrayList<>();
		
		boolean[][] visited = new boolean[12][6];
		
		q.offer(start);
		visited[start.y][start.x] = true;
		removeList.add(start);
		
		while(!q.isEmpty()) {
			Dir cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
				if(!isIn(next) || visited[next.y][next.x]) continue;
				if(map[next.y][next.x] == map[start.y][start.x]) {
					q.offer(next);
					visited[next.y][next.x] = true; 
					removeList.add(next);
				}
			}
		}
		
		if(removeList.size() >= 4) {
			for(Dir c : removeList) map[c.y][c.x] = '.';
			return 1;
		}
		else return 0;
	}
	
	public static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < 12 && 0 <= c.x && c.x < 6) return true;
		else return false;
	}	
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 12; i++) {
			String ip = br.readLine();
			for(int j = 0; j < ip.length(); j++) map[i][j] = ip.charAt(j);
		}
	}
}
```



### :video_game: 풀이 방법

올 초에 한시간 반동안 붙잡고도 못 푼 문제를

30분컷했씁니다.

뿌듯~



구현할 동작은 두 개입니다.

1. 뿌요 터뜨리기
2. 뿌요 내리기

네 개 이상 연결된 뿌요들은 동시에 터지기 때문에 필드를 한번 다 탐색해서 일단 터뜨립니다. 

boom() 메소드에서 뿌요를 터뜨렸으면 1을 리턴해서 전체 필드에서 터진 뿌요들이 있는지 여부를 체크해줍시다.

뿌요를 터뜨리는건 BFS를 돌려서 가뿐히 구현할 수 있습니다.

 

만약 탐색을 다 하고 boomCnt 가 0이면 그때 반복문을 종료하면 되겠져.

 

한 번이라도 뿌요들이 터졌다면 뿌요들을 내려줍시다.

이것도 그냥 반복문 돌려서 내려갈 수 있을 때까지 내려가면 됩니다. 범위를 벗어나는 경우를 주의하세욥!



###  :video_game:후기

올 초에는 증말 골치아팠는데 이렇게 쉽게 풀리다니~~

빨리 더 성장하고 싶다!!!

화이팅!!!