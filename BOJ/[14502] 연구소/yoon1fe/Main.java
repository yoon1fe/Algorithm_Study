import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Dir {
    int y;
    int x;

    Dir(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static int answer = 0;
    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0, 0, 1, -1 };
    static ArrayList<Dir> virus = new ArrayList<>();

    static boolean isIn(Dir cur) {
        if (0 <= cur.y && cur.y < N && 0 <= cur.x && cur.x < M) return true;
        else return false;
    }

    static void bfs() {
        int cnt = 0;
        Queue<Dir> q = new LinkedList<>();
        boolean[][] c = new boolean[N][M];
        int[][] copyMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        for (Dir v : virus) {
            c[v.y][v.x] = true;
            q.offer(v);
        }

        while (!q.isEmpty()) {
            Dir cur = q.poll();
            for (int i = 0; i < 4; i++) {
                Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);

                if (!isIn(next) || c[next.y][next.x]) continue;
                if (copyMap[next.y][next.x] == 0) {
                    c[next.y][next.x] = true;
                    q.offer(next);
                    copyMap[next.y][next.x] = 2;
                }
            }
        }
        // 안전 영역 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) cnt++;
            }
        }
        answer = (answer > cnt ? answer : cnt);
    }

    static void makeWall(int cnt, int y, int x) {
        if (cnt == 3) {
            bfs();
            return;
        }

        for (int i = y; i < N; i++) {
            for (int j = x; j < M; j++) {
                if (map[i][j] != 0) continue;
                map[i][j] = 1;
                makeWall(cnt + 1, i, j + 1);
                map[i][j] = 0;
            }
            x = 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 2) virus.add(new Dir(i, j));
            }
        }
        makeWall(0, 0, 0);

        System.out.println(answer);
    }
}
