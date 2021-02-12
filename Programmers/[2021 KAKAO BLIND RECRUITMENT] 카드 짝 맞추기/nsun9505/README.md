# [2021 KAKAO BLIND RECRUITMENT] 카드 짝 맞추기 - JAVA

## 분류
> 순열
>
> BFS
>
> 구현

## 코드
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Queue<Cursor> queue = new LinkedList<>();
	static boolean[][] isVisited = new boolean[4][4];
	static int[][] origin = new int [4][4];
	static int[][] tmp = new int[4][4];
	static int startRow, startCol;
	static HashMap<Integer, ArrayList<Element>> cardMap = new HashMap<>();
	static int ans = Integer.MAX_VALUE;
	public int solution(int[][] board, int r, int c) {
		origin = board;
		startRow = r;
		startCol = c;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(board[i][j] == 0)
					continue;
				
				int card = board[i][j];
				if(!cardMap.containsKey(card))
					cardMap.put(card, new ArrayList<Element>());
				cardMap.get(card).add(new Element(i, j));
			}
		}
		
		Set<Integer> keys = cardMap.keySet();
		int[] cards = new int[keys.size()];
		int idx = 0;
		for(int key : keys)
			cards[idx++] = key;
		
		DFS(0, cards);
		
		return ans;
	}
	
	public static int BFS(int[][] map, int startRow, int startCol, int targetRow, int targetCol) {
		
		int result = Integer.MAX_VALUE;
		queue.clear();
		for(int i=0; i<4; i++)
			Arrays.fill(isVisited[i], false);
		isVisited[startRow][startCol] = true;
		queue.offer(new Cursor(startRow, startCol, 0));
		

		while(!queue.isEmpty()) {
			Cursor cur = queue.poll();
			
			if(cur.row == targetRow && cur.col == targetCol) {
				result = cur.dist;
				break;
			}
			
			// 한 칸 씩 + Ctrl 가는 경우
			for(int dir =0; dir < 4; dir++) {
				Element ret = searchCard(map, cur.row, cur.col, dir);
				
				if(isVisited[ret.row][ret.col] && (ret.row != cur.row || ret.col != cur.col)) {				
					isVisited[ret.row][ret.col] = true;
					queue.offer(new Cursor(ret.row, ret.col, cur.dist + 1));
				}
				
				int nx = cur.row + dx[dir];
				int ny = cur.col + dy[dir];
				
				if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
					continue;
				
				if(isVisited[nx][ny])
					continue;
				
				isVisited[nx][ny] = true;
				queue.offer(new Cursor(nx, ny, cur.dist + 1));
			}
		}
		
		return result + 1;
	}
	
	public static Element searchCard(int[][] map, int row, int col, int dir) {
		Element elem = new Element(row, col);
		
		while(true) {
			int nx = elem.row + dx[dir];
			int ny = elem.col + dy[dir];
			
			if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
				break;
			
			elem.row = nx;
			elem.col = ny;
			
			if(map[nx][ny] > 0)
				break;
		}
		
		return elem;
	}
	
	public static void DFS(int idx, int[] cards) {
		if(idx >= cards.length) {
			for(int i=0; i<4; i++)
				for(int j=0; j<4; j++)
					tmp[i][j] = origin[i][j];
			
			int tmpStartRow = startRow;
			int tmpStartCol = startCol;
			int sum = 0;
			for(int card : cards) {
				ArrayList<Element> list = cardMap.get(card);
				
				int ret1 = BFS(tmp, tmpStartRow, tmpStartCol, list.get(0).row, list.get(0).col);
				ret1 += BFS(tmp, list.get(0).row, list.get(0).col, list.get(1).row, list.get(1).col);
				
				int ret2 = BFS(tmp, tmpStartRow, tmpStartCol, list.get(1).row, list.get(1).col);
				ret2 += BFS(tmp, list.get(1).row, list.get(1).col, list.get(0).row, list.get(0).col);
				
				tmp[list.get(0).row][list.get(0).col] = 0;
				tmp[list.get(1).row][list.get(1).col] = 0;
				
				if(ret1 < ret2) {
					sum += ret1;
					tmpStartRow = list.get(1).row;
					tmpStartCol = list.get(1).col;
				} else {
					sum += ret2;
					tmpStartRow = list.get(0).row;
					tmpStartCol = list.get(0).col;
				}
				
				if(ans < sum)
					return;
			}
			
			ans = sum;
			
			return;
		}
		
		for(int i=idx; i < cards.length; i++) {
			swap(cards, idx, i);
			DFS(idx+1, cards);
			swap(cards, idx, i);
		}
	}
	
	public static void swap(int[] cards, int x, int y) {
		int tmp = cards[x];
		cards[x] = cards[y];
		cards[y] = tmp;
	}
	
	static class Element{
		int row;
		int col;
		
		Element(int r, int c){
			this.row = r;
			this.col = c;
		}
	}
	
	static class Cursor{
		int row;
		int col;
		int dist;
		
		Cursor(int row, int col, int dist){
			this.row = row;
			this.col = col;
			this.dist = dist;
		}
	}
}

