package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	static class Shark{
		int y, x, no, dir;
		Shark(int y, int x, int no, int dir){
			this.y = y; this.x = x; this.no = no; this.dir = dir;
		}
	}
	
	static int N, M, K;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	static Map<Integer, Integer>[][] smellMap;				// 상어 번호, 냄새가 사라질 때까지 남은 턴
	static Map<Integer, Shark> sharks = new TreeMap<>();	// 상어 번호, 상어
	static List<Integer>[][] map;
	static int[][][] priority;
	
	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solve());
	}

	public static int solve() {
		
		for(int answer = 0; answer <= 1000; answer++) {
			if(sharks.size() == 1) return answer;
			
			// 냄새
			for(int no : sharks.keySet()) {
				int cy = sharks.get(no).y;
				int cx = sharks.get(no).x;
				
				smellMap[cy][cx].put(no, K);
			}
			
			// 상어 이동
			for(int no : sharks.keySet()) {
				int cy = sharks.get(no).y;
				int cx = sharks.get(no).x;
				int cd = sharks.get(no).dir;
				
				int nd = priority[no][cd][0];
				int ny = cy + dy[nd];
				int nx = cx + dx[nd];
				
				boolean isBlank = false;		// 한 군데라도 이동할 빈 칸이 있으면 true
				for(int i = 0; i < 4; i++) {
					nd = priority[no][cd][i];
					ny = cy + dy[nd]; nx = cx + dx[nd];
					if(!isIn(ny, nx)) continue;
					if(smellMap[ny][nx].size() == 0) {
						isBlank = true; break;
					}
				}
				
				if(isBlank) {			// 인접한 곳 중에서 이동할 곳이 있는 경우
					// 상어 이동
					map[ny][nx].add(no);
					map[cy][cx].remove((Integer)no);
					
					sharks.replace(no, new Shark(ny, nx, no, nd));

				}else {
					// 자기 냄새 찾기
					boolean isMySmell = false;
					for(int i = 0; i < 4; i++) {
						nd = priority[no][cd][i];
						ny = cy + dy[nd]; nx = cx + dx[nd];
						if(!isIn(ny, nx)) continue;
						if(smellMap[ny][nx].containsKey(no)) {
							isMySmell = true; break;
						}
					}
					
					if(isMySmell) {			// 내 냄새 있으면
						map[ny][nx].add(no);
						map[cy][cx].remove((Integer)no);
						
						sharks.replace(no, new Shark(ny, nx, no, nd));
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					// 냄새 남은 턴 줄이기
					if(smellMap[i][j].size() != 0) {
						for(int no : smellMap[i][j].keySet()) {
							if(smellMap[i][j].get(no) == 1) smellMap[i][j].remove(no);
							else smellMap[i][j].replace(no, smellMap[i][j].get(no) - 1);
						}
					}
					
					// 상어 죽이기
					if(map[i][j].size() != 0) {
						Collections.sort(map[i][j]);
						for(int k = 1; k < map[i][j].size(); k++) {
							sharks.remove(map[i][j].get(k));
						}
						int alive = map[i][j].get(0);
						map[i][j].clear();
						map[i][j].add(alive);
					}
				}
			}
		}
		
		return -1;
	}
	
	public static boolean isIn(int y, int x) {
		if(0 <= y && y < N && 0 <= x && x < N) return true;
		else return false;
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
		smellMap = new Map[N][N];
		map = new List[N][N];
		priority = new int[M + 1][4][4];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				smellMap[i][j] = new HashMap<>();
				map[i][j] = new ArrayList<>();
				int no = Integer.parseInt(st.nextToken());
				if(no == 0) continue;
				map[i][j].add(no);
				if(map[i][j].get(0) == 0) continue;
				sharks.put(map[i][j].get(0), new Shark(i, j, map[i][j].get(0), 0));
				smellMap[i][j].put(no, K);
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= M; i++) {
			sharks.replace(i, new Shark(sharks.get(i).y, sharks.get(i).x, sharks.get(i).no, Integer.parseInt(st.nextToken()) - 1));
		}
		
		for(int i = 1; i <= M; i++) {
			for(int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int k = 0; k < 4; k++)
					priority[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
	}
}