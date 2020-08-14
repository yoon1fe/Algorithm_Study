package com.ssafy.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class week01_주사위굴리기 {
	static int N, M, K;
	static int[][] map;
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	static Dice dice = new Dice(0,0,0,0,0,0);
	
	static class Dice {
		int U, D, F, B, L, R; // 위, 아래, 앞, 뒤, 왼, 오
		public Dice(int u, int d, int f, int b, int l, int r) {
			this.U = u; this.D = d;	this.F = f;	this.B = b;	this.L = l;	this.R = r;
		}
	}
	
	static void moveDice(int d) { // 주사위 굴리는 함수
		int temp = dice.D;
		switch(d) {
		case 1:
			dice.D = dice.R; dice.R = dice.U; dice.U = dice.L; dice.L = temp;
			break;
		case 2:
			dice.D = dice.L; dice.L = dice.U; dice.U = dice.R; dice.R = temp;
			break;
		case 3:
			dice.D = dice.B; dice.B = dice.U; dice.U = dice.F; dice.F = temp;
			break;
		case 4:
			dice.D = dice.F; dice.F = dice.U; dice.U = dice.B; dice.B = temp;
			break;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int k=0; k<K; k++) {
			int D = Integer.parseInt(st.nextToken());
			x += dx[D];
			y += dy[D];
			if(x<0||x>=N||y<0||y>=M) { // 맵 밖으로 이동하면 다시 위치 원상복귀 시키고 무시
				x -= dx[D];
				y -= dy[D];
				continue;
			}
			moveDice(D);
			if(map[x][y] == 0) map[x][y] = dice.D; // 바닥이 0 이면 주사위 바닥을 맵에 복사
			else {
				dice.D = map[x][y]; // 바닥이 0 아니면 주사위 바닥에 맵을 복사하고 맵은 0
				map[x][y] = 0;
			}
			System.out.println(dice.U);
		}
	}
}