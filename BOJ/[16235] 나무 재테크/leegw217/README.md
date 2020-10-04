# [16235] 나무 재테크 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class week08_나무재테크 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int answer = 0;
		int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		int[][] map = new int[N+1][N+1];
		int[][] A = new int[N+1][N+1];
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++) {
				map[i][j] = 5;
				A[i][j] = sc.nextInt();
			}
		
		List<Integer>[][] treeList = new ArrayList[N+1][N+1];
		for(int i=0; i<=N; i++)
			for(int j=0; j<=N; j++)
				treeList[i][j] = new ArrayList<Integer>();
		for(int i=0; i<M; i++)
			treeList[sc.nextInt()][sc.nextInt()].add(sc.nextInt());
	
		for(int k=0; k<K; k++) {
			// 봄~여름
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(treeList[i][j].isEmpty()) continue;
					if(treeList[i][j].size()>1) Collections.sort(treeList[i][j]);
					List<Integer> temp = new ArrayList<Integer>();
					int deleteSum = 0;
					for(int t=0; t<treeList[i][j].size(); t++) {
						if(map[i][j] >= treeList[i][j].get(t)) {
							map[i][j] -= treeList[i][j].get(t);
							temp.add(treeList[i][j].get(t)+1);
						} else deleteSum += treeList[i][j].get(t)/2;
					}
					map[i][j] += deleteSum;
					treeList[i][j].clear();
					treeList[i][j].addAll(temp);
				}
			}
			// 가을
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(treeList[i][j].isEmpty()) continue;
					for(int t=0; t<treeList[i][j].size(); t++) {
						if(treeList[i][j].get(t) % 5 == 0) {
							for(int d=0; d<8; d++) {
								int nx = i + dx[d];
								int ny = j + dy[d];
								if(nx<=0||nx>N||ny<=0||ny>N) continue;
								treeList[nx][ny].add(1);
							}
						}
					}
				}
			}
			// 겨울
			for(int i=1; i<=N; i++) 
				for(int j=1; j<=N; j++) 
					map[i][j] += A[i][j];
		}
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++) 
				answer += treeList[i][j].size();
		System.out.println(answer);
	}
}
```

### :octocat: 풀이 방법

1. 각 좌표별로 나무를 저장한다.
2. 봄~여름 : 해당 좌표에 나무들을 오름차순으로 정렬하고 나무 나이가 양분보다 
작으면 양분에서 나무 나이를 뺀다. 크면 해당 나무나이/2 한만큼 양분에서 뺀다.
3. 가을 : 해당 좌표 나무들 중 나이가 5의 배수일 경우 8방향으로 나무를 번식시킨다.
4. 겨울 : 양분 더해주기

### :octocat: 후기

처음에는 일차원 arraylist에 나무 좌표와 나이를 담은 객체를 저장해서 문제를 풀었는데
시간초과가 오지게 나와서 이차원 리스트를 만들어 나이값만 저장해놓고 풀었다.
원철이가 가르쳐준 방법대로 리스트에서 add remove해주지말고 새로 리스트를 만들어
저장해놓고 한번에 옮기는 방법을 선택하니 통과했다... arraylist 삭제 시간이 길다는점을
다시 깨닳게 되었다!
