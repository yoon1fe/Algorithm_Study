## [2020 카카오 인턴십] 경주로 건설 - Java

###   :racing_car: 분류

> BFS
>
> (다익스트라)



###  :racing_car: 코드

```java
import java.util.*;

class Solution {
    static class Dir{
        int y, x, d;
        Dir(int y, int x, int d){
            this.y = y; this.x = x; this.d = d;
        }
    }

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int N;
    static int[][] v;

    public static boolean isIn(Dir c) {
        if(0 <= c.y && c.y < N && 0 <= c.x && c.x < N) return true;
        else return false;
    }

    public static int bfs(Dir start, int[][] board) {
        int answer = Integer.MAX_VALUE;
        Queue<Dir> q = new LinkedList<>();
        int[][]v = new int[N][N];

        v[0][0] = 100;
        if(start.d == 0 &&board[0][1] == 0) {           // 처음에 왼쪽으로 가는 경우
            v[0][1] = 200;
            q.offer(new Dir(0, 1, 0));
        }
        else if(start.d == 1 && board[1][0] == 0) {     // 처음에 밑으로 가는 경우
            v[1][0] = 200;
            q.offer(new Dir(1, 0, 1));
        }

        while(!q.isEmpty()) {
            Dir cur = q.poll();
            if(cur.y == N-1 && cur.x == N-1) {                
                answer = Math.min(v[cur.y][cur.x] - 100, answer);
            }

            for(int i = 0 ; i <= 3; i++) {
                if(i == 2) continue;    // 꺾는 방향 계산 (d + 1(3)) % 4;
                Dir next = null;

                if(i == 0) {        // 같은 방향으로 가는 경우
                    next = new Dir(cur.y + dy[cur.d], cur.x + dx[cur.d], cur.d); 

                    if(!isIn(next) || board[next.y][next.x] == 1) continue;
                    if(v[next.y][next.x] == 0) v[next.y][next.x] = v[cur.y][cur.x] + 100;
                    else if(v[next.y][next.x] >= v[cur.y][cur.x] + 100) v[next.y][next.x] = v[cur.y][cur.x] + 100;
                    else continue;
                }else {             // 꺾는 경우
                    int nd  = (cur.d + i) % 4;
                    next = new Dir(cur.y + dy[nd], cur.x + dx[nd], nd); 

                    if(!isIn(next) || board[next.y][next.x] == 1) continue;
                    if(v[next.y][next.x] == 0 ) v[next.y][next.x] = v[cur.y][cur.x] + 600;
                    else if(v[next.y][next.x] >= v[cur.y][cur.x] + 600) v[next.y][next.x] = v[cur.y][cur.x] + 600;
                    else continue;
                }

                q.offer(next);
            }
        }
        return answer;
    }

    public static int solution(int[][] board) {
        N = board.length;
        v = new int[N][N];

        return Math.min(bfs(new Dir(0, 0, 0), board), bfs(new Dir(0, 0, 1), board));            // 오른쪽으로 가는 경우와 밑으로 가는 경우 중 최솟값
    }
}
```



### :racing_car: 풀이 방법

최소 비용을 구하는 문젭니다.

DFS로 깔짝대다가 포기하고 BFS 로 풀었습니다.



맨 처음 (0, 0)에서 갈 수 있는 경우는 오른쪽과 밑입니다.

그래서 두 방향으로 **BFS**를 돌려서 최솟값을 답으로 가져왔습니다.

**v 배열에는 그 좌표에서의 최소 비용**을 저장했습니다.

 

한 좌표에서 총 세가지의 경우가 있습니다.

1. 직진
2. 좌회전
3. 우회전

우선 `v[next.y][next.x]` 가 0이거나 기존의 값이 더 클 경우 갱신해주는 것은 똑같습니다.

다만 직진인 경우, 직선 도로를 하나 만드는 것이므로 +100, 방향을 꺾는 경우는 직선 도로와 코너를 하나 만드는 것이므로 +600 으로 갱신해주어야 합니다.

 

맨첨에 cur.y == N-1 && cur.x == N-1 인 경우 바로 v에 있는 값을 리턴해줬는데, 다른 방향에서 온 경우가 최종적으로 로 비용이 더 적을 수 있기 때문에 끝까지 봐주어야 합니다!! ㅜㅜ

 

###  :racing_car: 후기 

중간에 봐주지 않아도 될 곳을 계속 탐색해서 시간초과도 뜨고... 계속 테케 하나 틀리고... 시행착오가 많았씁니다...ㅠㅠ

아직 멀었습니다.... 하지만 화이팅!!!!!!