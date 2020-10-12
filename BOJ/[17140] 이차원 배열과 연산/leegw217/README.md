# [17140] 이차원 배열과 연산 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.Arrays;
import java.util.Scanner;
public class week09_이차원배열과연산 {
	static int[][] map;
	static int R = 3;
	static int C = 3;
	static int r, c, k;
	static void rCalc() {
		int maxC = C;
		for(int i=1; i<=R; i++) {
			int[][] numCnt = new int[101][2];
			for(int j=1; j<=C; j++) {
				numCnt[map[i][j]][0] = map[i][j];
				numCnt[map[i][j]][1] += 1;
			}
			Arrays.sort(numCnt, (o1,o2)->{
				if(o1[0] == 0 && o2[0] != 0) return 1;
				else if(o1[0] != 0 && o2[0] == 0) return -1;
				else if(o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
				else return Integer.compare(o1[1], o2[1]);
			});
			for(int m=0; m<50; m++) {
				if(numCnt[m][0] == 0) {
					if(maxC < m*2) maxC = m*2;
					else {
						for(int l=m*2+1; l<=100; l++)
							map[i][l] = 0;
					}
					break;
				}
				map[i][m*2+1] = numCnt[m][0];
				map[i][m*2+2] = numCnt[m][1];
			}
		}
		C = maxC;
	}
	static void cCalc() {
		int maxR = R;
		for(int i=1; i<=C; i++) {
			int[][] numCnt = new int[101][2];
			for(int j=1; j<=R; j++) {
				numCnt[map[j][i]][0] = map[j][i];
				numCnt[map[j][i]][1] += 1;
			}
			Arrays.sort(numCnt, (o1,o2)->{
				if(o1[0] == 0 && o2[0] != 0) return 1;
				else if(o1[0] != 0 && o2[0] == 0) return -1;
				else if(o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
				else return Integer.compare(o1[1], o2[1]);
			});
			for(int m=0; m<50; m++) {
				if(numCnt[m][0] == 0) {
					if(maxR < m*2) maxR = m*2;
					else {
						for(int l=m*2+1; l<=100; l++)
							map[l][i] = 0;
					}
					break;
				}
				map[m*2+1][i] = numCnt[m][0];
				map[m*2+2][i] = numCnt[m][1];
			}
		}
		R = maxR;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new int[101][101];
		r = sc.nextInt();
		c = sc.nextInt();
		k = sc.nextInt();
		for(int i=1; i<=R; i++)
			for(int j=1; j<=C; j++)
				map[i][j] = sc.nextInt();
		
		for(int t=0; t<=100; t++) {
			if(map[r][c] == k) {
				System.out.println(t);
				return;
			}
			if(R >= C) rCalc();
			else cCalc();
		}
		System.out.println(-1);
	}
}
```

### :octocat: 풀이 방법

1. 행의 개수 >= 열의 개수이면 R연산
2. 열의 개수 > 행의 개수이면 C연산
3. ex) R연산 : 각 행마다 수의 등장횟수 별로 오름차순 정렬, 등장횟수가 같으면 수 기준 오름차순 정렬
4. 주어진 좌표에 해댕하는 값이 일치할 때까지 연산 반복

### :octocat: 후기

R연산과 C연산에 겹치는 부분이 많아서 하나로 합칠 수 있을거같은데 귀찮아서 그냥 나눠서 풀었당.. 히히
