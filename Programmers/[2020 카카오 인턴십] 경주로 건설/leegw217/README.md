# [2020 카카오 인턴십] 경주로 건설 - Java

###  :octocat: 분류

> BFS

### :octocat: 코드

```java
import java.util.LinkedList;
import java.util.Queue;
public class week05_경주로건설 {
	class Point{
		int x, y; // 좌표
		int dir; // 이전 움직인 방향
		int cost; // 비용
		Point(int x, int y, int dir, int cost){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cost = cost;
		}
	}
	
	public int solution(int[][] board) {
		int answer = Integer.MAX_VALUE;
        int N = board[0].length;
    	int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int[][] visited = new int[N][N];
        Queue<Point> q = new LinkedList<Point>();
        q.offer(new Point(0,0,-1,0));
        visited[0][0] = Integer.MIN_VALUE;
        
        while(!q.isEmpty()) {
        	Point p = q.poll();
        	if(p.x==N-1 && p.y==N-1) // 최종지점에 도착했을 때 비용 최솟값
        		if(answer > p.cost) answer = p.cost;
        	
        	for(int dir=0; dir<4; dir++) {
    			int nx = p.x + dx[dir];
    			int ny = p.y + dy[dir];
    			
    			if(nx<0||nx>=N||ny<0||ny>=N) continue;
        		if(board[nx][ny]==1) continue;
        		
        		int cost = 0;
        		if(p.dir == -1 || p.dir == dir) // 첫번째 이동이거나 같은 방향이면
        			cost = p.cost + 100; // 같은방향 : 직선
        		else cost = p.cost + 600; // 다른방향 : 코너+직선
        		
        		if(visited[nx][ny]!=0 && visited[nx][ny]<cost) continue;
        		visited[nx][ny] = cost;
    			q.offer(new Point(nx, ny, dir, cost));
    		}
        }
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. 큰 틀은 미로찾기와 같기때문에 BFS를 사용한다.
2. 최단거리로 도착을 해도 최저비용이 아닐 수 있기 때문에 boolean 형태의 visited 배열이 아니라
int형 배열로 선언해 해당 지점에 도착했을 때 최저비용을 담아 업데이트하면서 길을 찾는다.
3. 같은 방향이면 직선 하나만 건설하기 때문에 100을 더해주고 다른방향이면 코너+직선을 건설하기때문에
600을 더해준다.
4. (N-1,N-1)에 가장 먼저 도착을 해도 최저비용이 아닐 수 있기 때문에 계속 최저비용을 찾는다!

### :octocat: 후기

처음에 BFS로 바로 풀었었는데 시간초과 몇개나왔다. 바로 다지우고 DFS로 백트래킹하면서 가지치기해서 구했는데
또 시간초과 몇개 떴다. 멘붕멘붕와서 찾아보다가 최저비용을 저장하면서 이동하면 더 빠르다고해서 그렇게 풀었다ㅜ
