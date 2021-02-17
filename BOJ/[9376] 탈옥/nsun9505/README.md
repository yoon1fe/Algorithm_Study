# [9376] 탈옥 - JAVA

## 분류
> BFS

## 코드
```java
package Backjoon.BOJ9376;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int[][][] dist;
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N+2][M+2];
            for(int i=0; i<=N+1; i++)
                for(int j=0;j<=M+1;j++)
                    map[i][j] = '.';

            dist = new int[3][N+2][M+2];
            for(int idx = 0; idx<3;idx++){
                for(int i=0; i<=N+1; i++){
                    for(int j=0; j<=M+1; j++)
                        dist[idx][i][j] = Integer.MAX_VALUE;
                }
            }

            ArrayList<Element> list = new ArrayList<>();
            list.add(new Element(0, 0));
            dist[0][0][0] = 0;
            for(int i=1; i<=N; i++){
                String input = br.readLine();
                for(int j=1; j<=input.length(); j++) {
                    map[i][j] = input.charAt(j - 1);
                    if(map[i][j] == '$') {
                        list.add(new Element(i, j));
                    }
                }
            }

            for(int i=0; i<list.size(); i++) {
                dist[i][list.get(i).row][list.get(i).col] = 0;
                BFS(list.get(i).row, list.get(i).col, i);
            }

            for(int idx=1; idx<=2; idx++){
                for(int i=1; i<=N; i++)
                    for(int j=1; j<=M; j++)
                        dist[0][i][j] += dist[idx][i][j];
            }

            int min = Integer.MAX_VALUE;
            for(int i=1;i<=N; i++){
                for(int j=1; j<=M; j++){
                    if(map[i][j] == '*')
                        continue;

                    if(map[i][j] == '#')
                        dist[0][i][j] -= 2;
                    min = Math.min(min, dist[0][i][j]);
                }
            }

            sb.append(min + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void BFS(int row, int col, int idx){
        boolean[][] isVisited = new boolean[N+2][M+2];
        LinkedList<Element> queue = new LinkedList<>();
        queue.offer(new Element(row, col));
        isVisited[row][col] = true;

        while(!queue.isEmpty()){
            Element elem = queue.pollFirst();

            for(int dir = 0; dir < 4; dir++){
                int nx = elem.row + dx[dir];
                int ny = elem.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N+2 || ny >= M+2)
                    continue;

                if(isVisited[nx][ny] || map[nx][ny] == '*')
                    continue;

                isVisited[nx][ny] = true;
                dist[idx][nx][ny] = dist[idx][elem.row][elem.col];
                if(map[nx][ny] == '.' || map[nx][ny] == '$') {
                    queue.offerFirst(new Element(nx, ny));
                }else{
                    dist[idx][nx][ny] += 1;
                    queue.offerLast(new Element(nx, ny));
                }
            }
        }

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
도저히 풀릴 각이 안 보여서 0-1 BFS에 대해서 찾아보고 그래도 안 풀려서 아이디어를 참고하고 BFS를 사용해서 풀었습니다.

총 3가지의 경우를 생각할 수 있습니다.
   1. 죄수 1이 죄수 2를 데리고 나가는 경우
   2. 죄수 2가 죄수 1을 데리고 나가는 경우
   3. 상근이가 직접 들어가서 죄수 1과 죄수 2를 데리고 나오는 경우

즉, 죄수1, 죄수2, 상근이에 대해서 BFS를 돌립니다.
   - 상근이의 경우 감옥 밖 어디든 접근할 수 있으므로 행렬에 N+2 x M+2로 선언해서 0행, N+1행, 0열, M+1열에 상근이가 돌아다닐 수 있도록 했습니다.
   - 그러면 상근이는 어디든 상관 없지만 (0,0)에서 출발하여 문을 열고 들어갈 수 있으면 문을 열고 들어가면 됩니다.

그리고 문을 최소로 연 경우를 알아내기 때문에 문을 최소로 열기 위해서 큐에 삽입할 떄 주의할 점이 있습니다.
   - 만약에 다음 칸이 문이라면 해당 칸으로 이동하면서 가중치가 1증가하므로 큐의 뒤(rear)에 삽입합니다.
   - 만약에 다음 칸이 빈칸(움직일 수 있는 칸)이라면 해당 칸으로 이동하면서 가중치가 증가하지 않으므로 큐의 앞(front)에 삽입합니다.
   - 왜냐하면 문을 최소로 열어서 도착하기 위해서 문을 열어서 이동한 경우에는 큐의 뒤쪽에 넣어서 우선순위를 낮추고
   - 문이 아닌 빈칸으로 이동시에는 큐의 앞쪽에 넣어서 우선순위를 높여서 문을 여는 경우를 최소로 하기 위함입니다.

이렇게 BFS를 다돌린 후에는 dist 배열에는 상근이, 죄수1, 죄수2의 문을 연 최솟값들이 담겨져 있습니다.
   - 이 세 개의 이차원 배열 각 원소를 모두 더합니다!
   - 대신, 문(#)에서 더할 때는 해당 문을 3명이 모두 열었으므로 한 번만 열도록 하기 위해서 -2를 해주어야 합니다.
   - 즉, 3명이 문에서 만난 경우입니다.

다 더한 값에서 최솟값을 출력하면 답이 됩니다.

## 후기
0-1 BFS에 대해서 공부가 되었습니다.

플레는 어려와유.. 확실히

문제에서 상근이가 개입하는지 몰랐는데, 제3자가 개입하는 것까지 생각해서 문제를 풀어보는데 도움이 되었습니다.