# [19237] 어른 상어 - Java

###  :shark: 분류

> 시뮬레이션
>



### :shark: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static class Shark{
		int y, x, no, dir;
		Shark(int y, int x, int no, int dir){
			this.y = y; this.x = x; this.no = no; this.dir = dir;
		}
	}
	
	static int N, M, K;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	static Map<Integer, Integer>[][] smellMap;				// 상어 번호, 냄새가 사라질 때까지 남은 턴
	static Map<Integer, Shark> sharks = new TreeMap<>();	// 상어 번호, 상어
	static List<Integer>[][] map;
	static int[][][] priority;
	
	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solve());
	}

	public static int solve() {
		
		for(int answer = 0; answer <= 1000; answer++) {
			if(sharks.size() == 1) return answer;
			
			// 냄새
			for(int no : sharks.keySet()) {
				int cy = sharks.get(no).y;
				int cx = sharks.get(no).x;
				
				smellMap[cy][cx].put(no, K);
			}
			
			// 상어 이동
			for(int no : sharks.keySet()) {
				int cy = sharks.get(no).y;
				int cx = sharks.get(no).x;
				int cd = sharks.get(no).dir;
				
				int nd = priority[no][cd][0];
				int ny = cy + dy[nd];
				int nx = cx + dx[nd];
				
				boolean isBlank = false;		// 한 군데라도 이동할 빈 칸이 있으면 true
				for(int i = 0; i < 4; i++) {
					nd = priority[no][cd][i];
					ny = cy + dy[nd]; nx = cx + dx[nd];
					if(!isIn(ny, nx)) continue;
					if(smellMap[ny][nx].size() == 0) {
						isBlank = true; break;
					}
				}
				
				if(isBlank) {			// 인접한 곳 중에서 이동할 곳이 있는 경우
					// 상어 이동
					map[ny][nx].add(no);
					map[cy][cx].remove((Integer)no);
					
					sharks.replace(no, new Shark(ny, nx, no, nd));

				}else {
					// 자기 냄새 찾기
					boolean isMySmell = false;
					for(int i = 0; i < 4; i++) {
						nd = priority[no][cd][i];
						ny = cy + dy[nd]; nx = cx + dx[nd];
						if(!isIn(ny, nx)) continue;
						if(smellMap[ny][nx].containsKey(no)) {
							isMySmell = true; break;
						}
					}
					
					if(isMySmell) {			// 내 냄새 있으면
						map[ny][nx].add(no);
						map[cy][cx].remove((Integer)no);
						
						sharks.replace(no, new Shark(ny, nx, no, nd));
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					// 냄새 남은 턴 줄이기
					if(smellMap[i][j].size() != 0) {
						for(int no : smellMap[i][j].keySet()) {
							if(smellMap[i][j].get(no) == 1) smellMap[i][j].remove(no);
							else smellMap[i][j].replace(no, smellMap[i][j].get(no) - 1);
						}
					}
					
					// 상어 죽이기
					if(map[i][j].size() != 0) {
						Collections.sort(map[i][j]);
						for(int k = 1; k < map[i][j].size(); k++) {
							sharks.remove(map[i][j].get(k));
						}
						int alive = map[i][j].get(0);
						map[i][j].clear();
						map[i][j].add(alive);
					}
				}
			}
		}
		
		return -1;
	}
	
	public static boolean isIn(int y, int x) {
		if(0 <= y && y < N && 0 <= x && x < N) return true;
		else return false;
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
		smellMap = new Map[N][N];
		map = new List[N][N];
		priority = new int[M + 1][4][4];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				smellMap[i][j] = new HashMap<>();
				map[i][j] = new ArrayList<>();
				int no = Integer.parseInt(st.nextToken());
				if(no == 0) continue;
				map[i][j].add(no);
				if(map[i][j].get(0) == 0) continue;
				sharks.put(map[i][j].get(0), new Shark(i, j, map[i][j].get(0), 0));
				smellMap[i][j].put(no, K);
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= M; i++) {
			sharks.replace(i, new Shark(sharks.get(i).y, sharks.get(i).x, sharks.get(i).no, Integer.parseInt(st.nextToken()) - 1));
		}
		
		for(int i = 1; i <= M; i++) {
			for(int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int k = 0; k < 4; k++)
					priority[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
	}
}
```



### :shark: 풀이 방법

햐 1시간 20분 컷!!

아무 도움없이 시간안에 푼거 첨인듯



이번에는 지난 번의 과오를 교훈 삼아 오래 고민하고 키보드에 손을 올렸습니다

중간에도 시행착오가 조금 있었지만 어떻게 짜야할지 흐름을 많이 생각하고 푸니 모듈화도 더 잘 되고 각각의 부분이 무슨 작업을 하는지 확실히 보여서 코드가 잘 짜지는듯!!!

 

다음은 필요한 변수들입니다.

```
static Map<Integer, Integer>[][] smellMap;				// 상어 번호, 냄새가 사라질 때까지 남은 턴
static Map<Integer, Shark> sharks = new TreeMap<>();	// 상어 번호, 상어
static List<Integer>[][] map;							// 상어 위치. 둘 이상 있을때 정렬해서 get(0)만 살린다
```

 

전체적인 흐름은 생각보다 쉬웠습니다. 제일 먼저 상어들이 있는 위치에 냄시를 뿌려줍니다. 그다음 사방 탐색해서 빈 칸을 찾아서 빈 칸이 있으면 거기로 걍 가면 되고, 없으면 자기 냄새가 있는 쪽으로 가도록 하면 됩니다. 이 때 인풋으로 받은 우선순위를 적용해서 탐색을 해야 합니당. 각각 탐색해서 이동할 곳이 있으면 상어를 이동해주고 연관되는 변수들을 모두 갱신해줍니다. 

이제 냄새를 하나씩 줄이고, 상어가 두마리 이상 있는 공간에 있는 상어들을 죽여야 합니다. 모두 N*N 을 탐색해줘야하니 중첩 반복문 하나에서 같이 봐줍시다! 이 때 map을 PQ로 만들어서 바로 뽑아서 써먹어도 되겠습니다... 하지만 걍 정렬해서 젤 작은 친구를 뽑았습니다. 헤헤

 

최대 아웃풋이 1000이기 때문에 1000번만 반복문 돌리면 됩니다! 첨에 < 1000 해서 틀렸습니다.. 요곳도 잘 봅시다!!



### :shark: 후기

사용할 변수를 처음에 명확히 해놓으니 코드를 짤 때 누구누굴 수정해야 할지 바로 보여서 헷갈리지도 않고 아주 좋았슴다!! 

코테 때도 키보드는 나중에 건듭시다~!