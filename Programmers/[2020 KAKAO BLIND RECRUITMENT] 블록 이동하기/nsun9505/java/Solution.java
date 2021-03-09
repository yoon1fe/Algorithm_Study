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