```

## 문제 풀이
순열과 BFS로 풀 수 있습니다!

순열에 대한 방법은 카카오 문제 풀이를 보고 알았습니다.
   - 카드가 많아 봤자 6개이므로 6!의 순서로 카드의 짝을 맞추는 것입니다.

순열로 6!를 찾아낸 다음에 BFS로 카드를 맞춥니다

주의할 점은 X라는 카드의 짝을 맞출 때 X에도 2 개의 카드가 있으니 예를 들어 1번 카드의 짝을 맞춘다고 할 때 1A, 1B라고 하겠습니다.
   - 1A -> 1B를 맞추는 경우도 있을 것이고
   - 1B -> 1A를 맞추는 경우도 있을 것입니다.
   - 그러면 커서의 위치에 따라 두 가지 방법을 돌리면 움직이는 횟수가 다를 것입니다.
   - 현재 커서 위치 -> 1A -> 1B로 가는 경우와 현재 커서 위치 -> 1B -> 1A로 가는 경우를 비교합니다.
   - 둘 중 작은 키조작 횟수를 가지는 것을 답으로 선택합니다.

답으로 선택한 카드가 1일 때 1의 위치에 0을 넣어서 맞췄다는 것을 표시합니다.

그리고 X번 카드를 맞춘 뒤에 Y번 카드를 맞출 때는 X번 카드를 맞춘 마지막 위치에서 시작하도록 start 값을 변경해줍니다.
   - 1번 카드를 맞출 때 1A -> 1B가 1B -> 1A보다 키 조작횟수가 작다면 다음 시작 위치를 1B로 변경해줍니다.
   - 1번 카드를 맞출 때 1B -> 1A가 1A -> 1B보다 키 조작횟수가 작다면 다음 시작 위치를 1A로 변경해줍니다.

마지막으로 순열로 찾은 순서로 카드를 다 찾았을 때의 키조작횟수와 답으로 출력할 키조작횟수를 비교하여 작다면 답을 갱신해주면 됩니다.

## 후기
후아 마지막에 빠뜨린 조건 하나만 생각해냈으면 풀 수 있었을 텐데 계속 테케 2개만 틀리길래 이것저것 고치다가 다른 사람들이 푼 소스를 참고하니깐 체크를 안 한거 발견하고 고쳐보니 바로 통과ㅠㅠ

22번이랑 28번이 안 맞았습니다ㅠ

문제는 Ctrl을 눌러서 이동할 때 해당 방향으로 가는데 이미 방문 체크가 되어 있으면 거기서 멈추는 것이였습니다.
   - Ctrl눌러서 이동할 경우 카드가 없으면 맨 끝으로 가고, 카드가 있으면 카드가 있는 곳으로 이동하게 됩니다.
   - 예를 들어 [X][0][1] 이라고 할 때 X에서 1인 위치로 Ctrl + -> 눌러서 이동할 수 있습니다.
   - 하지만 22, 28을 틀리는 코드에서는 0인 칸이 이미 방문했다는 표시가 되어 있으면 1이 방문이 되지 않았어도 더 이상 가지 않는 문제점이 있었습니다.
   - 그래서 Ctrl을 눌러서 이동하 떄는 무조건 해당 방향의 끝으로 이동시키거나 처음으로 카드가 나오는 위치로 이동시키도록 구현하니 22, 28번 테케가 맞으면서 통과를 받았습니다.