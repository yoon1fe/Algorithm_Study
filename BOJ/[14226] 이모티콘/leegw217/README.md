# [14226] 이모티콘 - Java

###  :octocat: 분류

> BFS

### :octocat: 코드

```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class week23_이모티콘 {
	static void bfs(int s) {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] v = new boolean[1001][1001];
		q.offer(new int[] {1, 0, 0});
		v[1][0] = true;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			if(p[0] == s) {
				System.out.println(p[2]);
				return;
			}
			
			// 1. 화면 이모티콘 클립보드로 저장
			if(!v[p[0]][p[0]]) {
				q.offer(new int[] {p[0], p[0], p[2]+1});
				v[p[0]][p[0]] = true;
			}
			// 2. 클립보드 이모티콘을 화면으로 붙여넣기
			if(p[1] != 0 && p[0]+p[1] <= 1000) {
				if(!v[p[0]+p[1]][p[1]]) {
					q.offer(new int[] {p[0]+p[1], p[1], p[2]+1});
					v[p[0]+p[1]][p[1]] = true;
				}
			}
			// 3. 화면에 이모티콘 1개 삭제
			if(p[0] != 1 && !v[p[0]-1][p[1]]) {
				q.offer(new int[] {p[0]-1, p[1], p[2]+1});
				v[p[0]-1][p[1]] = true;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int S = sc.nextInt();
		bfs(S);
	}
}
```

### :octocat: 풀이 방법

1. 방문체크는 화면에 있는 이모티콘 개수와 클립보드에 있는 이모티콘 개수 둘다 이용해야하기 때문에 
2차원 배열로 관리한다. 
2. 클립보드로 복사, 화면으로 붙여넣기, 화면 이모티콘 1개삭제 3연산을 한 뒤 결과가 방문했던건지 
체크하고 아니면 연산 후 큐에 담고 bfs 계속 돌린다.
3. 최초로 이모티콘이 S개가 된게 최솟값이기 때문에 끝낸다.

### :octocat: 후기

처음에 문제 잘못이해하고 3개 연산 한번에 다해야하는줄알았음 키키
