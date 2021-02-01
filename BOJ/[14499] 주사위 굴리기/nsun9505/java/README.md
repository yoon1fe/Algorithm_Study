# [1499] 주사위 굴리기 - JAVA

## 분류
> 구현
>
> 시뮬레이션

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        int[] dice = new int[7];
        int[][] map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                continue;

            int tmp = dice[6];
            if(dir == 0){
                dice[6] = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[4];
                dice[4] = tmp;
            } else if (dir == 1){
                dice[6] = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[3];
                dice[3] = tmp;
            } else if(dir == 2){
                dice[6] = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[5];
                dice[5] = tmp;
            } else if(dir == 3){
                dice[6] = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[2];
                dice[2] = tmp;
            }

            if(map[nx][ny] == 0){
                map[nx][ny] = dice[6];
            } else {
                dice[6] = map[nx][ny];
                map[nx][ny] = 0;
            }
            x = nx;
            y = ny;

            System.out.println(dice[1]);
        }

    }

}
```

## 문제 풀이
머리에 주사위를 움직일 때 배열의 위치가 어떻게 바뀌는지 남아있어서 수월하게 풀 수 있었습니다.

무조건 바닥면은 6이고, 주사위의 윗면은 1이라고 가정하고 움직인 방향에 따라 변경해주면 됩니다.

한 번 움직일 때마다 배열의 4개의 원소가 바뀌게 됩니다.

예를 들어 남쪽으로 움직인다면
   - 중앙에 있는 2, 1, 5, 6를 하나씩 옆으로 이동시켜 주면 됩니다.
   ```
         [2]
      [4][1][3]
         [5]
         [6]
   ```
      - 6은 바닥면이니깐 위 상태에서 아래로 움직이면 5가 바닥면으로 가게 됩니다.
      - 1은 5의 남쪽방향에 위치하게 됩니다.
      - 2는 5와 마주보는 윗면이 되므로 1로 이동하면 됩니다.
      - 6은 바닥면에서 주사위가 아래로 이동하였으므로 5의 북쪽 방향이므로 2의 위치로 이동하면 됩니다.
      - 이렇게 주사위로 어디로 움직이냐에 따라서 배열의 원소를 움직여주면 됩니다.
    
움직인 후에는 문제에서 원하는대로 움직인 위치가 0이면 바닥면을 복사하고, 0이 아니면 바닥면에 0이 아닌 값을 넣고, 움직인 위치를 0으로 만들어줍니다.

그리고 주사위 윗면의 값을 출력하면 됩니다.

```
위로 이동
tmp = arr[6]
arr[6] = arr[2]
arr[2] = arr[1]
arr[1] = arr[5]
arr[5] = tmp

아래로 이동
tmp = arr[6]
arr[6] = arr[5]
arr[5] = arr[1]
arr[1] = arr[2]
arr[2] = tmp

왼쪽으로 이동
tmp = arr[6]
arr[6] = arr[4]
arr[4] = arr[1]
arr[1] = arr[3]
arr[3] = tmp

오른쪽으로 이동
tmp = arr[6]
arr[6] = arr[3]
arr[3] = arr[1]
arr[1] = arr[4]
arr[4] = tmp
```

위와 같이 설정하고 문제를 풀면 금방 풀 수 있습니다!

## 후기
후아!