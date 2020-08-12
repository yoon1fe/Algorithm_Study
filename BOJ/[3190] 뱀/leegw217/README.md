# [3190] 뱀 - Java

###  :octocat: 분류

> 시뮬레이션

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class week01_뱀 {
	static class Move{
		int t;
		char dir;
		
		public Move(int t, char dir) {
			this.t = t;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int appleCnt = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		int time = 0;
		int[] dx = {0, 1, 0, -1}; // R D L U
		int[] dy = {1, 0, -1, 0};
		int snakeX = 0;
		int snakeY = 0;
		List<int[]> snake = new ArrayList<int[]>(); // 뱀 위치 배열
		snake.add(new int[] {snakeX, snakeY});
		
		for(int i=0; i<appleCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x-1][y-1] = 2; // 사과는 2, 뱀은 1
		}
		map[snakeX][snakeY] = 1;
		
		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		Queue<Move> q = new LinkedList<Move>(); // 방향전환 명령 저장
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			q.offer(new Move(Integer.parseInt(st.nextToken()),st.nextToken().charAt(0)));
		}
		Move p = q.poll();
		int dir = 0; // 시작하면 오른쪽 진행
		
		while(true) {
			time++;
			if(time == p.t+1) { // 방향전환 시간 지나면 회전
				if(p.dir == 'D') dir++; // 오른쪽 90도 회전
				else if(p.dir == 'L') dir--; // 왼쪽 90도 회전
				
				if(dir == 4) dir = 0;
				else if(dir == -1) dir = 3;
				
				if(!q.isEmpty())
					p = q.poll();
			}
			
			snakeX += dx[dir]; // 뱀 머리 이동
			snakeY += dy[dir];
			
			if(snakeX<0||snakeX>=N||snakeY<0||snakeY>=N) break; // 벽에 박으면 끝
			if(map[snakeX][snakeY] == 1) break; // 뱀 몸통에 박으면 끝
			
			snake.add(new int[] {snakeX, snakeY});
			
			if(map[snakeX][snakeY] != 2) { // 사과 못먹으면 젤 뒤 꼬리 짜르기
				map[snake.get(0)[0]][snake.get(0)[1]] = 0;
				snake.remove(0);
			}
			map[snakeX][snakeY] = 1;
		}
		System.out.println(time);
	}
}
```

### :octocat: 풀이 방법

주어진대로 맵을 만들고 주어진대로 사과를 놓고 주어진대로 이동시키면서 뱀을 죽이는 문제입니다.
1. 뱀 이동 시작
2. 방향전환 명령어에 따라 해당 시간이 되면 해당 방향으로 방향전환
3. 맵 밖으로 뱀 머리가 나가거나 뱀 머리가 몸통에 닿으면 종료!
4. 사과를 먹으면 꼬리를 그대로 두고 사과가 없으면 꼬리를 한칸씩 자르면서 이동시킵니다.

### :octocat: 후기

맵에 뱀이 움직이는거 찍으면서 풀었는데 재밌었습니다!
