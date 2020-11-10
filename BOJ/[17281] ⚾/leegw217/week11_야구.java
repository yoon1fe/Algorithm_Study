import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class week11_야구 {
	static int N;
	static int[] arr = new int[9];
	static int[] result = new int[9];
	static boolean[] visited = new boolean[9];
	static int[][] player;
	static int answer = Integer.MIN_VALUE;
	
	static void makePerm(int cnt) { // 순열 만들기
		if(cnt == result.length) {
			playGame();
			return;
		}
		
		for(int i=1; i<arr.length; i++) {
			if(cnt == 3) { // 4번타자는 1번으로 고정
				cnt++; i--; continue;
			}
			if(!visited[i]) {
				result[cnt] = arr[i];
				visited[i] = true;
				makePerm(cnt+1);
				visited[i] = false;
			}
		}
	}
	
	static void playGame() {
		int start = 0;
		int score = 0;
		for(int i=0; i<N; i++) { // 이닝
			boolean[] base = new boolean[4];
			int out = 0;
			int idx = start;
			while(true) {
				if(out == 3) break;
				int n = player[i][result[idx]];
				if(n == 0) { // 아웃
					out++;
				} else if(n == 1) { // 안타
					if(base[3]) score++;
					base[3] = base[2]?true:false;
					base[2] = base[1]?true:false;
					base[1] = true;
				} else if(n == 2) { // 2루타
					if(base[3]) score++;
					if(base[2]) score++;
					base[3] = base[1]?true:false;
					base[2] = true;
					base[1] = false;
				} else if(n == 3) { // 3루타
					if(base[3]) score++;
					if(base[2]) score++;
					if(base[1]) score++;
					base[3] = true;
					base[2] = false;
					base[1] = false;
				} else { // 홈런
					for(int b=3; b>=1; b--) {
						if(base[b]) score++;
						base[b] = false;
					}
					score++;
				}
				idx++;
				idx = idx%9;
			}
			start = idx;
		}
		answer = Math.max(answer, score);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		player = new int[N][9];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) 
				player[i][j] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<9; i++) arr[i] = i;
		result[3] = 0;
		makePerm(0);
		System.out.println(answer);
	}
}s