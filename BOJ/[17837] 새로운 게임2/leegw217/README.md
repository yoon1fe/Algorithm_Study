# [17837] 새로운 게임 2 - Java

###  :octocat: 분류

> 구현
> 시뮬레이션

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class week10_새로운게임2 {
	static class chess {
		int index;
		int x, y;
		int dir;
		chess(int index, int x, int y, int dir) {
			this.index = index;
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[][] board = new int[N+1][N+1];
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				board[i][j] = sc.nextInt();
		List<chess>[][] chessBoard = new ArrayList[N+1][N+1];
		for(int i=0; i<=N; i++)
			for(int j=0; j<=N; j++)
				chessBoard[i][j] = new ArrayList<chess>();
		List<chess> Chess = new ArrayList<chess>();
		for(int i=0; i<K; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int dir = sc.nextInt();
			if(dir == 2) dir = 3;
			else if(dir == 3) dir = 0;
			else if(dir == 4) dir = 2;
			Chess.add(new chess(i,x,y,dir));
			chessBoard[x][y].add(new chess(i,x,y,dir));
		}
		for(int t=1; t<=1000; t++) {
			for(int k=0; k<Chess.size(); k++) {
				int index = Chess.get(k).index;
				int x = Chess.get(k).x;
				int y = Chess.get(k).y;
				int dir = Chess.get(k).dir;
				int nx = x+dx[dir];
				int ny = y+dy[dir];
				if(nx<1||nx>N||ny<1||ny>N||board[nx][ny] == 2) {
					dir = (dir+2)%4;
					Chess.get(k).dir = dir;
					nx = x+dx[dir];
					ny = y+dy[dir];
				}
				if(nx<1||nx>N||ny<1||ny>N||board[nx][ny] == 2) continue;
				int start = 0;
				List<chess> temp = new ArrayList<chess>();
				for(int i=0; i<chessBoard[x][y].size(); i++) 
					if(chessBoard[x][y].get(i).index == index) {
						start = i;
						break;
					}
				temp = chessBoard[x][y].subList(start, chessBoard[x][y].size());
				for(int i=0; i<temp.size(); i++) {
					Chess.get(temp.get(i).index).x = nx;
					Chess.get(temp.get(i).index).y = ny;
				}
				if(board[nx][ny] == 1) Collections.reverse(temp);
				chessBoard[nx][ny].addAll(temp);
				chessBoard[x][y].removeAll(temp);
				if(chessBoard[nx][ny].size() >= 4) {
					System.out.println(t);
					System.exit(0);
				}
			}
		}
		System.out.println(-1);
	}
}
```

### :octocat: 풀이 방법

1. 체스말들의 번호, 위치와 방향을 순서대로 저장해놓을 리스트를 만든다.
2. 체스말들이 쌓여있는지 확인할 체스판을 만든다.
3. 체스말 리스트 순서대로 순회하면서 방향대로 한칸씩 이동시키는데 이때 자기 위에 얹어있는 모든 말을 같이 이동시킨다.
4. 이동할려는 위치가 파란색이거나 체스판을 벗어나면 방향을 바꾸고 한칸 이동시키는데 이때 또 파란색이거나 벗어나면 
제자리에서 멈춘다.
5.  이동하고나서 해당 칸에 체스말이 4개이상 쌓여있으면 종료. 1000번 돌때까지 안되면 -1 출력

### :octocat: 후기

체스말 이동할때 자기 위에 있는애들 위치를 안바꿔줬는데 우연히 테케가 돌아가서 틀렸다
백준은 테케를 더 잘만들어라!!!
