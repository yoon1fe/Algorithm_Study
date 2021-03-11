# [2020 카카오 인턴십] 경주로 건설 - JAVA

## 분류
> BFS

## 코드
```java
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int solution(int[][] board) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int answer = 0;
        int N = board.length;
        Queue<Element> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int[][] moneys = new int[N][N];
        queue.offer(new Element(0, 0, 0, 1));
        queue.offer(new Element(0, 0, 0, 2));
        visited[0][0] = true;

        while(!queue.isEmpty()){
            Element cur = queue.poll();

            for(int dir=0; dir<4; dir++){
                int nx = cur.row + dx[dir];
                int ny = cur.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                if(board[nx][ny] == 1)
                    continue;

                int money = 100;
                if(cur.dir != dir)
                    money += 500;

                if(visited[nx][ny]){
                    if(cur.money + money <= moneys[nx][ny]) {
                        moneys[nx][ny] = cur.money + money;
                        queue.offer(new Element(nx, ny, cur.money + money, dir));
                    }
                    continue;
                }

                moneys[nx][ny] = cur.money + money;
                visited[nx][ny] = true;
                queue.offer(new Element(nx, ny, moneys[nx][ny], dir));
            }
        }
        return moneys[N-1][N-1];
    }
    
    static class Element{
        int row;
        int col;
        int money;
        int dir;

        public Element(int row, int col, int money, int dir) {
            this.row = row;
            this.col = col;
            this.money = money;
            this.dir = dir;
        }
    }
}
```

## 문제 풀이
BFS를 사용해서 문제를 풀었습니다!

출발하는 지점에서 방향이 왼쪽이거나 아래일 수 있으므로 두 가지 경우를 처음에 큐로 넣어줍니다.

그리고 BFS로 탐색을 하면서, 아직 방문하지 않았다면, 방문한 표시를 남기고, 해당 위치까지의 도로 건설비용을 저장합니다.

만약, 이미 방문한 위치라면, 이미 저장된 건설 비용과 지금까지의 건설 비용 + 다음 위치로의 건설 비용과 비교합니다.

그래서 현재 위치에 새로 건설하는 비용이 더 작다면 원래의 값을 갱신하고 큐에 넣어줍니다.

아니면 더 탐색하지 않기 위해 continue로 끝내주면 됩니다.

그리고 건설 비용 계산은 일단 무조건 100이 들어갑니다!

왜냐하면 방향이 같은 경우에는 100이 들어가고, 코너를 만들 때는 코너를 만드는 비용 + 다음 위치로 가는 직진 비용이 필요하므로

코너를 만들 때는 500을 더해줘서 건설 비용을 계산하면 됩니다!

마지막으로 건설비용을 저장한 배열의 N-1, N-1좌표의 값을 리턴하면 답이 됩니다.