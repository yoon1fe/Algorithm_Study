# [2020 KAKAO BLIND RECRUITMENT] 기둥과 보 설치

## 분류
> 구현

## 코드
```java
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

                /*
                // 없어도 됩니다.
                if(delElem == null)
                    continue;
                */

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
```

## 문제 풀이
오해하거나 잘못 이해하면 풀기 어려운 문제였습니다.

### 주의할 점 : 구조물이 겹칠 수 없다.
   - 하지만, 출력 부분 설명에서 x, y 좌표가 같으면 기둥이 보보다 먼저오도록 한다고 했습니다.
   - 즉, 구조물은 보와 기둥이 겹치는 것은 상관없지만, 기둥과 기둥이 겹치거나, 보와 보가 겹치는 경우가 입력으로 주어지지 않는다는 것을 의미합니다.
   - 그래서 기둥에 대한 이차원 배열, 보에 대한 이차원 배열을 사용하였습니다.
   - 이것 때문에 한참을 고생했습니다..
   - 삭제할 때도 x, y, type까지 같은 것을 삭제해야 합니다.

map[0][row][col]는 기둥이 설치되는 이차원 배열이고, map[1][row][col]는 보가 설치되는 이차원 배열입니다.

### 기둥을 설치할 경우
1. 바닥에 기둥이 설치되는 경우
    - row == 0 : 바닥에 설치되는 경우
1. 기둥을 설치할 때는 기둥 위에 설치되는 경우
    - map[0][row-1][col] == 1 : 기둥 위에 설치될 수 있는 경우
1. 기둥이 보의 한 쪽 끝에 있는 경우도 있습니다.
    - map[1][row-1][col] == 1 이거나 map[1][row][col] == 1 이면 기둥이 보의 한쪽 끝에 올라가므로 기둥을 설치할 수 있습니다.
    - map[1][row-1][col] == 1 : 현재 위치에서 왼쪽에 보가 설치된 경우 보는 오른쪽으로 설치되므로 기둥이 설치되는 곳은 보의 오른쪽 끝이므로 설치 가능
    - map[1][row][col] == 1 : 현재 위치에 보가 설치된 경우는 보의 왼쪽 끝 위에 올라가므로 설치 가능
- 총 3 가지의 경우 하나만 만족하면 기둥을 설치할 수 있습니다.

### 보를 설치할 경우
1. 보의 한 쪽 끝이 기둥 위에 올라가는 경우
   - map[0][row-1][col] == 1 : 보의 왼쪽이 기둥 위에 올라가면 설치 가능
   - map[0][row-1][col+1] == 1 : 보의 오른쪽이 기둥 위에 올라가면 설치 가능
1. 보의 양 쪽이 다른 보와 연결되는 경우
   - map[1][row][col-1] == 1 && map[1][row][col+1] == 1 : 현재 위치(row, col) 양쪽에 보가 있는 경우 설치 가능
- 총 2가지 경우에 따라 설치가 가능합니다.

그러면 설치가 불가능할 때는 해당 구조물을 무시하고, 설치가 가능한 경우만 보와 기둥 배열에 표시하면 됩니다.

삭제할 때는 해당 구조물을 삭제해보고 정상적으로 설치된 구조물들 중에 설치가 될 수 없는 구조물이 나오는지 검사해봅니다.
   - 만약 X라는 구조물을 삭제했을 때 아무 이상 없이 구조물들의 상태가 유지되면 X를 삭제하면 됩니다.
   - 만약 X라는 구조물을 삭제했을 때 어떤 구조물도 설치할 수 없는 영향을 미칠 경우 X를 삭제하면 안 됩니다.

정렬은 문제에 따라 정렬하여 답을 리턴하면 됩니다.

## 후기
구조물이 겹치지 않는다.. x, y가 같으면 기둥이 먼저.. 

이번 문제는 뭔가 좀 꼬았다?는 느낌을 받았습니다.