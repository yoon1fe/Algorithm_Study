# [17609] 회문 - Java

###  :love_letter: 분류

> 그리디

​

### :love_letter: 코드

```java
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		input();
	}
	
	public static int check(String str) {
		if(checkPalindrome(str) == true) return 0;
		else if(checkPseudoPalindrome(str) == true) return 1;
		else return 2;
	}
	
	public static boolean checkPalindrome(String str) {
		int s = 0, e = str.length() - 1;
		
		while(s <= e) {
			if(str.charAt(s++) != str.charAt(e--)) return false;
		}
		
		return true;
	}

	public static boolean checkPseudoPalindrome(String str) {
		int s = 0, e = str.length() - 1;

		while(s <= e) {
			if(str.charAt(s) != str.charAt(e)) {
				// 왼쪽 없애고 난 뒤의 문자열 | 오른쪽 없애고 난 뒤의 문자열 중 하나라도 회문이 있으면 유사 회문
				return checkPalindrome(str.substring(s+1, e+1)) | checkPalindrome(str.substring(s, e));
			}
			
			s++; e--;
		}

		return true;
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			bw.write(String.valueOf(check(br.readLine())));
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}
}
package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static int N, M;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static int[][] map;
	static boolean [][] v;
	static List<Map<Integer, Integer>> graph = new ArrayList<>();
	
	static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < N && 0 <= c.x && c.x < M) return true;
		else return false;
	}
	
	public static void makeIslandNo(Dir s, int no){
		Queue<Dir> q = new LinkedList<>();
		q.offer(s);
		v[s.y][s.x] = true;
		map[s.y][s.x] = no;
		
		while(!q.isEmpty()) {
			Dir cur = q.poll();
			
			for(int i = 0 ; i < 4; i++) {
				Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
				if(!isIn(next) || v[next.y][next.x] == true || map[next.y][next.x] == 0) continue;
				
				v[next.y][next.x] = true;
				map[next.y][next.x] = no; 
				q.offer(next);
			}
		}
	}
	
	public static void makeBridge(Dir s, int curNo, int dir, int len) {
		if(map[s.y][s.x] != 0 && map[s.y][s.x] != curNo) {
			if(len == 2) return;
			if(graph.get(curNo).containsKey(map[s.y][s.x])) {
				int minDist = Math.min(graph.get(curNo).get(map[s.y][s.x]), len - 1);
				graph.get(curNo).replace(map[s.y][s.x], minDist);
			}
			else graph.get(curNo).put(map[s.y][s.x], len - 1);
			return;
		}
		
		Dir next = new Dir(s.y + dy[dir], s.x + dx[dir]);
		if(!isIn(next) || map[next.y][next.x] == curNo) return;
		makeBridge(next, curNo, dir, len + 1);
	}
	
	public static int prim() {
		boolean[] visited = new boolean[graph.size()];
		int[] minEdge = new int[graph.size()];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		
		int minVertex;
		int min, result = 0;
		minEdge[1] = 0;
		
		for(int c = 0; c < graph.size() - 1; c++) {
			min = Integer.MAX_VALUE;
			minVertex = 1;
			
			for(int i = 1; i < graph.size(); i++) {
				if(!visited[i] && minEdge[i] < min) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			result += min;
			visited[minVertex] = true;
			
			for(Integer to : graph.get(minVertex).keySet()) {
				int weight = graph.get(minVertex).get(to);
				if(!visited[to] && weight < minEdge[to]) {
					minEdge[to] = weight;
				}
			}
		}
		
		int falseCnt = 0;
		for(int i = 1; i < visited.length; i++) {
			if(!visited[i]) falseCnt++;
		}
		
		return falseCnt == 0 ? result : -1;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		v = new boolean[N][M];
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬 번호 매기기
		int no = 1;
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0 && !v[i][j]) {
					makeIslandNo(new Dir(i, j), no++);
				}
			}
		}
		
		for(int i = 0; i < no; i++) graph.add(new HashMap<>());
		
		// 섬 사이에 놓을 수 있는 다리의 최소 길이 구하기
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0) {
					for(int k = 0; k < 4; k++) {
						makeBridge(new Dir(i, j), map[i][j], k, 0);
					}
				}
			}
		}
		
		// MST 만들기
		System.out.println(prim());
	}
}
```



### :love_letter: 풀이 방법

오랜만입니다!!

합격 연락받고 방 구하고 짐싸고 이사하고 출근하고 한다고 정신없는 연말연초를 보내고 오랜만에 노트북 앞에 앉아 보았습니다.

 

실버1이길래 스근할 줄 알았는데 생각보다 쪼끔 까다로웠습니다 ^^;;;;



처음에는 단순히 문자열에서 하나씩 뺀 문자열을 구하고 얘가 회문인지를 체크했는데 시간초과가 떴습니다..

이유가 무엇인고하니 String .equals() 메소드의 시간 복잡도가 O(n)인 것이였슴다... 유사회문인지 체크하기 위해 새로 만든 문자열 O(n) 에 equals() 메소드를 호출하니 O(n^2)가 되는 것이지요..

 

두번째로는 intern() 메소드를 이용하는 방법을 생각햇습니다. 새로 만든 애를 intern()을 호출해서 같은 문자열이 있다면같은 애를 가리키도록 하는 것이지요. 이렇게 해서 .hashCode()의 값이 같은지 여부로 같은 문자열인지 체크했습니다..

하지만 이렇게 하면 String pool 에 문자열이 없다면 새로 만들기 때문에 메모리 초과가 떠버렸습니다...

 

그렇게 해서 회문 여부를 체크하는 로직부터 다 뜯어 고쳤습니다. 문자열의 왼쪽/오른쪽 을 비교해나가는 것이져. 회문은 단순히 이렇게 비교해가며 구할 수 있고, 유사 회문은 한 번 기회를 주는 겁니다. 왼/오 서로 다른 문자가 나오면 (왼쪽 애를 제외한 나머지가 회문인지 | 오른쪽 애를 제외한 나머지가 회문인지) 를 체크하는 것이죠. 둘 중에 하나라도 회문이라면 얘는 유사 회문이니깐 or 문으로 묶어씀니다.



### :love_letter: 후기

역시 꾸준히 하지 않으면 머리도 굳고 손가락도 굳는듯 합니다.

다시 화이팅!!!