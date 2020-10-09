# [17822] 원판 돌리기 - Java

###  :white_circle: 분류

> 시뮬레이션
>
> BFS

​

### :white_circle: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static class Dir{
		int y, x;					// y번 원판의 x번 자리
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static int N, M, T, total, totalCnt;
	static List<Integer>[] circles;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); T = Integer.parseInt(st.nextToken());
		
		circles = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			circles[i] = new ArrayList<>();
			for(int j = 0; j < M; j++) {
				circles[i].add(Integer.parseInt(st.nextToken()));
				total += circles[i].get(j);
				totalCnt++;
			}
		}
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()); int d = Integer.parseInt(st.nextToken()); int k = Integer.parseInt(st.nextToken());
			
			rotate(x, d, k);
			remove();
		}
		
		System.out.println(total);
	}
	
	public static void rotate(int x, int d, int k) {
		for(int i = x; i <= N; i += x) {
			List<Integer> temp = new ArrayList<>();
			if(d == 0) {
				temp.addAll(circles[i].subList(M - k, M));
				temp.addAll(circles[i].subList(0, M - k));
			}else {
				temp.addAll(circles[i].subList(k, M));
				temp.addAll(circles[i].subList(0, k));
			}
			circles[i].clear();
			circles[i].addAll(temp);
		}
	}

	public static void remove() {
		boolean[][] v = new boolean[N+1][M];
		boolean isDelete = false;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				if(v[i][j] || circles[i].get(j) == 0) continue;
				isDelete = isDelete | bfs(new Dir(i, j), v);
			}
		}
		
		if (!isDelete) {
			double avg = (double)total / totalCnt;

			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if (circles[i].get(j) == 0) continue;
					if (circles[i].get(j) > avg) {
						circles[i].set(j, circles[i].get(j) - 1);
						total--;
					}
					else if (circles[i].get(j) < avg) {
						circles[i].set(j, circles[i].get(j) + 1);
						total++;
					}
				}
			}
		}
		
	}

	public static boolean bfs(Dir s, boolean[][] v) {
		Queue<Dir> q = new LinkedList<>();
		int[] dx = {1, -1};
		int cn = circles[s.y].get(s.x);
		int cnt = 1;
		
		q.offer(s);
		v[s.y][s.x] = true;
		
		while(!q.isEmpty()) {
			Dir cur = q.poll();
			int ny, nx;
			
			// 같은 원판
			ny = cur.y;
			for(int i = 0; i < 2; i++) {
				nx = (cur.x + dx[i] + M) % M;
				if(v[ny][nx]) continue;
				if(circles[ny].get(nx) == cn) {
					circles[ny].set(nx, 0);
					total -= cn;
					totalCnt--;
					cnt++;
					v[ny][nx] = true; 
					q.offer(new Dir(ny, nx));
				}
			}
			
			// 같은 위치
			nx = cur.x;
			for(int i = 0; i < 2; i++) {
				ny = cur.y + dx[i];
				if(ny < 1 || ny > N) continue;
				if(v[ny][nx]) continue;
				if(circles[ny].get(nx) == cn) {
					circles[ny].set(nx, 0);
					total -= cn;
					totalCnt--;
					cnt++;
					v[ny][nx] = true; 
					q.offer(new Dir(ny, nx));
				}
			}
		}
		
		if(cnt != 1) {
			circles[s.y].set(s.x, 0);
			total -= cn; totalCnt--;
			return true;
		}else return false;
	}
}
```



### :white_circle: 풀이 방법

삼성 기출이 끝을 보고 있습니다..

아우 전에 풀었던건데 쫌 걸렸네여 ㅜㅜ 1시간 10분걸렸넹



코드가 쫌 더럽습니당 ,,, ㅜ

입력을 잘 받고, 큰 액션 (원판 돌리기, 인접한 숫자 없애기) 을 나누어서 메소드를 만들었습니다.

 

먼저 **rotate()** 메소드입니다.

x의 배수 번 원판을 돌리면 됩니다. i +=x; 로 해당하는만큼만 반복했습니다. 방향(d)와 돌리는 칸(k)에 따라 필요한 만큼 돌리면 됩니다. 요건 앞에 애를 먼저 똑 떼서 넣고 뒤에 넣고,, 뭐 이런 식으로 조금만 생각해보면 구현할 수 있지용.

 

관건은 **remove()** 메소드입니다.

맨 처음 짤 때 지운 숫자가 하나도 없으면 해야 하는 작업을 안해줘서 골치 아팠습니다... 문제를 정독하고 생각을 시작합시다..

 

N * M 만큼 보면서 BFS를 돌리면 됩니다. 이미 없앤 애를 봐주기 위해 v 배열을 한 턴에서 공유해야 합니다. 아 근데 지금 보니 0인지 아닌지만 봐도 되겠네여 ㅎ;;

암튼 들어가서 인접한 애들을 봐야 합니다. 어째 보느냐?? i번 원판의 j번째 자리를 [i][j]라고 했을때, i +- 1 두 번 보고, j +- 1 두 번 보면 됩니다. i +- 1은 인접한 원판을 보는 것이고, j +- 1은 한 원판 안에서 인접한 친구들을 봐주는 것이죠.

평균을 바로바로 구해주기 위해 원판 상의 숫자의 개수 totalCnt, 그 숫자들의 총 합 total 을 그때그때 갱신해주느라 코드가 쪼금 더럽네용 허허

 

다 봐주고나서 이 bfs() 메소드는 boolean 을 반환해줄겁니다. 인접한 친구가 있어서 없앴다면 (cnt != 1) true, 그렇지 않다면 false를 반환합니다. remove() 메소드에서는 OR 연산을 통해 그 턴에서 없앤 숫자가 하나도 없는지를 체크하는 것이죵. 만약 하나도 없으면 평균을 구하고 원판의 숫자들을 갱신하면 됩니다. 요때도 또 total을 같이 갱신해줘야겠져 ,,,^^;;



### :white_circle: 후기

세시간에 두문제... 풀 수 있을거같기도 하고~~ 못할거같기도 하고~~

얼른 올해 상반기 문제들도 풀어봐야게씀당~~!!

감사합니당!!