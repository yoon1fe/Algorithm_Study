import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solution {
    static int[][][] map;
    static int N;
    public int[][] solution(int n, int[][] build_frame) {
        ArrayList<Element> list = new ArrayList<>();
        /*
         * 0 : 기둥
         * 1 : 보
         */
        map = new int[2][n+1][n+1];
        N = n+1;

        for(int i=0; i<build_frame.length; i++){
            int row = build_frame[i][1];
            int col = build_frame[i][0];
            int type = build_frame[i][2];
            int cmd = build_frame[i][3];

            // delete
            if(cmd == 0){
                Element delElem = null;
                for (int idx=0; idx<list.size(); idx++){
                    if(list.get(idx).row == row && list.get(idx).col == col && list.get(idx).type == type){
                        delElem = list.get(idx);
                        list.remove(idx);
                        break;
                    }
                }

                if(delElem == null)
                    continue;

                map[type][row][col] = 0;

                if(!checkDelete(list)) {
                    map[type][row][col] = 1;
                    list.add(delElem);
                }
            }
            // add
            else {
                boolean ret = false;
                if(type == 0 && checkInstallGidung(row, col))
                    ret = true;
                else if(type == 1 && checkInstallBo(row, col))
                    ret = true;

                if(ret){
                    list.add(new Element(row, col, type));
                    map[type][row][col] = 1;
                }
            }
        }

        Collections.sort(list, new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                if(o1.col > o2.col)
                    return 1;
                else if(o1.col == o2.col){
                    if(o1.row > o2.row)
                        return 1;
                    else if(o1.row == o2.row)
                        return o1.type - o2.type;
                }
                return -1;
            }
        });
        int[][] answer = new int[list.size()][3];
        for(int i=0; i<list.size(); i++){
            Element elem = list.get(i);
            answer[i][0] = elem.col;
            answer[i][1] = elem.row;
            answer[i][2] = elem.type;
        }

        return answer;
    }

    public boolean checkDelete(ArrayList<Element> list){
        for(Element elem : list){
            if(elem.type == 0 && !checkInstallGidung(elem.row, elem.col))
                return false;
            else if(elem.type == 1 && !checkInstallBo(elem.row, elem.col))
                return false;
        }
        return true;
    }

    public boolean checkInstallBo(int row, int col){
        if(map[0][row-1][col] == 1)
            return true;
        else if(col + 1 < N && map[0][row-1][col+1] == 1)
            return true;
        else if(col - 1 >= 0 && col + 1 < N && map[1][row][col-1] == 1 && map[1][row][col+1] == 1)
            return true;
        return false;
    }

    public boolean checkInstallGidung(int row, int col){
        if(row == 0)
            return true;
        else if(map[0][row-1][col] == 1)
            return true;
        else if(col-1 >= 0 && map[1][row][col-1] == 1)
            return true;
        else if(map[1][row][col] == 1)
            return true;
        return false;
    }

    static class Element{
        int row;
        int col;
        int type;

        public Element(int row, int col, int type) {
            this.row = row;
            this.col = col;
            this.type = type;
        }
    }
}