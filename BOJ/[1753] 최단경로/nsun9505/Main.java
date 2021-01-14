import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        Node(int v, int w){
            this.vertex = v;
            this.weight = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine()) - 1;
        int[] dist = new int[N];
        ArrayList<Node>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        Arrays.fill(dist, INF);
        dist[start] = 0;

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if(dist[cur.vertex] < cur.weight)
                continue;

            for(int i=0; i<graph[cur.vertex].size(); i++){
                Node next = graph[cur.vertex].get(i);
                int nextDist = cur.weight + next.weight;
                if(nextDist < dist[next.vertex]){
                    dist[next.vertex] = nextDist;
                    pq.offer(new Node(next.vertex, nextDist));
                }
            }
        }

        for(int i=0; i<N; i++) {
            if (dist[i] == INF)
                sb.append("INF\n");
            else
                sb.append(dist[i] + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
