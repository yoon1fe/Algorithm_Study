import java.io.*;
import java.util.*;

public class Main {
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static char[][] seat = new char[5][5];
	static Map<Integer, Dir> map = new HashMap<>();					// (번호 : 좌표) 값이 저장된 map
	static List<Integer> list = new ArrayList<>();
	static int[] dy = {1, -1, 0 ,0};
	static int[] dx = {0, 0, 1, -1};
	static int answer, S, Y, total;
	
	public static void main(String[] args) throws Exception {
		input();
		// 25개 중에서 7개 뽑기 - 조합
		comb(0);
		System.out.println(total);
	}
	
	public static void comb(int idx) {
		// 다솜파가 적어도 네 명 이상 있어야 하므로 가지치기
		if(Y == 4) return;
		
		if(list.size() == 7) {
			// 선택된 자리가 인접해있는지 체크
			total++;
//			answer = (bfs() == true ? answer + 1 : answer);
			return;
		}
		
		for(int i = idx; i < 25; i++) {
			Dir next = map.get(i);
			
			// 다솜파와 도연파 수 구하기
			S = seat[next.y][next.x] == 'S' ? S + 1 : S;
			Y = seat[next.y][next.x] == 'Y' ? Y + 1 : Y;
			list.add(i);
			
			comb(i + 1);
			
			// 백트래킹
			list.remove((Integer)i);
			S = seat[next.y][next.x] == 'S' ? S - 1 : S;
			Y = seat[next.y][next.x] == 'Y' ? Y - 1 : Y;
		}
	}
	
	public static boolean bfs() {
		Queue<Dir> q = new LinkedList<>();
		boolean[][] v = new boolean[5][5];
		boolean[][] m = new boolean[5][5];
		int cnt = 1;
		
		// map 에 표시
		for(int i : list) {
			Dir c = map.get(i);
			m[c.y][c.x] = true; 
		}
		
		q.offer(map.get(list.get(0)));
		v[map.get(list.get(0)).y][map.get(list.get(0)).x] = true;
		
		// BFS로 인접한 애들 확인
		while(!q.isEmpty()) {
			Dir c = q.poll();
			
			for(int i = 0; i < 4; i++) {
				Dir next = new Dir(c.y + dy[i], c.x + dx[i]);
				if(!isIn(next) || v[next.y][next.x] || m[next.y][next.x] == false) continue;
				
				v[next.y][next.x] = true; 
				cnt++;
				q.offer(next);
			}
		}
		// 7개인 경우가 모두 인접한 경우
		return cnt == 7 ? true : false;
	}
	
	public static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < 5 && 0 <= c.x && c.x < 5) return true;
		else return false;
	}
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 5; i++) {
			seat[i] = br.readLine().toCharArray();
		}
		
		int idx = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				map.put(idx++, new Dir(i, j));
			}
		}
	}
}