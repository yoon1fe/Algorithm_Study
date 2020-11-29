# [9663] N-Queen - JAVA

## 분류
> 백트랙킹

## 코드
```java
package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9663 {
    static int N;
    static int[][] board;
    static boolean[] isUsed;
    static int[] dx = {-1, -1};
    static int[] dy = {-1, 1};
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        isUsed = new boolean[N];

        solution(0);
        System.out.println(ans);
    }

    public static void solution(int curRow){
        if(curRow >= N){
            ans++;
            return;
        }

        for(int i=0; i<N; i++){
            if(!isUsed[i] && checkDiagonal(curRow, i, 0) && checkDiagonal(curRow, i, 1)){
                isUsed[i] = true;
                board[curRow][i] = 1;
                solution(curRow+1);
                board[curRow][i] = 0;
                isUsed[i] = false;
            }
        }
    }
    public static boolean checkDiagonal(int r, int c, int dir){
        int curRow = r;
        int curCol = c;
        while(true){
            int nextRow = curRow + dx[dir];
            int nextCol = curCol + dy[dir];

            if(OOB(nextRow, nextCol))
                break;

            if(board[nextRow][nextCol] != 0)
                return false;
            curRow = nextRow;
            curCol = nextCol;
        }
        return true;
    }

    public static boolean OOB(int r, int c){
        if(r < 0 || c < 0 || r >= N || c >= N)
            return true;
        return false;
    }
}
```

## 문제 풀이
1. 현재 위치에 퀸을 놓을 수 있는지 체크한다.
- 왼쪽, 오른쪽 대각선에 퀸이 있거나 같은 열에 퀸이 있는지 확인한다.
- 셋 중에 퀸이 있으면 퀸을 놓지 못하므로 다음 칸으로 넘어간다.
- 없으면 퀸을 놓고 다음 행으로 넘어간다.

## 후기
알고리즘 수업 들을 때 풀었던 문제!