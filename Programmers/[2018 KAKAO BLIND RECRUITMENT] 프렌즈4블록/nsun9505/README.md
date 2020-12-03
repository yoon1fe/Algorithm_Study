# [2018 KAKAO BLIND RECRUITMENT] 프렌즈4블록 - JAVA

## 분류
> 구현

## 코드
```java
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
```

## 문제 풀이
String배열로 뭘 우짜기가 너무너무 귀찮아서 char[][]로 바꿔서 풀었습니다.

1. 삭제할 2x2 블록의 왼쪽 상단 위치를 찾습니다.
   - Board를 둘러보면서 삭제할 2x2 블록의 왼쪽 상단 위치를 찾아봅니다.
   - Board[i][j]가 Board[i][j+1], Board[i+1][j], Board[i+1][j+1] 이 셋과 모두 같다면 delList에 (i, j)를 넣습니다.

1. 실제로 Board에서 2x2 블록 삭제
   - delList에서 Pos를 받아와서 Board[i][j], Board[i][j+1], Board[i+1][j], Board[i+1][j+1]의 위치를 모두 0으로 만들어 줍니다.
   - 대신! 이미 Board[i][j]가 0이라면 cnt를 증가시키지 않고 0도 넣지 않습니다.
   - 그리고 cnt 값을 리턴해서 answer에 더합니다.

1. 블록을 지웠으니 붕 떠있는 블록을 아래로 이동시킵니다.
   - 삭제한 2x2 블록의 왼쪽 상단 위치 중 가장 큰 row 값을 찾아서 해당 row 리턴합니다.
   - row~0까지 보면서 밑으로 이동시킬 수 있는 블록을 찾았다면 해당 블록을 아래로 이동할 수 있는 곳으로 이동시킵니다.
   
## 후기
카카오 순한맛!