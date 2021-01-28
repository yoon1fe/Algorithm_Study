# [1194] 달이차오른다, 가자. - JAVA

## 분류
> BFS
> 
> 비트마스킹

## 코드
```java
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
```

## 문제 풀이
BFS로 탐색을 하면 됩니다!

대신 탐색할 떄 해당 위치를 방문한 경우를 가지고 있는 key에 따라서 달리 해주면 됩니다.

key는 FEDCBA -> 111111 형식으로 나타냅니다.
   - 만약 A에 대한 키만 가지고 있으면 000001이고, AF를 가지고 있다면 100001이 됩니다.
   - 그러면 각 위치(row,col)과 key(0~63)을 3차원 배열로 해서 방문을 체크하면 됩니다.

문을 만난 경우 해당 문을 열 수 있는 키가 있는지 확인하고 이동하면 됩니다.
   - 문을 열 수 없다면 패스

key를 만난 경우에는 key를 세팅하고 탐색을 이어갑니다.

BFS할 때 이차원 배열로만 체크했었다면, 여기서는 key까지 봐야하기 때문에 [row][col][key]와 같이 3차원 배열로 방문 체크를 하면서

BFS로 탐색을 하면 문제를 해결할 수 있습니다.

## 후기
문제를 잘 읽어야 합니다..

A-Z줄 알고 당연히 메모리가 터지는거 아닌가 생각했는데, 잘 읽어보니 A-F라는거~

제발.. 문제를 잘 읽자ㅠ