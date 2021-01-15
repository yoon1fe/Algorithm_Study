import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        int[][] dp = new int[str1.length()+1][str2.length()+1];

        for(int i=0; i<str1.length(); i++){
            int row = i+1;
            for(int j=0; j<str2.length(); j++){
                int col = j + 1;
                if(str1.charAt(i) == str2.charAt(j))
                    dp[row][col] = dp[row-1][col-1] + 1;
                else
                    dp[row][col] = Math.max(dp[row-1][col], dp[row][col-1]);
            }
        }
        System.out.println(dp[str1.length()][str2.length()]);
    }
}