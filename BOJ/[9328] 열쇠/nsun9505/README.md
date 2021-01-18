# [9328] 열쇠 - JAVA

## 분류
> 구현
>
> BFS

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Element{
        int row;
        int col;
        char ch;
        Element(int r, int c, char ch){
            this.row = r;
            this.col = c;
            this.ch = ch;
        }
    }
    static int N, M;
    static char[][] board;
    static boolean[][] isVisited;
    static HashMap<Character, ArrayList<Element>> keyMap;
    static HashSet<Character> keySet;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for(int t=0; t<testCase; t++){
            int answer = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new char[N][M];
            isVisited = new boolean[N][M];
            keyMap = new HashMap<>();
            keySet = new HashSet<>();

            Queue<Element> queue = new LinkedList<>();


            for(int i=0; i<N; i++){
                String str = br.readLine();
                for(int j=0; j<M; j++) {
                    board[i][j] = str.charAt(j);
                    if(board[i][j] != '*' && (i == 0 || i == N-1 || j == 0 || j == M-1)) {
                        if(board[i][j] == '$' || board[i][j] == '.') {
                            answer = board[i][j] == '$' ? answer+1 : answer;
                            queue.offer(new Element(i, j, board[i][j]));
                            isVisited[i][j] = true;
                        }
                        else if(board[i][j] >= 'A' && board[i][j] <= 'Z') {
                            ArrayList<Element> list;
                            if(keyMap.containsKey(board[i][j]))
                                list = keyMap.get(board[i][j]);
                            else {
                                list = new ArrayList<Element>();
                                keyMap.put(board[i][j], list);
                            }
                            list.add(new Element(i, j, board[i][j]));
                        }
                    }
                }
            }

            String keys = br.readLine();
            for(int i=0; i<keys.length(); i++) {
                char key = Character.toUpperCase(keys.charAt(i));
                if(keyMap.containsKey(key)){
                    ArrayList<Element> list = keyMap.get(key);
                    for(Element elem : list) {
                        queue.offer(elem);
                        isVisited[elem.row][elem.col] = true;
                    }
                }
                keySet.add(key);
            }


            while(!queue.isEmpty()){
                Element elem = queue.poll();

                for(int dir = 0; dir <4; dir++){
                    int nx = elem.row + dx[dir];
                    int ny = elem.col + dy[dir];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                        continue;
                    if(isVisited[nx][ny] || board[nx][ny] == '*')
                        continue;

                    if(board[nx][ny] == '$' || board[nx][ny] == '.'){
                        queue.offer(new Element(nx, ny, board[nx][ny]));
                        isVisited[nx][ny] = true;
                        if(board[nx][ny] == '$')
                            answer++;
                    } else if(board[nx][ny] >= 'A' && board[nx][ny] <= 'Z'){
                        ArrayList<Element> list = keyMap.get(board[nx][ny]);
                        if(list == null) {
                            list = new ArrayList<>();
                            keyMap.put(board[nx][ny], list);
                        }
                        list.add(new Element(nx, ny, board[nx][ny]));

                        if(keySet.contains(board[nx][ny])){
                            for (Element e : list) {
                                queue.offer(e);
                                isVisited[e.row][e.col] = true;
                            }
                            list.clear();
                        }
                    } else if(board[nx][ny] >= 'a' && board[nx][ny] <= 'z'){
                        queue.offer(new Element(nx, ny, board[nx][ny]));
                        isVisited[nx][ny] = true;
                        char newKey = Character.toUpperCase(board[nx][ny]);
                        keySet.add(newKey);
                        if(keyMap.containsKey(newKey)) {
                            for (Element e : keyMap.get(newKey)) {
                                queue.offer(e);
                                isVisited[e.row][e.col] = true;
                            }
                        }
                    }
                }
            }
            System.out.println(answer);
        }
    }
}
```

## 문제 풀이
먼저 가장 자리에 있는 부분에서 문이거나 빈칸인 경우를 큐에 담습니다.

그리고 가장자리에 문서가 있을 수 있으므로 문서가 발견되면 answer++를 하고 큐에 담습니다.

문인 경우에는 해당 문의 위치를 HashMap에 저장합니다.

키를 입력으로 받고 대문자로 치환해서 HashSet에 저장합니다.

BFS를 사용해서 board를 탐색합니다.

1. 유효하지 않은 인덱스이거나 벽 또는 이미 방문했다면 pass

1. 방문할 곳이 빈칸이거나 문서가 있는 곳이라면 큐에 담습니다.
   - 문서인 경우에는 answer++

1. 방문할 곳이 문(영어 대문자)인 경우
   - 처음 방문하는 문이라면 keyMap에 해당 위치와 함께 넣어줍니다.
   - 해당 문을 열고 들어갈 수 있는지 가지고 있는 키(keySet)를 확인합니다.
   - 들어갈 수 있다면, 해당 문이 하나 이상일 수 있으므로 list에 저장했었기 때문에, list를 돌면서 문 위치들을 queue에 다 넣어줘서 탐색을 더 할 수 있도록 해준다.

1. 방문할 곳이 키(영어 소문자)인 경우
   - 키를 줍는다! -> keySet에 추가
   - 방금 주운 키로 방문은 했지만 키가 없어서 들어가지 못했던 문이 있었는지 찾아본다.
   - 있다면, 해당하는 문의 위치들을(문이 1개 이상일 수 있으므로) queue에 넣어줘서 탐색을 이어나갈 수 있도록 한다.

BFS를 사용해서 keySet(내가 가지고 있는 키 집합), keyMap(지금까지 방문했던 문들의 위치들)을 사용해서 키를 획득하면 이전에는 키가 없어서 들어가지 못했던 문들을 찾아보고 그런 문들이 있으면 큐에 넣어줘서 탐색하게끔 해서 풀 수 있었습니다.

## 후기
역시 구현은 구현하는 맛이 있지요.

오늘 하루도 파이팅!!