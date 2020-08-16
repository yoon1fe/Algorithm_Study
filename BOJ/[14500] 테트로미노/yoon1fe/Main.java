import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Dir {
		int y, x;

		Dir(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static Dir[] polyomino = new Dir[4];
	static int N, M;
	static int[][] map;
	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };
	static int answer = 0;

	static boolean isIn(Dir c) {
		if (0 <= c.y && c.y < N && 0 <= c.x && c.x < M) return true;
		else return false;
	}

	static int calc() { // 네 개 만든 폴리오미노 계산
		int sum = 0;
		for (Dir p : polyomino)
			sum += map[p.y][p.x];
		return sum;
	}

	static void dfs(Dir c, int cnt) {
		if (cnt == 4) {
			answer = Math.max(answer, calc());
			return;
		}

		polyomino[cnt] = c;
		outer:
		for (int i = 0; i < 4; i++) {
			Dir next = new Dir(c.y + dy[i], c.x + dx[i]);
			if (!isIn(next)) continue;
			for(int j = 0; j < cnt; j++) {
				if(next.y == polyomino[j].y && next.x == polyomino[j].x) continue outer;
			}

			dfs(next, cnt + 1);
		}
	}

	static void makeSpecialCase(Dir c) {
		polyomino[0] = c;
		boolean flag = false;
		boolean isMade = true;
		Dir temp = c;
		for (int i = 1; i < 3; i++) { // 가로
			Dir next = new Dir(temp.y + dy[2], temp.x + dx[2]);
			if (!isIn(next)) {
				flag = true; break; // 폴리오미노 생성 실패
			}
			polyomino[i] = next;
			temp = next;
		}
		if (!flag) {
			Dir up = new Dir(c.y - 1, c.x + 1);
			Dir down = new Dir(c.y + 1, c.x + 1);
			if (isIn(up) && isIn(down)) polyomino[3] = (map[up.y][up.x] > map[down.y][down.x] ? up : down);
			else if (isIn(up)) polyomino[3] = up;
			else if (isIn(down)) polyomino[3] = down;
			else isMade = false;		//둘다 해당 안되는 경우
		}
		if (isMade)
			answer = Math.max(answer, calc());

		flag = false;
		temp = c;
		for (int i = 1; i < 3; i++) { // 세로
			Dir next = new Dir(temp.y + dy[0], temp.x + dx[0]);
			if (!isIn(next)) {
				flag = true; break; // 폴리오미노 생성 실패
			}
			polyomino[i] = next;
			temp = next;
		}
		if (!flag) {
			Dir right = new Dir(c.y + 1, c.x + 1);
			Dir left = new Dir(c.y + 1, c.x - 1);
			if (isIn(right) && isIn(left)) polyomino[3] = (map[right.y][right.x] > map[left.y][left.x] ? right : left);
			else if (isIn(right)) polyomino[3] = right;
			else if (isIn(left)) polyomino[3] = left;
			else isMade = false;		//둘다 해당 안되는 경우
		}
		if (isMade)
			answer = Math.max(answer, calc());
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				//boolean[][] v = new boolean[N][M];
				dfs(new Dir(i, j), 0);
				makeSpecialCase(new Dir(i, j));
			}
		}
		System.out.println(answer);
	}
}