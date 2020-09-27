import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};
    static boolean[][] map = new boolean[101][101];

    public static void makeDragonCurve(int x, int y, int d, int g) {
        List<Integer> direction = new ArrayList<>();
        direction.add(d);

        for(int i = 1; i <= g; i++) {
            for(int j = direction.size() - 1; j >= 0; j--) direction.add((direction.get(j) + 1) % 4);
        }

        map[y][x] = true; 
        for(Integer i : direction) {
            y += dy[i]; x += dx[i];
            map[y][x] = true; 
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            makeDragonCurve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < 100; i++) {
            for(int j = 0 ; j < 100; j++) {
                if(map[i][j] == true && map[i][j+1] == true && map[i+1][j] == true && map[i+1][j+1] == true) answer++;
            }
        }
        System.out.println(answer);
    }
}