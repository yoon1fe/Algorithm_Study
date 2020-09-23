package week07;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class week07_경사로 {
	static int findPath(int ord, int N, int L, int[][] board) {
		int answer = 0;
		loop:
		for(int i=0; i<N; i++) { // ord=1 : 가로길체크, ord=2 : 세로길체크
			int n = (ord==1?board[i][0]:board[0][i]);
			int cnt = 1;
			int j = 1;
			while(true) { // 1부터 검사
				if(j >= N) {answer++; break;} // 끝까지 검사 다했으면 지나갈 수 있음
				if(n == (ord==1?board[i][j]:board[j][i])) { // 같은 수 일 때 
					n = (ord==1?board[i][j]:board[j][i]);
					j++;
					cnt++;
					continue;
				}
				// 다른 수 일 때
				if(Math.abs(n - (ord==1?board[i][j]:board[j][i]))>1) continue loop; // 차가 1보다 큰경우 제외
				if(n < (ord==1?board[i][j]:board[j][i])) { // 올라가는 계단
					if(cnt >= L) { // 경사로 놓을 수 있을 때
						n = (ord==1?board[i][j]:board[j][i]);
						j++;
						cnt = 1;
					} else {continue loop;}
				} else if(n > (ord==1?board[i][j]:board[j][i])) { // 내려가는 계단
					n = (ord==1?board[i][j]:board[j][i]);
					j++;
					cnt = 1;
					while(true) { //경사로 놓을 수 있는지 검사
						if(cnt == L) {
							if(j >= N) {answer++; continue loop;}
							cnt = 0; 
							break;
						}
						if(j >= N) continue loop;
						if(n != (ord==1?board[i][j]:board[j][i])) continue loop;
						n = (ord==1?board[i][j]:board[j][i]);
						j++;
						cnt++;
					}
				}
			}
		}
		return answer;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int answer = 0;
		int[][] board = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}
		answer += findPath(1, N, L, board);
		answer += findPath(2, N, L, board);
		System.out.println(answer);
	}
}