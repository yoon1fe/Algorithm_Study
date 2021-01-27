# [13460] 구슬 탈출 2 - JAVA

## 분류
> 구현
>
> 시뮬레이션
>
> BFS

## 코드
```java
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
```

## 문제 풀이
BFS를 사용합니다!

위로 기울어질 때, 아래로 기울어 질때, 왼쪽으로 기울어질 때, 오른쪽으로 기울어질 때 모두 검사!

기운다는 것은 해당 방향으로 구슬이 이동

구슬이 #(벽)을 만나면 이동을 멈춤.
   - 가장자리는 벽으로 되어 있기 때문에 범위를 벗어날 걱정 없음.

구슬이 O(구멍)를 만나면 이동을 멈춤.

파란 구슬이 구멍에 빠진 경우 해당 방향으로 기울인 것을 더 이상 진행하지 않음.

구슬이 겹쳐져 있을 수 있음.
   - 왼쪽이나 오른쪽으로 기울였을 때 col 값에 따라 구슬 위치 변경
   - 위쪽이나 아래쪽으로 기울였을 때 row 값에 따라 구슬 위치 변경

이미 방문한 상태인지 검사
   - 4차원 배열을 사용해서 빨간구슬 위치, 파란구슬 위치를 하나의 상태값으로 표현해서 중복 방지

만약 빨간구슬이 구멍에 빠졌고, 현재 움직인 횟수가 가장 최소인지 검사하고 정답 갱신

## 후기
두 번째로 풀어보는데

음 Element 저장할 때 빨간구슬하고 파란구슬을 바꿔서 넣는 바람에 바로 틀림 ㅠ

고치니깐 맞음 ㅠ