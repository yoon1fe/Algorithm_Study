package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[][] trees;
	static int[][] map;
	static int[][] nutrient;
	static int N, M, K;
	static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	static boolean isIn(int y, int x) {
		if(1 <= y && y <= N && 1 <= x && x <= N) return true;
		else return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int answer = 0;
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		trees = new ArrayList[N+1][N+1];
		map = new int[N+1][N+1];
		nutrient = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= N; j++) {
				trees[i][j] = new ArrayList<>();
				nutrient[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			trees[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < K; i++) {
			year();
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				answer += trees[i][j].size();
			}
		}
		
		System.out.println(answer);
	}
	
	public static void year() {
		List<Integer>[][] newTrees = new ArrayList[N+1][N+1];
		
		// spring - summer
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				int dead = 0;
				newTrees[i][j] = new ArrayList<>();
				if(trees[i][j].isEmpty()) continue;
				if(trees[i][j].size() > 1) Collections.sort(trees[i][j]);
				List<Integer> temp = new ArrayList<>();
				
				for(int k = 0; k < trees[i][j].size(); k++) {
					if(map[i][j] >= trees[i][j].get(k)) {
						map[i][j] -= trees[i][j].get(k);
						temp.add(trees[i][j].get(k) + 1);
					}else dead += trees[i][j].get(k) / 2;
				}
				
				map[i][j] += dead;
				trees[i][j].clear();
				trees[i][j].addAll(temp);
			}
		}
		
		// fall
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(trees[i][j].isEmpty()) continue;
				
				for(int k = 0; k < trees[i][j].size(); k++) {
					if(trees[i][j].get(k) % 5 != 0) continue;
					
					for(int d = 0; d < 8; d++) {
						int ny = i + dy[d];
						int nx = j + dx[d];
						if(!isIn(ny, nx)) continue;
						newTrees[ny][nx].add(1);
					}
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(newTrees[i][j].isEmpty()) continue;
				trees[i][j].addAll(newTrees[i][j]);
			}
		}
		
		// winter
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(nutrient[i][j] == 0) continue;
				map[i][j] += nutrient[i][j];
			}
		}
	}
}