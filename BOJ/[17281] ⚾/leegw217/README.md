# [17281] 야구 - Java

###  :octocat: 분류

> 구현
> 
> 브루트포스

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class week11_야구 {
	static int N;
	static int[] arr = new int[9];
	static int[] result = new int[9];
	static boolean[] visited = new boolean[9];
	static int[][] player;
	static int answer = Integer.MIN_VALUE;
	
	static void makePerm(int cnt) { // 순열 만들기
		if(cnt == result.length) {
			playGame();
			return;
		}
		
		for(int i=1; i<arr.length; i++) {
			if(cnt == 3) { // 4번타자는 1번으로 고정
				cnt++; i--; continue;
			}
			if(!visited[i]) {
				result[cnt] = arr[i];
				visited[i] = true;
				makePerm(cnt+1);
				visited[i] = false;
			}
		}
	}
	
	static void playGame() {
		int start = 0;
		int score = 0;
		for(int i=0; i<N; i++) { // 이닝
			boolean[] base = new boolean[4];
			int out = 0;
			int idx = start;
			while(true) {
				if(out == 3) break;
				int n = player[i][result[idx]];
				if(n == 0) { // 아웃
					out++;
				} else if(n == 1) { // 안타
					if(base[3]) score++;
					base[3] = base[2]?true:false;
					base[2] = base[1]?true:false;
					base[1] = true;
				} else if(n == 2) { // 2루타
					if(base[3]) score++;
					if(base[2]) score++;
					base[3] = base[1]?true:false;
					base[2] = true;
					base[1] = false;
				} else if(n == 3) { // 3루타
					if(base[3]) score++;
					if(base[2]) score++;
					if(base[1]) score++;
					base[3] = true;
					base[2] = false;
					base[1] = false;
				} else { // 홈런
					for(int b=3; b>=1; b--) {
						if(base[b]) score++;
						base[b] = false;
					}
					score++;
				}
				idx++;
				idx = idx%9;
			}
			start = idx;
		}
		answer = Math.max(answer, score);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		player = new int[N][9];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) 
				player[i][j] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<9; i++) arr[i] = i;
		result[3] = 0;
		makePerm(0);
		System.out.println(answer);
	}
}
```

### :octocat: 풀이 방법

1. 0~8까지 순열을 만든다. 이때 3번칸은 0으로 고정해야함 (4번타자는 무조건 1번 선수)
2. 이닝별로 순열로 만든 선수가 치는 결과에 맞는 연산을 해준다.
3. 베이스는 boolean형태로 만들어서 주자가 있는지 없는지 확인해주고 득점 여부를 판단한다.
4. 순열별로 득점 중 최댓값 찾기

### :octocat: 후기

처음 순열 만들때 4번타자는 1번선수로 고정한다는걸 못보고 짜서 계속 틀렸다;
웡게 코드 보고 고정해야하는거 알아서 수정하고 바로 맞췄다!
