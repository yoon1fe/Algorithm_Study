# [19237] 어른 상어 - Java

###  :octocat: 분류

> 구현
> 
> 시뮬레이션

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class week11_어른상어 {
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	
	static class Shark {
		int x, y;
		int dir;
		boolean alive;
		Shark(int x, int y, int dir, boolean alive){
			this.x = x; this.y = y;
			this.dir = dir; this.alive = alive;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Shark[] shark = new Shark[M+1];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int shk = Integer.parseInt(st.nextToken());
				if(shk != 0)
					shark[shk] = new Shark(i,j,0,true);
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=M; i++) 
			shark[i].dir = Integer.parseInt(st.nextToken());
		int[][][] priority = new int[M+1][5][4];
		for(int i=1; i<=M; i++) {
			for(int j=1; j<=4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int d=0; d<4; d++) 
					priority[i][j][d] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] smell = new int[N][N];
		Queue<int[]> smellq = new LinkedList<int[]>();
		loop:
		for(int t=1; t<=1000; t++) {
			int size = smellq.size();
			int[][] sharkBoard = new int[N][N];
			for(int s=1; s<=M; s++) {
				if(!shark[s].alive) continue;
				// 상어 냄새 뿌리기
				smell[shark[s].x][shark[s].y] = s;
				smellq.offer(new int[] {K-1,shark[s].x,shark[s].y});
			}
			for(int s=1; s<=M; s++) {
				if(!shark[s].alive) continue;
				boolean f = false;
				// 인접 칸 중 냄새가 없는 칸으로 이동
				for(int d=0; d<4; d++) {
					int dir = priority[s][shark[s].dir][d];
					int nx = shark[s].x + dx[dir];
					int ny = shark[s].y + dy[dir];
					if(nx<0||nx>=N||ny<0||ny>=N) continue;
					if(smell[nx][ny] != 0) continue;
					f = true;
					// 상어가 이동한 위치에 다른 상어가 있을 때
					if(sharkBoard[nx][ny] != 0) {
						if(s > sharkBoard[nx][ny]) { // 나보다 번호 작은 상어가 있으면 잡아먹힘
							shark[s].alive = false;
							break;
						} else  // 나보다 번호 큰 상어가 있으면 잡아먹음
							shark[sharkBoard[nx][ny]].alive = false;
					} 
					sharkBoard[nx][ny] = s;
					shark[s].x = nx;
					shark[s].y = ny;
					shark[s].dir = dir;
					break;
				}
				if(!f) { // 인접 칸 중 냄새가 없는 칸이 없으면 자기 냄새 칸으로 이동
					for(int d=0; d<4; d++) {
						int dir = priority[s][shark[s].dir][d];
						int nx = shark[s].x + dx[dir];
						int ny = shark[s].y + dy[dir];
						if(nx<0||nx>=N||ny<0||ny>=N) continue;
						if(smell[nx][ny] == s) {
							sharkBoard[nx][ny] = s;
							smell[nx][ny] = -1;
							shark[s].x = nx;
							shark[s].y = ny;
							shark[s].dir = dir;
							break;
						}
					}
				}
			}
			// 냄새 최신화
			for(int sm=0; sm<size; sm++) {
				int[] p = smellq.poll();
				// 냄새가 시간이 지나서 사라지거나 상어가 다시 자기 냄새 위치로 돌아온 경우
				if(p[0]==1 || smell[p[1]][p[2]]==-1) { 
					smell[p[1]][p[2]] = 0;
					continue;
				}
				smellq.offer(new int[] {p[0]-1,p[1],p[2]});
			}
			// 1번 말고 다 죽었나 확인
			for(int i=2; i<=M; i++) 
				if(shark[i].alive) continue loop;
			System.out.println(t);
			System.exit(0);
		}
		System.out.println(-1);
	}
}
```

### :octocat: 풀이 방법

1. 상어들은 자기 위치에 냄새를 남기고 우선순위에 맞는 방향으로 한칸씩 이동한다.
2. 이동할때 우선적으로 4방향 중 냄새가 없는 칸으로 이동하고 없다면 자기 냄새로 이동한다.
3. 상어들의 위치, 방향, 생존여부를 담는 리스트(shark)를 만들어 관리하고
상어들을 이동시킬때 새로 비어있는 2차원 배열(sharkBoard)을 만들어 1번부터 살아있는 상어들만 이동시킨다.
4. 냄새는 K초가 지나면 사라지게 queue로 냄새들을 담아 관리하고 (smellq)
2차원 배열(smell)을 만들어 냄새를 남긴 상어의 번호를 저장해 관리한다.
이렇게 하면 queue에는 상어의 번호가 들어가있지 않아도 위치를 담고 있어서 관리가 가능하다!
상어가 이동할때는 smell 배열에서 4방탐색으로 검사하면 되니 편하다
5. 만약 이동한 위치에 다른 상어가 있으면 번호가 작은 상어가 큰 상어를 먹는다!
6. 1번 상어만 남을 때까지 걸리는 시간을 출력하고 1000번 초과로 돌아가면 -1 출력

### :octocat: 후기

완벽하게 로직짰다고 생각하고 돌렸는데 테케, 반례 다맞고 25퍼쯤에서 실패했다...
디버깅하기도 까다로운 문제여서 디버깅하는데 시간을 엄청 투자했다 ㅜㅜ
혹시나 내가 실수한게 있나싶어서 문제를 읽어보니 상어들이 먼저 냄새를 자기 위치에 남기고 나서 이동해야하는데
나는 상어마다 이동할때 원래 위치에 냄새를 뿌리고 이동시켜서 안되는거시였다... 한번에 냄새 다 남기고 이동시키니 됐다!!!
상어가 겹치는 경우도 다 짜고나서 확인해보니 어차피 1번부터 이동시키기 때문에 무조건 나보다 번호가 작은 상어가
이동하려는 위치에 있을테니 조건을 하나 지워도 됐었다 히히
