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
