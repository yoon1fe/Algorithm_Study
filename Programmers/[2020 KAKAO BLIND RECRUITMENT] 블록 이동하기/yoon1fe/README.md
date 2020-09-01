## [2020 KAKAO BLIND RECRUITMENT] 벽돌 이동하기 - Java

###    :airplane: 분류

> 시뮬레이션
>
> BFS

###  :airplane: 코드

```java
int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};

    static class Robot{
        int y, x, d;                                // d 방향으로 한 칸 더 있음을 의미. 0: 가로, 1: 세로
        Robot(int y, int x, int d){
            this.y = y; this.x = x; this.d = d;
        }
    }

    public int solution(int[][] board) {
        int N = board.length;
        int answer = 0;
        Queue<Robot> q = new LinkedList<>();
        q.offer(new Robot(1, 1, 0));
        int[][][] v = new int[N+2][N+2][2];            // 가로일때, 세로일때
        v[1][1][0] = 1;
        int[][] paddingBoard = new int[N+2][N+2];    // isIn 필요없어

        for(int i = 0; i < N + 2; i++) {
            for(int j = 0; j < N + 2; j++) {
                if(1 <= i && i < 1 + N && 1 <=j && j < 1 + N) {
                    paddingBoard[i][j] = board[i-1][j-1];
                }
                else paddingBoard[i][j] = 1;
            }
        }

        while(!q.isEmpty()) {
            Robot cur = q.poll();

            if(cur.d == 0 && cur.y == N && cur.x == N-1) {
                answer = v[cur.y][cur.x][cur.d] - 1; break;
            }else if(cur.d == 1 && cur.y == N-1 && cur.x == N) {
                answer = v[cur.y][cur.x][cur.d] - 1; break;
            }

            for(int i = 0; i < 8; i++) {                    // 4방향 이동 + 4방향 회전
                Robot next = null;
                if(i>3) {                                    // 회전하자
                    if(cur.d == 0) {                        // 누워 있는 경우
                           if(i == 4) {
                            next = new Robot(cur.y, cur.x, 1);
                            if(paddingBoard[next.y+1][next.x+1] == 1 || paddingBoard[next.y+1][next.x] == 1) continue;
                        }else if(i == 5) {
                            next = new Robot(cur.y-1, cur.x, 1);
                            if(paddingBoard[next.y][next.x]== 1 || paddingBoard[next.y][next.x+1] == 1) continue;
                        }else if(i == 6) {
                               next = new Robot(cur.y, cur.x+1, 1);
                               if(paddingBoard[next.y+1][next.x-1] == 1 || paddingBoard[next.y+1][next.x] == 1) continue;
                           }else {
                               next = new Robot(cur.y-1, cur.x+1, 1);
                               if(paddingBoard[next.y][next.x-1] == 1 || paddingBoard[next.y][next.x] == 1) continue;
                           }
                    }else {                                    // 서있는놈
                        if(i == 4) {
                            next = new Robot(cur.y, cur.x-1, 0);
                            if(paddingBoard[next.y+1][next.x] == 1 || paddingBoard[next.y][next.x] == 1) continue;
                           }else if(i == 5) {
                               next = new Robot(cur.y, cur.x, 0);
                               if(paddingBoard[next.y+1][next.x+1]== 1 || paddingBoard[next.y][next.x + 1] == 1) continue;
                           }else if(i == 6) {
                               next = new Robot(cur.y+1, cur.x-1, 0);
                               if(paddingBoard[next.y-1][next.x] == 1 || paddingBoard[next.y][next.x] == 1) continue;
                           }else {
                               next = new Robot(cur.y+1, cur.x, 0);                                
                               if(paddingBoard[next.y-1][next.x+1] == 1 || paddingBoard[next.y][next.x+1] == 1) continue;
                           }
                    }
                }else {
                    next = new Robot(cur.y + dy[i], cur.x + dx[i], cur.d);
                }

                if(v[next.y][next.x][next.d] != 0 || paddingBoard[next.y][next.x] == 1) continue;
                if(next.d == 0 && paddingBoard[next.y][next.x+1] == 1) continue;
                if(next.d == 1 && paddingBoard[next.y + 1][next.x] == 1) continue;


                q.offer(next);
                v[next.y][next.x][next.d] = v[cur.y][cur.x][cur.d] + 1;
            }
        }
//        for(int i = 0; i <= N+1; i++){
//            for(int j = 0; j <= N+1; j++) {
//                for(int k = 0; k < 2; k++) {
//                    System.out.print(v[i][j][k] + "\t");
//                }
//                System.out.print("   ");
//            }
//            System.out.println();
//        }
        return answer;
    }
```



### :airplane:풀이 방법

암튼 이 문제는.... 죽어라 풀었던 BFS 종류입니다...

하지만 갓카오 블채 마지막 문제이니만큼 예삿놈이 아니었습니다....



지ㄴ이이인짜 지저분합니다.

처음에 드론의 좌표를 드론이 차지하는 두개의 좌표로 표현했습니다. 근데 이렇게 하니 방문 여부 체크하는게 너무너무 힘들었습니다..

 

그래서 갓카오의 기술 블로그를 참고했습니다 ..^^;;;

역시 킹갓카오는 정답을 알려주십니다..

 

드론을 차지하는 한 부분의 좌표와 어디로 튀어나와있는지로 표현을 했습니다.

d가 0이면 누워있는놈, 1이면 서있는 놈입니다.

예를 들어 0, 0, 1 이라 함은, (0, 0) 좌표와 (0, 1) 좌표를 차지하고 있는 겁니다.

이런 아이디어를 갖고 접근했습니다.

 

BFS를 돌리는건 까다롭진 않습니다. 한 위치에서 총 여덟개의 액션을 취할 수 있습니다. 4방향으로 움직이는 것과 4방향으로 회전하는 거죠.

 

이동하는 부분은 뭐 누워서 떡먹깁니다. 

회전하는 부분에서 또 인덱스를 갖고 노는거라 시간을 좀 잡아먹었습니다...

연습장 두장썼습니다.

 

마지막으로 정답을 구하는건 d가 0이냐 1이냐에 따라 마지막 좌표값이 다르기 때문에 구분해서 봐줬습니당 ㅎㅎ

 

테스트 케이스에서의 visited 배열 적어놨는데 혹시나 해서 첨부합니다 ^_^



![img](https://blog.kakaocdn.net/dn/cVFVig/btqHCVdkLlf/Gm7E7lUrM1Yp4lnZvsS350/img.png)

###  :airplane: 후기

무지는 왜래 커다란 로봇ㅅ을 준비했을까 1x1 짜리로 만들었으면 이런 고생은 하지 않았을텐데