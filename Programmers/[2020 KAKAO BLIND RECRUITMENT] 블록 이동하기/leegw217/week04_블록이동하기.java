package week04;
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