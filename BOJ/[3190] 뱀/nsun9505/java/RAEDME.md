# [3190] 뱀 - JAVA

## 분류
> 구현

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] board = new boolean[N][N];
        char[] changeDir = new char[10001];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int M = Integer.parseInt(br.readLine());
        ArrayList<Node> apples = new ArrayList<>();
        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            apples.add(new Node(row, col));
            board[row][col] = true;
        }

        int L = Integer.parseInt(br.readLine());
        for(int i=0; i<L; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            changeDir[sec] = dir;
        }

        LinkedList<Node> list = new LinkedList<>();
        list.offerFirst(new Node(0, 0));
        int curDir = 1;
        for(int sec = 1;;sec++){
            Node head = list.getFirst();
            int nx = head.row + dx[curDir];
            int ny = head.col + dy[curDir];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N){
                System.out.println(sec);
                break;
            }

            boolean isEnd = false;
            for(Node node : list)
                if(node.row == nx && node.col == ny){
                    System.out.println(sec);
                    isEnd = true;
                    break;
                }

            if(isEnd)
                break;

            list.offerFirst(new Node(nx, ny));
            if(!board[nx][ny])
                list.removeLast();
            else
                board[nx][ny] = false;

            if(changeDir[sec] == 'L')
                curDir = changeDirection(curDir-1);
            else if(changeDir[sec] == 'D')
                curDir = changeDirection(curDir+1);
        }
    }

    public static int changeDirection(int cur){
        if(cur == -1)
            return 3;
        else if(cur == 4)
            return 0;
        return cur;
    }

    static class Node{
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
```

## 문제 풀이
0초부터 시작합니다!

뱀은 링크드 리스트로 표현했습니다.
   - 움직인 위치는 앞에 넣어주고, 길이가 늘어나면 마지막에 있는 노드를 뺴지 않습니다.
   - 길이가 늘어나지 않을 경우 마지막에 있는 노드를 빼줍니다.

만약에 다음 칸이 벽이거나 뱀의 일부일 경우에는 초를 출력하고 종료하면 됩니다.

움직인 위치에 사과가 있으면 리스트의 마지막 번째에 있는 노드를 지우지 않고, 사과가 있던 자리를 false로 만들어줍니다.

사과가 없다면, 리스트의 마지막의 노드를 없애줍니다.

그리고 현재 sec이 끝난 후에 방향을 바꿔야 할 경우
    - changeDir[sec]이 L 또는 D일 경우 뱀의 머리 방향을 바꿔주면 됩니다.

## 후기
사과를 먹었으면 없애야지~