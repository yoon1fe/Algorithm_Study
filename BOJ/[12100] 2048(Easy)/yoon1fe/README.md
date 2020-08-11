# [12100] 2048 (Easy) - Java

###   :game_die:분류

> 스택
>
> 시뮬레이션



###  :game_die:코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int[] moveOrder = new int[5];		//최대 다섯번 이동-> 중복 순열
	static int[] dy = {1, -1, 0, 0};			//D U R L
	static int[] dx = {0, 0, 1, -1};
	static int answer = 0;
	
	public static int combine() {
		int[][] temp = new int[N][N];
		int max = 0;
		for(int i = 0; i< N; i++) System.arraycopy(map[i], 0, temp[i], 0, map[i].length);

		for(int t = 0; t < 5; t++) {
			switch(moveOrder[t]) {
			case 0:		// Down
				for(int i = 0; i < N; i++) {
					Stack<Integer> s = new Stack<Integer>();
					Stack<Integer> resultS = new Stack<Integer>();
					for(int j = N-1; j>=0; j--) {
						if(temp[j][i] == 0) continue;
						if(s.isEmpty()) s.push(temp[j][i]);
						else {
							if(s.peek() == temp[j][i]) {
								s.pop();
								resultS.push(temp[j][i] * 2);
							}else {
								resultS.push(s.pop());
								s.push(temp[j][i]);
							}
						}
						temp[j][i] = 0;
					}
					while(!s.isEmpty()) {
						resultS.push(s.pop());
					}
					for(int j = N - resultS.size(); j < N; j++) {
						temp[j][i] = resultS.pop();
					}
				}
				break;
			case 1:		// Up
				for(int i = 0; i < N; i++) {
					Stack<Integer> s = new Stack<Integer>();
					Stack<Integer> resultS = new Stack<Integer>();
					for(int j = 0; j < N; j++) {
						if(temp[j][i] == 0) continue;
						if(s.isEmpty()) s.push(temp[j][i]);
						else {
							if(s.peek() == temp[j][i]) {
								s.pop();
								resultS.push(temp[j][i] * 2);
							}else {
								resultS.push(s.pop());
								s.push(temp[j][i]);
							}
						}
						temp[j][i] = 0;
					}
					while(!s.isEmpty()) {
						resultS.push(s.pop());
					}
					for(int j = resultS.size() - 1; j >= 0; j--) {
						temp[j][i] = resultS.pop();
					}
				}
				break;
			case 2:		// Right
				for(int i = 0; i < N; i++) {
					Stack<Integer> s = new Stack<Integer>();
					Stack<Integer> resultS = new Stack<Integer>();
					for(int j = N-1; j>=0; j--) {
						if(temp[i][j] == 0) continue;
						if(s.isEmpty()) s.push(temp[i][j]);
						else {
							if(s.peek() == temp[i][j]) {
								s.pop();
								resultS.push(temp[i][j] * 2);
							}else {
								resultS.push(s.pop());
								s.push(temp[i][j]);
							}
						}
						temp[i][j] = 0;
					}
					while(!s.isEmpty()) {
						resultS.push(s.pop());
					}
					for(int j = N - resultS.size(); j < N; j++) {
						temp[i][j] = resultS.pop();
					}
				}
				
				break;
			case 3:		// Left
				for(int i = 0; i < N; i++) {
					Stack<Integer> s = new Stack<Integer>();
					Stack<Integer> resultS = new Stack<Integer>();
					for(int j = 0; j < N; j++) {
						if(temp[i][j] == 0) continue;
						if(s.isEmpty()) s.push(temp[i][j]);
						else {
							if(s.peek() == temp[i][j]) {
								s.pop();
								resultS.push(temp[i][j] * 2);
							}else {
								resultS.push(s.pop());
								s.push(temp[i][j]);
							}
						}
						temp[i][j] = 0;
					}
					while(!s.isEmpty()) {
						resultS.push(s.pop());
					}
					for(int j = resultS.size() - 1; j >= 0; j--) {
						temp[i][j] = resultS.pop();
					}
				}
				break;
			}
		}
		
		for(int i = 0; i< N; i++) {
			for(int j = 0; j < N; j++) {
				max = Math.max(max, temp[i][j]);
			}
		}
		
		return max;
	}
	
	public static void perm(int cnt) {
		if(cnt == 5) {
			answer = Math.max(answer,  combine());
			//System.out.println(Arrays.toString(moveOrder));
			
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			moveOrder[cnt] = i;
			perm(cnt+1);
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i< N; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	for(int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());        		
        	}
        }
        
        
        perm(0);
        
        System.out.println(answer);
    }
}
```



### :game_die: 풀이 방법

문제에는 **`최대`** 다섯 번을 움직였을 때라고 나오지만 게임을 진행하는데 한번 더 움직인다고 해서 최댓값이 줄어들진 않기 때문에, 그리고 다섯 번 다 움직였을 때 최댓값이 나올 수 있으므로 무조건 5번 움직이고 나서 값을 구해주면 됩니다.

먼저 중복 순열로 움직이는 순서를 구해줍니다. 같은 방향으로 다섯 번 움직일 수도 있기 때문에 중복 순열을 사용합니다. 총 다섯 번 움직이는 배열을 만들고 나면 그 순서에 따라 이리저리 움직여줍니다.

숫자 계산은 스택을 사용해서 구현했습니다.

#### **이 때 주의할 점 ! **

한 번 합쳐진 놈은 그 턴에서는 다시 합쳐지지 않기 때문에 이 점을 유의해야 합니다. 

저는 스택(`s`, `resultS`)을 두 개 두고 풀었습니다.

e.g)

`2 2 4 4` :arrow_forward: `4 8`

맨 처음 스택에 숫자를 넣다가 스택의 `top`에 있는 친구랑 새로 들어올 친구랑 같으면 합친 값을 `result` 스택에 넣어줍니다. 이러면 한 번 합쳐진 놈을 다시 안봐도 됩니다.

2차원 배열의 좌표를 갖고 놀아야 하기 때문에 인덱스 헷갈리지 않도록 주의합시다.



###  :game_die:후기

4개월 전에 풀었던 코드를 보니 큐를 사용해서 풀었었습니다. 지금 생각해보면 어떻게 큐로 짜나 싶습니다. 풀어봤던 문제지만 풀 때마다 새로운 방식으로 풀리는게 이게 실력이 는건지 기억력이 안좋은건지 헷갈리지만 좋은 현상이라고 생각합니다.