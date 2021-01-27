import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(arr, 0));

        int ans = 0;
        while(!queue.isEmpty()){
            Element elem = queue.poll();

            if(elem.cnt == 5){
                int max = 0;
                for(int i=0; i<N; i++)
                    for(int j=0; j<N; j++)
                        max = Math.max(max, elem.arr[i][j]);
                ans = Math.max(ans, max);
                continue;
            }

            // UP
            Element up = new Element(elem.arr, elem.cnt + 1);
            for(int row = 0; row < N; row++){
                for(int col = 0; col < N; col++){
                    move(up, row, col, 0);
                }
            }
            queue.offer(up);

            // RIGHT
            Element right = new Element(elem.arr, elem.cnt+1);
            for(int col = N-1; col >= 0; col--){
                for(int row = 0; row < N; row++){
                    move(right, row, col, 1);
                }
            }
            queue.offer(right);

            // DOWN
            Element down = new Element(elem.arr, elem.cnt+1);
            for(int row = N-1; row >= 0; row--){
                for(int col = 0; col < N; col++){
                    move(down, row, col, 2);
                }
            }
            queue.offer(down);

            // LEFT
            Element left = new Element(elem.arr, elem.cnt + 1);
            for(int col = 0; col < N; col++){
                for(int row = 0; row < N; row++){
                    move(left, row, col, 3);
                }
            }
            queue.offer(left);
        }
        System.out.println(ans);
    }

    public static void move(Element elem, int row, int col, int dir){
        int val = elem.arr[row][col];
        elem.arr[row][col] = 0;
        while(true){
            int nx = row + dx[dir];
            int ny = col + dy[dir];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
                elem.arr[row][col] = val;
                break;
            }

            if(elem.arr[nx][ny] == 0){
                row = nx;
                col = ny;
            } else if(elem.arr[nx][ny] == val){
                if(!elem.isMerge[nx][ny]) {
                    elem.isMerge[nx][ny] = true;
                    elem.arr[nx][ny] *= 2;
                } else
                    elem.arr[row][col] = val;
                break;
            } else {
                elem.arr[row][col] = val;
                break;
            }
        }
    }

    static class Element{
        int[][] arr;
        boolean[][] isMerge;
        int cnt;

        Element(int[][] arr, int cnt){
            this.arr = new int[arr.length][arr.length];
            this.isMerge = new boolean[arr.length][arr.length];
            for(int i=0; i<arr.length; i++)
                for(int j=0; j<arr.length; j++)
                    this.arr[i][j] = arr[i][j];
            this.cnt = cnt;
        }
    }
}