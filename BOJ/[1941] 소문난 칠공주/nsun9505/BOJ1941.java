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
