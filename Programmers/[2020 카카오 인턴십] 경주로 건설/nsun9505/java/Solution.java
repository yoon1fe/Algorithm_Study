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