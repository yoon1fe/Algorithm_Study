import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, Dir> map;
    static int[][] ladder;
    static int answer = -1;
    static int N, M, H;

    static List<Integer> addIdx = new ArrayList<>();
    static class Dir{
        int y, x;
        Dir(int y, int x){
            this.y = y; this.x = x;
        }
    }

    public static void checkLadder(int depth) {
        for(int i = 1; i < N; i++) {
            Dir cur = new Dir(1, i);

            while(cur.y <= H) {
                if(ladder[cur.y][cur.x] == 1) cur.x++;
                else if(ladder[cur.y][cur.x - 1] == 1) cur.x--;
                cur.y++;
            }
            if(cur.x != i) return;
        }
        answer = answer == -1 ? depth : answer;
    }

    public static void comb(int idx, int depth, int cnt) {
        if(cnt == depth) {
            checkLadder(depth);
            return;
        }

        for(int i = idx; i <= map.size(); i++) {
            // 연속으로 가로선 놓지 말기
            if(ladder[map.get(i).y][map.get(i).x - 1] == 1 || ladder[map.get(i).y][map.get(i).x + 1] == 1) continue;

            ladder[map.get(i).y][map.get(i).x] = 1;
            comb(i+1, depth, cnt+1);
            ladder[map.get(i).y][map.get(i).x] = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        map = new HashMap<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H + 1][N + 1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            ladder[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        int idx = 1;
        for(int i = 1; i <= H; i++) {
            for(int j = 1; j < N; j++) {
                if(ladder[i][j] == 0) map.put(idx++, new Dir(i, j));
            }
        }

        for(int i = 0; i <= 3; i++) {        // depth
            if(answer != -1) break;
            comb(1, i, 0);
        }

        System.out.println(answer);
    }
}