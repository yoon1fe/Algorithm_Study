# [7453] 합이 0인 네 정수 - Java

###  :octocat: 분류

> 투 포인터

### :octocat: 코드

```java
package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class week14_합이0인네정수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		long ans = 0;
		int N = Integer.parseInt(br.readLine());
		int[][] Arr = new int[4][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) Arr[j][i] = Integer.parseInt(st.nextToken());
		}
		int[] AB = new int[N*N];
		int[] CD = new int[N*N];
		int idx = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				AB[idx] = Arr[0][i] + Arr[1][j];
				CD[idx++] = Arr[2][i] + Arr[3][j];
			}
		}
		Arrays.sort(AB);
		Arrays.sort(CD);
		int abIdx = 0;
		int cdIdx = (N*N)-1;
		while(abIdx < (N*N) && cdIdx >= 0) {
			int sum = AB[abIdx] + CD[cdIdx];
			if(sum < 0) abIdx++;
			else if(sum > 0) cdIdx--;
			else {
				int ABsum = AB[abIdx];
				int CDsum = CD[cdIdx];
				int ABcnt = 0;
				int CDcnt = 0;
				while(abIdx<(N*N) && ABsum == AB[abIdx]) {
					abIdx++;
					ABcnt++;
				}
				while(cdIdx>=0 && CDsum == CD[cdIdx]) {
					cdIdx--;
					CDcnt++;
				}
				ans += (long)ABcnt*(long)CDcnt;
			}
		}
		System.out.println(ans);
	}
}
```

### :octocat: 풀이 방법

1. A배열과 B배열의 합배열 AB를 만든다.
2. C배열과 D배열의 합배열 CD를 만든다.
3. AB배열과 CD배열을 오름차순 정렬한다.
4. AB배열은 시작점부터, CD배열은 끝점부터 합이 0인경우를 찾는다.
5. 합이 0보다 크면 CD포인터를 한칸 내리고 0보다 작으면 AB포인터를 한칸 올린다.
6. 합이 0이면 AB배열에서 중복인 개수를 구하고 CD배열에서 중복인 개수를 구해 
곱한값을 ans에 더한다.

### :octocat: 후기

AB배열과 CD배열을 만들어서 0을 찾는 것까지는 생각했는데
합이 0인 경우 중복값을 찾는 부분을 생각못해서 오래걸렸다..
