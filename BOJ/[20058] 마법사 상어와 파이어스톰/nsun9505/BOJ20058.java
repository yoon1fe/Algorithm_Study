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
            for(int i=0; i<N; i+=L) {
                for (int j = 0; j < N; j += L)
                    rotate(i, j, L);
            }
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
