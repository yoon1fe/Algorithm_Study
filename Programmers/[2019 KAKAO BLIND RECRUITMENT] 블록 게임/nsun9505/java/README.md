# [2019 KAKAO BLIND RECRUITMENT] 블록 게임

## 분류
> 시뮬레이션

## 코드
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Solution {
    static HashMap<Integer, ArrayList<Element>> elemMap = new HashMap<>();
    static HashMap<Integer, ArrayList<Element>> emptyMap = new HashMap<>();
    static int[] count = new int[201];
    public int solution(int[][] board) {
        int answer = 0;

        search(board);
        while(true){
            int ret = removeBlock(board);
            if(ret == 0)
                break;
            answer += ret;
        }

        return answer;
    }

    public int removeBlock(int[][] board) {
        int ret = 0;
        ArrayList<Integer> removeList = new ArrayList<>();
        Set<Integer> keys = emptyMap.keySet();
        for(int blockNumber : keys){
            int cnt = 0;
            for(Element empty : emptyMap.get(blockNumber)){
                if(!checkRemove(empty, board))
                    break;
                cnt++;
            }

            if(cnt < 2)
                continue;

            ret++;
            for(Element elem : emptyMap.get(blockNumber))
                board[elem.row][elem.col] = 0;
            for(Element elem : elemMap.get(blockNumber))
                board[elem.row][elem.col] = 0;
            removeList.add(blockNumber);
        }

        for(int blockNumber : removeList){
            emptyMap.remove(blockNumber);
            elemMap.remove(blockNumber);
        }

        return ret;
    }

    public boolean checkRemove(Element elem, int[][] board){
        int row = elem.row;
        if(board[elem.row][elem.col] != 0)
            return false;

        while(true){
            int nr = row - 1;

            if(nr < 0)
                return true;

            if(board[nr][elem.col] > 0)
                return false;

            row = nr;
        }
    }


    public static void search(int[][] board){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board.length; j++){
                check(i, j, board, i+1, j+2);
                check(i, j, board, i+2, j+1);
            }
        }
    }

    public static void check(int row, int col, int[][] board, int rowLimit, int colLimit){
        if(rowLimit >= board.length || colLimit >= board.length)
            return;

        Arrays.fill(count, 0);
        int numberOfBlock = -1;
        for(int i=row; i<=rowLimit && numberOfBlock == -1; i++){
            for(int j=col; j<=colLimit; j++){
                if(board[i][j] == 0)
                    continue;

                count[board[i][j]]++;
                if(count[board[i][j]] == 4){
                    numberOfBlock = board[i][j];
                    break;
                }
            }
        }

        if(numberOfBlock == -1)
            return;

        ArrayList<Element> list = new ArrayList<>();
        ArrayList<Element> emptyList = new ArrayList<>();
        for(int i=row; i<=rowLimit; i++){
            for(int j=col; j<=colLimit; j++) {
                if(board[i][j] == numberOfBlock)
                    list.add(new Element(i, j));
                else
                    emptyList.add(new Element(i, j));
            }
        }
        elemMap.put(numberOfBlock, list);
        emptyMap.put(numberOfBlock, emptyList);
    }

    static class Element{
        int row;
        int col;

        public Element(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
```

## 문제 풀이
블록을 검사하기 위해 2x3 또는 3x2 크기의 직사각형 범위 안에 4개의 똑같은 블록 넘버가 있는지 체크해서 블록을 알아냈습니다.

블록을 알아내면 2칸은 비어져있습니다.

하지만, 다른 블록이 그 위치를 차지하고 있을 수도 있지만, 일단 같은 블록 넘버가 아닌 애들 2개를 emptyMap에 blockNumber를 키로 저장합니다.

모든 블록을 찾았고, 블록에 해당하는 빈칸! 꽉 채우면 해당 블록이 2x3 또는 3x2 직사각형 크기로 가득 차게 해주는 빈칸에서 위 방향을 검사합니다.

만약 2칸의 위방향을 검사했을 때 아무 블록(자기 자신 포함)도 없다면 해당 블록을 지울 수 있으므로 지워줍니다.

그렇지 않은 블록은 넘어가도록 합니다.

그래서 지워지는 블록의 개수를 카운트해서 블록을 지우는 함수에서 0을 리턴하면 더 이상 지울 수 있는 블록이 없으므로

지워진 블록 개수의 누적합을 리턴하고 끝내면 됩니다.

## 후기
재미있어요~