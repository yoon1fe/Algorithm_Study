# [17825] 주사위 윷놀이 - Java

###  :video_game: 분류

> 시뮬레이션
>
> 완전 탐색



### :video_game: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	
	static List<Integer>[] board = new List[33];
	static int[] point = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 
			13, 16, 19, 25, 22, 24, 30, 35, 0, 28, 27, 26};
	
	static int[] moves = new int[10];
	static int[] pieces = new int[10];
	static int answer;
	
	public static void makeBoard() {
		for(int i = 0; i < 33; i++) board[i] = new ArrayList<>();
		
		for(int i = 0; i < 20; i++) board[i].add(i + 1);
		board[5].add(21); board[21].add(22); board[22].add(23); board[23].add(24);
		board[10].add(25); board[25].add(26); board[26].add(24);
		board[24].add(27); board[27].add(28); board[28].add(20); board[20].add(29);
		board[15].add(30); board[30].add(31); board[31].add(32); board[32].add(24);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 10; i++) moves[i] = Integer.parseInt(st.nextToken());
		
		makeBoard();

		perm(0);
		
		System.out.println(answer);
	}
	
	static void perm(int cnt) {
		if(cnt == 10) {
			answer = Math.max(answer, move());
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			pieces[cnt]  = i;
			perm(cnt + 1);
		}
	}

	public static int move() {
		int score = 0;
		int[] loc = new int[4];
		boolean[] v = new boolean[33];

		for (int i = 0; i < 10; i++) {
			int curPiece = pieces[i];
			int curPos = loc[curPiece];
			int nextPos;
			int move = moves[i];
			
			if(curPos == 29) continue;
			nextPos = board[curPos].size() > 1 ? board[curPos].get(1) : board[curPos].get(0);

			for (int j = 1; j < move; j++) {
				if(nextPos == 29) break;
				nextPos = board[nextPos].get(0);
			}

			if (nextPos != 29 && v[nextPos]) return 0;
			
			v[nextPos] = true;
			v[curPos] = false;
			loc[curPiece] = nextPos;
			score += point[nextPos];
		}

		return score;
	}
}
```



### :video_game: 풀이 방법

아우 한시간 반 더걸렸네 ㅜㅜ

윷판을 어떤 식으로 만들어야할지 결정하는게 너무 힘들어씀니다ㅜ



먼저 윷판은 그래프로 구현했습니다.

그리고 각 노드 번호별로 해당하는 점수를 1차원 배열로 만들어놓았습니다.

 

그러고 완전 탐색을 돌립니다. 순열을 만들면 됩니다. 말이 네개니깐 4^10 제곱만큼 만들어주면 되죵. 

이제 이걸갖고 말들을 옮겨주면 됩니다. move() 메소드에서 말들을 옮기고, 다 옮겼을 때의 점수를 반환할겁니다.

v 배열로 해당 위치에 말이 있는지 여부를 체크하고, loc 배열에 해당 말들의 위치를 저장합니다.

 

순열로 만든 옮길 말들을 하나씩 봐주면서, 그 말의 현재 위치에서 이동하는 거리만큼 옮기고 원래 위치(curPos)의 v값을 false, 이동한 위치(nextPos)의 v 값을 true로 바꿔주면 됩니다. 만약 도착한 경우(nextPos == 29)가 아닌데 이미 v의 값이 true인 곳에 도착을 했다면 이는 허용되지 않는 움직임이므로 바로 0을 리턴해주면 되져.

 

 파란색 화살표로 가는 경우는 맨 처음 이동을 시작할 때 board[curPost]의 사이즈를 보고 알 수 있습니다. 파란색으로 가야 하는 경우는 갈 수 있는 경우가 두 개인 경우죠. 이런 경우에는 .get(1) 로 가주면 되는 거죵~!



### :video_game: 후기

하 빡 집중해서 한시간 반으로 푸는 연습을 해야하는데 ㅜㅜ

더 열심히 해야겠습니다. 화이팅~!!

 