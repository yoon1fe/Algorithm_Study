import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++)
            map[0][i] = Integer.parseInt(st.nextToken());

        for(int i=1; i<=N; i++)
            for(int j=i; j<=N; j++)
                map[i][j] = isPalindrome(i, j);

        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(map[s][e] + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int isPalindrome(int start, int end){
        int len = (end - start + 1)/2;
        if(len % 2 == 1)
            len++;
        for(int i=0; i<len; i++){
            if(map[0][start++] != map[0][end--])
                return 0;
        }
        return 1;
    }
}