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

                if(nextR.day > 10) {
                    return -1;
                }
                
                if (map[nextR.y][nextR.x] == '#' && map[nextB.y][nextB.x] == '#') continue;

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

                if (nextB.y == nextR.y && nextB.x == nextR.x) {
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