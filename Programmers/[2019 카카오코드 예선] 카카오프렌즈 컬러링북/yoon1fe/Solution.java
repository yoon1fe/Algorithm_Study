package Programmers;

import java.util.*;

class Solution {
	static class Dir {
		int y, x;

		Dir(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	int[] dy = { 1, -1, 0, 0 };
	int[] dx = { 0, 0, 1, -1 };
	boolean[][] v;
	int[][] p;
	int M, N;

	public int[] solution(int m, int n, int[][] picture) {
		M = m; N = n;
		p = picture;
		v = new boolean[m][n];
		Set<Integer> map = new HashSet<>();
		int[] answer = new int[2];

        for(int i = 0; i < m; i++) {
        	for(int j = 0; j < n; j++) {
        		if(v[i][j] == true) continue;
        		int color = p[i][j];
        		
        		if(color != 0) {
        			answer[0]++;
        			answer[1] = Math.max(answer[1], bfs(new Dir(i, j), color));
        		}
        	}
        }
        
        return answer;
    }

	public int bfs(Dir start, int color) {
		Queue<Dir> q = new LinkedList<>();
		int cnt = 1;

		q.offer(start);
		v[start.y][start.x] = true;

		while (!q.isEmpty()) {
			Dir cur = q.poll();

			for (int i = 0; i < 4; i++) {
				Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
				if (!isIn(next) || p[next.y][next.x] != color || v[next.y][next.x] == true) continue;
				
				v[next.y][next.x] = true;
				q.offer(next);
				cnt++;
			}
		}

		return cnt;
	}

	public boolean isIn(Dir c) {
		if (0 <= c.y && c.y < M && 0 <= c.x && c.x < N) return true;
		else return false;
	}

	public static void main(String[] args) {
		Solution s = new Solution();

		System.out.println(Arrays.toString(s.solution(6, 4, new int[][] {
			{1, 1, 1, 0}, 
			{1, 2, 2, 0}, 
			{1, 0, 0, 1}, 
			{0, 0, 0, 1}, 
			{0, 0, 0, 3}, 
			{0, 0, 0, 3}})));
		System.out.println(Arrays.toString(s.solution(6, 4, new int[][] { { 1, 1, 1, 0 }, { 1, 1, 1, 0 },
				{ 0, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 } })));
	}
}