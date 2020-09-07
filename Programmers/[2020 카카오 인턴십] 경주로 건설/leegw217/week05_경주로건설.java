package week05;

import java.util.LinkedList;
import java.util.Queue;
public class week05_경주로건설 {
	class Point{
		int x, y; // 좌표
		int dir; // 이전 움직인 방향
		int cost; // 비용
		Point(int x, int y, int dir, int cost){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cost = cost;
		}
	}
	
	public int solution(int[][] board) {
		int answer = Integer.MAX_VALUE;
        int N = board[0].length;
    	int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int[][] visited = new int[N][N];
        Queue<Point> q = new LinkedList<Point>();
        q.offer(new Point(0,0,-1,0));
        visited[0][0] = Integer.MIN_VALUE;
        
        while(!q.isEmpty()) {
        	Point p = q.poll();
        	if(p.x==N-1 && p.y==N-1) // 최종지점에 도착했을 때 비용 최솟값
        		if(answer > p.cost) answer = p.cost;
        	
        	for(int dir=0; dir<4; dir++) {
    			int nx = p.x + dx[dir];
    			int ny = p.y + dy[dir];
    			
    			if(nx<0||nx>=N||ny<0||ny>=N) continue;
        		if(board[nx][ny]==1) continue;
        		
        		int cost = 0;
        		if(p.dir == -1 || p.dir == dir) // 첫번째 이동이거나 같은 방향이면
        			cost = p.cost + 100; // 같은방향 : 직선
        		else cost = p.cost + 600; // 다른방향 : 코너+직선
        		
        		if(visited[nx][ny]!=0 && visited[nx][ny]<cost) continue;
        		visited[nx][ny] = cost;
    			q.offer(new Point(nx, ny, dir, cost));
    		}
        }
        return answer;
    }
}