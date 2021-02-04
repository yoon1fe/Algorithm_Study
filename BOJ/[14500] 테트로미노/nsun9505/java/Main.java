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