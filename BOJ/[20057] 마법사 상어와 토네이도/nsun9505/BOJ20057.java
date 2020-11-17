package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20057 {
    static class Element{
        int xx;
        int yy;
        int percent;

        Element(int x, int y, int p){
            this.xx = x;
            this.yy = y;
            this.percent = p;
        }
        int getXx(){
            return this.xx;
        }
        int getYy(){
            return this.yy;
        }
        int getPercent(){
            return this.percent;
        }
    }

    static int[][] board;
    static int N;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int ans = 0;
    static Element[][] impactIndex;

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    public static void init() throws IOException {
        impactIndex = new Element[][]{
                { new Element(-2, 0, 5), new Element(-1, -1, 10), new Element(-1, 1, 10), new Element(0, -1, 7), new Element(0, 1, 7), new Element(0, -2, 2), new Element(0, 2, 2), new Element(1, -1, 1), new Element(1, 1, 1) },
                { new Element(0, 2, 5), new Element(-1, 1, 10), new Element(1, 1, 10), new Element(-1, 0, 7), new Element(1, 0, 7), new Element(-2, 0, 2), new Element(2, 0, 2), new Element(-1, -1, 1), new Element(1, -1, 1)  },
                { new Element(2, 0, 5), new Element(1, 1, 10), new Element(1, -1, 10), new Element(0, -1, 7), new Element(0, 1, 7), new Element(0, -2, 2), new Element(0, 2, 2), new Element(-1, -1, 1), new Element(-1, 1, 1) },
                { new Element(0, -2, 5), new Element(-1, -1, 10), new Element(1, -1, 10), new Element(-1, 0, 7), new Element(1, 0, 7), new Element(-2, 0, 2), new Element(2, 0, 2), new Element(-1, 1, 1), new Element(1, 1, 1) }
        };

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    public static boolean isOutOfIndex(int row, int col) {
        if (row < 1 || col < 1 || row > N || col > N)
            return true;
        return false;
    }

    public static int changeMoveCnt(int cnt) {
        return cnt + 2;
    }

    public static int getNextDir(int dir) {
        if (dir == 0)
            return 3;
        return dir - 1;
    }

    public static int getRemainSand(int row, int col, int dir) {
        int ret = 0;
        for (int i = 0; i < 9; i++) {
            Element elem = impactIndex[dir][i];
            int nx = row + elem.getXx();
            int ny = col + elem.getYy();
            int amountOfSand = (board[row][col] * elem.getPercent()) / 100;
            ret += amountOfSand;
            if (!isOutOfIndex(nx, ny))
                board[nx][ny] += amountOfSand;
            else
                ans += amountOfSand;
        }

        return ret;
    }

    public static void solution(){
        int downMoveCnt = 1;
        int leftMoveCnt = 1;
        int rightMoveCnt = 2;
        int upMoveCnt = 2;
        int curDir = 3;
        int curRow = N / 2 + 1;
        int curCol = curRow;

        while (true) {
            if (curDir == 0) {
                for (int i = 0; i < upMoveCnt; i++) {
                    int nx = curRow + dx[curDir];
                    int ny = curCol + dy[curDir];

                    int diff = board[nx][ny] - getRemainSand(nx, ny, curDir);
                    board[nx][ny] = 0;
                    if (nx - 1 < 1)
                        ans += diff;
                    else
                        board[nx - 1][ny] += diff;
                    curRow = nx;
                    curCol = ny;
                }
                upMoveCnt = changeMoveCnt(upMoveCnt);
            }
            else if (curDir == 1) {
                for (int i = 0; i < rightMoveCnt; i++) {
                    int nx = curRow + dx[curDir];
                    int ny = curCol + dy[curDir];

                    int diff = board[nx][ny] - getRemainSand(nx, ny, curDir);
                    board[nx][ny] = 0;
                    if (ny + 1 > N)
                        ans += diff;
                    else
                        board[nx][ny + 1] += diff;
                    curRow = nx;
                    curCol = ny;
                }
                rightMoveCnt = changeMoveCnt(rightMoveCnt);
            }
            else if (curDir == 2) {
                for (int i = 0; i < downMoveCnt; i++) {
                    int nx = curRow + dx[curDir];
                    int ny = curCol + dy[curDir];
                    int diff = board[nx][ny] - getRemainSand(nx, ny, curDir);
                    board[nx][ny] = 0;
                    if (nx + 1 > N)
                        ans += diff;
                    else
                        board[nx + 1][ny] += diff;
                    curRow = nx;
                    curCol = ny;
                }
                downMoveCnt = changeMoveCnt(downMoveCnt);
            }
            else if (curDir == 3) {
                for (int i = 0; i < leftMoveCnt; i++) {
                    int nx = curRow + dx[curDir];
                    int ny = curCol + dy[curDir];
                    int diff = board[nx][ny] - getRemainSand(nx, ny, curDir);
                    board[nx][ny] = 0;
                    if (ny - 1 < 1)
                        ans += diff;
                    else
                        board[nx][ny - 1] += diff;
                    curRow = nx;
                    curCol = ny;
                }
                leftMoveCnt = changeMoveCnt(leftMoveCnt);
            }
            curDir = getNextDir(curDir);

            if (curRow == 1 && curCol == 0)
                break;
        }

        System.out.println(ans);
    }
}
