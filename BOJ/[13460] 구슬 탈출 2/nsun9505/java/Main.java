package Backjoon.BOJ13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        char[][] map = new char[N][M];
        boolean[][][][] isVisited = new boolean[N][M][N][M];

        Element start = new Element();
        start.dist = 0;
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<line.length(); j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'B'){
                    start.blueRow = i;
                    start.blueCol = j;
                } else if(map[i][j] == 'R'){
                    start.redRow = i;
                    start.redCol = j;
                }
            }
        }

        Queue<Element> queue = new LinkedList<>();
        queue.offer(start);
        isVisited[start.redRow][start.redCol][start.blueRow][start.blueCol] = true;

        int ans = 11;
        while(!queue.isEmpty()){
            Element elem = queue.poll();

            for(int dir = 0; dir < 4; dir++){
                int redRow = elem.redRow;
                int redCol = elem.redCol;
                while(true){
                    int nx = redRow + dx[dir];
                    int ny = redCol + dy[dir];

                    if(map[nx][ny] == '#')
                        break;

                    if(map[nx][ny] == 'O'){
                        redRow = nx;
                        redCol = ny;
                        break;
                    }

                    redRow = nx;
                    redCol = ny;
                }

                int blueRow = elem.blueRow;
                int blueCol = elem.blueCol;
                while(true){
                    int nx = blueRow + dx[dir];
                    int ny = blueCol + dy[dir];

                    if(map[nx][ny] == '#')
                        break;

                    if(map[nx][ny] == 'O'){
                        blueRow = nx;
                        blueCol = ny;
                        break;
                    }

                    blueRow = nx;
                    blueCol = ny;
                }

                if(map[blueRow][blueCol] == 'O')
                    continue;

                if(redRow == blueRow && redCol == blueCol){
                    // UP
                    if(dir == 0){
                        if(elem.redRow < elem.blueRow) blueRow += 1;
                        else redRow += 1;
                    }
                    else if(dir == 1){
                        if(elem.redCol < elem.blueCol)  redCol -= 1;
                        else blueCol -= 1;
                    }
                    else if(dir == 2){
                        if(elem.redRow < elem.blueRow) redRow -= 1;
                        else blueRow -= 1;
                    }
                    else if(dir == 3){
                        if(elem.redCol < elem.blueCol) blueCol += 1;
                        else redCol += 1;
                    }
                }

                if(isVisited[redRow][redCol][blueRow][blueCol])
                    continue;

                isVisited[redRow][redCol][blueRow][blueCol] = true;
                queue.offer(new Element(redRow, redCol, blueRow, blueCol, elem.dist + 1));
                if(map[redRow][redCol] == 'O' && elem.dist + 1 < ans)
                    ans = elem.dist + 1;
            }
        }

        if(ans == 11)
            System.out.println(-1);
        else
            System.out.println(ans);
    }

    static class Element{
        int blueRow;
        int blueCol;
        int redRow;
        int redCol;
        int dist;

        public Element() { }

        public Element(int redRow, int redCol, int blueRow, int blueCol, int dist) {
            this.blueRow = blueRow;
            this.blueCol = blueCol;
            this.redRow = redRow;
            this.redCol = redCol;
            this.dist = dist;
        }
    }
}
