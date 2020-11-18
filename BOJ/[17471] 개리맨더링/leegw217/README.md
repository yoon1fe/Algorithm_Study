# [17471] 게리맨더링 - Java

###  :octocat: 분류

> 그래프
> BFS
> 부분집합

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class week13_게리맨더링 {
	static int N;
	static int[] people;
	static ArrayList<Integer>[] adjlist;
	static int[] arr; 
	static ArrayList<Integer> listA;
	static ArrayList<Integer> listB;
	static int min = Integer.MAX_VALUE;
	static boolean flag = false;
	
	static boolean bfs(int start, ArrayList<Integer> list) {
		boolean[] visited = new boolean[N];
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int p = q.poll();

			for(int i=0; i<adjlist[p].size(); i++) {
				if(visited[adjlist[p].get(i)]) continue;
				if(!list.contains(adjlist[p].get(i))) continue;
				q.offer(adjlist[p].get(i));
				visited[adjlist[p].get(i)] = true;
			}
		}
		int cnt = 0;
		for(int i=0; i<N; i++) 
			if(visited[i]) cnt++;
		return cnt == list.size() ? true : false;
	}
	
	static void makeProduct(int begin, int cnt, int n) {
		if(cnt == n) {
			for(int i=0; i<N; i++)
                if(!listA.contains(i)) listB.add(i);
			
			if(bfs(listA.get(0), listA) && bfs(listB.get(0), listB)) {
				flag = true;
				int aSum = 0, bSum = 0;
				for(int a=0; a<listA.size(); a++) 
					aSum += people[listA.get(a)];
				for(int b=0; b<listB.size(); b++) 
					bSum += people[listB.get(b)];
				if(min > Math.abs(aSum - bSum)) min = Math.abs(aSum - bSum);
			}
			listB.clear();
			return;
		}
		
		for(int i=begin; i<arr.length; i++) {
			listA.add(arr[i]);
			makeProduct(i+1, cnt+1, n);
			listA.remove(listA.size()-1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		people = new int[N];
		adjlist = new ArrayList[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
			adjlist[i] = new ArrayList<Integer>();
		}
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int j=0; j<n; j++)
				adjlist[i].add(Integer.parseInt(st.nextToken())-1);
		}
		arr = new int[N];
		for(int i=0; i<N; i++) arr[i] = i;
		for(int k=1; k<=N/2; k++) {
			listA = new ArrayList<Integer>();
			listB = new ArrayList<Integer>();
			makeProduct(0, 0, k);
		}
		
		if(!flag) System.out.println(-1);
		else System.out.println(min);
	}
}
```

### :octocat: 풀이 방법

1. 구역들의 부분집합을 구해서 모든 구역을 두 선거구로 나눈다.
2. 나눈 두 선거구에서 각각 BFS를 돌려 선거구 내에 모든 구역이 이동 가능한지 체크한다.
3. 두 선거구 모두 각자 이동 가능하다면 두 선거구의 인구수 차이를 구한다.
4. 모든 경우를 계산해 인구수 차이의 최솟값을 구한다.

### :octocat: 후기

이 문제도 싸피에서 풀었던 문제였다. 선거구가 서로 이어져있는지 확인하는 함수를 짤 때 bfs 말고
dfs나 다른 그래프 알고리즘을 써서 풀어볼걸 그랬다..
