package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class week14_합이0인네정수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		long ans = 0;
		int N = Integer.parseInt(br.readLine());
		int[][] Arr = new int[4][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) Arr[j][i] = Integer.parseInt(st.nextToken());
		}
		int[] AB = new int[N*N];
		int[] CD = new int[N*N];
		int idx = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				AB[idx] = Arr[0][i] + Arr[1][j];
				CD[idx++] = Arr[2][i] + Arr[3][j];
			}
		}
		Arrays.sort(AB);
		Arrays.sort(CD);
		int abIdx = 0;
		int cdIdx = (N*N)-1;
		while(abIdx < (N*N) && cdIdx >= 0) {
			int sum = AB[abIdx] + CD[cdIdx];
			if(sum < 0) abIdx++;
			else if(sum > 0) cdIdx--;
			else {
				int ABsum = AB[abIdx];
				int CDsum = CD[cdIdx];
				int ABcnt = 0;
				int CDcnt = 0;
				while(abIdx<(N*N) && ABsum == AB[abIdx]) {
					abIdx++;
					ABcnt++;
				}
				while(cdIdx>=0 && CDsum == CD[cdIdx]) {
					cdIdx--;
					CDcnt++;
				}
				ans += (long)ABcnt*(long)CDcnt;
			}
		}
		System.out.println(ans);
	}
}