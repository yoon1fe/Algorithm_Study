package Programmers;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	static int M;
	static int N;
	
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	public boolean check(int[][] entireMap) {
		for(int i = M-1; i < M - 1 + N; i++) {
			for(int j = M-1; j < M - 1 + N; j++) {
				if(entireMap[i][j] == 0 || entireMap[i][j] == 2) return false;
			}
		}
		return true;
	}
	
	public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        int mapSize = 2*(M-1) + N;
        int[][] map = new int[mapSize][mapSize];
    	
        for(int r = 0; r < 4; r++) {
        	List<Dir> keyDir = new ArrayList<>();
            
            for(int i = 0; i < M; i++) {
            	for(int j = 0; j < M; j++) {
            		if(key[i][j] == 1) keyDir.add(new Dir(i, j));
            	}
            }
            
            
            for(int i = 0; i < N; i++) {
            	for(int j = 0; j < N; j++) {
            		map[i + M -1][j + M - 1] = lock[i][j];
            	}
            }
            
            for(int i = 0; i < M - 1 + N; i++) {
            	for(int j = 0; j < M - 1 + N; j++) {
            		for(int k = 0; k < keyDir.size(); k++) {
            			Dir cur = keyDir.get(k);
            			if(cur.y + i < M-1 || cur.y + i > M+N-2 || cur.x + j < M-1 || cur.x + j > M+N-2) continue;

            			map[cur.y + i][cur.x + j]++;
            		}
            		if(check(map)) return true;
            		
            		for(int k = 0; k < keyDir.size(); k++) {
            			Dir cur = keyDir.get(k);
            			if(map[cur.y + i][cur.x + j] == 0) continue;
            			map[cur.y + i][cur.x + j]--;
            		}
            	}
            }
            rotate(key);
        }
        return false;
    }

	private void rotate(int[][] key) {
		int[][] rotatedKey = new int[M][M];
		for(int i = 0; i < M; i++) {
			int[] hori = key[i].clone();
			for(int j = 0; j < M; j++) {
				rotatedKey[j][M - i - 1] = hori[j];
			}
		}
		for(int i = 0; i < M; i++) {
			key[i] = rotatedKey[i].clone();
		}
	}

	
	private void printMap(int[][] m) {
		for(int i = 0; i < m.length; i++) {
			for(int j = 0; j < m.length; j++) {
				System.out.print(m[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Solution sol = new Solution();

		int[][] k = {
				{0, 0, 0, 0}, 
				{1, 0, 0, 0}, 
				{1, 0, 0, 0}, 
				{0, 1, 1, 0}
				};
//		int[][] k = {
//				{0, 1, 1, 0}, 
//				{1, 0, 0, 0}, 
//				{1, 0, 0, 0}, 
//				{0, 0, 0, 0}
//		};
//		int[][] l = {
//				{1, 1, 1}, 
//				{0, 1, 1}, 
//				{1, 0, 1}};
		int[][] l = {
				{1, 1, 1}, 
				{1, 1, 0}, 
				{1, 0, 1}};
		
		System.out.println(sol.solution(k, l));
	}

}
