package Programmers;

import java.util.*;

public class Solution {
	
	static class Dir{
		int y, x, d;
		Dir(int y, int x, int d){
			this.y = y; this.x = x; this.d = d;
		}
	}
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	static int N;
	static int[][] v;
	
	public static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < N && 0 <= c.x && c.x < N) return true;
		else return false;
	}
	
	public static int bfs(Dir start, int[][] board) {
		int answer = Integer.MAX_VALUE;
		Queue<Dir> q = new LinkedList<>();
		int[][]v = new int[N][N];

		v[0][0] = 100;
		if(start.d == 0 &&board[0][1] == 0) {           // 처음에 왼쪽으로 가는 경우
			v[0][1] = 200;
			q.offer(new Dir(0, 1, 0));
		}
		else if(start.d == 1 && board[1][0] == 0) {     // 처음에 밑으로 가는 경우
			v[1][0] = 200;
			q.offer(new Dir(1, 0, 1));
		}
        
		while(!q.isEmpty()) {
			Dir cur = q.poll();
			if(cur.y == N-1 && cur.x == N-1) {				
				answer = Math.min(v[cur.y][cur.x] - 100, answer);
			}
			
			for(int i = 0 ; i <= 3; i++) {
				if(i == 2) continue;    // 꺾는 방향 계산 (d + 1(3)) % 4;
				Dir next = null;
				
				if(i == 0) {        // 같은 방향으로 가는 경우
					next = new Dir(cur.y + dy[cur.d], cur.x + dx[cur.d], cur.d); 
					
					if(!isIn(next) || board[next.y][next.x] == 1) continue;
					if(v[next.y][next.x] == 0) v[next.y][next.x] = v[cur.y][cur.x] + 100;
					else if(v[next.y][next.x] >= v[cur.y][cur.x] + 100) v[next.y][next.x] = v[cur.y][cur.x] + 100;
					else continue;
				}else {             // 꺾는 경우
					int nd  = (cur.d + i) % 4;
					next = new Dir(cur.y + dy[nd], cur.x + dx[nd], nd); 
					
					if(!isIn(next) || board[next.y][next.x] == 1) continue;
					if(v[next.y][next.x] == 0 ) v[next.y][next.x] = v[cur.y][cur.x] + 600;
					else if(v[next.y][next.x] >= v[cur.y][cur.x] + 600) v[next.y][next.x] = v[cur.y][cur.x] + 600;
					else continue;
				}
				
				q.offer(next);
			}
		}
		return answer;
	}
	
	public static int solution(int[][] board) {
        N = board.length;
        v = new int[N][N];

        return Math.min(bfs(new Dir(0, 0, 0), board), bfs(new Dir(0, 0, 1), board));            // 오른쪽으로 가는 경우와 밑으로 가는 경우 중 최솟값
    }
	
	
	public static void main(String[] args) {
//		int [][] b = {
//				{0,0,0,0,0,0,0,1},
//				{0,0,0,0,0,0,0,0},
//				{0,0,0,0,0,1,0,0},
//				{0,0,0,0,1,0,0,0},
//				{0,0,0,1,0,0,0,1},
//				{0,0,1,0,0,0,1,0},
//				{0,1,0,0,0,1,0,0},
//				{1,0,0,0,0,0,0,0}};
		
//		int[][] b = {{0,0,0},{0,0,0},{0,0,0}};
		
//		int[][] b = {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}};

//		int[][] b =  {{0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 0}, {0, 0, 1, 0, 0, 0}, {1, 0, 0, 1, 0, 1}, {0, 1, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0}};
		
		int[][] b = {
		        {0, 0, 0, 0, 0},
		        {0, 1, 1, 1, 0},
		        {0, 0, 1, 0, 0},
		        {1, 0, 0, 0, 1},
		        {0, 1, 1, 0, 0}
		    };
		
//		int[][] b = {
//		        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//		        {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
//		        {0, 0, 1, 0, 0, 1, 1, 1, 0, 0},
//		        {1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
//		        {0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
//		        {0, 1, 1, 0, 1, 1, 1, 0, 0, 0},
//		        {0, 1, 1, 0, 1, 0, 0, 0, 1, 0},
//		        {0, 1, 1, 0, 1, 0, 0, 1, 0, 0},
//		        {0, 1, 1, 0, 0, 0, 1, 0, 0, 0},
//		        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
//		    };
		
		System.out.println(solution(b));
		
	}
}