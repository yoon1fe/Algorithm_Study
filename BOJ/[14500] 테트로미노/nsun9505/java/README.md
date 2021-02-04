# [14500] 테트로미노 - JAVA

## 분류
> 구현
>
> 시뮬레이션

## 코드
```java
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isVisited = new boolean[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Pos> list = new ArrayList<>();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                isVisited[i][j] = true;
                DFS(i, j, 0, list, 0);
                isVisited[i][j] = false;
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void DFS(int row, int col, int depth, ArrayList<Pos> list,int sum){
        if(depth == 3){
            for(Pos pos : list){
                for(int dir = 0; dir < 4; dir++){
                    int nx = pos.row + dx[dir];
                    int ny = pos.col + dy[dir];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                        continue;
                    if(isVisited[nx][ny])
                        continue;

                    ans = Math.max(ans, sum + map[nx][ny]);
                }
            }
            return;
        }

        for(int dir = 0; dir<4; dir++){
            int nx = row + dx[dir];
            int ny = col + dy[dir];

            if(nx < 0 || ny < 0 || nx >= N || ny >=M)
                continue;
            if(isVisited[nx][ny])
                continue;

            isVisited[nx][ny] = true;
            list.add(new Pos(nx, ny));
            DFS(nx, ny, depth+1, list, sum + map[nx][ny]);
            list.remove(list.size()-1);
            isVisited[nx][ny] = false;
        }

    }

    static class Pos{
        int row;
        int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}
```

## 문제 풀이
DFS로 3개까지만 찾아갑니다.

찾은 3개 각각의 위치에서 상하좌우 중 아직 방문하지 않았고, 범위 안에 드는 칸에 값을 세칸의 합과 더해줍니다.

더한 값을 정답과 비교하여 크다면 정답을 갱신하고, 크지 않다면 갱신하지 않습니다.

## 후기
풀이가 머리에 남아 있네요.