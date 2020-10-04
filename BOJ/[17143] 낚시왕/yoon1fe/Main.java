package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static class Shark implements Comparable<Shark>{
		Dir loc;
		int size, d, s;

		Shark(int y, int x, int s, int d, int size) {
			this.loc = new Dir(y, x); this.size = size; this.d = d; this.s = s;
		}
		
		public int compareTo(Shark o) {
			if(this.loc.x == o.loc.x) return this.loc.y - o.loc.y;
			return this.loc.x - o.loc.x;
		}
	}
	
	static int R, C, M;
	static List<Shark> sharks = new ArrayList<>();
	static int[] dy = {0, -1, 1, 0, 0};
	static int[] dx = {0, 0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			sharks.add(new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(sharks);

		System.out.println(solve());
	}

	public static int solve() {
		int answer = 0;
		
		for(int fisherman = 1; fisherman <= C; fisherman++) {
			List<Shark>[][] map = new ArrayList[R+1][C+1];
			for(int i = 1; i <= R; i++) {
				for(int j = 1; j <= C; j++) {
					map[i][j] = new ArrayList<>();
				}
			}
			// 상어 잡음
			for(int i = 0; i < sharks.size(); i++) {
				if(sharks.get(i).loc.x > fisherman) break;		// 낚시꾼이 있는 열에 상어가 없는 경우
				if(sharks.get(i).loc.x == fisherman) {
					answer += sharks.get(i).size;
					sharks.remove(i); break;
				}
			}
			
			// 상어 이동(새로운 위치 구하고 map에 먼저 넣자)
			for(Shark shark : sharks) {
				int mod = 0;
				int ny = shark.loc.y, nx = shark.loc.x, nd = shark.d;
				switch(shark.d) {
				case 1: case 2: mod = shark.s % ((R - 1) * 2); break;	// 위아래 이동, R 고려
				case 3: case 4: mod = shark.s % ((C - 1) * 2);			// 좌우 이동, C 고려
				}
				
				for(int i = 0; i < mod; i++) {
					ny += dy[nd];
					nx += dx[nd];
					
					if(nx <= 0 || ny <= 0 || ny >= R+1 || nx >= C+1) {
						nd = (nd == 1 || nd == 2) ? (nd == 1 ? 2 : 1) : (nd == 3 ? 4 : 3); 
						ny += dy[nd];
						nx += dx[nd];
						i--;
					}
				}
				map[ny][nx].add(new Shark(ny, nx, shark.s, nd, shark.size));
			}
			
			// 큰놈이 작은놈 잡아먹고 난 뒤의 map으로 sharks 리스트 갱신
			sharks.clear();
			for(int i = 1; i <= R; i++) {
				for(int j = 1; j <= C; j++) {
					if(map[i][j].size() > 0) {
						Collections.sort(map[i][j], new Comparator<Shark>() {
							public int compare(Shark o1, Shark o2) {
								return o2.size - o1.size;
							}
						});
						sharks.add(map[i][j].get(0));
					}
				}
			}
			Collections.sort(sharks);
		}
		return answer;
	}
}