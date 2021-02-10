# [14502] 연구소 - JAVA

## 분류
> 구현
>
> BFS

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] isVisited;
	static Queue<Element> queue = new LinkedList<>();
	static ArrayList<Element> emptyList = new ArrayList<>();
	static ArrayList<Element> virusList = new ArrayList<>();
	static int emptyCnt = 0;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		isVisited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0)
					emptyList.add(new Element(i, j));
				else if(map[i][j] == 2)
					virusList.add(new Element(i, j));
			}
		}
		
		emptyCnt = emptyList.size();
		
		DFS(0, 0);
		
		sb.append(ans);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void DFS(int cnt, int idx) {
		if(cnt == 3) {
			int ret = BFS();
			ans = Math.max(ret, ans);
			return;
		}
		
		if(idx >= emptyList.size())
			return;
		
		Element elem = emptyList.get(idx);
		map[elem.row][elem.col] = 1;
		DFS(cnt+1, idx+1);
		
		map[elem.row][elem.col] = 0;
		DFS(cnt, idx+1);
	}
	
	public static int BFS() {
		int ret = 0;
		for(int i=0; i<N; i++)
			Arrays.fill(isVisited[i], false);
		queue.clear();
		for(Element virus : virusList) {
			queue.offer(virus);
			isVisited[virus.row][virus.col] = true; 
		}
		
		while(!queue.isEmpty()) {
			Element virus = queue.poll();
			
			for(int dir = 0; dir<4; dir++) {
				int nx = virus.row + dx[dir];
				int ny = virus.col + dy[dir];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if(isVisited[nx][ny] || map[nx][ny] == 1)
					continue;
				
				ret++;
				queue.offer(new Element(nx, ny));
				isVisited[nx][ny] = true;
			}
		}
		
		ret = emptyCnt - 3 - ret;
		return ret;
	}
	
	static class Element{
		int row;
		int col;
		
		Element(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
}
```

## 문제 풀이
0인 위치를 list에 담습니다.
   - 이를 emptyList라고 이름을 붙이겠습니다.

virus가 있는 위치도 list에 담습니다.

emptyList에 대해서 3개를 선택한 경우를 알아내기 위해 DFS를 돌립니다.
   - emptyList에서 하나를 꺼내서 그 위치에 0대신 1을 넣는 것입니다.
   - 다시 돌아올 떄는 0으로 돌려 놓습니다.

DFS를 돌려서 3개의 벽을 세울 때마다 BFS를 돌려서 안전영역의 크기를 알아냅니다.
   - 0인 공간이 안전한 곳이므로, 0인 곳의 카운트를 emptyList()로 알 수 있고 여기에
   - 3개의 0인 곳에 벽(1)을 세웠으므로 -3을하고
   - BFS로 바이러스가 퍼진곳(0)에 대해서 카운트를 해서 뺴주면 된다.

BFS를 돌린 결과는 안전 영역의 크기를 알려주므로 리턴 값과 답으로 출력할 값과 어떤 것이 더 큰지 비교합니다.
   - BFS 리턴값이 더 크다면 답을 갱신하면 됩니다.

## 후기
옛날에 많이 틀렸구만유~