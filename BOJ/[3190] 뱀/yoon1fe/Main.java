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