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