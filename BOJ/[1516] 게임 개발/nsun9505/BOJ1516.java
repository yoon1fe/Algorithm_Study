package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1516 {
    static int N;
    static int[] W;
    static int[] inDegree;
    static int[] result;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        init();
        solution();
        for(int i=1; i<=N; i++)
            System.out.println(result[i]);
    }

    public static void solution(){
        Queue<Integer> Q = new LinkedList<Integer>();
        for(int i=1; i<=N; i++) {
            if (inDegree[i] == 0) {
                Q.offer(i);
                result[i] = W[i];
            }
        }

        while(!Q.isEmpty()){
            int cur = Q.poll();

            for(int i=0; i<graph[cur].size(); i++){
                int next = graph[cur].get(i);
                inDegree[next] -= 1;
                result[next] = Math.max(result[next], result[cur] + W[next]);
                if(inDegree[next] == 0)
                    Q.add(next);
            }
        }
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        W = new int[N+1];
        inDegree = new int[N+1];
        result = new int[N+1];

        for(int i=1; i<=N; i++)
            graph[i] = new ArrayList<Integer>();

        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            while(true){
                int prev = Integer.parseInt(st.nextToken());
                if(prev == -1)
                    break;
                graph[prev].add(i);
                inDegree[i] += 1;
            }
        }
    }
}