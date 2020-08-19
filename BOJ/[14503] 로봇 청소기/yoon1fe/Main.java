import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N, M;
	static int[][] map;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static int answer = 0;
	
	static class Robot {
		Dir pos;
		int d;
		Robot(Dir pos, int d){
			this.pos = pos; this.d = d;
		}
	}
	
	static class Dir{
		int y, x;		//r, c
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}

	public static boolean isIn(Dir c) {
		if(0<= c.y && c.y < N && 0<= c.x & c.x < M ) return true;
		else return false;
	}
	
	static int move(Robot robot) {
		boolean[][] v = new boolean[N][M];
		Queue<Robot> q = new LinkedList<>();
		q.offer(robot);
		v[robot.pos.y][robot.pos.x] = true;		//청소
		answer++;
		
		outer:
		while(!q.isEmpty()) {
			Robot c = q.poll();
			
			int nextDir = c.d;
			for(int i = 0; i < 4; i++) {
				nextDir = (nextDir + 4 - 1) % 4;
				if(v[c.pos.y + dy[nextDir]][c.pos.x + dx[nextDir]] || map[c.pos.y + dy[nextDir]][c.pos.x + dx[nextDir]] == 1) {
					continue;
				}

				answer++;
				v[c.pos.y + dy[nextDir]][c.pos.x + dx[nextDir]] = true;
				q.offer(new Robot(new Dir(c.pos.y + dy[nextDir], c.pos.x + dx[nextDir]), nextDir));
				continue outer;
			}
			
			int backDir = (nextDir + 4 - 2) % 4;
			if(map[c.pos.y + dy[backDir]][c.pos.x + dx[backDir]] != 1) {
				q.offer(new Robot(new Dir(c.pos.y + dy[backDir], c.pos.x + dx[backDir]), c.d));
			}else {
				return answer;
			}
			
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		st = new StringTokenizer(br.readLine(), " ");
		Robot robot = new Robot(new Dir(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())), Integer.parseInt(st.nextToken()));
		
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(move(robot));
		
	}
}