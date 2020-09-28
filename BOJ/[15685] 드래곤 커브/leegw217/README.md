# [15685] 드래곤 커브 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class week08_드래곤커브 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] map = new int[101][101];
		int N = sc.nextInt();
		List<Integer>[] dCurve = new ArrayList[N+1];
		int[] dy = {0, -1, 0, 1};
		int[] dx = {1, 0, -1, 0};
		int answer = 0;
		for(int i=1; i<=N; i++) {
			dCurve[i] = new ArrayList<Integer>();
			int x = sc.nextInt();
			int y = sc.nextInt();
			int dir = sc.nextInt();
			int gener = sc.nextInt();
			dCurve[i].add(dir);
			for(int j=0; j<gener; j++) {
				int limit = dCurve[i].size()-1;
				for(int l=limit; l>=0; l--) {
					int g = dCurve[i].get(l);
					if(g + 1 == 4) dCurve[i].add(0);
					else dCurve[i].add(g + 1);
				}
			}
			map[y][x] = i;
			for(int j=0; j<dCurve[i].size(); j++) {
				y += dy[dCurve[i].get(j)];
				x += dx[dCurve[i].get(j)];
				map[y][x] = i;
			}
		}
		
		for(int i=0; i<100; i++) 
			for(int j=0; j<100; j++) 
				if(map[i][j] != 0) 
					if(map[i][j+1] != 0) 
						if(map[i+1][j+1] != 0) 
							if(map[i+1][j] != 0) 
								answer += 1;
		System.out.println(answer);
	}
}
```

### :octocat: 풀이 방법

1. 드래곤 커브는 세대를 거듭할때마다 길이가 2배씩 늘어난다.
2. 드래곤 커브마다 방향을 담은 리스트를 만든다. 앞 세대 드래곤 커브의 끝에서
제일 앞까지 내려가면서 방향을 시계 반대방향으로 회전시킨걸 합치면 다음세대
드래곤 커브가 된다. (0:오른쪽, 1:위, 2:왼쪽, 3:아래, +1하면 시계 반대방향이다)
3. 예) 1세대 방향(0, 1) -> 2세대 방향(0, 1, 2, 1)
4. 모든 드래곤 커브에 시작부터 끝까지 방향을 저장하고 시작점부터 방향에 맞게
이동하면서 맵에 숫자 표시
5. 맵 전체를 순회하면서 (x,y), (x,y+1), (x+1,y+1), (x+1,y) 4개가 0이 아니면 사각형
이므로 정답 +1 해준다.

### :octocat: 후기

처음에 드래곤 커브 만드는 로직을 빨리 떠올릴 수 있어서 금방 풀 수 있었다!
