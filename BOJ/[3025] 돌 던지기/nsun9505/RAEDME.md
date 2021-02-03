# [3025] 돌 던지기 - JAVA

## 분류
> 구현
>
> 시뮬레이션

## 코드
```java
import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        int[][] move = new int[R][C];
        for(int i=0; i<R; i++){
            String row = br.readLine();
            for(int j=0; j<C; j++)
                map[i][j] = row.charAt(j);
        }
        Stack<Pos>[] paths = new Stack[C];
        for(int i=0; i<C; i++)
            paths[i] = new Stack<>();

        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            int col = Integer.parseInt(br.readLine()) - 1;

            while(!paths[col].isEmpty()) {
                Pos putPos = paths[col].peek();
                if (map[putPos.row][putPos.col] == '.')
                    break;
                paths[col].pop();
            }

            setPath(paths[col],col);
            Pos putPos = paths[col].pop();
            map[putPos.row][putPos.col] = 'O';
        }

        for(int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++)
                bw.write(map[row][col]);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void setPath(Stack<Pos> path, int c){
        int row = 0;
        int col = c;
        if(!path.isEmpty()){
            Pos pos = path.pop();
            row = pos.row;
            col = pos.col;
        }

        // 돌 움직이기
        while(row < R){
            path.push(new Pos(row, col));
            if(row < R - 1 && map[row+1][col] == 'X')
                break;
            else if(row < R - 1 && map[row+1][col] == 'O'){
                if(col > 0 && map[row][col-1] == '.' && map[row+1][col-1] == '.')
                    col--;
                else if(col < C - 1 && map[row][col+1] == '.' && map[row+1][col+1] == '.')
                    col++;
                else
                    break;
            }
            row++;
        }
    }

    static class Pos{
        int row;
        int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
```

## 문제 풀이
그냥 돌이 움직이는 것만 구현하면 풀릴줄 알았지만 그렇지 않습니다!

돌을 던지는 횟수가 100,000이고 R(내려가는 길이)이 30000이므로 N * R으로는 시간초과입니다.

최대한 시간을 줄이기 위해서 출발하는 col에서 갈 수 있는 경로를 stack에 저장합니다.

경로에 이미 돌이 놓아져있다면 돌을 놓을 수 있을 때까지 pop을 하면 됩니다.
   - 만약, 아직 경로를 세팅하지 않았다면 세팅하면 됩니다.

그러면 마지막 경로(top)를 보고 '.'일 경우에 돌을 놓으면 됩니다.
   - 대신, 돌을 놓기 전에 거기서 더 밑으로 내려갈 수 있는지 체크 후에 돌을 놓아야 합니다.

## 후기
후아 돌 이동이 꼬여서 하루종일 본거 같네요ㅠ