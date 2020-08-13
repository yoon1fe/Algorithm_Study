import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Dir{
	int y, x;
	Dir(int y, int x){
		this.y = y; this.x = x;
	}
}

public class Main{
	static int N, M, x, y, K;
	static Dir cur;
	static int[][] map;
	static int[] order;
	static int[] dy = {0, 0, 0, -1, 1};
	static int[] dx = {0, 1, -1, 0, 0};
	static int[] dice = {0, 0, 0, 0, 0, 0, 0};
		
	static boolean isIn(Dir cur) {
		if(0<= cur.y && cur.y < N && 0<=cur.x && cur.x < M) return true;
		else return false;
	}	

	private static void rollSouth(Dir next) {
		int temp = dice[1];
		dice[1] = dice[2]; dice[2] = dice[6]; dice[6] = dice[5]; dice[5] = temp;
		action(next);
	}

	private static void rollNorth(Dir next) {
		int temp = dice[1];
		dice[1] = dice[5]; dice[5] = dice[6]; dice[6] = dice[2]; dice[2] = temp;
		action(next);
	}

	private static void rollWest(Dir next) {
		int temp = dice[1];
		dice[1] = dice[3]; dice[3] = dice[6]; dice[6] = dice[4]; dice[4] = temp;
		action(next);
	}

	private static void rollEast(Dir next) {
		int temp = dice[1];
		dice[1] = dice[4]; dice[4] = dice[6]; dice[6] = dice[3]; dice[3] = temp;
		action(next);
	}
	
	private static void action(Dir next) {
		if(map[next.y][next.x] == 0 ) {
			map[next.y][next.x] = dice[6];
		}else {
			dice[6] = map[next.y][next.x];
			map[next.y][next.x] = 0; 
		}
		cur = next;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken()); x = Integer.parseInt(st.nextToken());
		cur = new Dir(y, x);
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		order = new int[K];
		
		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j< M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < K; i++) {
			int d = Integer.parseInt(st.nextToken());
			Dir next = new Dir(cur.y + dy[d], cur.x + dx[d]);
			if(!isIn(next)) continue;		// 바깥으로 이동시키려 하는 경우
			
			switch(d) {
			case 1: rollEast(next); break;
			case 2: rollWest(next); break;
			case 3: rollNorth(next); break;
			case 4: rollSouth(next); break;
			}
			System.out.println(dice[1]);	// 주사위 윗 면에 쓰여 있는 수 출력
		}		
	}
}