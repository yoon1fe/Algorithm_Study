import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static int[][] hits;
	static int[] order;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		
		input();
		// 순열로 타순 만들기
		perm(1);
		
		System.out.println(answer);
		
	}
	
	public static void perm(int cnt) {
		if(cnt == 10) {
			solve();
			return;
		}
		
		for(int i = 2; i <= 9; i++) {
			if(cnt == 4) {
				cnt++;	i--; continue;
			}
			if(isSelected[i]) continue;
			isSelected[i] = true;
			order[cnt] = i;
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}
	
	public static void solve() {
		int score = 0, startNo = 1;
		for(int i = 0; i < N; i++) {		// 0회 ~ N-1회까지 경기
			int outCnt = 0;
			boolean[] base = new boolean[4];	// base[0]: 홈,  1: 1루, 2: 2루, 3: 3루 
		
			int cur = startNo;
			while(outCnt != 3) {			// 3아웃이면 한 이닝 끝
				switch(hits[i][order[cur]]) {
				case 0: outCnt++; break;
				case 1:
					if(base[3]) score++;
					base[3] = base[2] ? true : false;
					base[2] = base[1] ? true : false;
					base[1] = true;
					break;
				case 2:
					if(base[3]) score++;
					if(base[2]) score++;
					base[3] = base[1] ? true : false;
					base[2] = true;
					base[1] = false;
					break;
				case 3:
					for(int b = 1; b <= 3; b++) {
						if(base[b]) score++;
						base[b] = false;
					}
					base[3] = true;
					break;
				case 4:
					for(int b = 1; b <= 3; b++) {
						if(base[b]) score++;
						base[b] = false;
					}
					score++;
					break;
				}
				
				cur = (cur + 1) % 10;
				if(cur == 0) cur = 1;
			
			}
			startNo = cur;
			
		}
		
		answer = Math.max(answer, score);
	}	

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); 
		
		hits = new int[N][10];
		order = new int[10];
		isSelected = new boolean[10];
		order[4] = 1;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 1; j <= 9; j++) hits[i][j] = Integer.parseInt(st.nextToken());
		}
		
	}
}