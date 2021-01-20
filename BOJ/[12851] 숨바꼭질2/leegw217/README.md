# [12851] 숨바꼭질 2 - Java

###  :octocat: 분류

> BFS

### :octocat: 코드

```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class week23_숨바꼭질2 {
	static int N, K;
	static int time = Integer.MAX_VALUE;
	static int cnt = 0;
	
	static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[] v = new boolean[100001];
		q.offer(new int[] {N, 0});
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			if(time < p[1]) continue;
			v[p[0]] = true;
			
			if(p[0] == K) {
				time = Math.min(time, p[1]);
				cnt++;
				continue;
			}
			if(p[0]-1 >= 0 && !v[p[0]-1]) q.offer(new int[] {p[0]-1, p[1]+1});
			if(p[0]+1 <= 100000 && !v[p[0]+1]) q.offer(new int[] {p[0]+1, p[1]+1});
			if(p[0]*2 <= 100000 && !v[p[0]*2]) q.offer(new int[] {p[0]*2, p[1]+1});
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		bfs();
		System.out.println(time);
		System.out.println(cnt);
	}
}
```

### :octocat: 풀이 방법

1. BFS를 이용해서 -1, +1, *2 이동하는 경우를 모두 조사한다.
2. 이동할때마다 이동횟수를 체크하고 동생을 만난경우 이동횟수보다 클 경우 종료
3. 숨바꼭질 1이랑 다르게 가능한 모든 경우를 구해야하기 때문에 중복방문이 가능하게 해야한다.

### :octocat: 후기

아 방문체크를 큐에서 뽑고나서 해줘야하는거 때문에 골치아픈 문제였다.. 쉬운줄알았는데
메모리초과만 4번인가.. ㅜ
