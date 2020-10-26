# [17070] 파이프 옮기기 1 - Java

###  :pick: 분류

> 시뮬레이션
>
> 백트래킹



### :pick: 코드

```java
import java.io.*;
import java.util.*;

public class Main {

    static int N, answer;
    static int[][] map;
    static int[] dy = {0, 1, 1};
    static int[] dx = {1, 1, 0};

    public static void main(String[] args) throws IOException {
        input();

        dfs(0, 0, 1);    // status 0: 가로        1: 세로    2: 대각선
        System.out.println(answer);
    }

    public static void dfs(int status, int y, int x) {
        if(y == N - 1 && x == N - 1) {
            answer++;
            return;
        }

        switch(status) {
        case 0:
            for(int i = 0; i < 2; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                if(i == 0 && available(ny, nx)) dfs(i, ny, nx);
                if(i == 1 && checkDiagonal(ny, nx)) dfs(i, ny, nx);
            }
            break;
        case 1:
            for(int i = 0; i < 3; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                if(i == 0 && available(ny, nx)) dfs(i, ny, nx);
                if(i == 1 && checkDiagonal(ny, nx)) dfs(i, ny, nx);
                if(i == 2 && available(ny, nx)) dfs(i, ny, nx);
            }
            break;
        case 2:
            for(int i = 1; i < 3; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                if(i == 1 && checkDiagonal(ny, nx)) dfs(i, ny, nx);
                if(i == 2 && available(ny, nx)) dfs(i, ny, nx);
            }
            break;
        }
    }

    public static boolean available(int y, int x) {
        if(0 <= y && y < N && 0 <= x && x < N && map[y][x] == 0) return true;
        else return false;
    }

    public static boolean checkDiagonal(int y, int x) {
        if(available(y -1, x) && available(y, x) && available(y, x - 1)) return true;
        else return false;
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); 
            }
        }
    }
}
```



### :pick: 풀이 방법

삼성 SW 역량 테스트 A형 기출 문제입니다.

아주 단순한 백트래킹 문제입니다.



결국 구해야 하는게 맨 오른쪽 밑으로 이동시킬 수 있는 경우의 수를 모두 구하는 거니깐 좌표를 갖고 가능한 경우를 보면서 백트래킹하면 됩니다.

 

맨 처음 시작은 파이프의 상태가 가로(status == 0), 시작 좌표는 (0, 1) 입니다. 먼저 파이프의 위치? 모양? 에 따라 세 경우로 나뉩니다. 가로일 때는 가로, 대각선, 대각선일 때는 세 경우, 세로일 때는 대각선, 세로 이렇게 봐주면 됩니다. 가로, 세로로 가는 경우는 거기만 봐주면 되고, 대각선인 경우에는 세군데를 봐줘야 합니다.

 

맨처음 제출했을 때 100프로에서 시간초과가 떴씁니다.... 뭐지 했는데 맨첨에 좌표를 Dir 클래스를 만들어서 사용했는데 대각선 봐줄 때 인스턴스를 새로 생성해서 넘겨줬는데 요게 오버헤드가 컸나 봅니다..... 걍 int 변수 y, x로 바꾸니깐 바로 통과됐습니다 허허

### :pick: 후기

15분컷!!!

역테 치고싶당