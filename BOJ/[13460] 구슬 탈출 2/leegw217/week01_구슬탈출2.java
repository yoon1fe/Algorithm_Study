import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class week01_구슬탈출2 {
	static int N, M;
	static char[][] map;
	static Point RED;
	static Point BLUE;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean rFlag;
	static boolean bFlag;
	
	static class Point{
		int x, y;
		int cnt = 0;
		
		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		ArrayList<Point> redSet = new ArrayList<Point>();
		ArrayList<Point> blueSet = new ArrayList<Point>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			map[i] = s.toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'R') RED = new Point(i, j, 1);
				if(map[i][j] == 'B') BLUE = new Point(i, j, 1);
			}
		}
		redSet.add(RED);
		blueSet.add(BLUE);
		
		Queue<Point> q = new LinkedList<Point>();
		q.offer(RED);
		q.offer(BLUE);
		
		while(!q.isEmpty()) {
			Point r = q.poll();
			Point b = q.poll();
			
			if(r.cnt >= 10) break;
			
			loop:
			for(int d=0; d<4; d++) {
				rFlag = false;
				bFlag = false;
				int Rnx = r.x;
				int Rny = r.y;
				int Bnx = b.x;
				int Bny = b.y;

				if(map[Rnx+dx[d]][Rny+dy[d]] == '#' && map[Bnx+dx[d]][Bny+dy[d]] == '#') continue;
				
				if(map[Rnx+dx[d]][Rny+dy[d]] != '#') { // 빨간공 움직이기
					while(true) {
						if(map[Rnx+dx[d]][Rny+dy[d]] == '#') break;
						if(map[Rnx+dx[d]][Rny+dy[d]] == 'O') rFlag = true;
						Rnx += dx[d];
						Rny += dy[d];
					}
				}
				
				if(map[Bnx+dx[d]][Bny+dy[d]] != '#') { // 파란공 움직이기
					while(true) {
						if(map[Bnx+dx[d]][Bny+dy[d]] == '#') break;
						if(map[Bnx+dx[d]][Bny+dy[d]] == 'O') bFlag = true;
						Bnx += dx[d];
						Bny += dy[d];
					}
				}
				
				if(bFlag) continue loop; // 파란공이 구멍에 들어가면 실패
				
				if(rFlag && !bFlag) { // 빨간공만 구멍에 들어가면 성공
					System.out.println(r.cnt);
					System.exit(0);
				}
				
				if(Rnx==Bnx && Rny==Bny) { // 겹쳤을 때 공들 위치 조정
					switch(d) {
					case 0: // 상
						if(r.x < b.x) Bnx++;
						else Rnx++;
						break;
					case 1: // 우
						if(r.y < b.y) Rny--;
						else Bny--;
						break;
					case 2: // 하
						if(r.x < b.x) Rnx--;
						else Bnx--;
						break;
					case 3: // 좌
						if(r.y < b.y) Bny++;
						else Rny++;
						break;
					}
				}
				
				for(int i=0; i<redSet.size(); i++) { // 이동한 위치가 이미 방문했던 곳이면 제외
					if(redSet.get(i).x == Rnx && redSet.get(i).y == Rny &&
							blueSet.get(i).x == Bnx && blueSet.get(i).y == Bny)
						continue loop;
				}
				
				redSet.add(new Point(Rnx, Rny, r.cnt+1)); // 빨간공 방문여부 리스트
				blueSet.add(new Point(Bnx, Bny, b.cnt+1)); // 파란공 방문여부 리스트
				q.offer(new Point(Rnx, Rny, r.cnt+1));
				q.offer(new Point(Bnx, Bny, b.cnt+1));
			}
		}
		System.out.println(-1);
	}
}