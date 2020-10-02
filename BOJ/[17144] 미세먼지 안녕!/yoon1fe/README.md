# [17144] 미세먼지 안녕! - Java

###  :robot: 분류

> 시뮬레이션
>



### :robot: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static boolean[] selected;
	static int[][] map;
	static int R, C, T;
	static List<Dir> dust = new ArrayList<>();
	static Dir airCleaner;
	
	public static boolean isIn(Dir c) {
		if(0<= c.y && c.y < R && 0 <= c.x && c.x < C) return true;
		else return false;
	}
	
	public static int[][] spread() {
		int[][] temp = new int[R][C];
		for(Dir d : dust) {
			int origin = map[d.y][d.x]; 
			int spreadNum = (origin / 5);
			
			for(int i = 0 ; i < 4; i++) {
				Dir next = new Dir(d.y + dy[i], d.x + dx[i]);
				if(!isIn(next)) continue;
				if(next.x == 0 && (next.y == airCleaner.y || next.y == airCleaner.y-1)) continue;
				temp[next.y][next.x] +=  spreadNum;
				temp[d.y][d.x] -= spreadNum;
			}
			temp[d.y][d.x] += origin; 
		}
		return temp;
	}
	
	public static int[][] clean(int[][] temp) {
		for(int i = airCleaner.y-2; i > 0; i--) temp[i][0] = temp[i-1][0];
		for(int i = 0; i < C - 1; i++) temp[0][i] = temp[0][i+1];
		for(int i = 0; i < airCleaner.y-1; i++) temp[i][C-1] = temp[i+1][C-1];
		for(int i = C-1; i > 1; i--) temp[airCleaner.y-1][i] = temp[airCleaner.y-1][i-1];
		temp[airCleaner.y-1][1] = 0;
		
		for(int i = airCleaner.y+1; i < R - 1; i++) temp[i][0] = temp[i+1][0];
		for(int i = 0; i < C - 1; i++) temp[R-1][i] = temp[R-1][i+1];
		for(int i = R-1; i > airCleaner.y; i--) temp[i][C-1] = temp[i-1][C-1];
		for(int i = C-1; i > 1; i--) temp[airCleaner.y][i] = temp[airCleaner.y][i-1];
		temp[airCleaner.y][1] = 0;
		
		return temp;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0) {
					dust.add(new Dir(i, j));
				}
				if(map[i][j] == -1) airCleaner = new Dir(i, j);			//[i-1][j], [i][j]
			}
		}
		
		for(int time = 0; time < T; time++) {
			// 1: 미세먼지 확산 후 청소
			map = clean(spread());
			dust.clear();
			// 2: dust, map 업데이트
			for(int i = 0 ; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(map[i][j] > 0) dust.add(new Dir(i, j));
				}
			}
		}
		
		int answer = 0;
		for(Dir d : dust) answer+= map[d.y][d.x];
		System.out.println(answer);
	}
}
```



### :robot: 풀이 방법

삼성 SW 역량 테스트 기출문제입니다.

특별한 알고리즘을 요하지 않는 단순 시뮬레이션 문제입니다.



1초 동안 다음과 같은 일이 일어납니다.

1. 미세먼지 확산
2. 공기청정기 동작



먼저 미세먼지 확산을 구현한 spread() 메소드입니다.

map에 있는 값을 곧바로 갱신하면 값들이 엉망이 되기 때문에 temp 배열을 두고 여기다 일단 넣어줍니다.

기존 미세먼지 위치에서 네 방향을 보면서 각 방향으로 퍼질 수 있는지 체크한 후 미리 계산해준 퍼지는 양으로 갱신하고, 기존 위치에서는 그만큼 빼줍니다.

다 끝나면 기존 위치에 있던 양만큼 더해주면 됩니다.

 

미세먼지 확산을 끝낸 뒤 새로 temp를 리턴해 줍니다.

 

다음으로는 공기청정기 작동 clean() 메소드입니다.

아까 미세먼지가 퍼지고 난 뒤의 temp 배열을 파라미터로 받아서 문제에서 요구하는 대로 쭉쭉 밀고 고 친구를 다시 리턴해줬습니다.

 

이 동작을 t만큼 해주면 되는 것이져.



### :robot: 후기

화팅~!~!