import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        boolean[][][] isVisited = new boolean[N][M][64];

        int row = 0;
        int col = 0;
        for(int i=0; i<N; i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<M; j++){
                if(map[i][j] == '0'){
                    row = i;
                    col = j;
                    map[i][j] = '.';
                }
            }
        }

        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(row, col, 0, 0));
        isVisited[row][col][0] = true;

        int ans = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            Element elem = queue.poll();

            for(int dir=0; dir<4; dir++){
                int nx = elem.row + dx[dir];
                int ny = elem.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                if(isVisited[nx][ny][elem.key] || map[nx][ny] == '#')
                    continue;

                if(map[nx][ny] == '1'){
                    ans = Math.min(ans, elem.dist + 1);
                    continue;
                }

                if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F'){
                    if(!hasKey(keyChar2Int(map[nx][ny]), elem.key))
                        continue;

                    queue.offer(new Element(nx, ny, elem.key, elem.dist + 1));
                    isVisited[nx][ny][elem.key] = true;
                }

                else if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f'){
                    int nextKey = elem.setKey(keyChar2Int(map[nx][ny]));
                    queue.offer(new Element(nx, ny, nextKey, elem.dist + 1));
                    isVisited[nx][ny][elem.key] = true;
                    isVisited[nx][ny][nextKey] = true;
                }
                else if(map[nx][ny] == '.'){
                    queue.offer(new Element(nx, ny, elem.key, elem.dist + 1));
                    isVisited[nx][ny][elem.key] = true;
                }
            }

        }

        if(ans == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(ans);

    }

    public static int keyChar2Int(char key){
        key = Character.toUpperCase(key);
        if(key >= 'A' && key <= 'Z')
            return key - 'A';
        return 0;
    }

    public static boolean hasKey(int key, int keys){
        key = 1 << key;
        if((key & keys) == 0)
            return false;
        return true;
    }


    static class Element {
        int row;
        int col;
        int key;
        int dist;

        public Element(int row, int col, int key, int dist) {
            this.row = row;
            this.col = col;
            this.key = key;
            this.dist = dist;
        }

        public int setKey(int key){
            key = 1 << (key);
            return this.key | key;
        }
    }
}