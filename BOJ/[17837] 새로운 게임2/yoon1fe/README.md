# [17837] 새로운 게임 2 - Java

###  :video_game: 분류

> 시뮬레이션
>

​

### :video_game: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    static class Dir {
        int y, x;

        Dir(int y, int x) {
            this.y = y; this.x = x;
        }
    }

    static int N, K;
    static Map<Integer, Integer> pieceDir = new HashMap<>();    // 체스 말의 이동 방향
    static List<Dir> pieceList = new ArrayList<>();                // 체스 말 list
    static int[][] board;                                        // 체스 판
    static StringBuilder[][] map;                                // 체스 말들 위치
    static int[] dy = { 0, 0, 0, -1, 1 };
    static int[] dx = { 0, 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        map = new StringBuilder[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new StringBuilder();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            map[y][x].append(i);
            pieceDir.put(i, d);
            pieceList.add(new Dir(y, x));
        }

        System.out.println(move());
    }

    public static boolean isIn(Dir c) {
        if (0 <= c.y && c.y < N && 0 <= c.x && c.x < N) return true;
        else return false;
    }

    public static int move() {
        int ans = 1;

        outer:
        for (ans = 1; ans <= 1000; ans++) {
            for (int i = 0; i < pieceList.size(); i++) {
                Dir cur = pieceList.get(i);
                int dir = pieceDir.get(i);

                Dir next = new Dir(cur.y + dy[dir], cur.x + dx[dir]);

                if (!isIn(next) || board[next.y][next.x] == 2) {
                    dir = dir == 1 ? 2 : (dir == 2 ? 1 : (dir == 3 ? 4 : 3));
                    next.y = cur.y + dy[dir]; next.x = cur.x + dx[dir];
                    pieceDir.replace(i, dir);
                    if (!isIn(next) || board[next.y][next.x] == 2) continue;
                }

                int idx = map[cur.y][cur.x].indexOf(i+"");
                for(int j = idx; j < map[cur.y][cur.x].length(); j++) pieceList.set(map[cur.y][cur.x].charAt(j) - '0', next); 

                if (board[next.y][next.x] == 0) map[next.y][next.x].append(map[cur.y][cur.x].substring(idx));
                else map[next.y][next.x].append(new StringBuilder(map[cur.y][cur.x].substring(idx)).reverse());

                map[cur.y][cur.x].delete(idx, map[cur.y][cur.x].length());

                if(map[next.y][next.x].length() >= 4) break outer;
            }
        }

        return ans > 1000 ? -1 : ans;
    }
}
```



### :video_game: 풀이 방법

역시 삼성st 빡구현! 입니다.

요샌 인풋으로 들어오는 애들을 차례대로 어떤 동작을 수행해야 돼서 2차원 배열과 리스트를 동시에 갖고가야 편한 문제가 나오네용.



아 큰 틀은 한 30분만에 만들었는디 ~~

해당하는 체스 말이랑 위에 있는 애들만 옮겨야 하는데 다 옮겨버리고~~

pieceList의 위치를 그때그때 갱신해야하는걸 생각못하고~~

빨간색으로 이동할 때 뒤집은걸 옮겨야 하는데 옮기고 뒤집고~~

 

그냥 잘 못 짠거구나 ;;

 

암튼.. 선언한 변수들은 이렇습니다.

```java
static int[][] board;											// 체스 판
static Map<Integer, Integer> pieceDir = new HashMap<>();		// 체스 말의 이동 방향
static List<Dir> pieceList = new ArrayList<>();					// 체스 말 list
static StringBuilder[][] map;									// 체스 말들 위치
```

먼저 체스 말들은 각각 유일하기 때문에 **Map**을 써서 이동 방향을 저장해두었습니다. 이러면 위치를 pieceDir.get(i)로 바로 얻을 수가 있지용.

그리고 체스 말들을 1번(코드에선 0번 인덱스)부터 K번 까지 차례로 이동을 해줘야 합니다. 이를 위한 List가 바로 pieceList입니다.

 

체스 말들의 상태(누가 누구 위에 있는지)는 StringBuilder로 표현했습니다. append도 편하고.. reverse() 메소드도 있어서 요거 썼습니당.. ㅎ 

Collections에도 static 메소드로 reverse()가 있긴 한데.. 엄 둘중에 뭐가 더 효율적인진 잘 모르겠습니다 ,, ^^;;

 

 

이제 얘들을 갖고 문제에서 하라는 대로 하면 됩니다.

해당하는 체스말의 현재 위치에서 자기부터 위로 똑 떼서 옮겨야 하기 때문에 자신의 인덱스(idx)를 구해줍니다.

subString()으로 똑 떼서 이동할 위치에 똑 붙여주면 됩니다. 

그러고 원래 자리에는 똑 뗀만큼 똑 없애야겠죵

 

똑 붙였는데 길이가 4 이상이 되면 칼같이 멈춰줍시당.

아니면 천번 돌렸는데 아직 4 이상인 경우가 없으면 칼같이 -1을 리턴해줍시다!!



### :video_game: 후기

삼성 코테까지 일주일 쪼끔 더 남았습니다...

호다닥 다 풀고 다른 것도 쫌 많이 풀어보고 쳐야할텐데 ㅜㅜ 

열심히 하면 다 돌아오겠지용 화이팅~!~!