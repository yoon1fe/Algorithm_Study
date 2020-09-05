# [2020 KAKAO BLIND RECRUITMENT] 벽돌 이동하기 - Java

###  :octocat: 분류

> 시물레이션
> 
> BFS

### :octocat: 코드

```java
import java.util.LinkedList;
import java.util.Queue;
public class week04_블록이동하기 {
	static class robot{
		int x1, y1, x2, y2, dir, len; // dir = 0 : 가로, dir = 1 : 세로
		robot(int x1, int y1, int x2, int y2, int dir, int len){
			this.x1 = x1; this.y1 = y1;
			this.x2 = x2; this.y2 = y2;
			this.dir = dir;
			this.len = len;
		}
	}
	
	static boolean check(int x1, int y1, int x2, int y2, int N) {
		if(x1<0||x2<0||y1<0||y2<0||x1>=N||x2>=N||y1>=N||y2>=N)
			return false;
		return true;
	}
	
	public int solution(int[][] board) {
		int answer = Integer.MAX_VALUE;
        robot rb = new robot(0,0,0,1,0,0);
        int N = board[0].length;
        boolean[][][] visited = new boolean[N][N][2];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<robot> q = new LinkedList<robot>();
        q.offer(rb);
        visited[rb.x1][rb.y1][rb.dir] = true;
        visited[rb.x2][rb.y2][rb.dir] = true;
        
        while(!q.isEmpty()) {
        	robot p = q.poll();
        	
        	if(p.x2==N-1 && p.y2==N-1) {
        		if(answer > p.len) answer = p.len;
        	}
        	
        	int nx1=0, ny1=0, nx2=0, ny2=0, d=0;
        	for(int i=0; i<8; i++) {
        		if(i>3) { // 회전
        			if(p.dir == 0) { // 가로일때
        				if(i==4 || i==5) {
        					if(p.x1-1<0||p.x2-1<0) continue;
        					if(board[p.x1-1][p.y1]==1 || board[p.x2-1][p.y2]==1) continue;
        					if(i == 4) { // 위 오른쪽
        						nx1 = p.x1-1; ny1 = p.y1+1;
            					nx2 = p.x2; ny2 = p.y2;
            				} else { // 위 왼쪽
            					nx1 = p.x1 -1; ny1 = p.y1;
            					nx2 = p.x2; ny2 = p.y2-1;
            				}
        				} else {
        					if(p.x1+1>=N||p.x2+1>=N) continue;
        					if(board[p.x1+1][p.y1]==1 || board[p.x2+1][p.y2]==1) continue;
        					if(i == 6) { // 아래 오른쪽
        						nx1 = p.x1; ny1 = p.y1+1;
            					nx2 = p.x2+1; ny2 = p.y2;
        					} else { // 아래 왼쪽
        						nx1 = p.x1; ny1 = p.y1;
            					nx2 = p.x2+1; ny2 = p.y2-1;
        					}
        				}
        				d = 1;
        			} else { // 세로일때
        				if(i==4 || i==5) {
        					if(p.y1+1>=N||p.y2+1>=N) continue;
        					if(board[p.x1][p.y1+1]==1 || board[p.x2][p.y2+1]==1) continue;
        					if(i == 4) {
        						nx1 = p.x1; ny1 = p.y1;
            					nx2 = p.x2-1; ny2 = p.y2+1;
        					} else {
        						nx1 = p.x1+1; ny1 = p.y1;
            					nx2 = p.x2; ny2 = p.y2+1;
        					}
        				} else {
        					if(p.y1-1<0||p.y2-1<0) continue;
        					if(board[p.x1][p.y1-1]==1 || board[p.x2][p.y2-1]==1) continue;
        					if(i == 6) {
        						nx1 = p.x1; ny1 = p.y1-1;
            					nx2 = p.x2-1; ny2 = p.y2;
        					} else {
        						nx1 = p.x1+1; ny1 = p.y1-1;
            					nx2 = p.x2; ny2 = p.y2;
        					}
        				}
        				d = 0;
        			}
        		} else { // 이동
        			nx1 = p.x1 + dx[i]; ny1 = p.y1 + dy[i];
        			nx2 = p.x2 + dx[i]; ny2 = p.y2 + dy[i];
        			d = p.dir;
        		}
        		if(!check(nx1, ny1, nx2, ny2, N)) continue;
        		if(board[nx1][ny1]==1 || board[nx2][ny2] ==1) continue;
        		if(visited[nx1][ny1][d] && visited[nx2][ny2][d]) continue;
        		q.offer(new robot(nx1, ny1, nx2, ny2, d, p.len+1));
        		visited[nx1][ny1][d] = true;
        		visited[nx2][ny2][d] = true;
        	}
        }
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. 로봇은 총 8가지 행동을 할 수 있다. (4방향 이동, 4방향 회전)
2. 로봇을 두 부분으로 나눈다. (가로기준 왼쪽 1번, 오른쪽 2번, 세로기준 위 1번, 아래 2번)
3. 8가지 행동 시 맵밖에 벗어났는지, 벽을 만났는지 체크하면서 BFS를 돌린다.
4. 방문체크는 로봇이 가로일때 방문했는지, 세로일때 방문했는지 따로 체크하고
로봇 1번, 2번이 모두 방문했을 때만 방문했다고 한다.
5. (N, N) 좌표에는 무조건 로봇 2번만 도착할 수 있기 때문에 방문하면 거리를 체크해서
가장 짧은 거리를 출력한다.

### :octocat: 후기

여태 했던 BFS중에 가장 더럽고 지저분한 문제였다... 다풀고나서 원철이 푼거 봤는데 로봇 좌표 하나두고
방향으로 가로세로 정한거 보고 지려버렸당
