# [16235] 나무 재테크 - Java

###  :evergreen_tree: 분류

> 시뮬레이션
>

​

### :evergreen_tree: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[][] trees;
	static int[][] map;
	static int[][] nutrient;
	static int N, M, K;
	static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	static boolean isIn(int y, int x) {
		if(1 <= y && y <= N && 1 <= x && x <= N) return true;
		else return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int answer = 0;
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		trees = new ArrayList[N+1][N+1];
		map = new int[N+1][N+1];
		nutrient = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= N; j++) {
				trees[i][j] = new ArrayList<>();
				nutrient[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			trees[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < K; i++) {
			year();
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				answer += trees[i][j].size();
			}
		}
		
		System.out.println(answer);
	}
	
	public static void year() {
		List<Integer>[][] newTrees = new ArrayList[N+1][N+1];
		
		// spring - summer
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				int dead = 0;
				newTrees[i][j] = new ArrayList<>();
				if(trees[i][j].isEmpty()) continue;
				if(trees[i][j].size() > 1) Collections.sort(trees[i][j]);
				List<Integer> temp = new ArrayList<>();
				
				for(int k = 0; k < trees[i][j].size(); k++) {
					if(map[i][j] >= trees[i][j].get(k)) {
						map[i][j] -= trees[i][j].get(k);
						temp.add(trees[i][j].get(k) + 1);
					}else dead += trees[i][j].get(k) / 2;
				}
				
				map[i][j] += dead;
				trees[i][j].clear();
				trees[i][j].addAll(temp);
			}
		}
		
		// fall
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(trees[i][j].isEmpty()) continue;
				
				for(int k = 0; k < trees[i][j].size(); k++) {
					if(trees[i][j].get(k) % 5 != 0) continue;
					
					for(int d = 0; d < 8; d++) {
						int ny = i + dy[d];
						int nx = j + dx[d];
						if(!isIn(ny, nx)) continue;
						trees[ny][nx].add(1);
					}
				}
			}
		}
		
		// winter
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(nutrient[i][j] == 0) continue;
				map[i][j] += nutrient[i][j];
			}
		}
	}
}
```



### :evergreen_tree: 풀이 방법

빡구현 문제입니다.

비교적 빠른 시간에 다 짜고 제출했는데 시간초과가 떠서 쪼끔 골치 아팠슴니다...

자료 구조를 결정할 때는 문제를 다 읽고 어떤 조건이 있는지, 그 조건을 어떻게 해결할 것인지 많이 고민해보고 정해야겠습니다.

 

상도의 땅 한 칸 안에 여러 개의 나무가 있을 수 있고, 가장 작은 나무부터 양분을 먹어야 하네용.

그래서 **ArrayList<>** 2차원 배열로 구현했습니다.

 

죽는 나무를 결정할 때 양분이 될 양을 바로 구할 수 있기 때문에 봄-여름, 가을, 겨울 로 봅시당.

 

먼저 봄-여름에는 각 나무들이 양분을 먹을 수 있는지 없는지 체크하는게 가장 중요합니다. 먹을 수 없다면 그 나무는 죽습니다 ㅜㅜ.

 

**!!! 여기서 시간초과가 떴씁니다 !!!**

List에 있는 값을 갱신해야 하기 때문에 처음에는 add 후 remove를 하는 식으로 코드를 짰는데 이게 오버헤드가 큰가봅니다..

그래서 temp라는 List를 선언해서 거기에 새로 갱신된 값을 넣고, 기존의 List를 temp로 바꾸어줬습니당 ^_^

 

가을에는 나무가 번식합니다. 5의 배수인 경우에 인접한 8칸에 나이 1짜리 나무를 만들어주면 됩니다. 

 

마지막 겨울에는 땅의 양분에 기계 뭐시기가 추가해주는 양분을 더해주면 됩니당.

 

이러한 봄-여름-가을-겨울에 해당하는 행동을 K번만큼 수행한 뒤 남아있는 나무의 수를 리턴하면 끝!!



### :evergreen_tree: 후기

시뮬레이션 문제는 읽을 때는 짜증나는데 푸는건 재밌숩니다~!~!~!

2020년 4/4분기 화이팅!!!!!!!!!!!!!!!!!!!!!!!!!!