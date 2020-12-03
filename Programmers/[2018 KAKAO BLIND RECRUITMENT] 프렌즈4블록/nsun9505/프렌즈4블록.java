package Programmers;

import java.util.ArrayList;

public class 프렌즈4블록 {
    static class Pos{
        int row;
        int col;

        Pos(int r, int c){
            this.row = r;
            this.col = c;
        }
    }

    public static char[][] Board;
    public int solution(int m, int n, String[] board) {
        Board = new char[m][n];
        for(int i=0; i<m; i++)
            Board[i] = board[i].toCharArray();
        int answer = 0;

        while(true){
            ArrayList<Pos> delList = searchDelBlock(m, n);

            if(delList.size() == 0)
                break;

            answer += deleteBlock(delList);
            int moveStartRow = getMoveStartRow(delList);
            moveBlock(m, n, moveStartRow);
        }

        return answer;
    }

    public static void moveBlock(int m, int n, int row){
        for(int i = row; i>= 0; i--){
            for(int j=0; j<n; j++){
                if(Board[i][j] == '0')
                    continue;
                int curRow = i;
                while(true){
                    int nextRow = curRow + 1;

                    if(nextRow >= m)
                        break;
                    if(Board[nextRow][j] != '0')
                        break;

                    curRow = nextRow;
                }

                if(curRow == i)
                    continue;

                Board[curRow][j] = Board[i][j];
                Board[i][j] = '0';
            }
        }
    }

    public static int getMoveStartRow(ArrayList<Pos> delList){
        int row = 0;
        for(Pos p : delList)
            if(row < p.row)
                row = p.row;
        return row;
    }

    public static int deleteBlock(ArrayList<Pos> delList){
        int cnt = 0;
        for(Pos pos : delList){
            for(int i=0; i<2; i++){
                if(Board[pos.row+i][pos.col] != '0'){
                    cnt++;
                    Board[pos.row+i][pos.col] = '0';
                }

                if(Board[pos.row + i][pos.col+1] != '0'){
                    cnt++;
                    Board[pos.row + i][pos.col+1] = '0';
                }
            }
        }

        return cnt;
    }

    public static ArrayList<Pos> searchDelBlock(int m, int n){
        ArrayList<Pos> delList = new ArrayList<>();
        for(int i=0; i<m-1; i++){
            for(int j=0; j<n-1; j++){
                if(Board[i][j] == '0')
                    continue;
                char compCh = Board[i][j];
                if(compCh == Board[i][j+1] && compCh == Board[i+1][j] && compCh == Board[i+1][j+1])
                    delList.add(new Pos(i, j));
            }
        }
        return delList;
    }
    public static void main(String[] args){
        프렌즈4블록 f = new 프렌즈4블록();
        System.out.println(f.solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
    }
}