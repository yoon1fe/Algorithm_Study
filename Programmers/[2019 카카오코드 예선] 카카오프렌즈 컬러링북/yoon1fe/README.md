## [2017 카카오코드 예선] 카카오프렌즈 컬러링북 - Java

### :book: 분류

> BFS



###  :book: 코드

```java
import java.util.*;

class Solution {
    static class Dir {
        int y, x;

        Dir(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    int[] dy = { 1, -1, 0, 0 };
    int[] dx = { 0, 0, 1, -1 };
    boolean[][] v;
    int[][] p;
    int M, N;

    public int[] solution(int m, int n, int[][] picture) {
        M = m; N = n;
        p = picture;
        v = new boolean[m][n];
        int[] answer = new int[2];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(v[i][j] == true) continue;
                int color = p[i][j];

                if(color != 0) {
                    answer[0]++;
                    answer[1] = Math.max(answer[1], bfs(new Dir(i, j), color));
                }
            }
        }

        return answer;
    }

    public int bfs(Dir start, int color) {
        Queue<Dir> q = new LinkedList<>();
        int cnt = 1;

        q.offer(start);
        v[start.y][start.x] = true;

        while (!q.isEmpty()) {
            Dir cur = q.poll();

            for (int i = 0; i < 4; i++) {
                Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
                if (!isIn(next) || p[next.y][next.x] != color || v[next.y][next.x] == true) continue;

                v[next.y][next.x] = true;
                q.offer(next);
                cnt++;
            }
        }

        return cnt;
    }

    public boolean isIn(Dir c) {
        if (0 <= c.y && c.y < M && 0 <= c.x && c.x < N) return true;
        else return false;
    }
}
```



### :book: 풀이 방법

간만에 간단한 BFS 문제입니다.

맨첨에 색깔이 같으면 같은 영역이라고 생각했는데 그것도 아니고 그냥 따로 봐주면 돼서 더 간단합니다.

험 일단 m, n, picture를 다른 메소드에서도 쓰려고 파라미터로 안 넘기고 전역변수를 선언해서 썼습니다. 

로직은 뭐 간단합니다!! 눈감고 물구나무서서도 짤 수 있을 정도

 

m * n 을 돌면서 picture[i][j] 가 0인거 제끼고 안에서 bfs를 돌려서 그 영역의 넓이를 구해주면 됩니다. 이때 최대 넓이를 구해야 하므로 최댓값을 갱신해주고, picture[i][j]가 0이 아닐때마다 영역의 개수++ 해주면 되는 것이지요..



###  :book: 후기

감사합니다!!!