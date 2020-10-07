# [17779] 게리맨더링 2 - Java

###  :office: 분류

> 시뮬레이션
>
> 완전 탐색



### :office: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int total;
	static int[][] map;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= N; j++) {
				total += map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 2; j < N; j++) {
				int x = i, y = j;
				for(int d1 = 1; d1 < N; d1++) {
					for(int d2 = 1; d2 < N; d2++) {
						if(x + d1 + d2 > N) continue;
						if(0 >= y - d1 || y + d2 > N) continue;
						answer = Math.min(answer, getPopulation(x, y, d1, d2));
					}
				}
			}
		}
		
		System.out.println(answer);
	}

	public static int getPopulation(int x, int y, int d1, int d2) {
		int[] area = new int[5];
		boolean[][] boundary = new boolean[N+1][N+1];
		
		for(int i = 0; i <= d1; i++){
			boundary[x+i][y-i] = true;
		}
		for(int i = 0; i <= d2; i++){
			boundary[x+i][y+i] = true;
		}
		for(int i = 0; i <= d2; i++){
			boundary[x+d1+i][y-d1+i] = true;
		}
		for(int i = 0; i <= d1; i++){
			boundary[x+d2+i][y+d2-i] = true;
		}
		
		// 1번 구역
		for(int i = 1; i < x + d1; i++) {
			for(int j = 1; j <= y; j++) {
				if(boundary[i][j]) break;
				area[0] += map[i][j];
			}
		}
		// 2번 구역
		for(int i = 1; i <= x + d2; i++) {
			for(int j = N; j > y; j--) {
				if(boundary[i][j]) break;
				area[1] += map[i][j];
			}
		}
		// 3번 구역
		for(int i = x + d1; i <= N; i++) {
			for(int j = 1; j < y - d1 + d2; j++) {
				if(boundary[i][j]) break;
				area[2] += map[i][j];
			}
		}
		// 4번 구역
		for(int i = x + d2 + 1; i <= N; i++) {
			for(int j = N; j >= y - d1 + d2; j--) {
				if(boundary[i][j]) break;
				area[3] += map[i][j];
			}
		}
		area[4] = total - Arrays.stream(area).sum();
		
		return Arrays.stream(area).max().getAsInt() - Arrays.stream(area).min().getAsInt();
	}
	
}
```



### :office: 풀이 방법

어우 모처럼 처음 푸는 삼성 기출...

그래도 어찌 저찌 푸는데 한시간 반은 안걸립디다 허허~!



사실 다 풀고 나니 로직이 굉장히 간단합니다....

정말루 문제에 주어진 범위 고대로만 경계를 만들고 계산해주면 됩니다....

 

어찌 보면 완전 탐색이라 해야 하겠습니다. 문제에 주어진 x, y, d1, d2의 조건을 잘 보고 가능한 모든 경우를 만들어서 구역을 나누어 보아야 합니다.

 

가능한 경우를 만들고, getPopulation() 메소드에서 5번 선거구 테두리를 만들고, 1~4번 선거구의 인구수를 구하면 됩니다. 이 때, 5번 선거구에서 BFS를 돌려서 안에 있는 공간을 모두 채워주려고 했는데 한칸씩 대각선으로 이어져있는 경우 사방 탐색으로 채워줄 수가 없습니다.. 그래서 테두리를 한칸씩 밖으로 다시 만들어줄까 고민하다가 그냥 말았습니다.

 

그래서 1~4번 선거구의 인구수를 구할 때 반복문을 조금 고쳤습니다. 1, 3번 선거구는 그대로 r, c를 더하면서 봐주면 되고, 2, 4번 선거구는 c를 밖에서 안으로 들어오면서 체크했습니다. 이러면 5번 선거구 경계를 만났을 때 바로 break 해주면 되겠죵.

 

각 선거구의 인구수를 배열에 두고, 5번 선거구의 인구 수는 전체 - 나머지의 합으로 구했습니다. 이 때 stream() API를 썼습니다. 이게 뭔진 좀만 있다가 정리해야징

 

모든 경우를 봐주면서 가장 작은 값을 출력하면 끝!!



### :office: 후기

처음에 괜히 깔끔하게 짠답시고 area 배열의 크기를 6으로 선언했습니다.. 이러면 당연히 min() 은 area[0]지 ;;

몇 초간 그것도 모르고 위에 있는 로직이 잘못된건가 싶었구먼유

 

실수를 줄입시다!!