import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        int[] dice = new int[7];
        int[][] map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                continue;

            int tmp = dice[6];
            if(dir == 0){
                dice[6] = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[4];
                dice[4] = tmp;
            } else if (dir == 1){
                dice[6] = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[3];
                dice[3] = tmp;
            } else if(dir == 2){
                dice[6] = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[5];
                dice[5] = tmp;
            } else if(dir == 3){
                dice[6] = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[2];
                dice[2] = tmp;
            }

            if(map[nx][ny] == 0){
                map[nx][ny] = dice[6];
            } else {
                dice[6] = map[nx][ny];
                map[nx][ny] = 0;
            }
            x = nx;
            y = ny;

            System.out.println(dice[1]);
        }

    }

}
