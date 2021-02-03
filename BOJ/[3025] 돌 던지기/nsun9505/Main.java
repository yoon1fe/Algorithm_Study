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
            if(row < R - 1 && map[row+1][col] == 'X') // 다음에 벽인 경우 여기까지만!
                break;
            else if(row < R - 1 && map[row+1][col] == 'O'){
                if(col - 1 >= 0 && map[row][col-1] == '.' && map[row+1][col-1] == '.') // 외쪽으로 가는 경우
                    col--; 
                else if(col + 1 < C && map[row][col+1] == '.' && map[row+1][col+1] == '.') // 오른쪽으로 가는 경우
                    col++;
                else
                    break; // 밑에 돌이 있지만 오른쪽이나 왼쪽으로 더 이상 갈 수 없는 경우
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