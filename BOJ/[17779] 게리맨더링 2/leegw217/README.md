# [17779] 게리맨더링2 - Java

###  :octocat: 분류

> 구현
> 브루트포스
> 시뮬레이션

### :octocat: 코드

```java
import java.util.Scanner;
public class week10_게리맨더링2 {
	static int N;
	static int[][] map;
	public static void main(String[] args) {
		int answer = Integer.MAX_VALUE;
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N+1][N+1];
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				map[i][j] = sc.nextInt();
		
		for(int x=1; x<=N; x++) {
			for(int y=1; y<=N; y++) {
				for(int d1=1; d1<N; d1++) {
					for(int d2=1; d2<N; d2++) {
						if(x+d1+d2>N||1>y-d1||y+d2>N) continue;
						int[][] tempMap = new int[N+1][N+1];
						int[] sumArr = new int[6];
						int max = Integer.MIN_VALUE;
						int min = Integer.MAX_VALUE;
						for(int i=0; i<=d1; i++) {
							int nx = x+i;
							int ny = y-i;
							tempMap[nx][ny] = 5; // 1번 경계선
							nx = x+d2+i;
							ny = y+d2-i;
							tempMap[nx][ny] = 5; // 4번 경계선
						}
						for(int i=0; i<=d2; i++) {
							int nx = x+i;
							int ny = y+i;
							tempMap[nx][ny] = 5; // 2번 경계선
							nx = x+d1+i;
							ny = y-d1+i;
							tempMap[nx][ny] = 5; // 3번 경계선
						}
						
						for(int i=1; i<=N; i++) {
							int start = 0;
							int end = 0;
							for(int j=1; j<=N; j++) {
								if(tempMap[i][j] == 5) {
									start = j;
									break;
								}
							}
							if(start==0) continue;
							for(int j=start+1; j<=N; j++) {
								if(tempMap[i][j] == 5) {
									end = j;
									break;
								}
							}
							if(end==0) continue;
							for(int j=start; j<end; j++) 
								tempMap[i][j] = 5;
						}
						
						for(int r=1; r<x+d1; r++) // 1번 선거구
							for(int c=1; c<=y; c++) 
								if(tempMap[r][c] != 5) tempMap[r][c] = 1;
						for(int r=1; r<=x+d2; r++) // 2번 선거구
							for(int c=y+1; c<=N; c++)
								if(tempMap[r][c] != 5) tempMap[r][c] = 2;
						for(int r=x+d1; r<=N; r++) // 3번 선거구
							for(int c=1; c<y-d1+d2; c++)
								if(tempMap[r][c] != 5) tempMap[r][c] = 3;
						for(int r=x+d2+1; r<=N; r++) // 4번 선거구
							for(int c=y-d1+d2; c<=N; c++)
								if(tempMap[r][c] != 5) tempMap[r][c] = 4;
						
						for(int i=1; i<=N; i++) // 선거구 인구 합 계산
							for(int j=1; j<=N; j++) 
								sumArr[tempMap[i][j]] += map[i][j];
							
						for(int i=1; i<=5; i++) { // 선거구 인구차이 계산
							if(max < sumArr[i]) max = sumArr[i];
							if(min > sumArr[i]) min = sumArr[i];
						}
						
						if(answer > max-min) // 최솟값 계산
							answer = max-min;
					}
				}
			}
		}
		System.out.println(answer);
	}
}
```

### :octocat: 풀이 방법

1. 주어진 범위 내에 기준점 (x,y)와 경계길이 d1, d2 구하기 (4중 for문)
2. 새로 map을 만들어서 경계선 내에 5번 선거구 채우기
3. 주어진 범위 내에 1~4번 선거구 채우기
4.  선거구별로 인원수 더해서 최댓값과 최솟값을 구한다.
5. 최댓값과 최솟값 차이가 최소인 경우를 찾아서 출력!

### :octocat: 후기

처음에 시간초과 날거같아서 범위 계산에서 바로 선거구 인원을 더할려고했는데 각 선거구 별로 경계선과 겹치는 구간이 있어서
5번인지 표시해줄려고 새로 맵을 만들어서 선거구 표시했음!
그래도 시간초과가 안떴다!
