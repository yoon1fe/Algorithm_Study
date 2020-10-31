import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    static int[][] map;
    static boolean[] isSelected;
    static int N, M, K;
    static int[][] operation;
    static int[] order;
    static int answer = 99999999;

    public static void rotate(int idx, int[][] map) {
        int r = operation[idx][0]; int c = operation[idx][1]; int s = operation[idx][2];
        int mR = r-s; int mC = c-s; int MR = r+s; int MC = c+s;

        for(int i = 0; i < s; i++ ) {
            int start = map[mR+i][mC+i];
            for(int j = mR + i; j < MR - i; j++) map[j][mC+i] = map[j+1][mC+i];
            for(int j = mC + i; j < MC - i; j++) map[MR-i][j] = map[MR-i][j+1];
            for(int j = MR - i; j > mR + i; j--) map[j][MC-i] = map[j-1][MC-i];
            for(int j = MC - i; j > mC + i + 1; j--) map[mR+i][j] = map[mR+i][j-1];
            map[mR+i][mC+i+1] = start;
        }
    }

    public static void perm(int cnt) {
        if(cnt == K) {
            int[][] tempMap = new int[N+1][M+1];
            for(int i = 1; i <= N; i++) System.arraycopy(map[i], 0, tempMap[i], 0, map[i].length);

            for(int i = 0; i < K; i++) rotate(order[i], tempMap);

            answer = Math.min(getArrayValue(tempMap), answer);

            return;
        }

        for(int i = 0; i < K; i++) {
            if(isSelected[i]) continue;
            isSelected[i] = true;
            order[i] = cnt;
            perm(cnt+1);
            isSelected[i] = false;
        }
    }

    public static int getArrayValue(int[][] map) {
        int ret = 99999999;
        for(int i = 1; i <= N; i++) ret = Math.min(Arrays.stream(map[i]).sum(), ret);

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        isSelected = new boolean[K];
        operation = new int[K][3];
        order = new int[K];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 3; j++) {
                operation[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        perm(0);

        System.out.println(answer);
    }
}