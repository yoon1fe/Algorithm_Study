# [12100] 2048(Easy) - JAVA

## 분류
> 구현
>
> 완전탐색

## 코드
```java
package Backjoon.BOJ12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(arr, 0));

        int ans = 0;
        while(!queue.isEmpty()){
            Element elem = queue.poll();

            if(elem.cnt == 5){
                int max = 0;
                for(int i=0; i<N; i++)
                    for(int j=0; j<N; j++)
                        max = Math.max(max, elem.arr[i][j]);
                ans = Math.max(ans, max);
                continue;
            }

            // UP
            Element up = new Element(elem.arr, elem.cnt + 1);
            for(int row = 0; row < N; row++){
                for(int col = 0; col < N; col++){
                    move(up, row, col, 0);
                }
            }
            queue.offer(up);

            // RIGHT
            Element right = new Element(elem.arr, elem.cnt+1);
            for(int col = N-1; col >= 0; col--){
                for(int row = 0; row < N; row++){
                    move(right, row, col, 1);
                }
            }
            queue.offer(right);

            // DOWN
            Element down = new Element(elem.arr, elem.cnt+1);
            for(int row = N-1; row >= 0; row--){
                for(int col = 0; col < N; col++){
                    move(down, row, col, 2);
                }
            }
            queue.offer(down);

            // LEFT
            Element left = new Element(elem.arr, elem.cnt + 1);
            for(int col = 0; col < N; col++){
                for(int row = 0; row < N; row++){
                    move(left, row, col, 3);
                }
            }
            queue.offer(left);
        }
        System.out.println(ans);
    }

    public static void move(Element elem, int row, int col, int dir){
        int val = elem.arr[row][col];
        elem.arr[row][col] = 0;
        while(true){
            int nx = row + dx[dir];
            int ny = col + dy[dir];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
                elem.arr[row][col] = val;
                break;
            }

            if(elem.arr[nx][ny] == 0){
                row = nx;
                col = ny;
            } else if(elem.arr[nx][ny] == val){
                if(!elem.isMerge[nx][ny]) {
                    elem.isMerge[nx][ny] = true;
                    elem.arr[nx][ny] *= 2;
                } else
                    elem.arr[row][col] = val;
                break;
            } else {
                elem.arr[row][col] = val;
                break;
            }
        }
    }

    static class Element{
        int[][] arr;
        boolean[][] isMerge;
        int cnt;

        Element(int[][] arr, int cnt){
            this.arr = new int[arr.length][arr.length];
            this.isMerge = new boolean[arr.length][arr.length];
            for(int i=0; i<arr.length; i++)
                for(int j=0; j<arr.length; j++)
                    this.arr[i][j] = arr[i][j];
            this.cnt = cnt;
        }
    }
}
```

## 문제 풀이
큐를 사용해서 움직인 상태를 큐에 넣어줍니다.

상하좌우로 기울인 모든 경우에 대해서 배열을 새로 생성하고, 합쳐진 경우를 표시하는 배열, 이동 횟수를 하나의 상태로 보고 

Element로 만들어서 큐로 돌리면 됩니다.

주의할 점은 숫자가 이동해서 같은 크기를 만난 경우에 해당 숫자가 이미 합쳐져서 커진 숫자인지를 검사해야 합니다.
   - 그래서 합쳐졌는지 아닌지를 검사하기 위한 isMerge를 사용했습니다.

숫자가 이동하는 것만 잘 구현하고, 움직였을 때 상태를 만들어주기 위해 각각에 맞게 배열이 있어야 된다는 점만 캐치하면

수월하게 풀 수 있을 것 같습니다.

## 후기
풀었던 기억이 있어서 그런가 조건들이 머리에 생각이 나서 금방 풀 수 있었습니다.

저번에는 재귀호출로 이번에는 큐를 사용했습니다.