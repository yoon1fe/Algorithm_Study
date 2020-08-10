## [3190] 뱀 - Java

### :snake: 분류

> 시뮬레이션



### :snake: 코드

```java
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

class Dir{
    int y;
    int x;
    Dir(int y, int x){
        this.y = y;
        this.x = x;
    }
}

class Move{
    int sec;
    char dir;
    Move(int sec, char dir){
        this.sec = sec;
        this.dir = dir;
    }
}

class Snake{
    int dir;
    Deque<Dir> body = new LinkedList<>();
    Snake(int dir, Dir cur){
        this.dir = dir;
        this.body.offer(cur);
    }
}

public class Main {
    static int[][] map;
    static int N;
    static Move[] moves;
    static char snakeDir = 1;
    static int[] dy = {-1, 0, 1, 0};    //U R D L
    static int[] dx = {0, 1, 0, -1};
    static Snake snake;

    static boolean isIn(Dir cur) {
        if(0<= cur.y && cur.y < N && 0<= cur.x && cur.x < N) return true;
        else return false;
    }
    //대가리가 q.rear
    static void moveSnake() {
        int time = 0;
        int i = 0;    //moves idx
        while(true) {
            time++;
            Dir next = new Dir(snake.body.getLast().y + dy[snake.dir], 
                            snake.body.getLast().x + dx[snake.dir]);
            if(!isIn(next) || map[next.y][next.x] == 2) {
                System.out.println(time);
                return;
            }

            if(map[next.y][next.x] == 1) {
                snake.body.offer(next);
                map[next.y][next.x] = 2; 
            }
            else {
                map[snake.body.peek().y][snake.body.peek().x] = 0;
                snake.body.poll();
                snake.body.offer(next);
                map[next.y][next.x]= 2; 
            }
            if(i < moves.length) {
                if(time == moves[i].sec) {
                    snake.dir = moves[i].dir == 'D' ? (snake.dir+5) % 4 : (snake.dir+3) % 4;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        snake = new Snake(1, new Dir(0, 0));
        N = Integer.parseInt(sc.nextLine());
        map = new int[N][N];
        map[0][0] = 2;
        int K = Integer.parseInt(sc.nextLine());

        for(int i = 0; i < K; i++) {
            int y = Integer.parseInt(sc.next()) - 1;
            int x = Integer.parseInt(sc.next()) - 1;
            map[y][x] = 1;
        }

        int L = Integer.parseInt(sc.next());
        moves = new Move[L];
        for(int i = 0; i< L; i++) moves[i] = new Move(Integer.parseInt(sc.next()), sc.next().charAt(0));
        
        moveSnake();
    }
}
```



### :snake: 풀이 방법

뱀이 왔다갔다 하는 문제입니다. 문제에서 요구하는 조건을 잘 고려해서 구현하면 됩니다.

- `Snake` - 뱀의 정보를 담는 클래스입니다. 

  `dir`은 뱀이 바라보고 있는 방향입니다.

  `Deque<Dir> body`: 덱`(Deque)`을 이용해 뱀의 몸통 부분을 구현했습니다. 

처음에 뱀의 몸통 부분을 `Queue`로 구현했는데, 다음 이동 방향이 `Queue`의 `rear` 쪽에서 업데이트 해주어야 하므로 `Deque`로 변경했습니다.

근데 `body.contains(next)`를 통해 뱀의 머리가 몸통에 부딪히는 상황을 구현하려 했는데 참조 자료형이라 그런지 `body`에 `next` 값이 들어 있어도 `true`를 리턴하지 않아서 `map`에 뱀의 몸을 반영하면서 구현했습니다.

사과를 먹으면 몸통이 줄지 않기 때문에 `next`를 `Deque` 맨 뒤에 넣어주면 됩니다. 

사과를 먹지 않으면 한 칸씩 이동하기 것이기 때문에 `Deque`의 맨 앞에 놈(꼬리쪽)을 빼고 `next`를 넣어줌으로써 뱀의 이동을 쉽게 구현했습니다.



### :snake: 후기

몇 개월전에 풀어보았던 문제인데도 지금 구현한 방법과 조금씩 차이가 있습니다. 어떤 문제든 꾸준히 많이 접해보면 분명히 코딩 실력이 일취월장하리라 믿습니다.