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