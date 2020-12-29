import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static long[] tree;
    static int mod = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        arr = new int[N+1];

        int height = (int)Math.ceil(Math.log(N)/Math.log(2)) + 1;
        int size = (int)Math.pow(2, height);
        tree = new long[size];

        for(int i=1; i<=N; i++)
            arr[i] = Integer.parseInt(br.readLine());
        makeSegTree(1, 1, N);
        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            int cond = Integer.parseInt(st.nextToken());
            if(cond == 1){
                int index = Integer.parseInt(st.nextToken());
                int newValue = Integer.parseInt(st.nextToken());
                int oldValue = arr[index];
                arr[index] = newValue;
                update(1, 1, N, index, oldValue, newValue);
            } else {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                System.out.println(findMul(1, 1, N, left, right) % mod);
            }
        }
    }

    public static long makeSegTree(int node, int start, int end){
        if(start == end)
            return tree[node] = arr[start];
        int mid = (start+end) / 2;
        return tree[node] = (makeSegTree(node*2, start, mid) * makeSegTree(node*2+1, mid+1, end)) %mod;
    }

    public static long findMul(int node, int start, int end, int left, int right){
        if(left > end || right < start)
            return 1;
        if(left <= start && end <= right)
            return tree[node];
        int mid = (start + end) / 2;
        return (findMul(node*2, start, mid, left, right) * findMul(node*2+1, mid+1, end, left, right)) % mod;
    }

    public static void update(int node, int start, int end, int index, int oldValue, int newValue){
        if(index < start || end < index)
            return;
        if(start == end){
            if(start == index)
                tree[node] = newValue;
            return;
        }
        int mid = (start + end) / 2;
        update(node*2, start, mid, index, oldValue, newValue);
        update(node*2+1, mid+1, end, index, oldValue, newValue);
        tree[node] = (tree[node*2] * tree[node*2+1]) % mod;
    }
}