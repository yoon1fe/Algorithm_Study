# [19236] 청소년 상어 - Java

###  :octocat: 분류

> 구현
> DFS
> 시뮬레이션

### :octocat: 코드

```java
import java.util.Scanner;
public class week11_청소년상어 {
	static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int max = Integer.MIN_VALUE;
	
	static class Fish {
		int x, y;
		int dir;
		boolean alive;
		Fish(int x, int y, int dir, boolean alive){
			this.x = x; this.y = y;
			this.dir = dir; this.alive = alive;
		}
	}
	
	static void eatFish(int[][] tb, Fish[] tf, int x, int y, int sdir, int sum) {
		max = Math.max(max, sum);
		
		for(int i=1; i<=3; i++) {
			int nx = x + dx[sdir]*i;
			int ny = y + dy[sdir]*i;
			if(nx<0||nx>=4||ny<0||ny>=4) continue;
			if(tb[nx][ny] == 0) continue;
			// 보드와 물고기리스트 복사본 만들기
			int[][] tempboard = new int[4][4];
			for(int t=0; t<4; t++) System.arraycopy(tb[t], 0, tempboard[t], 0, 4);
			Fish[] tempfish = new Fish[17];
			for(int t=1; t<17; t++) tempfish[t] = new Fish(tf[t].x,tf[t].y,tf[t].dir,tf[t].alive);
			// 물고기 먹기
			int idx = tempboard[nx][ny];
			int dir = tempfish[idx].dir;
			tempboard[x][y] = 0;
			tempboard[nx][ny] = -1;
			tempfish[idx].alive = false;
			// 물고기 이동 시키고 상어 이동
			moveFish(tempboard, tempfish);
			eatFish(tempboard, tempfish, nx, ny, dir, sum+idx);
		}
	}
	
	static void moveFish(int[][] tb, Fish[] tf) {
		int i = 1;
		int dcnt = 0;
		while(true) {
			if(i >= 17 || dcnt == 8) break;
			if(!tf[i].alive) {i++; continue;}
			int nx = tf[i].x + dx[tf[i].dir];
			int ny = tf[i].y + dy[tf[i].dir];
			if(nx<0||nx>=4||ny<0||ny>=4||tb[nx][ny]==-1) {
				tf[i].dir = tf[i].dir+1==9?1:tf[i].dir+1;
				dcnt++; // 방향 한바퀴 다돌아서 검사했는데 못움직이면 종료
				continue;
			}
			if(tb[nx][ny] >= 0) {
				int idx = tb[nx][ny];
				int tx = tf[i].x;
				int ty = tf[i].y;
				// 바꿀 위치에 원래 위치 물고기 넣어주기
				tf[i].x = nx; tf[i].y = ny;
				tb[nx][ny] = i;
				// 원래 위치에 바꿀 위치 물고기 넣어주기
				if(idx >= 1) {
					tf[idx].x = tx;
					tf[idx].y = ty;
				}
				tb[tx][ty] = idx;
				i++;
				dcnt=0;
			} 
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] board = new int[4][4];
		Fish[] fishList = new Fish[17];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				int idx = sc.nextInt();
				int dir = sc.nextInt();
				board[i][j] = idx;
				fishList[idx] = new Fish(i, j, dir, true);
			}
		}
		// [0,0]칸 물고기 먹고 시작
		int idx = board[0][0];
		fishList[idx].alive = false;
		int d = fishList[idx].dir;
		board[0][0] = -1;
		moveFish(board, fishList);
		eatFish(board, fishList, 0, 0, d, idx);
		System.out.println(max);
	}
}
```

### :octocat: 풀이 방법

1. 상어는 0,0 칸에서 출발해서 먹은 물고기 방향을 가지고 이동할 수 있고
물고기가 있는 칸으로만 이동할 수 있다.
2. 먹을 수 있는 물고기가 여러개인 경우에 dfs를 이용해 물고기를 먹어가면서
최대한 먹은 물고기 번호 합이 최대가 되는 경우를 찾아야 한다.
3. 여러 경우마다 먹은 물고기가 다르기 때문에 dfs를 돌릴때 기존 물고기 위치 2차원 배열과
물고기 정보를 순서대로 담아놓은 리스트의 복사본을 만들어 다음 dfs로 넘겨준다.
4. 만약 상어가 더이상 움직일 수 없는 경우가 되면 dfs를 뒤에서 부터 빠져나오면서
원본 배열과 리스트에서 다른 물고기를 먹으며 다시 dfs를 시도할 수 있다.
5. 상어가 물고기를 먹고나면 물고기들은 각자 방향에 맞게 한칸씩 이동한다.

### :octocat: 후기

처음에 BFS를 이용해서 풀려고 했다.. 3차원 배열로 비트마스킹을 이용해 상어 방문체크를 해줄려고 했으나
어떤 물고기를 먹느냐에 따라 물고기 이동 위치가 계속 달라지기 때문에 중간에 포기했다 ㅜㅜ
원철이가 배열과 리스트를 복사해서 다음 dfs로 넘겨주는 방법을 가르쳐줘서 쉽게 풀 수 있었다!!!
원철갓!
