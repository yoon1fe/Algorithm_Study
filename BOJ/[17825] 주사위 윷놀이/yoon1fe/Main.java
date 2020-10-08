import java.io.*;
import java.util.*;

public class Main {
    static class Dir {
        int y, x;

        Dir(int y, int x) {
            this.y = y; this.x = x;
        }
    }

    static int N, K;
    static Map<Integer, Integer> pieceDir = new HashMap<>();    // 체스 말의 이동 방향
    static List<Dir> pieceList = new ArrayList<>();                // 체스 말 list
    static int[][] board;                                        // 체스 판
    static StringBuilder[][] map;                                // 체스 말들 위치
    static int[] dy = { 0, 0, 0, -1, 1 };
    static int[] dx = { 0, 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        map = new StringBuilder[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new StringBuilder();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            map[y][x].append(i);
            pieceDir.put(i, d);
            pieceList.add(new Dir(y, x));
        }

        System.out.println(move());
    }

    public static boolean isIn(Dir c) {
        if (0 <= c.y && c.y < N && 0 <= c.x && c.x < N) return true;
        else return false;
    }

    public static int move() {
        int ans = 1;

        outer:
        for (ans = 1; ans <= 1000; ans++) {
            for (int i = 0; i < pieceList.size(); i++) {
                Dir cur = pieceList.get(i);
                int dir = pieceDir.get(i);

                Dir next = new Dir(cur.y + dy[dir], cur.x + dx[dir]);

                if (!isIn(next) || board[next.y][next.x] == 2) {
                    dir = dir == 1 ? 2 : (dir == 2 ? 1 : (dir == 3 ? 4 : 3));
                    next.y = cur.y + dy[dir]; next.x = cur.x + dx[dir];
                    pieceDir.replace(i, dir);
                    if (!isIn(next) || board[next.y][next.x] == 2) continue;
                }

                int idx = map[cur.y][cur.x].indexOf(i+"");
                for(int j = idx; j < map[cur.y][cur.x].length(); j++) pieceList.set(map[cur.y][cur.x].charAt(j) - '0', next); 

                if (board[next.y][next.x] == 0) map[next.y][next.x].append(map[cur.y][cur.x].substring(idx));
                else map[next.y][next.x].append(new StringBuilder(map[cur.y][cur.x].substring(idx)).reverse());

                map[cur.y][cur.x].delete(idx, map[cur.y][cur.x].length());

                if(map[next.y][next.x].length() >= 4) break outer;
            }
        }

        return ans > 1000 ? -1 : ans;
    }
}