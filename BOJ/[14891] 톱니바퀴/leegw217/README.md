# [14891] 톱니바퀴 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
public class week07_톱니바퀴 {
	static void rotationWheel(int dir, int[] wheel) {
		if(dir == 1) { // 시계방향 회전
			int n = wheel[wheel.length-1];
			for(int i=wheel.length-2; i>=0; i--) 
				wheel[i+1] = wheel[i];
			wheel[0] = n;
		} else { // 반시계방향 회전
			int n = wheel[0];
			for(int i=1; i<wheel.length; i++)
				wheel[i-1] = wheel[i];
			wheel[wheel.length-1] = n;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		int answer = 0;
		int[][] wheel = new int[5][8];
		for(int i=1; i<=4; i++) {
			char[] arr = sc.nextLine().toCharArray();
			for(int j=0; j<8; j++) wheel[i][j] = arr[j]-'0';
		}
		int K = Integer.parseInt(sc.nextLine());
		for(int i=0; i<K; i++) {
			int[] rotation = new int[5];
			Arrays.fill(rotation, 0);
			st = new StringTokenizer(sc.nextLine());
			int point = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			rotation[point] = dir;
			for(int j=point; j>1; j--) { // 회전하는 톱니바퀴 기준 왼쪽 검사
				if(wheel[j][6] == wheel[j-1][2]) break;
				rotation[j-1] = rotation[j] == -1?1:-1;
			}
			for(int j=point; j<4; j++) { // 회전하는 톱니바퀴 기준 오른쪽 검사
				if(wheel[j][2] == wheel[j+1][6]) break;
				rotation[j+1] = rotation[j] == -1?1:-1;
			}
			for(int d=1; d<=4; d++)
				if(rotation[d] != 0)
					rotationWheel(rotation[d], wheel[d]);
		}
		for(int i=1; i<=4; i++)
			if(wheel[i][0] == 1) answer += Math.pow(2,i-1);
		System.out.println(answer);
	}
}
```

### :octocat: 풀이 방법

1. 회전하는 톱니바퀴를 기준으로 왼쪽과 오른쪽으로 이동하면서 회전이 가능한지 검사한다.
2. n번째 톱니바퀴가 회전이 가능하면 rotation[n] 에 회전방향을 저장한다. (0:안함, 1:시계, -1:반시계)
3. K번 회전 시킨 후 값을 계산해서 출력하면 끝!

### :octocat: 후기

회전할 수 있는지 검사하는 로직과 검사가 다끝나고 한번에 회전시키는 로직이 바로 떠올라서
빨리 풀 수 있었따!
