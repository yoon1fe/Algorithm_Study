package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	static boolean[] selected;
	static int[][] S;
	static int N;
	static int answer = Integer.MAX_VALUE;
	
	public static int getAbility() {
		List<Integer> start = new ArrayList<>();
		List<Integer> link = new ArrayList<>();
		int startSum = 0, linkSum = 0;
		for(int i = 0; i < N; i++) {
			if(selected[i]) start.add(i);
			else link.add(i);
		}
		for(int i = 0; i < start.size(); i++) {
			for(int j = i+1; j < start.size(); j++) {
				startSum += S[start.get(i)][start.get(j)] + S[start.get(j)][start.get(i)];
				linkSum += S[link.get(i)][link.get(j)] + S[link.get(j)][link.get(i)];
			}
		}
		return Math.abs(startSum-linkSum);
	}
	
	public static void comb(int cnt, int idx) {
		if(cnt == N / 2) {
			answer = Math.min(answer, getAbility());
			return;
		}
		
		for(int i = idx; i < N; i++) {
			selected[i] = true;
			comb(cnt+1, i+1);
			selected[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		selected = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0, 0);
		System.out.println(answer);
	}
}