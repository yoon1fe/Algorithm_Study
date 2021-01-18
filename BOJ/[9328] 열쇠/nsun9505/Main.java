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