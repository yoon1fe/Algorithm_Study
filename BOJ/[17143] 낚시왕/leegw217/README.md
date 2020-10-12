# [17143] 낚시왕 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class week09_낚시왕 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int answer = 0;
	static int R,C,M;
	static int sharkCnt = 0;
	static Queue<shark> sharkList;
	static class shark {
		int x, y;
		int speed;
		int dir;
		int size;
		shark(int x, int y, int speed, int dir, int size){
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	
	static void catchShark(int y, int[][] board) {
		for(int x=1; x<=R; x++) {
			if(board[x][y] != 0) {
				answer += board[x][y];
				board[x][y] = 0;
				sharkCnt++;
				break;
			}
		}
	}
	
	static void moveShark(int[][] board1, int[][] board2) {
		int size = sharkList.size();
		for(int i=0; i<size; i++) {
			shark temp = sharkList.poll();
			if(board1[temp.x][temp.y] != temp.size) continue;
			int nx = temp.x;
			int ny = temp.y;
			int d = temp.dir;
			int sp = temp.speed;
			if(d==0 || d==2) sp = sp%((R-1)*2);
			else sp = sp%((C-1)*2);
			
			for(int j=0; j<sp; j++) {
				if(nx+dx[d]<=0||nx+dx[d]>R||ny+dy[d]<=0||ny+dy[d]>C)
					d = (d+2)%4;
				nx += dx[d];
				ny += dy[d];
			}
			if(board2[nx][ny] <= temp.size) {
				board2[nx][ny] = temp.size;
				sharkList.add(new shark(nx,ny,sp,d,temp.size));
			}
			board1[temp.x][temp.y] = 0; 
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] board1 = new int[R+1][C+1];
		int[][] board2 = new int[R+1][C+1];
		sharkList = new LinkedList<shark>();
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int sp = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			if(dir == 1) dir = 0;
			else if(dir == 4) dir = 1;
			int size = Integer.parseInt(st.nextToken());
			board1[x][y] = size;
			sharkList.add(new shark(x, y, sp, dir, size));
		}
		int cur = 0;
		while(cur < C) {
			if(M==0 || M==sharkCnt) break;
			cur++;
			if(cur%2 != 0) {
				catchShark(cur, board1);
				moveShark(board1, board2);
			} else {
				catchShark(cur, board2);
				moveShark(board2, board1);
			}
		}
		System.out.println(answer);
	}
}
```

### :octocat: 풀이 방법

1. 낚시왕이 이동하고 땅에 가장 가까운 상어 잡기
2. 남은 상어들 이동
이때 상어가 있는 보드를 2개만들어서 이동 전과 이동 후로 관리
3. 상어 이동할 때 이동하는 거리는 (한바퀴 돌아서 다시 자기 자리로 돌아올때까지 거리)로 나눴을때
나머지 만큼 이동한다.

### :octocat: 후기

상어 이동할때 시간초과때문에 골치가 아팠던 문제.. 결국 구글에서 찾아서 로직 바꿨다 ㅜ
