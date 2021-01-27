# [Summer_Winter Coding(~2018)] 방문길이 - JAVA

## 분류
> 구현

## 코드
```java
class Solution {

    public int solution(String dirs) {
        int answer = 0;
        int N = 10;
        boolean[][][][] map = new boolean[N+1][N+1][N+1][N+1];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int row = 5;
        int col = 5;
        for(int i=0; i<dirs.length(); i++) {
            int dir = getDir(dirs.charAt(i));
            int nx = row + dx[dir];
            int ny = col + dy[dir];

            if(nx < 0 || ny < 0 || nx > N || ny > N)
                continue;

            if(map[row][col][nx][ny] || map[nx][ny][row][col]) {
                row = nx;
                col = ny;
                continue;
            }

            map[row][col][nx][ny] = true;
            map[nx][ny][row][col] = true;
            row = nx;
            col = ny;
            answer++;
        }

        return answer;
    }

    public static int getDir(char dir){
        if(dir == 'U')
            return 0;
        else if(dir == 'R')
            return 1;
        else if(dir == 'D')
            return 2;
        return 3;
    }
}
```

## 문제 풀이
위치가 마이너스인 경우에는 배열로는 방문 체크가 되지 않기 떄문에 

10 x 10으로 바꿔서 생각했습니다.

시작은 (5, 5)에서 시작하고 주어진 방문길이에 따라 움직이면 됩니다.

(row, col) -> (nx, ny)로 이동할 때 4차원 배열을 사용해서 (현위치) -> (다음위치)로 갔다는 것을 표시했습니다.

그리고 (nx, ny) -> (row, col)과 같이 (다음위치) -> (현위치)로 오는 것도 동일하므로 똑같이 방문 체크를 해줘서 

이미 방문한 경로를 방문하지 않았다고 체크하는 경우를 방지합니다.

## 후기
음.. 난이도조절 실패 ㅠ 

풀기 전에 그림있으면 뭔가 어려워 보여서 골랐는데..ㅠ