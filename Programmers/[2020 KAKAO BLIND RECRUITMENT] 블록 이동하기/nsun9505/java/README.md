# [2020 KAKAO BLIND RECRUITMENT] 블록 이동하기 - JVAV

## 분류
> BFS
>
> 구현
>
> 시뮬레이션

## 코드
```java
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int N;
    static int[][] board;
    public int solution(int[][] board) {
        N = board.length;
        this.board = board;

        return BFS();
    }

    public static int BFS(){
        boolean[][][][] visited = new boolean[N][N][N][N];
        Queue<Robot> queue = new LinkedList<>();
        visited[0][0][0][1] = true;
        queue.offer(new Robot(0, 0, 0, 1, 0));
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while(!queue.isEmpty()){
            Robot robot = queue.poll();

            for(int dir = 0; dir < 4; dir++){
                int nlx = robot.leftRow + dx[dir];
                int nly = robot.leftCol + dy[dir];
                int nrx = robot.rightRow + dx[dir];
                int nry = robot.rightCol + dy[dir];

                if(isOutOfBound(nlx, nly) || isOutOfBound(nrx, nry))
                    continue;

                if(visited[nlx][nly][nrx][nry] || board[nlx][nly] == 1 || board[nrx][nry] == 1)
                    continue;

                if((nlx == N-1 && nly == N-1) || (nrx == N-1 && nry == N-1))
                    return robot.dist + 1;

                queue.offer(new Robot(nlx, nly, nrx, nry, robot.dist+1));
                visited[nlx][nly][nrx][nry] = true;
            }

            // 왼쪽 또는 오른쪽 기준으로 회전 : 위, 아래
            // 가로로 있는 경우
            if(robot.leftRow == robot.rightRow){
                // 위쪽으로 돌릴 수 있는지
                if(checkOf(robot.leftRow-1, robot.leftCol) && checkOf(robot.leftRow-1, robot.rightCol)) {
                    if(!visited[robot.leftRow][robot.leftCol][robot.leftRow-1][robot.leftCol]){
                        visited[robot.leftRow][robot.leftCol][robot.leftRow-1][robot.leftCol] = true;
                        queue.offer(new Robot(robot.leftRow, robot.leftCol, robot.leftRow-1, robot.leftCol, robot.dist+1));
                    }
                    if(!visited[robot.leftRow-1][robot.rightCol][robot.rightRow][robot.rightCol]){
                        visited[robot.leftRow-1][robot.rightCol][robot.rightRow][robot.rightCol] = true;
                        queue.offer(new Robot(robot.leftRow-1, robot.rightCol, robot.rightRow, robot.rightCol, robot.dist+1));
                    }
                }

                // 아래쪽으로 돌릴 수 있는지
                if(checkOf(robot.leftRow+1, robot.leftCol) && checkOf(robot.leftRow+1, robot.rightCol)){
                    if(!visited[robot.leftRow][robot.leftCol][robot.leftRow+1][robot.leftCol]){
                        visited[robot.leftRow][robot.leftCol][robot.leftRow+1][robot.leftCol] = true;
                        queue.offer(new Robot(robot.leftRow, robot.leftCol, robot.leftRow+1, robot.leftCol, robot.dist+1));
                    }
                    if(!visited[robot.leftRow+1][robot.rightCol][robot.rightRow][robot.rightCol]){
                        visited[robot.leftRow+1][robot.rightCol][robot.rightRow][robot.rightCol] = true;
                        queue.offer(new Robot(robot.leftRow+1, robot.rightCol, robot.rightRow, robot.rightCol, robot.dist+1));
                    }
                }
            }
            // 세로로 있는 경우
            else {
                // 오른쪽 으로 돌릴 수 있는지
                if(checkOf(robot.leftRow, robot.leftCol+1) && checkOf(robot.rightRow, robot.rightCol+1)){
                    if(!visited[robot.leftRow][robot.leftCol][robot.leftRow][robot.leftCol+1]){
                        visited[robot.leftRow][robot.leftCol][robot.leftRow][robot.leftCol+1] = true;
                        queue.offer(new Robot(robot.leftRow, robot.leftCol, robot.leftRow, robot.leftCol+1, robot.dist+1));
                    }

                    if(!visited[robot.rightRow][robot.rightCol+1][robot.rightRow][robot.rightCol]){
                        visited[robot.rightRow][robot.rightCol+1][robot.rightRow][robot.rightCol] = true;
                        queue.offer(new Robot(robot.rightRow, robot.rightCol+1, robot.rightRow, robot.rightCol, robot.dist+1));
                    }
                }
                // 왼쪽으로 돌릴 수 있는지
                if(checkOf(robot.leftRow, robot.leftCol-1) && checkOf(robot.rightRow, robot.rightCol-1)){
                    if(!visited[robot.leftRow][robot.leftCol][robot.leftRow][robot.leftCol-1]){
                        visited[robot.leftRow][robot.leftCol][robot.leftRow][robot.leftCol-1] = true;
                        queue.offer(new Robot(robot.leftRow, robot.leftCol, robot.leftRow, robot.leftCol-1, robot.dist+1));
                    }

                    if(!visited[robot.rightRow][robot.rightCol-1][robot.rightRow][robot.rightCol]){
                        visited[robot.rightRow][robot.rightCol-1][robot.rightRow][robot.rightCol] = true;
                        queue.offer(new Robot(robot.rightRow, robot.rightCol-1, robot.rightRow, robot.rightCol, robot.dist+1));
                    }
                }
            }
        }
        return 0;
    }

    public static boolean checkOf(int row, int col){
        if(isOutOfBound(row, col)) return false;
        else if(board[row][col] == 1) return false;
        return true;
    }

    public static boolean isOutOfBound(int row, int col){
        if(row < 0 || col < 0 || row >= N || col >= N)
            return true;
        return false;
    }

    static class Robot{
        int leftRow;
        int leftCol;
        int rightRow;
        int rightCol;
        int dist;

        public Robot(int leftRow, int leftCol, int rightRow, int rightCol, int dist) {
            this.leftRow = leftRow;
            this.leftCol = leftCol;
            this.rightRow = rightRow;
            this.rightCol = rightCol;
            this.dist = dist;
        }
    }
}
```

