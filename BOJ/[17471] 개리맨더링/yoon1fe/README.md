# [17471] 게리맨더링 - Java

###  :office: 분류

> BFS



### :office: 코드

```java
import java.io.*;
import java.util.*;

public class Main{
	static final int MIN = 9999999;
	static int[] population;
	static boolean[] isSelected;
	static int N;
	static int ans = MIN;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	
	public static boolean bfs(ArrayList<Integer> area) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] v = new boolean[N+1];
		q.offer(area.get(0));
		v[area.get(0)] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i = 0; i< graph.get(cur).size(); i++) {
				int next = graph.get(cur).get(i);
				if(!area.contains(next) || v[next]) continue;
				
				q.offer(next);
				v[next] = true;
			}
		}
		for(int i = 0; i < area.size(); i++) if(!v[area.get(i)]) return false;
		
		return true;
	}
	
	public static void solve(ArrayList<Integer> area1, ArrayList<Integer> area2) {
		if(!bfs(area1) || !bfs(area2)) return;

		int sum1 = 0, sum2 = 0;
		for(int i : area1) sum1 += population[i];
		for(int i : area2) sum2 += population[i];
		ans = Math.min(ans, Math.abs(sum1 - sum2));		
	}
	
	public static void makeSubset(int cnt) {
		if(cnt == N) {
			ArrayList<Integer> area1 = new ArrayList<Integer>();
			ArrayList<Integer> area2 = new ArrayList<Integer>();
			
			for(int i = 1; i<= N; i++) {
				if(isSelected[i]) area1.add(i);
				else area2.add(i);
			}
			if(area1.size() == N || area1.size() == 0) return;
			solve(area1, area2);
			
			return;
		}
		
		isSelected[cnt+1] = true;
		makeSubset(cnt+1);
		isSelected[cnt+1] = false;
		makeSubset(cnt+1);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		population = new int[N+1];
		isSelected = new boolean[N+1];
		for(int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		graph.add(new ArrayList<>());
		for(int i = 1; i<= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			ArrayList<Integer> t = new ArrayList<Integer>();
			int num =  Integer.parseInt(st.nextToken());
			for(int j = 0; j< num; j++) {
				t.add(Integer.parseInt(st.nextToken()));
			}
			graph.add(t);
		}
		
		makeSubset(0);
		
		if(ans == MIN) ans = -1;
		System.out.println(ans);	
	}
}
```



### :office: 풀이 방법

`N`개의 구역이 주어지고 인접한 구역들을 묶어서 두 개의 선거구를 만듭니다. 두 선거구의 인구 차이의 최솟값을 구하는 문제입니다. 부분집합을 만들고 `BFS`로 인접한지 탐색했습니다.

처음에 인접한 구역들의 부분집합을 어떻게 구할까 골치가 아팠습니다. 근디 `N`의 최댓값이 10이기 때문에 부분집합의 최대 개수가 `1022 (2^10 - 2 공집합 하나랑 N개자리 하나)`이기 때문에 부분집합을 만들고 이 부분집합이 유효한 놈인지 체크해주는 식으로 풀었습니다. 어차피 부분집합 두 개를 만드는 거라서 `1022 / 2 번` 만큼만 봐주면 되긴 한데 중복되는걸 어떻게 체크할지는 고민 안 해봤습니다.

 

먼저 부분집합을 구해주고 `ArrayList 두 개(선거구 두 개)`를 생성해줍니다.

각 선거구를 입력 데이터로 `BFS`를 돌려주면서 선거구 안에 있는 구역들이 인접해있는지를 체크합니다. 이 때 선거구에 포함되어있지 않은 구역은 탐색을 하지 않으면 됩니다.

 

이렇게 두 선거구를 `BFS`로 탐색해서 유효한 선거구들인지 검사한 후, 인구 수의 차를 계산해주면서 최솟값을 업데이트해나가면 됩니다.



### :office: 후기

감사합니다 ._.