## [2019 KAKAO BLIND RECRUITMENT] 블록 게임 - Java

###    :bookmark_tabs: 분류

> 구현



###  :bookmark_tabs: 코드

```java
import java.util.*;

class Solution {
    static class Dir{
        int y, x;
        Dir(int y, int x){
            this.y = y; this.x = x;
        }
    }

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int N;

    public static boolean isIn(int y, int x) {
        if(0 <= y && y < N && 0 <= x && x < N) return true;
        else return false;
    }

    public int solution(int[][] board) {
        int answer = 0;
        Map<Integer, Dir> blocks = new HashMap<>();        // 블럭 번호 : 블럭 시작 인덱스
        N = board.length;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(board[j][i] == 0) continue;
                if(blocks.containsKey(board[j][i])) continue;

                blocks.put(board[j][i], new Dir(j, i));
            }
        }

        for(Integer k : blocks.keySet()) {
            List<Integer> available = new ArrayList<>();        // 없앨 수 있는 블럭 번호
        }

        while(true) {
            List<Integer> available = new ArrayList<>();        // 없앨 수 있는 블럭 번호
            for(Integer k : blocks.keySet()) {
                if(checkRect(blocks.get(k), board)) {
                    available.add(k);
                }
            }

            for(int i : available) {
                removeBlocks(blocks.get(i), board);
                blocks.remove(i);
                answer++;
            }

            if(available.size() == 0) break;
        }

        return answer;
    }

    public static boolean checkRect(Dir s, int[][] board) {
        int boardNo = board[s.y][s.x];

        if((isIn(s.y+1, s.x) && board[s.y+1][s.x] == boardNo) && 
            (isIn(s.y+1, s.x+1) &&board[s.y+1][s.x+1] == boardNo) && 
            (isIn(s.y+1, s.x+2) &&board[s.y+1][s.x+2] == boardNo)) {
            if(board[s.y][s.x+1] == 0 && board[s.y][s.x+2] == 0) {
                for(int i = 0; i < s.y; i++) {
                    if(board[i][s.x+1] != 0) return false;
                    if(board[i][s.x+2] != 0) return false;
                }
                return true;
            }
        }else if((isIn(s.y, s.x+1) && board[s.y][s.x+1] == boardNo) && 
                (isIn(s.y-1, s.x+1) && board[s.y-1][s.x+1] == boardNo) && 
                (isIn(s.y-2, s.x+1) && board[s.y-2][s.x+1] == boardNo)) {
            if(board[s.y-1][s.x]== 0 && board[s.y-2][s.x] == 0) {
                for(int i = 0; i < s.y-1; i++) {
                    if(board[i][s.x] != 0) return false;
                }
                for(int i = 0; i < s.y-2; i++) {
                    if(board[i][s.x] != 0) return false;
                }
                return true;
            }
        }else if((isIn(s.y+1, s.x) && board[s.y+1][s.x]== boardNo) && 
                (isIn(s.y+2, s.x) && board[s.y+2][s.x] == boardNo) && 
                (isIn(s.y+2, s.x+1) && board[s.y+2][s.x+1] == boardNo)) {
            if(board[s.y][s.x+1] == 0 && board[s.y+1][s.x+1] == 0) {
                for(int i = 0; i < s.y; i++) {
                    if(board[i][s.x+1] != 0) return false;
                }
                for(int i = 0; i < s.y+1; i++) {
                    if(board[i][s.x+1] != 0) return false;
                }
                return true;
            }
        }else if((isIn(s.y-1, s.x+1) &&board[s.y-1][s.x+1] == boardNo) && 
                (isIn(s.y, s.x+1) && board[s.y][s.x+1]== boardNo) && 
                (isIn(s.y, s.x+2) && board[s.y][s.x+2] == boardNo)) {
            if(board[s.y-1][s.x]== 0 && board[s.y-1][s.x+2] == 0) {
                for(int i = 0; i < s.y-1; i++) {
                    if(board[i][s.x] != 0) return false;
                    if(board[i][s.x+2] != 0) return false;
                }
                return true;
            }
        }else if((isIn(s.y, s.x+1) && board[s.y][s.x+1] == boardNo) && 
                (isIn(s.y, s.x+2) && board[s.y][s.x+2] == boardNo) && 
                (isIn(s.y-1, s.x+2) && board[s.y-1][s.x+2] == boardNo)) {
            if(board[s.y-1][s.x] == 0 && board[s.y-1][s.x+1] == 0) {
                for(int i = 0; i < s.y-1; i++) {
                    if(board[i][s.x] != 0) return false;
                    if(board[i][s.x+1] != 0) return false;
                }
                return true;
            }
        }

        return false;
    }

    public void removeBlocks(Dir start, int[][] board) {
        Queue<Dir> q = new LinkedList<>();
        int blockNo = board[start.y][start.x];
        q.offer(start);

        while(!q.isEmpty()) {
            Dir cur = q.poll();

            for(int i = 0; i < 4; i++) {
                Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
                if(!isIn(next.y, next.x) || board[next.y][next.x] != blockNo ) continue;
                board[next.y][next.x] = 0;
                q.offer(next);
            }
        }
    }
}
```



### :bookmark_tabs: 풀이 방법

대망의 2019 블채 마지막 문제!!

마지막 문제라서 살쫄이었는데 생각보다 로직이 퍼펙트하게 빨리 생각이 나서 30분컷했습니다!!

다만 블록 체크하는 부분은 하드 코딩한 점 ^^;



전체적인 로직은 이렇습니다.

1. {블록의 번호 : 블록의 시작 인덱스} 를 저장합니다. -> 이 때 일정할 규칙을 갖게 하기 위해 왼쪽 위에서부터 보면서 가장 먼저 만나는 인덱스를 담았습니다.
2. 블록의 개수만큼 반복하면서 그 블록을 없앨 수 있는지 체크합니다. -> 검은 블록은 위에서 떨어지기 때문에 직사각형이 만들어지는 경우는 끽해봤자 다섯가지밖에 안됩니다. 다만 인덱스로 노가다로 봐주기 때문에 밖으로 튀어나가는지 하나하나 확인해줘야 합니다 ^^;; 이 다섯가지 중 하나에 해당된다면, 검은 블록이 위에서 떨어져서 빈 곳에 도착할 수 있는지 체크합니다. y좌표(세로) 0부터 빈칸까지 반복문을 돌려서 확인할 수 있습니다. 제거할 수 있으면 available 리스트에 넣어줍니다.
3. available 리스트를 돌면서 제거해줍니다. 네 칸만 지우면 됩니다. BFS로 호다닥 지워주면 됩니다. DFS 써도 되겠져.
4. 1번~3번을 available 리스트에 아무 것도 들어가지 않을 때까지 돌면 끝!!



###  :bookmark_tabs: 후기 

다 풀고 보니 제거할 수 있는 블록의 모양의 규칙이 보입니다.. ^^;;;;;;

그래도 자알 풀었다~~~~!!!