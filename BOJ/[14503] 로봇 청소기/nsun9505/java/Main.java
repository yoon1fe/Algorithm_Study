import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ans = 0;
    static boolean isBreak = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        isVisited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++)
                map[i][j] = st.nextToken().charAt(0);
        }

        solution(row, col, dir);
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int row, int col, int dir) {
        if(!isVisited[row][col]) {
            isVisited[row][col] = true;
            ans++;
        }

        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            dir = dir == 0 ? 3 : dir-1;
            int nx = row + dx[dir];
            int ny = col + dy[dir];

            if (map[nx][ny] == '1' || isVisited[nx][ny]) {
                cnt++;
                continue;
            }

            solution(nx, ny, dir);
            break;
        }

        if(cnt == 4) {
            int tmpDir = (dir + 2) % 4;
            row += dx[tmpDir];
            col += dy[tmpDir];
            if(map[row][col] != '1')
                solution(row, col, dir);
        }
    }
}