# [15683] 감시 - Java

###  :video_camera: 분류

> 완전 탐색
>
> 시뮬레이션

​

### :video_camera: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static int[] cctvDir;
	static List<Dir> cctv = new ArrayList<>();
	static int[][] map;
	static int N, M;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static int answer;
	
	static class Dir{
		int no;
		int y, x; 
		Dir(int no, int y, int x){
			this.no = no; this.y = y; this.x = x;
		}
	}
	
	public static void comb(int cnt) {
		if(cnt == cctv.size()) {
			setCCTV();
			return;
		}
		
		switch(cctv.get(cnt).no) {
		case 1:
			for(int i = 0; i < 4; i++) {
				cctvDir[cnt] = i;
				comb(cnt + 1);
			}
			break;
		case 2:
			for(int i = 0; i < 2; i++) {
				cctvDir[cnt] = i;
				comb(cnt + 1);
			}
			break;
		case 3:
			for(int i = 0; i < 4; i++) {
				cctvDir[cnt] = i;
				comb(cnt + 1);
			}
			break;
		case 4:
			for(int i = 0; i < 4; i++) {
				cctvDir[cnt] = i;
				comb(cnt + 1);
			}
			break;
		case 5:
			cctvDir[cnt] = 0;
			comb(cnt + 1);
		}
	}
	
	public static void setCCTV() {
		boolean[][] checked = new boolean[N][M];
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M; j++) {
				checked[i][j] = map[i][j] > 0 ? true : false;
			}
		}
		
		for(int i = 0; i < cctv.size(); i++) {
			Dir cur = cctv.get(i);
			switch(cur.no) {
			case 1:
				switch(cctvDir[i]) {
				case 0: checkUp(checked, cur); break;
				case 1: checkDown(checked, cur); break;
				case 2: checkLeft(checked, cur); break;
				case 3: checkRight(checked, cur); break;
				}
				break;
			case 2:
				switch(cctvDir[i]) {
				case 0: checkUp(checked, cur); checkDown(checked, cur); break;
				case 1: checkLeft(checked, cur); checkRight(checked, cur); break;
				}
				break;
			case 3:
				switch(cctvDir[i]) {
				case 0: checkUp(checked, cur); checkRight(checked, cur); break;
				case 1: checkUp(checked, cur); checkLeft(checked, cur); break;
				case 2: checkDown(checked, cur); checkRight(checked, cur); break;
				case 3: checkDown(checked, cur); checkLeft(checked, cur); break;
				}
				break;
				
			case 4:
				switch(cctvDir[i]) {
				case 0: checkUp(checked, cur); checkLeft(checked, cur); checkRight(checked, cur); break;
				case 1: checkUp(checked, cur); checkLeft(checked, cur); checkDown(checked, cur); break;
				case 2: checkDown(checked, cur); checkRight(checked, cur); checkUp(checked, cur); break;
				case 3: checkDown(checked, cur); checkLeft(checked, cur); checkRight(checked, cur); break;
				}
				
				break;
			case 5:
				checkUp(checked, cur);
				checkDown(checked, cur);
				checkLeft(checked, cur);
				checkRight(checked, cur);
			}
		}
		
		int cnt = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M; j++) {
				if(checked[i][j] == false) cnt++;
			}
		}
		answer = Math.min(answer, cnt);
	}
	
	public static void checkUp(boolean[][] checked, Dir cur) {
		for(int i = cur.y - 1; i >= 0; i--) {
			if(map[i][cur.x] == 6 ) break;
			checked[i][cur.x] = true; 
		}
	}
	public static void checkDown(boolean[][] checked, Dir cur) {
		for(int i = cur.y + 1; i < N; i++) {
			if(map[i][cur.x] == 6 ) break;
			checked[i][cur.x] = true; 
		}
	}
	public static void checkLeft(boolean[][] checked, Dir cur) {
		for(int i = cur.x - 1; i >= 0; i--) {
			if(map[cur.y][i] == 6 ) break;
			checked[cur.y][i] = true; 
		}
	}
	public static void checkRight(boolean[][] checked, Dir cur) {
		for(int i = cur.x + 1; i < M; i++) {
			if(map[cur.y][i] == 6 ) break;
			checked[cur.y][i] = true; 
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0 ; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(1<= map[i][j] && map[i][j] <= 5) cctv.add(new Dir(map[i][j], i, j)); 
				if(map[i][j] == 0) answer++;
			}
		}
		
		cctvDir = new int[cctv.size()];
		if(cctv.size() != 0) {
			answer = Integer.MAX_VALUE;
			comb(0);
		}

		System.out.println(answer);
	}
}
```



### :video_camera: 풀이 방법

1. 코드가 상당히 더럽습니다.

   CCTV의 번호마다 가능한 경우의 수가 다르기 때문에 분기가 무진장 많습니다.

    

   아래는 번호마다 가능한 경우의 수 입니다.

   1번 -> 4가지

   2번 -> 2가지

   3번 -> 4가지

   4번 -> 4가지

   5번 -> 1가지

    

   인풋으로 주어지는 CCTV의 개수는 최대 8개이기 때문에 가능한 경우의 수는 모두 2^8 = 6만 얼마입니다.

   CCTV가 벽에 붙어 있는 경우 벽쪽으로 보는 경우는 가지치기할 수 있지만 귀찮아서 안했습니다.

    

   CCTV의 사방으로 봐주는 로직은 똑같기 때문에 네 경우의 메소드를 만들어서 활용해씀니다.

    

   !! 그리고 CCTV가 하나도 없는 경우를 주의해줍시다 !!

   먼저 빈 칸을 세주면서 입력을 받고 CCTV가 하나도 없으면 빈 칸 수를 그대로 출력해주면 됩니당.



### :video_camera: 후기

반례찾는 연습!!!!!!!!!!!!!!!