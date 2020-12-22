import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
	static class Element{
		int row;
		int col;
		
		Element(int r , int c){
			this.row = r;
			this.col = c;
		}
	}
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] isVisited;
    public int[] solution(int m, int n, int[][] picture) {
    	int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        isVisited = new boolean[m][n];
        
        for(int i=0; i<m; i++) {
        	for(int j=0; j<n; j++) {
        		if(isVisited[i][j] == true || picture[i][j] == 0)
        			continue;
        		numberOfArea++;
        		int areaSize = getAreaSize(picture, m, n, i, j);
        		maxSizeOfOneArea = maxSizeOfOneArea < areaSize ? areaSize : maxSizeOfOneArea;
        	}
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public static int getAreaSize(int picture[][], int m, int n, int row, int col) {
    	Queue<Element> Q = new LinkedList<>();
    	isVisited[row][col] = true;
    	Q.offer(new Element(row, col));
    	int cnt = 1;
    	while(!Q.isEmpty()) {
    		Element elem = Q.poll();
    		
    		for(int dir = 0; dir < 4; dir++) {
    			int nx = elem.row + dx[dir];
    			int ny = elem.col + dy[dir];
    			
    			if(nx < 0 || ny < 0 || nx >= m || ny >= n)
    				continue;
    			if(picture[row][col] != picture[nx][ny] || isVisited[nx][ny])
    				continue;
    			
    			isVisited[nx][ny] = true;
    			Q.offer(new Element(nx, ny));
    			cnt++;
    		}
    	}
    	return cnt;
    }
}
