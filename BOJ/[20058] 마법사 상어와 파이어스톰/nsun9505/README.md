# [20058] 마법사 상어와 파이어스톰 - JAVA

## 분류
> 구현

## 코드
```java
package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20058 {

    static class Pos{
        int r;
        int c;

        public Pos(int i, int j) {
            this.r = i;
            this.c = j;
        }
    }

    static int N;
    static int Q;
    static int[][] board;
    static int[][] tmp;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] lenArr;
    static int largeIce;

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    public static void solution() throws IOException {
        for(int loop=0; loop < Q; loop++){
            int L = 1 << lenArr[loop];
            for(int i=0; i<N; i+=L)
                for (int j = 0; j < N; j += L)
                    rotate(i, j, L);
            meltIce();
        }

        int sum = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(isVisited[i][j] || board[i][j] <= 0)
                    continue;
                sum += BFS(i, j);
            }
        }
        System.out.println(sum);
        System.out.println(largeIce);
    }

    public static int BFS(int r, int c) {
        int sum = board[r][c];
        int size = 1;
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(r, c));
        isVisited[r][c] = true;

        while (!queue.isEmpty()) {
            Pos p = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = p.r + dx[dir];
                int ny = p.c + dy[dir];

                if (isOutOfIndex(nx, ny))
                    continue;
                if (isVisited[nx][ny] || board[nx][ny] <= 0)
                    continue;

                queue.offer(new Pos(nx, ny));
                isVisited[nx][ny] = true;
                size++;
                sum += board[nx][ny];
            }
        }

        largeIce = largeIce < size ? size : largeIce;
        return sum;
    }

    public static void meltIce(){
        ArrayList<Pos> meltList = new ArrayList<>();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                int cnt = 0;
                for(int dir = 0; dir<4; dir++){
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];

                    if(isOutOfIndex(nx, ny))
                        continue;
                    if(board[nx][ny] <= 0)
                        continue;
                    cnt++;
                }
                if(cnt < 3)
                    meltList.add(new Pos(i, j));
            }
        }
        for(Pos p : meltList)
            board[p.r][p.c] -= 1;
    }

    public static void rotate(int r, int c, int L){
        for(int i=0; i<L; i++) {
            for (int j = 0; j < L; j++)
                tmp[j][L - 1 - i] = board[r + i][c + j];
        }
        for(int i=0; i<L; i++){
            for(int j=0; j<L; j++)
                board[r+i][c+j] = tmp[i][j];
        }
    }

    public static boolean isOutOfIndex(int r, int c){
        if(r < 0 || r >= N || c < 0 || c >= N)
            return true;
        return false;
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = (int)Math.pow(2, Integer.parseInt(st.nextToken()));
        Q = Integer.parseInt(st.nextToken());
        lenArr = new int [Q];
        board = new int[N][N];
        tmp = new int[N][N];
        isVisited = new boolean[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<Q; i++)
            lenArr[i] = Integer.parseInt(st.nextToken());
    }
}

```

## 문제 풀이
1. 회전을 시킨다!
- lenArr에 회전시킬 크기가 담긴다.
- lenArr[i]가 1이면 board에서 2x2 사이즈의 부분을 90도 회전시키면 된다.
- 그러니깐 lenArr[i]가 L이라고 하면 2^L x 2^L 행렬을 90도 회전!

1. 얼음을 녹인다!
- 얼음을 녹일 떄는 현재 위치에서 상하좌우에 얼음이 있는지 본다!
- 값이 0인 칸과 인덱스를 벗어나는 곳은 제외하고 카운트를 한다.
- 카운트 값이 3보다 작다면 얼음을 녹여야하는 곳이므로 ArrayList에 넣어준다.
- 바로 녹이지 않는 이유는 바로 녹이면 상하좌우에 속한 얼음이 원래는 안 녹아도 되는데 녹아버릴 수 있기에 ArrayList에 담고 녹일 얼음의 위치를 알아낸다.
- 녹일 위치들을 1씩 감소시켜 준다. 그리고 다시 1번부터 다시 돌다가 회전을 모두 하였다면 3번을 수행한다.

1. BFS로 돌면서 가장 큰 얼음의 사이즈(?)와 board 전체의 합을 출력!
- BFS를 돌면서 가장 큰 얼음의 사이즈를 구한다.
- 0이 아닌 칸을 탐색하기에 BFS를 돌면서 가장 큰 사이즈를 구하는겸 전체 합을 구한다.

## 후기
오랜만에 풀어보는 삼성 문제~