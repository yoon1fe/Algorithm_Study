# [1799] 비숍 - Java

## 분류
> 백트랙킹

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1799 {
	public static int N;
	public static int ans;
	public static int[][] board;
	public static boolean[] leftDownToRightUp;
	public static boolean[] leftUpToRightDown;
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		solution(0, 0, 0);
		int ret = ans;
		ans = 0;
		solution(0, 1, 0);
		System.out.println(ret + ans);
	}
	
	public static void solution(int row, int col, int cnt) {
		if(col >= N) {
			row += 1;
			if(col%2 == 0) col = 1;
			else col = 0;
		}
		
		if(row >= N) {
			ans = ans < cnt ? cnt : ans;
			return;
		}
		
		if(board[row][col] == 1 && !leftDownToRightUp[row+col] && !leftUpToRightDown[col-row+N-1]) {
			leftDownToRightUp[row+col] = true;
			leftUpToRightDown[col-row+N-1] = true;
			solution(row, col + 2, cnt+1);
			leftDownToRightUp[row+col] = false;
			leftUpToRightDown[col-row+N-1] = false;
		}
		solution(row, col+2, cnt);
		
	}
	
	
	public static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		leftDownToRightUp = new boolean[N*2];
		leftUpToRightDown = new boolean[N*2];
		ans = 0;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}
	}
}

```

## 문제 풀이
전체 칸을 백트랙킹하면 안 된다는 사실을 구글링해서 알아냈음!

체스판을 보는 것처럼 board를 보면 된다.(★핵심)
```
    [흰][검][흰]
    [검][흰][검]
    [흰][검][흰]
```
과 같이 board를 보면, 비숍은 대각선으로만 이동할 수 있기 때문에 흰색에 놓을 수 있는 비숍의 수 + 검은색에 놓을 수 있는 비숍의 수를 답으로 출력하면 된다.
- 흰색, 검은색 따로 백트랙킹을 돌려야 함!

흰색은 col이 0에서 출발하고, 검은색은 1에서 출발한다고 할 때
- 주의할 점은 col이 2씩 증가하는데 N보다 커지면 row 값을 변경해줘야 한다.
- 그리고 현재 col 값이 N보다 크거나 같은 경우, row에서 시작하는 col 값이 달라진다.
- 다음 행으로 넘어가게 되었을 때, col이 홀수이면 0에서 시작하고, 짝수이면 1에서 시작하면 된다.

그리고 row가 N보다 크다면 더이상 탐색할 칸이 없으므로 끝내면서 현재 cnt값이 ans보다 큰지 확인한다.
- ans가 cnt보다 작다면 ans를 cnt로 갱신!

아직 row가 N보다 작다는 것이다. 그러면 아래 3가지 조건을 모두 만족하는지 검사!
   1. 현재 칸에 비숍을 놓을 수 있는지
   1. 현재 칸이 속한 왼쪽 아래에서 오른쪽 위로 향하는 대각선에 어떤 비숍도 없는지(row+col로 각 칸이 어느 대각선에 속하는지)
   1. 현재 칸이 속한 왼쪽 위에서 오른쪽 아래로 향하는 대각선에 어떤 비숍도 없는지(col-row+N-1로 각 칸이 어느 대각선에 속하는지)
   > 참조 : https://blog.encrypted.gg/732

위 3가지 조건을 만족한다면 해당 칸에 비숍을 놓고, 다음 칸으로 넘어간다.
- 3가지 조건 중 하나라도 만족시키지 못하면 해당 칸에 비숍을 놓지 않고, 다음 칸으로 넘어간다.

그러면 흰색에 대해서 백트랙킹 돌린 값 + 검은색에 대해서 백트랙킹 돌린 값을 답으로 출력하면 된다!

## 후기
N-Queen에서 좀 더 복잡해진 문제 같다.

대신 체스판처럼 문제를 보고 나누어서 해결하는 방법을 배워야겠다!

이상!