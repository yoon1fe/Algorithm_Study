package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1922 {
    static class Node implements Comparable<Node>{
        int cur;
        int next;
        int weight;

        Node(int cur, int n, int w){
            this.cur = cur;
            this.next = n;
            this.weight = w;
        }

        @Override
        public int compareTo(Node node) {
            return (this.weight - node.weight);
        }
    }

    public static int N;
    public static int M;
    public static PriorityQueue<Node> pq;
    public static int[] parent;
    public static int[] rank;
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }

    public static int solution(){
        int cnt = 0;
        int ret = 0;
        while(cnt < N-1 && !pq.isEmpty()){
            Node node = pq.poll();

            if(!union(node.cur, node.next))
                continue;

            cnt++;
            ret += node.weight;
        }
        return ret;
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

        if(rank[u] < rank[v])
            parent[u] = v;
        else if(rank[u] > rank[v])
            parent[v] = u;
        else{
            parent[v] = u;
            rank[u] += 1;
        }

        return true;
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        rank = new int[N+1];

        for(int i=1; i<=N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        pq = new PriorityQueue<>();

        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Node(u, v, w));
        }
    }
}
