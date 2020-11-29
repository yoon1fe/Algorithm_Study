package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1197 {
    static class Node implements Comparable<Node>{
        int u;
        int v;
        int weight;

        Node(int u, int v, int w){
            this.u = u;
            this.v = v;
            this.weight = w;
        }

        @Override
        public int compareTo(Node n) {
            return this.weight >= n.weight ? 1 : -1;
        }
    }

    static int V, E;
    static int[] parent;
    static int[] rank;
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    public static void solution(){
        int nodeCnt = 0;
        int ans = 0;
        while(nodeCnt < V-1){
            Node node = pq.poll();

            int u = find(node.u);
            int v = find(node.v);

            if(union(u, v)){
                nodeCnt += 1;
                ans += node.weight;
            }
        }
        System.out.println(ans);
    }

    public static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int u, int v){
        u = find(u);
        v = find(v);

        if(u == v)
            return false;

        if(rank[u] > rank[v]){
                int tmp = u;
                u = v;
                v = tmp;
        }
        parent[u] = v;
        if(rank[u] == rank[v])
            rank[u]++;

        return true;
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V+1];
        rank = new int[V+1];
        pq =  new PriorityQueue<>();

        for(int i=1; i<=V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Node(u, v, w));
        }
    }
}
