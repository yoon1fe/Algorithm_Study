import java.io.*;
import java.util.*;

public class Main {
    static int ans = 0;
    static int N;
    static ArrayList<Element> graph[];
    static boolean[] isVisited;
    static Queue<Element> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        isVisited = new boolean[N+1];
        for(int i=1; i<=N; i++)
            graph[i] = new ArrayList<>();

        for(int i=0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[parent].add(new Element(child, weight));
            graph[child].add(new Element(parent, weight));
        }

        Element startVertex = BFS(1);
        Element endVertex = BFS(startVertex.vertex);

        sb.append(endVertex.dist);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static Element BFS(int start) {
        Arrays.fill(isVisited, false);
        isVisited[start] = true;
        queue.offer(new Element(start, 0));

        Element max = new Element(1, 0);
        while (!queue.isEmpty()) {
            Element elem = queue.poll();

            for (Element next : graph[elem.vertex]) {
                if (isVisited[next.vertex])
                    continue;

                isVisited[next.vertex] = true;
                queue.offer(new Element(next.vertex, next.dist + elem.dist));
                if (max.dist < next.dist + elem.dist) {
                    max.dist = next.dist + elem.dist;
                    max.vertex = next.vertex;
                }
            }
        }
        return max;
    }

    static class Element{
        int vertex;
        int dist;

        public Element(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }
}
