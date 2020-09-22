package week07;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class week07_스타트와링크 {
	static int N;
	static int[][] S;
	static int[] result;
	static List<Integer> arr;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	
	static void makeComb(int begin, int cnt) {
		if (cnt == result.length) {
			List<Integer> p = new ArrayList<Integer>();
			for(int t : arr) p.add(t);
			for(int i=0; i<N/2; i++) p.remove(result[i]-i);
			int sum1 = 0, sum2 = 0;
			for(int i=0; i<N/2-1; i++) {
				for(int j=i+1; j<N/2; j++) {
					sum1 += S[result[i]][result[j]] + S[result[j]][result[i]];
					sum2 += S[p.get(i)][p.get(j)] + S[p.get(j)][p.get(i)];
				}
			}
			int num = Math.abs(sum1-sum2);
			if(min > num) min = num;
			return;
		}
		
		for (int i = begin; i < arr.size(); i++) {
			result[cnt] = arr.get(i);
			makeComb(i + 1, cnt + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				S[i][j] = Integer.parseInt(st.nextToken());
		}
		arr = new ArrayList<Integer>();
		result = new int[N/2];
		visited = new boolean[N];
		for(int i=0; i<N; i++) arr.add(i);
		makeComb(0, 0);
		System.out.println(min);
	}
}