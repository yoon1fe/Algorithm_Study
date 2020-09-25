# [15684] 사다리 조작 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.Scanner;
public class week07_사다리조작 {
	static int N, M, H;
	static int[][] line;
	static void makeLine(int[][] li, int cnt, int limit) {
		if(cnt == limit) { // 사다리 추가한 개수 제한 , 0,1,2,3 개
			if(play(li)) {
				System.out.println(limit);
				System.exit(0);
			}
			return;
		}
		for(int i=1; i<N; i++) { // 사다리 추가 순열
			for(int j=1; j<=H; j++) {
				if(li[i][j] == 1) continue;
				if(li[i-1][j]==1 || li[i+1][j]==1) continue;
				li[i][j] = 1;
				makeLine(li, cnt+1, limit);
				li[i][j] = 0;
			}
		}
	}
	
	static boolean play(int[][] li) { // 사다리타기
		for(int i=1; i<=N; i++) {
			int curLine = i;
			int curH = 1;
			while(true) {
				if(curH > H) break;
				if(li[curLine-1][curH] == 1) curLine -= 1; // 왼쪽으로 이동
				else if(li[curLine][curH] == 1) curLine += 1; // 오른쪽으로 이동
				curH += 1; // 밑으로 이동
			}
			if(curLine != i) return false; // 최종 도착번호가 출발번호와 같아야함
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		line = new int[N+1][H+1];
		for(int i=0; i<M; i++) {
			int h = sc.nextInt();
			int n = sc.nextInt();
			line[n][h] = 1;
		}
		for(int i=0; i<=3; i++) {
			int[][] temp = new int[N+1][H+1];
			for(int j=0; j<N+1; j++) System.arraycopy(line[j], 0, temp[j], 0, H+1);
			makeLine(temp, 0, i);
		}
		System.out.println(-1);
	}
}
```

### :octocat: 풀이 방법

1. 각 라인별 사다리를 이차원 배열로 만든다.
2. 예) 1번 라인과 2번라인 사이에 있는 사다리는 line[2][1] 부터 line[2][H] 내에서 1의 값을 가진다.
3. 추가할 사다리 개수를 0개부터 3개까지 늘리면서 가능한 모든 경우를 순열로 만든다.
4. 사다리를 추가하고 사다리 타기를 한다.
5. 예) 2번 라인에서 출발하면 line[1][h]과 line[2][h]를 검사해서 1인 방향으로 라인을 옮기고 
h를 한칸 내린다. 
6. 모든 라인이 출발해서 자기 번호와 같은 곳으로 도착하면 추가한 사다리 개수를 출력한다.
7. 0개부터 추가하므로 조건을 만족하면 최솟값이 된다. 3개 초과하면 -1, 못만들면 -1

### :octocat: 후기

로직을 생각하느라 시간을 좀 썼지만 그래도 구현하자마자 바로 통과해서 기부니가 너무 좋아요~~
