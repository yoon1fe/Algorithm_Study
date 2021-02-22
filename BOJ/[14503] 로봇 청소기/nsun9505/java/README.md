# [14503] 로봇 청소기 - JAVA

## 분류
> 구현
>
> 시뮬레이션

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ans = 0;
    static boolean isBreak = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        isVisited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++)
                map[i][j] = st.nextToken().charAt(0);
        }

        solution(row, col, dir);
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int row, int col, int dir) {
        if(!isVisited[row][col]) {
            isVisited[row][col] = true;
            ans++;
        }

        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            dir = dir == 0 ? 3 : dir-1;
            int nx = row + dx[dir];
            int ny = col + dy[dir];

            if (map[nx][ny] == '1' || isVisited[nx][ny]) {
                cnt++;
                continue;
            }

            solution(nx, ny, dir);
            break;
        }

        if(cnt == 4) {
            int tmpDir = (dir + 2) % 4;
            row += dx[tmpDir];
            col += dy[tmpDir];
            if(map[row][col] != '1')
                solution(row, col, dir);
        }
    }
}
```

## 문제 풀이
DFS로 풀었습니다.

로봇이 청소할 수 있으면 이동한 후에 방문하지 않은 경우에는 청소 카운트(ans)를 하나 증가시킵니다.

이미 청소를 한 경우는 이전 위치에서 네 방향 모두 벽이거나 이미 청소한 경우라서 방향을 유지한 채로 뒤로 온 경우를 의미합니다.

그래서 중복으로 카운트하는 것을 방지하기 위해 첫 방문인 곳만 카운트를 증가시키면 됩니다.

사방 탐색은 일반 사방 탐색과 동일하고, 청소할 위치를 알아내면 바로 이동하면 됩니다.
   - 이후에 다른 방향에 청소할 공간이 있어도 탐색하지 않기 위해 break문을 걸어줘야 합니다.
   - 해당 위치를 다시 오는 경우는 뒤로 후진해서 오는 경우이므로, 다시 스택에 쌓여 있던 함수로 돌아와서 다른 방향을 또 탐색하면 안 되기 때문입니다.

solution() 마지막에 cnt가 4인 경우는 위에서 말한 것과 같이 4방향 모두 청소를 했거나 벽인 경우를 의미합니다.
   - 이 경우에는 방향을 유지하여 뒤로 후진하면 됩니다.
   - 그리고 후진하는 쪽이 벽이라면 solution()을 호출하지 않고 끝내면 됩니다.

## 후기
중복 체크에 대해서 이전에도 그랬고 지금도 좀 까먹고 있었던거 같습니다!

그래도 예전보다는 빠르게 풀었습니다!

룰루! 오늘도 화이팅!