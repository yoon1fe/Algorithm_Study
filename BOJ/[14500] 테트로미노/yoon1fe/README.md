# [14500] 테트로미노 - Java

###  &#128160; 분류

> 시뮬레이션
>
> DFS



### &#128160; 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Dir {
        int y, x;

        Dir(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static Dir[] polyomino = new Dir[4];
    static int N, M;
    static int[][] map;
    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0, 0, 1, -1 };
    static int answer = 0;

    static boolean isIn(Dir c) {
        if (0 <= c.y && c.y < N && 0 <= c.x && c.x < M) return true;
        else return false;
    }

    static int calc() { // 네 개 만든 폴리오미노 계산
        int sum = 0;
        for (Dir p : polyomino)
            sum += map[p.y][p.x];
        return sum;
    }

    static void dfs(Dir c, int cnt) {
        if (cnt == 4) {
            answer = Math.max(answer, calc());
            return;
        }

        polyomino[cnt] = c;
        outer:
        for (int i = 0; i < 4; i++) {
            Dir next = new Dir(c.y + dy[i], c.x + dx[i]);
            if (!isIn(next)) continue;
            for(int j = 0; j < cnt; j++) {
                if(next.y == polyomino[j].y && next.x == polyomino[j].x) continue outer;
            }

            dfs(next, cnt + 1);
        }
    }

    static void makeSpecialCase(Dir c) {
        polyomino[0] = c;
        boolean flag = false;
        boolean isMade = true;
        Dir temp = c;
        for (int i = 1; i < 3; i++) { // 가로
            Dir next = new Dir(temp.y + dy[2], temp.x + dx[2]);
            if (!isIn(next)) {
                flag = true; break; // 폴리오미노 생성 실패
            }
            polyomino[i] = next;
            temp = next;
        }
        if (!flag) {
            Dir up = new Dir(c.y - 1, c.x + 1);
            Dir down = new Dir(c.y + 1, c.x + 1);
            if (isIn(up) && isIn(down)) polyomino[3] = (map[up.y][up.x] > map[down.y][down.x] ? up : down);
            else if (isIn(up)) polyomino[3] = up;
            else if (isIn(down)) polyomino[3] = down;
            else isMade = false;        //둘다 해당 안되는 경우
        }
        if (isMade)
            answer = Math.max(answer, calc());

        flag = false;
        temp = c;
        for (int i = 1; i < 3; i++) { // 세로
            Dir next = new Dir(temp.y + dy[0], temp.x + dx[0]);
            if (!isIn(next)) {
                flag = true; break; // 폴리오미노 생성 실패
            }
            polyomino[i] = next;
            temp = next;
        }
        if (!flag) {
            Dir right = new Dir(c.y + 1, c.x + 1);
            Dir left = new Dir(c.y + 1, c.x - 1);
            if (isIn(right) && isIn(left)) polyomino[3] = (map[right.y][right.x] > map[left.y][left.x] ? right : left);
            else if (isIn(right)) polyomino[3] = right;
            else if (isIn(left)) polyomino[3] = left;
            else isMade = false;        //둘다 해당 안되는 경우
        }
        if (isMade)
            answer = Math.max(answer, calc());
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                //boolean[][] v = new boolean[N][M];
                dfs(new Dir(i, j), 0);
                makeSpecialCase(new Dir(i, j));
            }
        }
        System.out.println(answer);
    }
}
```



### &#128160; 풀이 방법

블럭 네 개짜리 테트로미노를 만들어서 입력으로 주어진 맵에 테트로미노에 해당하는 구역의 합이 젤 큰 값을 구하는 문제입니다. 여러 방법으로 블럭을 만들 수 있습니다.



대칭, 회전 다 되기 때문에 나올 수 있는 블럭의 모양은 다 합쳐서 19개입니다. 다섯 달전에 풀 때는 이 19개를 다 좌표로 지정해두고 하나하나 맞춰보면서 답을 구했습니다.

블럭 네개짜리 테트로미노는 잘 보면 빠큐모양빼고는 모두 DFS로 만들 수 있습니다. 그래서 이번에는 DFS로 테트로미노들을 만들어보았습니다.

처음 풀 때 메인 메소드에서 dfs로 들어갈 때마다 방문했는지 여부를 판단하는 2차원 배열 v를 만들고 검사해줬었는데 시간초과가 떴습니다.

N과 M의 최댓값이 500이기 때문에 쓸데없이 25만개짜리 배열을 만들어줘서 그런가봅니다.

어차피 최대 세 곳만 봐주면 되기 때문에 이를 v를 이용해 방문 여부를 판단하지 않고, polyomino 배열에 들어있는지로 판단해서 시간초과를 해결했습니다.

빠큐모양 4 가지 경우를 제외한 모든 경우는 dfs를 이용해서 만들어서 answer 값을 업데이트 해줬고 이제 빠큐모양을 만들어봅시다.

현재 좌표를 기준점으로 왼쪽과 오른쪽으로 뻗친 빠큐는 봐줄 필요가 없습니다. 오른쪽 밑쪽만 만들어주면 어차피 다른 좌표에서 봐줄꺼기 때문입니다. 따라서 밑으로 내려가는 세로, 오른쪽으로 가는 가로를 만들어서 봐주면 됩니다.

이때 3개짜리 직선을 만든 경우에만 테트로미노를 만들 수 있습니다. 근데 또 항상 만들어지는게 아님다. 뽈록 튀어나온 빠큐모양을 봐줘야 되는데 얘가 또 맵을 벗어날 수 있으므로 잘 생각해서 짜줍시다.

 

 

### &#128160; 후기

DFS는 생각보다 훨씬 활용도가 높네여.

그리고 시간초과뜬다고 당황하지 맙시다!!