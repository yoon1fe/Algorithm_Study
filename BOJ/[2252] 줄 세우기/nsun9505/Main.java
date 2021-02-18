import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] indegree = new int[N+1];
        LinkedList<Integer>[] graph = new LinkedList[N+1];
        for(int i=1; i<=N; i++)
            graph[i] = new LinkedList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            indegree[v] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=N; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }

        while(!queue.isEmpty()){
            int cur = queue.poll();

            sb.append(cur + " ");

            for(int next : graph[cur]){
                indegree[next] -= 1;
                if(indegree[next] == 0)
                    queue.offer(next);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}