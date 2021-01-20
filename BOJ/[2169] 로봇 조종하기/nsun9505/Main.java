import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;
    static boolean[][] isVisited;
    static int[] dx = {0, 1, 0};
    static int[] dy = {1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        int[][] dp = new int[N+1][M+2];
        arr = new int[N+1][M+2];
        for(int i=1; i<=N; i++)
            dp[i][0] = dp[i][M+1] = Integer.MIN_VALUE;

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        dp[1][1] = arr[1][1];
        for(int i=2;i<=M; i++)
            dp[1][i] += arr[1][i] + dp[1][i-1];

        for(int i=2; i<=N; i++){
            int[] LU = new int[M+2];
            LU[M+1] = Integer.MIN_VALUE;
            int[] RU = new int[M+2];
            RU[0] = Integer.MIN_VALUE;

            // Left, Up에서 오는 경우
            for(int j=M; j>=1; j--)
                LU[j] = Math.max(dp[i-1][j], LU[j+1]) + arr[i][j];

            // Right, Up에서 오는 경우
            for(int j=1; j<=M; j++)
                RU[j] = Math.max(dp[i-1][j], RU[j-1]) + arr[i][j];

            // LU, RU 비교 : 왼쪽, 오른쪽, 위에서 오는 경우 모두 비교
            for(int j=1; j<=M; j++)
                dp[i][j] = Math.max(RU[j], LU[j]);
        }

        System.out.println(dp[N][M]);
    }
}
