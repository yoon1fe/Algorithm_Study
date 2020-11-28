import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1799 {
	public static int N;
	public static int ans;
	public static int[][] board;
	public static boolean[] leftDownToRightUp;
	public static boolean[] leftUpToRightDown;
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		solution(0, 0, 0);
		int ret = ans;
		ans = 0;
		solution(0, 1, 0);
		System.out.println(ret + ans);
	}
	
	public static void solution(int row, int col, int cnt) {
		if(col >= N) {
			row += 1;
			if(col%2 == 0) col = 1;
			else col = 0;
		}
		
		if(row >= N) {
			ans = ans < cnt ? cnt : ans;
			return;
		}
		
		if(board[row][col] == 1 && !leftDownToRightUp[row+col] && !leftUpToRightDown[col-row+N-1]) {
			leftDownToRightUp[row+col] = true;
			leftUpToRightDown[col-row+N-1] = true;
			solution(row, col + 2, cnt+1);
			leftDownToRightUp[row+col] = false;
			leftUpToRightDown[col-row+N-1] = false;
		}
		solution(row, col+2, cnt);
		
	}
	
	
	public static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		leftDownToRightUp = new boolean[N*2];
		leftUpToRightDown = new boolean[N*2];
		ans = 0;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}
	}
}