# [13460] 구슬 탈출 2 - Java

###  :crystal_ball: 분류

> BFS
>
> 시뮬레이션



### :crystal_ball: 코드

```java
import java.util.*;

class Dir{
    int y, x, day;
    Dir(int y, int x, int day){
        this.y = y; this.x = x; this.day = day;
    }
}

public class Main {
    static char[][] map;
    static int[][] visited;
    static Dir red, blue;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int bfs() {
        Queue<Dir> q = new LinkedList<Dir>();
        visited[red.y][red.x] = 1;
        boolean flag = false;
        boolean blueIn = false;
        int ans = 0;
        q.offer(red);
        q.offer(blue);

        while (!q.isEmpty()) {
            Dir curR = q.poll();
            Dir curB = q.poll();


            forloop:
            for (int i = 0; i < 4; i++) {
                Dir nextR = new Dir(curR.y + dy[i], curR.x + dx[i], curR.day + 1);
                Dir nextB = new Dir(curB.y + dy[i], curB.x + dx[i], 0);

                if (nextR.day > 10) return -1;
                if (map[nextR.y][nextR.x] == '#' && map[nextB.y][nextB.x] == '#') 
                    continue;

                while (map[nextR.y][nextR.x] != '#') {
                    if (map[nextR.y][nextR.x] == 'O') {
                        flag = true;
                        ans = nextR.day;

                        nextR.y += dy[i];
                        nextR.x += dx[i];
                        break;
                    }
                    nextR.y += dy[i];
                    nextR.x += dx[i];
                }
                nextR.y -= dy[i];
                nextR.x -= dx[i];        //끝까지 이동

                whileloop:
                while (map[nextB.y][nextB.x] != '#') {
                    if (map[nextB.y][nextB.x] == 'O') {
                        ans = -1;
                        if(map[nextR.y][nextR.x] == 'O'){
                            blueIn = true;
                        }
                        continue forloop;
                    }
                    nextB.y += dy[i];
                    nextB.x += dx[i];
                }
                nextB.y -= dy[i];
                nextB.x -= dx[i];

                if (flag == true) {
                    if(!blueIn) return ans;
                    else {
                        blueIn = false;
                        flag = false;
                    }
                }

                if (nextB.y == nextR.y && nextB.x == nextR.x) {    //겹쳤을 때 처리
                    switch (i) {
                        case 0:
                            if (curR.y < curB.y) nextR.y -= dy[i];
                            else nextB.y -= dy[i];
                            break;
                        case 1:
                            if (curR.y < curB.y) nextB.y -= dy[i];
                            else nextR.y -= dy[i];
                            break;
                        case 2:
                            if (curR.x < curB.x) nextR.x -= dx[i];
                            else nextB.x -= dx[i];
                            break;
                        case 3:
                            if (curR.x < curB.x) nextB.x -= dx[i];
                            else nextR.x -= dx[i];
                            break;
                    }
                }
                q.offer(nextR);
                q.offer(nextB);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        map = new char[N][M];
        visited = new int[N][M];
        sc.nextLine();
        for (int i = 0; i < N; i++) {
            String temp = sc.nextLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = temp.charAt(j);
                if (map[i][j] == 'R') red = new Dir(i, j, 0);
                if (map[i][j] == 'B') blue = new Dir(i, j, 0);
            }
        }
        System.out.println(bfs());
    }
}
```



### :crystal_ball: 풀이 방법

`BFS`를 활용한 시뮬레이션 문제입니다. 판을 상하좌우로 기울여서 빨간 공을 구멍에 넣는 게임인데, 파란 공이 구멍에 들어가면 게임 오버입니다. 

처음에 구현한 방식은 문제가 아주 많았습니다.

1. 파란 공이 구멍에 들어가면 무조건 게임 오버로 구현했는데, 이렇게 짜면 큰일납니다. 그 경우가 아닌 경우라도 빨간 공이 구멍에 들어갈 수 있기 때문에 완전 탐색으로 봐주어야 합니다.

2. 빨간 공이 움직일 수 없는 경우(벽에 닿아 있는 경우) 탐색에서 제외했는데, 이 경우에도 커다란 **맹점**이 생깁니다. 빨간 공이 움직이지 않더라도 판을 기울여 파란 공을 구멍에 들어가지 않도록 위치시켜줄 수 있기 때문입니다.

빨간 공이 구멍에 들어가고 나서 파란 공이 들어가는지 여부를 판단해주기 위해 `flag` 변수를 뒀습니다.

빨간 공, 파란 공의 기울이고 난 다음의 위치를 구해주고, 빨간 공이 들어갔는지 여부, 파란 공도 들어갔는지 여부를 체크한 다음 두 공의 위치가 중복되면 이동해온 방향에 따라 공의 위치를 다시 재조정해주면서 `BFS`를 반복하면 됩니다.



### :crystal_ball: 후기

어째저째 여러 반례들을 찾아가며 풀긴 풀었는데 스스로 반례를 만드는 연습을 많이 해야겠습니다.

또한 나중에 4차원 배열을 두고 풀어봐야겠습니다..