package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	static class Info{
		int n, dir;
		Info(int n, int dir){
			this.n = n; this.dir = dir;
		}
	}
	
	static String[] gear = new String[5];		// 톱니바퀴
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		List<Info> order = new ArrayList<>();
		int answer = 0;
		
		for(int i = 1; i <= 4; i++) {
			gear[i] = br.readLine();
		}
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			order.add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))); // 회전할 톱니 번호
		}
		
		for(Info i : order) {
			action(i);
		}
		
		for(int i = 1; i <= 4; i++) {
			answer += (gear[i].charAt(0) - '0') * Math.pow(2, i-1);
		}
		
		System.out.println(answer);
	}

	public static void action(Info start) {
		Queue<Info> q = new LinkedList<>();					// 톱니바퀴 q
		boolean[] v = new boolean[5];
		List<Info> rotateAction = new ArrayList<>();
		int[] d = {1, -1};
		
		q.offer(start);
		v[start.n] = true;
		
		while(!q.isEmpty()) {
			Info cur = q.poll();
			rotateAction.add(cur);
			
			for(int i = 0; i < 2; i++) {
				Info next = new Info(cur.n + d[i], cur.dir * -1);
				if(next.n < 1 || next.n > 4 || v[next.n]) continue;
				
				if(i == 0) {				// 오른쪽
					if(gear[cur.n].charAt(2) == gear[next.n].charAt(6)) continue;
				}else {						// 왼쪽
					if(gear[cur.n].charAt(6) == gear[next.n].charAt(2)) continue;
				}
				
				q.offer(next);
				v[next.n] = true;
			}
		}
		
		for(Info i : rotateAction) rotate(i);
	}
	
	public static void rotate(Info g) {
		StringBuilder sb = new StringBuilder();
		if(g.dir == 1) {					// 시계 방향
			sb.append(gear[g.n].charAt(7)).append(gear[g.n].substring(0, 7));
		}else {								// 반시계 방향
			sb.append(gear[g.n].substring(1, 8)).append(gear[g.n].charAt(0));
		}
		gear[g.n] = sb.toString();
	}
}
