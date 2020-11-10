# [11559] Puyo Puyo - Java

###  :octocat: 분류

> 구현
> 
> BFS

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class week11_PuyoPuyo {
	static char[][] board = new char[12][6];
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static boolean checkColor(int x, int y) {
		char color = board[x][y];
		int cnt = 1;
		List<int[]> delList = new ArrayList<int[]>();
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {x,y});
		delList.add(new int[] {x,y});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			for(int d=0; d<4; d++) {
				int nx = p[0] + dx[d];
				int ny = p[1] + dy[d];
				if(nx<0||nx>=12||ny<0||ny>=6) continue;
				if(board[nx][ny]=='.'||visited[nx][ny]) continue;
				if(board[nx][ny] != color) continue;
				q.offer(new int[] {nx,ny});
				delList.add(new int[] {nx,ny});
				visited[nx][ny] = true;
				cnt++;
			}
		}
		
		if(cnt >= 4) {
			for(int i=0; i<delList.size(); i++)
				board[delList.get(i)[0]][delList.get(i)[1]] = '.';
			return true;
		} else return false;
	}
	
	static void move() {
		for(int i=10; i>=0; i--) {
			for(int j=0; j<6; j++) {
				if(board[i][j]=='.') continue;
				int nx = i;
				while(true) {
					nx++;
					if(nx>=12||board[nx][j]!='.') {
						nx--;
						break;
					}
				}
				if(nx != i) {
					board[nx][j] = board[i][j];
					board[i][j] = '.';
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;
		
		for(int i=0; i<12; i++) 
			board[i] = br.readLine().toCharArray();
		
		while(true) {
			visited = new boolean[12][6];
			boolean flag = false;
			for(int i=11; i>=0; i--) {
				for(int j=0; j<6; j++) {
					if(board[i][j]=='.'||visited[i][j]) continue;
					if(checkColor(i,j)) flag = true;
				}
			}
			move();
			if(flag) answer++;
			else break;
		}
		
		System.out.println(answer);
	}
}
```

### :octocat: 풀이 방법

1. board 밑에서부터 방문한적없는 색을 만나면 BFS를 통해 같은 색 몇개가 이어져 있는지 확인한다.
2. BFS 할때 따로 리스트에 좌표를 넣어둬서 같은색이 4개 이상 이어져있으면 해당 리스트에서 좌표를 가져와
점으로 바꿔준다.
3. 한번에 여러 색이 터져도 한번으로 친다고 했으니 더이상 지울 색이 없을 때 까지 반복해서 지워주고
한번에 블럭들을 내린다.
4. 반복해서 검사한뒤 더이상 지울수가 없으면 몇번 연쇄했는지 출력하고 끝

### :octocat: 후기

문제 이름 보자마자 모노미노 비슷한 블럭쌓기 게임문제인가 싶었는데 그냥 판하나 주고 터트리는거라 아주 쉬웠다.
빠르게 풀고 한번에 정답!
