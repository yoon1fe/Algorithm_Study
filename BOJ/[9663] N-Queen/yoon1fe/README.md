# [9663] N-Queen - Java

###  :princess: 분류

> 백트래킹
>

​

### :princess: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static int[] board;

	public static void main(String[] args) throws IOException {
		input();
		
		dfs(1);
		System.out.println(answer);
	}
	
	public static void dfs(int row) {
		if(row == N + 1) {
			answer++;
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			board[row] = i;
			if(check(row) == true) dfs(row + 1);
		}
	}
	
	public static boolean check(int x) {
		for(int i = 1; i < x; i++) {
			if(board[x] == board[i] || Math.abs(board[x] - board[i]) == x - i) return false;
		}
		
		return true;
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N + 1];
	}
}

```



### :princess: 풀이 방법

백트래킹 단골 문제 N-Queen 문제입니다.

이 문제는 N의 최댓값이 14기 때문에 이렇게 풀어도 되는데 15 부터는 터지기 때문에 DP인가 뭔가를 써야 합니다.

 

체스판을 2차원 배열로 표현하지 않고 1차원 배열로 표현했습니다. board[1]=1; 이라면 1번 행의 1번 열에 퀸이 놓여져 있다는 의미입니다.

 

1번 행에서부터 1~N번 열에 퀸을 놓으면서 놓을 수 있는 위치인지 체크한 후 dfs로 파고 들어가면 됩니다.

 

놓을 수 있는지 여부를 체크하는 check() 메소드에서는 이미 놓인 말들을 갖고 체크를 해야겠져. 그래서 1~x 앞까지 반복하면서 체크하면 됩니다. if 문 안에서 앞쪽은 세로에 놓인 퀸이 있는지를 보는 부분이고 뒷쪽은 대각선을 체크하는 부분임다.



### :princess: 후기

아주 유명한 문제임당.^^ 

감사합니다~!~!