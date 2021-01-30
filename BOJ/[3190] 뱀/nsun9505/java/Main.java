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