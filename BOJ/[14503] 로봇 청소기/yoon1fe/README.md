# [14503] 로봇 청소기 - Java

###  :robot: 분류

> 시뮬레이션
>
> BFS



### :robot: 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int N, M;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int answer = 0;

    static class Robot {
        Dir pos;
        int d;
        Robot(Dir pos, int d){
            this.pos = pos; this.d = d;
        }
    }

    static class Dir{
        int y, x;        //r, c
        Dir(int y, int x){
            this.y = y; this.x = x;
        }
    }

    public static boolean isIn(Dir c) {
        if(0<= c.y && c.y < N && 0<= c.x & c.x < M ) return true;
        else return false;
    }

    static int move(Robot robot) {
        boolean[][] v = new boolean[N][M];
        Queue<Robot> q = new LinkedList<>();
        q.offer(robot);
        v[robot.pos.y][robot.pos.x] = true;        //청소
        answer++;

        outer:
        while(!q.isEmpty()) {
            Robot c = q.poll();

            for(int i = 0; i < 4; i++) {
                int nextDir = (c.d + 4 - 1) % 4;
                if(v[c.pos.y + dy[nextDir]][c.pos.x + dx[nextDir]] || map[c.pos.y + dy[nextDir]][c.pos.x + dx[nextDir]] == 1) continue;

                answer++;
                v[c.pos.y + dy[nextDir]][c.pos.x + dx[nextDir]] = true;
                q.offer(new Robot(new Dir(c.pos.y + dy[nextDir], c.pos.x + dx[nextDir]), nextDir));
                continue outer;
            }
            int backDir = (c.d + 4 - 2) % 4;
            if(map[c.pos.y + dy[backDir]][c.pos.x + dx[backDir]] != 1) {
                q.offer(new Robot(new Dir(c.pos.y + dy[backDir], c.pos.x + dx[backDir]), c.d));
            }else {
                return answer;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(br.readLine(), " ");
        Robot robot = new Robot(new Dir(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())), Integer.parseInt(st.nextToken()));


        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(move(robot));
    }
}
```



### :robot: 풀이 방법

삼성 SW 역량테스트 기출문제로 시뮬레이션 문제입니다. 단순 시뮬레이션 문제라서 문제에서 요구하는 조건을 잘 생각해서 풀면 됩니다.

이런 단순 시뮬레이션 문제를 풀 때는 처음에 어떻게 코드를 짜야겠다 생각을 잘 하고 푸는게 제일 중요하다고 생각됩니다. 단순히 코드 한 줄이 어디에 있냐에 따라 결과가 천차만별이기 때문에 전체 흐름을 잘 생각해놓고 코딩하는 연습을 해야겠습니다.

이 문제는 부끄럽게도 스스로 그렇게 고민을 많이 하고 풀지 않았슴다. 첨에 어떻게 푸나 고민하다가 일단 한 줄씩 치다 보니 운좋게 로직에 잘 맞게 짜서 쉽게 풀었습니다...

청소하러 이동한 경우나, 사방을 탐색하고 청소할 곳을 못 찾았을 때 뒤로 후진한 경우를 Queue에 넣는 식으로 풀었습니다..

네번 탐색하기 전에 청소할 곳을 찾으면 answer를 ++ 해주면 됩니다. 그러고 그 쪽으로 이동했기 때문에 바깥 반복문으로 continue 해주는 거지요.

 사방 탐색하는 반복문 밑으로 간다는 뜻은 네 방향을 다 탐색했는데 청소할 곳을 못 찾았을 때입니다. 후진한 좌표를 토대로 거기로 이동할 수 있는지 없는지 체크하면 됩니다.



### :robot: 후기

최근에 나온 기출문제에 비하면 아주 양반입니다... 아직은 시뮬레이션이 재밌네여...