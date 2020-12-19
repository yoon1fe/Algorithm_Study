# [1057] 토너먼트 - Java

###  :video_game: 분류

> 시뮬레이션
>
> 완전 탐색



### :video_game: 코드

```java
import java.io.*;
import java.util.*;


public class Main {
	static int N, K, L;
	
	public static void main(String[] args) throws Exception {
		input();
		
		System.out.println(solve());
	}
	
	public static int solve() {
		int answer = 0;
		List<Integer> curRound = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			curRound.add(i);
		}
		
		while(true) {
			List<Integer> nextRound = new ArrayList<>();
			if(curRound.size() == 1) break;
			
			for(int i = 0; i < curRound.size(); i+=2) {
				// 홀수일 때 마지막 참가자
				if(i == curRound.size() - 1) {
					nextRound.add(curRound.get(i));
					break;
				}
				int left = curRound.get(i);
				int right = curRound.get(i+1);

				// 둘이 만나면 종료
				if((left == K && right == L )|| (left == L && right == K)) return answer+1;
				// 다음 라운드 진출자 결정
				else if(left == K || left == L) nextRound.add(left);
				else if(right == K || right == L) nextRound.add(right);
				else nextRound.add(left);
			}
			answer++;
			
			curRound = nextRound;
		}
		
		return -1;
	}
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
	}
}
```



### :video_game: 풀이 방법

아주 간단한 구현 문제입니다. 

인풋 사이즈가 그리 크지 않으므로 완전 탐색으로 답을 구할 수 있습니다.



ArrayList 로 현재 라운드에 진출한 참가자와 다음 라운드에 진출할 참가자를 나타냈습니다.

먼저 curRound를 쭉 돌면서 두개씩 뽑아서 다음 라운드 진출자를 결정해줍니다. 만약 두 개를 뽑았는데 그게 지민이랑 한수라면 고대로 종료시켜주면 되겠죠. 만약 지민이나 한수라면 걔들을 다음 라운드로 올려야 합니다. 나머지 경우는 아무나 올려주면 됩니다. 요 때 현재 라운드에 올라온 사람이 홀수라면 마지막 친구는 걍 부전승으로 올려줍시다.



### :video_game: 후기

주말에 맥주 한캔하면서 풀 수 있는 간단한 문제였습니다.!!

감사합니다!!