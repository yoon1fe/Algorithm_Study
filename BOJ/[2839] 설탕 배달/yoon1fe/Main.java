import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    static int solve() {
        int[] dp = new int[N + 1];
        Arrays.fill(dp, 10000);
        dp[3] = 1;
        if(N >= 5) dp[5] = 1;

        for(int i = 6; i <= N; i++) {
            dp[i] = Math.min(dp[i - 5], dp[i - 3]) + 1;
        }

        System.out.println(dp[N] < 10000 ? dp[N] : -1);
        return 0;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
    }

}
