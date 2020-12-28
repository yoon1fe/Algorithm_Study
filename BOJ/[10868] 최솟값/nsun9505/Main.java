import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];

        int height = (int)Math.ceil(Math.log(N)/Math.log(2)) + 1;
        int size = (int)Math.pow(2, height);
        tree = new int[size];

        for(int i=1; i<=N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        makeSegTree(1, 1, N);
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            System.out.println(find(1, 1, N, left, right));
        }

    }

    public static int makeSegTree(int node, int start, int end){
        if(start == end)
            return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return tree[node] = Math.min(makeSegTree(node*2, start, mid), makeSegTree(node*2+1, mid+1, end));
    }

    public static int find(int node, int start, int end, int left, int right){
        if(left > end || right < start)
            return Integer.MAX_VALUE;
        if(left <= start && end <= right)
            return tree[node];
        int mid = (start + end) / 2;
        return Math.min(find(node*2, start, mid, left, right), find(node*2+1, mid+1, end, left, right));
    }
}