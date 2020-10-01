# [16236] 아기 상어 - Java

###  :shark: 분류

> 시뮬레이션
>
> BFS
>
> 우선 순위 큐



### :shark: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	
	static class Fish implements Comparable<Fish> {
		Dir loc;
		int size;
		
		public Fish(Dir loc, int size) {
			this.loc = loc; this.size = size;
		}

		public int compareTo(Fish o) {
			if(this.loc.y == o.loc.y) return this.loc.x - o.loc.x;
			else return this.loc.y - o.loc.y;
		}
	}
	
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static int N;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static int[][] map;
	static Fish shark;
	static int eatCnt;
	static int time;
	
	static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < N && 0 <= c.x && c.x < N) return true;
		else return false;
	}
	
	static boolean bfs(Fish shark) {
		Queue<Fish> q = new LinkedList<>(); 
		PriorityQueue<Fish> fishes = new PriorityQueue<>();
		int[][] v = new int[N][N];
		q.offer(shark);
		v[shark.loc.y][shark.loc.x] = 1;
		
		// bfs 돌면서 pq 사이즈가 1 이상이 되면 그 중에서 peek 에 있는 쪽으로 가자
		int curDist = 401;		// 먹을 수 있는 물고기를 찾은 경우 거기까지의 거리만 체크
		
		while(!q.isEmpty()) {
			Fish cur = q.poll();
			if(v[cur.loc.y][cur.loc.x] >= curDist) continue;  
			for(int i = 0; i < 4; i++) {
				Fish next = new Fish(new Dir(cur.loc.y + dy[i], cur.loc.x + dx[i]), cur.size);
				if(!isIn(next.loc) || v[next.loc.y][next.loc.x] != 0) continue;
				if(map[next.loc.y][next.loc.x] > next.size ) continue;
				
				if(map[next.loc.y][next.loc.x] != 0 && map[next.loc.y][next.loc.x] < next.size ) {
					fishes.offer(new Fish(next.loc, map[next.loc.y][next.loc.x]));
					curDist = v[cur.loc.y][cur.loc.x] + 1;
				}
				v[next.loc.y][next.loc.x] = v[cur.loc.y][cur.loc.x] + 1;
				q.offer(next);
			}
		}
		// 먹을 수 있는 물고기들이 생긴 경우
		if(!fishes.isEmpty()) {
			Fish fishToEat = fishes.poll();
			time += v[fishToEat.loc.y][fishToEat.loc.x] - 1;		// 가는데 걸린 시간 더해 줌
			eatCnt++;
			if(eatCnt == shark.size) {
				shark.size++;
				eatCnt = 0;
			}
			shark.loc = fishToEat.loc;
			map[shark.loc.y][shark.loc.x] = 0;
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					map[i][j] = 0;
					shark = new Fish(new Dir(i, j), 2);
				}
			}
		}
		
		while(true) {
//			System.out.println("shark loc(" + shark.loc.y + ", " + shark.loc.x + ") size: " + shark.size + ", time: " + time);
			if(!bfs(shark)) 
				break;
		}
		
		System.out.println(time);
	}
}
```



### :shark: 풀이 방법

5개월전에 못풀었던 아기상어...

생각보다 수월하게 풀었슴니다 ^^~~

메소드를 만들때 그게 무슨 일을 하고 뭘 리턴하는지 명확하게 정의하고 푸는 습관이 중요하다고 생각합니다~~



프로그램의 종료 조건은 **아기 상어가 더 이상 먹을 수 있는 물고기가 없는 경우** 입니다.

아기 상어와 물고기의 관계를 살펴봅시다.

| **상어의 크기 vs. 물고기의 크기** | **이동** | **먹기** |
| --------------------------------- | -------- | -------- |
| 상어의 크기 > 물고기의 크기       | O        | O        |
| 상어의 크기 == 물고기의 크기      | O        | X        |
| 상어의 크기 < 물고기의 크기       | X        | X        |

위와 같은 조건을 참고해서 기존 상어의 위치에서 BFS로 물고기를 먹을 수 있는 곳을 찾습니다.

BFS 메소드에서는 상어의 위치에서 먹을 수 있는 물고기들을 찾고, 그 중 먹을 물고기를 정해줄 겁니다.

물고기를 먹은 경우 true, 하나도 먹지 않은 경우는 **종료 조건에 부합**하므로 false를 리턴해줍니다. 

물고기를 먹는 우선 순위는 

1. 가장 가까운 물고기
2. 위에 있는 물고기
3. 왼쪽에 있는 물고기

입니다. 

이때, 먹을 수 있는 물고기를 하나 찾으면 그 곳까지의 거리를 갖고 그 이상은 검사하지 않음으로써 무조건 가장 가까운 거리에 있는 물고기들만 체크해줍니다. 먹을 수 있는 물고기들을 우선순위 큐에 넣어서 이 중 먹을 물고기를 바로 찾아줄 수 있습니다.

먹을 수 있는 물고기가 있는 경우 상어의 위치와 map과 time을 갱신하고 true를 리턴해줍니다.

그리고 상어가 먹을 물고기가 없는 경우(= bfs()의 리턴 값이 false 인 경우) 반복문을 끝내주면 됩니다.!



### :shark: 후기

PQ 짱!!

몇 개월 전에 봤을 때는 내 머리론 절대 못 풀겠다 느꼈는데 술술 풀려서 기분이 좋습니다!!

 

매일 매일 성장한다는 느낌 좋습니다!!!