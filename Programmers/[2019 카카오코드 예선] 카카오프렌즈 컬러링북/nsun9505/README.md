# 

## 분류
> BFS

## 코드
```java
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
```

## 문제 풀이
오우 오랜만에 BFS 문제를 만나보네용

예선문제라서 그런지 매우 이지합니다.

영역의 개수를 구할 때는 BFS를 돌린 횟수를 카운트하면 됩니다!
- 0은 포함하지 않습니다. 

영역의 크기를 구할 때는 BFS를 돌려서 구하면 됩니다.
- 같은 번호로 상하좌우로 연결된 영역의 크기를 구하기!
- 그 영역 크기가 가장 큰 것을 maxSizeOfOneArea에 담습니다.

## 후기
오랜만에 순한맛 ㅎㅎ

테케가 1라서 조금 놀람 ㅎㅎ