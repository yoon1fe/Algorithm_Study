package Programmers;

import java.util.Arrays;

public class Solution {
	static int[][] map;
	static int n = 0;
	static int cnt;
	static boolean[][] cols, bos;
	public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        Solution.n = n;
        Solution.cols = new boolean[n+3][n+3];
        Solution.bos = new boolean[n+3][n+3];
        
        for(int i = 0; i < build_frame.length; i++) {
        	int x = build_frame[i][0] + 1;
        	int y = build_frame[i][1] + 1;
        	int a = build_frame[i][2];						// 0: 기둥		1: 보
        	int b = build_frame[i][3];						// 0: 삭제		1: 생성
        	
        	switch(a) {
        	case 0: colFunc(x, y, b); break;
        	case 1: boFunc(x, y, b); break;
        	}
        }

        // 리턴 배열
        answer = new int[cnt][3];
        int idx = 0;
        for(int i = 1;  i <= n + 1; i++) {
        	for(int j = 1; j <= n + 1; j++) {
        		if(cols[i][j]) answer[idx++] = new int[] {i-1, j-1, 0};
        		if(bos[i][j]) answer[idx++] = new int[] {i-1, j-1, 1};
        	}
        }
        return answer;
    }

	void boFunc(int x, int y, int b) {
		switch(b) {
		case 0:				// 삭제
			if(canRemove(x, y, 1)) {
				bos[x][y] = false;
				Solution.cnt--;
			}
			break;
		case 1:				// 생성
			if(canMakeBo(x, y)) {
				bos[x][y] = true;
				Solution.cnt++;
			}
			break;
		}
	}

	void colFunc(int x, int y, int b) {
		switch(b) {
		case 0:				// 삭제
			if(canRemove(x, y, 0)) {
				cols[x][y] = false;
				Solution.cnt--;
			}
			break;
		case 1:				// 생성
			if(canMakeCol(x, y)) {
				cols[x][y] = true;
				Solution.cnt++;
			}
			break;
		}
		
	}
	
	boolean canMakeCol(int x, int y) {
		return y == 1 || cols[x][y - 1] || bos[x][y] || bos[x-1][y];
	}
	
	boolean canMakeBo(int x, int y) {
		return cols[x][y-1] || cols[x+1][y-1] || (bos[x-1][y] && bos[x+1][y]);
	}
	
	boolean canRemove(int x, int y, int type) {
		boolean result = true;
		// type == 0 : 기둥 			type == 1 : 보
		if(type == 0) cols[x][y] = false;
		else bos[x][y] = false;
		
		outer:
		for (int i = 1; i <= n + 1; i++) {
			for (int j = 1; j <= n + 1; j++) {
				if (cols[i][j] && !canMakeCol(i, j)) {
					result = false;
					break outer;
				}
				if (bos[i][j] && !canMakeBo(i, j)) {
					result = false;
					break outer;
				}
			}
		}
		if(type == 0) cols[x][y] = true;
		else bos[x][y] = true;

		return result;
	}
	

	public static void main(String[] args) {
		Solution sol = new Solution();
//		int[][] k = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
		int[][] k = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
//		int[][] k = {{0,0,0,1}, {0,1,0,1},{0,0,1,1}};
		int[][] s = sol.solution(5, k);
		
		
		for(int i = 0; i < s.length; i++)
			System.out.println(Arrays.toString(s[i]));
	}

}
