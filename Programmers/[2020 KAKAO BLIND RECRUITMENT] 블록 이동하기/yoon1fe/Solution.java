package Programmers;

import java.util.*;

public class Solution {
	int[] dy = {1, -1, 0, 0};
	int[] dx = {0, 0, 1, -1};
	
	static class Robot{
		int y, x, d;								// d 방향으로 한 칸 더 있음을 의미. 0: 가로, 1: 세로
		Robot(int y, int x, int d){
			this.y = y; this.x = x; this.d = d;
		}
	}
	
	public int solution(int[][] board) {
        int N = board.length;
        int answer = 0;
        Queue<Robot> q = new LinkedList<>();
        q.offer(new Robot(1, 1, 0));
        int[][][] v = new int[N+2][N+2][2];			// 가로일때, 세로일때
        v[1][1][0] = 1;
        int[][] paddingBoard = new int[N+2][N+2];	// isIn 필요없어
        
        for(int i = 0; i < N + 2; i++) {
        	for(int j = 0; j < N + 2; j++) {
        		if(1 <= i && i < 1 + N && 1 <=j && j < 1 + N) {
        			paddingBoard[i][j] = board[i-1][j-1];
        		}
        		else paddingBoard[i][j] = 1;
        	}
        }
        
        while(!q.isEmpty()) {
        	Robot cur = q.poll();
        	
        	if(cur.d == 0 && cur.y == N && cur.x == N-1) {
    			answer = v[cur.y][cur.x][cur.d] - 1; break;
    		}else if(cur.d == 1 && cur.y == N-1 && cur.x == N) {
    			answer = v[cur.y][cur.x][cur.d] - 1; break;
    		}
        	
        	for(int i = 0; i < 8; i++) {					// 4방향 이동 + 4방향 회전
        		Robot next = null;
        		if(i>3) {									// 회전하자
        			if(cur.d == 0) {						// 누워 있는 경우
       					if(i == 4) {
        					next = new Robot(cur.y, cur.x, 1);
        					if(paddingBoard[next.y+1][next.x+1] == 1 || paddingBoard[next.y+1][next.x] == 1) continue;
        				}else if(i == 5) {
        					next = new Robot(cur.y-1, cur.x, 1);
        					if(paddingBoard[next.y][next.x]== 1 || paddingBoard[next.y][next.x+1] == 1) continue;
        				}else if(i == 6) {
       						next = new Robot(cur.y, cur.x+1, 1);
       						if(paddingBoard[next.y+1][next.x-1] == 1 || paddingBoard[next.y+1][next.x] == 1) continue;
       					}else {
       						next = new Robot(cur.y-1, cur.x+1, 1);
       						if(paddingBoard[next.y][next.x-1] == 1 || paddingBoard[next.y][next.x] == 1) continue;
       					}
        			}else {									// 서있는놈
        				if(i == 4) {
        					next = new Robot(cur.y, cur.x-1, 0);
        					if(paddingBoard[next.y+1][next.x] == 1 || paddingBoard[next.y][next.x] == 1) continue;
       					}else if(i == 5) {
       						next = new Robot(cur.y, cur.x, 0);
       						if(paddingBoard[next.y+1][next.x+1]== 1 || paddingBoard[next.y][next.x + 1] == 1) continue;
       					}else if(i == 6) {
       						next = new Robot(cur.y+1, cur.x-1, 0);
       						if(paddingBoard[next.y-1][next.x] == 1 || paddingBoard[next.y][next.x] == 1) continue;
       					}else {
       						next = new Robot(cur.y+1, cur.x, 0);        						
       						if(paddingBoard[next.y-1][next.x+1] == 1 || paddingBoard[next.y][next.x+1] == 1) continue;
       					}
        			}
        		}else {
        			next = new Robot(cur.y + dy[i], cur.x + dx[i], cur.d);
        		}
        		
        		if(v[next.y][next.x][next.d] != 0 || paddingBoard[next.y][next.x] == 1) continue;
        		if(next.d == 0 && paddingBoard[next.y][next.x+1] == 1) continue;
        		if(next.d == 1 && paddingBoard[next.y + 1][next.x] == 1) continue;
        		
        		
        		q.offer(next);
        		v[next.y][next.x][next.d] = v[cur.y][cur.x][cur.d] + 1;
        	}
        }
//        for(int i = 0; i <= N+1; i++){
//        	for(int j = 0; j <= N+1; j++) {
//        		for(int k = 0; k < 2; k++) {
//        			System.out.print(v[i][j][k] + "\t");
//        		}
//        		System.out.print("   ");
//        	}
//        	System.out.println();
//        }
        return answer;
    }
	
	
	public static void main(String[] args) {
		Solution sol = new Solution();
//		int[][] b = {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}};
		
		int[][] b = {
				{0, 0, 0, 0, 0, 0, 1}, 
				{1, 1, 1, 1, 0, 0, 1}, 
				{0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 1, 1, 1, 0, 0}, 
				{0, 1, 1, 1, 1, 1, 0}, 
				{0, 0, 0, 0, 0, 1, 0}, 
				{0, 0, 1, 0, 0, 0, 0}};

		System.out.println(sol.solution(b));
	}
}
