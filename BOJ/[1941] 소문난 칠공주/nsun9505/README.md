# [1941] 소문난 칠공주 - Java

## 분류
> 백트랙킹

## 코드
```java
package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1941 {
    static class Element{
        int row;
        int col;
        char val;

        Element(int r, int c, char v){
            this.row = r;
            this.col = c;
            this.val = v;
        }

        Element(int r, int c){
            this.row = r;
            this.col = c;
            this.val = ' ';
        }
    }

    public static final int N = 5;
    public static int ans = 0;
    public static char[][] board;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static ArrayList<Element> elements = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        init();
        solution(new ArrayList<>(), 0, 0, 0);
        System.out.println(ans);
    }

    public static void solution(ArrayList<Element> list, int cur, int cntS, int cntY){
        if(list.size() == 7){
            if(cntS < 4)
                return;

            if(isAnswer(list.get(0).row, list.get(0).col))
                ans++;
            return;
        }

        if(cur >= elements.size())
            return;

        Element elem = elements.get(cur);
        board[elem.row][elem.col] = '1';
        list.add(elem);

        if(elem.val == 'S') solution(list, cur+1, cntS + 1, cntY);
        else solution(list, cur + 1, cntS, cntY + 1);

        board[elem.row][elem.col] = '0';
        list.remove(list.size()-1);

        solution(list, cur+1, cntS, cntY);
    }

    public static boolean isAnswer(int row, int col){
        boolean visited[][] = new boolean[N][N];
        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(row, col));
        visited[row][col] = true;
        int ret = 1;

        while(!queue.isEmpty()){
            Element elem = queue.poll();

            for(int dir = 0; dir < 4; dir++){
                int nx = elem.row + dx[dir];
                int ny = elem.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                if(visited[nx][ny] || board[nx][ny] == '0')
                    continue;

                visited[nx][ny] = true;
                queue.offer(new Element(nx, ny));
                ret++;
            }
        }

        if(ret != 7)
            return false;
        return true;
    }

    public static void init() throws IOException {
        board = new char[N][N];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<str.length(); j++){
                board[i][j] = '0';
                elements.add(new Element(i, j,str.charAt(j)));
            }
        }
    }
}
```

## 문제 풀이
조합 + BFS로 문제를 해결!

5x5에서 조합으로 위치를 7개 뽑는다.
- DFS를 사용해서 해당 위치들을 뽑으면서 board에 1을 체크한다.
- 또한, list에 뽑은 애들을 담는다.

7명의 위치를 담았을 때 S의 개수가 4보다 작다면 계산할 필요가 없으므로 바로 리턴한다.

만약 S의 개수가 4와 크거나 같다면 뽑은 위치가 연결되어 있는지 BFS를 돌려본다.
- BFS를 돌리면서 방문한 노드를 카운트해서 7이면 모두 연결된 것이므로 리턴 true, 아니면 return false

BFS를 돌린 결과가 true이면, 답으로 출력할 ans에 1을 더한다.

## 코드
처음에는 DFS로 쭉 풀다가 아니라는 것을 알고! 조합으로!

테트로미노랑 비슷한 문제같기도 하고! 거기서 3개까지 DFS로 들어가서 3개의 위치에서 +1한 위치를 카운트한 것으로 알고 있는데 여기서는 5개까지 들어가서 +2를 해보면 될랑가..?