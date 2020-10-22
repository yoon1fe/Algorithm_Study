# [17281] ⚾ - Java

###  ⚾ 분류

> 시뮬레이션
>



### ⚾ 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static int[][] hits;
	static int[] order;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		
		input();
		// 순열로 타순 만들기
		perm(1);
		
		System.out.println(answer);
		
	}
	
	public static void perm(int cnt) {
		if(cnt == 10) {
			solve();
			return;
		}
		
		for(int i = 2; i <= 9; i++) {
			if(cnt == 4) {
				cnt++;	i--; continue;
			}
			if(isSelected[i]) continue;
			isSelected[i] = true;
			order[cnt] = i;
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}
	
	public static void solve() {
		int score = 0, startNo = 1;
		for(int i = 0; i < N; i++) {		// 0회 ~ N-1회까지 경기
			int outCnt = 0;
			boolean[] base = new boolean[4];	// base[0]: 홈,  1: 1루, 2: 2루, 3: 3루 
		
			int cur = startNo;
			while(outCnt != 3) {			// 3아웃이면 한 이닝 끝
				switch(hits[i][order[cur]]) {
				case 0: outCnt++; break;
				case 1:
					if(base[3]) score++;
					base[3] = base[2] ? true : false;
					base[2] = base[1] ? true : false;
					base[1] = true;
					break;
				case 2:
					if(base[3]) score++;
					if(base[2]) score++;
					base[3] = base[1] ? true : false;
					base[2] = true;
					base[1] = false;
					break;
				case 3:
					for(int b = 1; b <= 3; b++) {
						if(base[b]) score++;
						base[b] = false;
					}
					base[3] = true;
					break;
				case 4:
					for(int b = 1; b <= 3; b++) {
						if(base[b]) score++;
						base[b] = false;
					}
					score++;
					break;
				}
				
				cur = (cur + 1) % 10;
				if(cur == 0) cur = 1;
			
			}
			startNo = cur;
			
		}
		
		answer = Math.max(answer, score);
	}	

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); 
		
		hits = new int[N][10];
		order = new int[10];
		isSelected = new boolean[10];
		order[4] = 1;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 1; j <= 9; j++) hits[i][j] = Integer.parseInt(st.nextToken());
		}
		
	}
}
```



### ⚾ 풀이 방법

야구 시뮬레이션입니다.

두 손을 내려놓고 차분히 고민해보면 쉽게 풀 수 있습니다.



오랜만에 순열이 나왔습니다.

순열로 타순을 먼저 만들어줍니다. 이 때 문제를 잘 보면 1번 선수는 항상 4번 타순에 위치해야 하는 걸 주의하세용.

1번 선수빼고 **8! =** **40320** 가지 경우가 나온답니다.

 

타순을 완성하면 N회까지 공격을 진행하면 됩니다. 

 

한 이닝은 아웃 카운트와 베이스의 상황을 갖고 있어야겠죠. 그리고 한 게임에서는 시작 타선과 점수를 같이 갖고 가야 합니다. 

해당하는 타순의 타자가 칠 수 있는 경우에 따라 0~4(아웃~홈런) case 문으로 나누어줬습니다. 각각의 경우는 조금만 생각해보면 쉽게 구현할 수 있습니다.

모든 경우를 보면서 그 중 최댓값을 출력하면 끝!!





### ⚾ 후기

저는 타자 번호를 쓸데없이 1번부터 해서 모드 연산할 때 10으로 나눠줘서 쓸데없이 한 줄이 늘었습니다 ^^;

걍 0번부터 할껄ㅋ

감사합니다!!