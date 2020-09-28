package week08;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class week08_드래곤커브 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] map = new int[101][101];
		int N = sc.nextInt();
		List<Integer>[] dCurve = new ArrayList[N+1];
		int[] dy = {0, -1, 0, 1};
		int[] dx = {1, 0, -1, 0};
		int answer = 0;
		for(int i=1; i<=N; i++) {
			dCurve[i] = new ArrayList<Integer>();
			int x = sc.nextInt();
			int y = sc.nextInt();
			int dir = sc.nextInt();
			int gener = sc.nextInt();
			dCurve[i].add(dir);
			for(int j=0; j<gener; j++) {
				int limit = dCurve[i].size()-1;
				for(int l=limit; l>=0; l--) {
					int g = dCurve[i].get(l);
					if(g + 1 == 4) dCurve[i].add(0);
					else dCurve[i].add(g + 1);
				}
			}
			map[y][x] = i;
			for(int j=0; j<dCurve[i].size(); j++) {
				y += dy[dCurve[i].get(j)];
				x += dx[dCurve[i].get(j)];
				map[y][x] = i;
			}
		}
		
		for(int i=0; i<100; i++) 
			for(int j=0; j<100; j++) 
				if(map[i][j] != 0) 
					if(map[i][j+1] != 0) 
						if(map[i+1][j+1] != 0) 
							if(map[i+1][j] != 0) 
								answer += 1;
		System.out.println(answer);
	}
}
