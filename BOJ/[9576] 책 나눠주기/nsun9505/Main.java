import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] memo;
    static Range[] ranges;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N + 1];
            memo = new int[M];
            ranges = new Range[M];
            Arrays.fill(arr, 1);

            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                ranges[i] = new Range(from, to);
            }

            Arrays.sort(ranges);
            int ans = 0;
            for(int i=0; i<M; i++){
                for(int bIdx = 1; bIdx <= N; bIdx++){
                    if(arr[bIdx] == 1 && ranges[i].from <= bIdx && bIdx <= ranges[i].to){
                        ans++;
                        arr[bIdx] = 0;
                        break;
                    }
                }
            }
            sb.append(ans + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Range implements Comparable<Range> {
        int from;
        int to;

        public Range(int f, int t){
            this.from = f;
            this.to = t;
        }

        @Override
        public int compareTo(Range o1){
            if(o1.to == this.to)
                return Integer.compare(this.from, o1.from);
            return Integer.compare(this.to, o1.to);
        }
    }
}