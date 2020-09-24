# [15683] 감시 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
public class week07_감시 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static int R, C;
	static List<CCTV> cctvList;
	static int cctvCnt;
	static int min = Integer.MAX_VALUE;
	
	static class CCTV{
		int type;
		int x, y;
		int dir;
		CCTV(int type, int x, int y, int dir){
			this.type = type;
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	static void makeDir(int cnt) { // cctv 방향 순열 구하기
		if(cnt == cctvCnt) {
			int result = findSafe();
			if(min > result) min = result;
			return;
		}
		int limit = cctvList.get(cnt).type == 2?2:4;
		
		if(cctvList.get(cnt).type == 5) 
			makeDir(cnt+1);
		else {
			for(int i=0; i<limit; i++) {
				cctvList.get(cnt).dir = i;
				makeDir(cnt+1);
			}
		}
	}
	
	static int findSafe() {
		int answer = 0;
		int[][] temp = new int[R][C];
		for(int i=0; i<R; i++) System.arraycopy(map[i], 0, temp[i], 0, C);
		for(int i=0; i<cctvCnt; i++) {
			CCTV cctv = cctvList.get(i);
			int nx = cctv.x;
			int ny = cctv.y;
			int dir = cctv.dir;
			int type = cctv.type;
			drawLine(temp, nx, ny, dir);
			if(type == 2 || type == 5) { // 반대 방향 선긋기
				nx = cctv.x; ny = cctv.y;
				dir = cctv.dir;
				if(dir == 0) dir = 2;
				else dir = 3;
				drawLine(temp, nx, ny, dir);
			}
			if(type == 3 || type == 4 || type == 5) { // 오른쪽 방향 선긋기
				nx = cctv.x; ny = cctv.y;
				dir = cctv.dir;
				dir += 1;
				if(dir == 4) dir = 0;
				drawLine(temp, nx, ny, dir);
			}
			if(type == 4 || type == 5) { // 왼쪽 방향 선긋기
				nx = cctv.x; ny = cctv.y;
				dir = cctv.dir;
				dir -= 1;
				if(dir == -1) dir = 3;
				drawLine(temp, nx, ny, dir);
			}
		}
		for(int i=0; i<R; i++)
			for(int j=0; j<C; j++)
				if(temp[i][j] == 0) answer++;
		return answer;
	}
	
	static void drawLine(int[][] temp, int nx, int ny, int dir) {
		while(true) {
			nx += dx[dir];
			ny += dy[dir];
			if(nx<0||nx>=R||ny<0||ny>=C) break;
			if(temp[nx][ny] == 6) break;
			if(temp[nx][ny] != 0) continue;
			temp[nx][ny] = -1;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		cctvList = new ArrayList<CCTV>();
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(sc.nextLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0 && map[i][j] != 6)
					cctvList.add(new CCTV(map[i][j], i, j, 0));
			}
		}
		cctvCnt = cctvList.size();
		makeDir(0);
		System.out.println(min);
	}
}
```

### :octocat: 풀이 방법

1. cctv 종류에 따라서 볼 수 있는 방향 순열을 구한다.
2. 1, 3, 4번은 4방향, 2번은 2방향, 5번은 상관없으니 0으로 고정.
3. 해당 순열에 맞게 cctv가 감시할 수 있는 부분을 -1로 채운다.
4. 2,5번은 보는 방향 반대도 -1로 채운다.
5. 3, 4, 5번은 보는 방향 오른쪽도 -1로 채운다.
6. 4, 5번은 보는 방향 왼쪽도 -1로 채운다.
7. map에서 0 갯수가 사각지대이므로 최소일때를 구한다.

### :octocat: 후기

구현문제는 통과했을때 뭔가 쾌감이 있다 ㅎㅎ
틀린 부분도 테스트케이스로 쉽게 잡을 수 있었어서 빨리 풀 수 있었다!!
