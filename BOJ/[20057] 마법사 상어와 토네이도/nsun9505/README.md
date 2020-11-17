# [20057] 마법사 상어와 토네이도 - Java

## 분류
> 구현

## 코드
```java
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
```

## 문제풀이
영향을 주는? 범위를 미리 구해서 배열에 넣는다.
- 방향에 따라 영향을 받는 칸이 달라지기에 상하좌우 모두에 대해서 구하면 됨!

이동 수를 카운트
- 왼쪽과 아래로 이동하는 횟수은 처음에 1이다. 왜냐하면 문제에서 보면 처음에 왼쪽과 아래로 움직이는 횟수는 1이기 때문이다.
- 오른쪽과 위로 이동하는 횟수는 처음에 2이다. 왜냐하면 문제에서 보면 처음에 아래쪽과 위로 움직이는 횟수는 2이기 때문이다.
- 한 방향에 대해서 이동이 끝나면 동일하게 2씩 늘려주면 된다.

움직일 때 현재 방향에 따라서 영향을 받는 칸들에 모래양을 퍼센트만큼 더해주고 벗어나는 경우 출력될 답에 더해주면 된다.
- 즉, 흩어진 모래양을 구하게 되고 그것을 반환하여 원래 모래의 양에서 빼주면 된다.
- 알파의 위치가 범위를 넘어서면 출력할 답에 더해주면 되고, 아니면 알파 위치에 더해주면 된다.

## 후기
음 C++로 했던거 자바로 짜보니 귀찮은게 한둘이 아니다..