## 문제 풀이
BFS로 풀리는 문제입니다! 회전하는 조건만 신경써주면 쉽게 풀 수 있는거 같습니다.

BFS를 돌리면서 방문체크는 4차원 배열을 사용해서 체크했습니다.
   - 날개가 두 개이므로 두개의 위치를 하나의 상태 값으로 보기 위해서 입니다.

가장 먼저 상하좌우로 이동하는 것에 대해서 방문을 하고, 아직 방문하지 않았다면 방문 체크를 하고 큐에 넣어줍니다.

그 다음 회전하는 것을 검사합니다.

먼저 로봇이 가로로 있는 경우는 날개 하나를 기준으로 다른 날개를 위로 움직이거나, 아래로 움직이는 방법입니다.
   - 위로 다른 날개를 움직일 시에는 위의 두 칸이 두 칸이 비어져 있어야 합니다.
   - 아래로 다른 날개를 움직일 시에는 아래 두 칸이 비어져 있어야 합니다.

왜냐하면 만약에, 왼쪽 날개의 위쪽 칸이 1이라면 왼쪽 날개를 기준으로 오른쪽 날개를 위로 이동시키지 못합니다.
   - 또한, 오른쪽 날개를 기준으로 왼쪽 날개를 위로 이동시키려고 해도 왼쪽 날개 바로 위에 1이 있어서 이동하지 못합니다.
   - 즉, 로봇이 가로로 있을 때 위로 회전하기 위해서는 로봇의 바로 위 두 칸이 0이어야 움직일 수 있는 것입니다.
   ```  
        [ 1 ][ 0 ]    [ 0 ][ 1 ]
        [ L ][ R ] OR [ L ][ R ] : 어느 날개를 기준으로 하더라도 위쪽으로 회전할 수 없음.

        [ 0 ][ 0 ]
        [ L ][ R ] : 위쪽으로 어느 날개를 기준으로 해도 회전 가능
   ```

위 그림에서 보다시피, 로봇의 모양(가로 또는 세로)에 회전하려면 무조건 회전하려고 하는 방향의 2칸이 0이어야 합니다.

그래서 이를 검사하기 위해 먼저 로봇의 모양을 파악하고, 

세로면 왼쪽 또는 오른쪽에 0인 칸이 2개인지 확인하고, 가로면 위쪽 또는 아래쪽에 0인 칸이 2개인지 확인해서 움직일 수 있는 칸으로만 이동시키면 됩니다!

## 후기
잘 풀었습니데이~