import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] list, segTree;

    static int findMin(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return 1000000001;
        if (left <= start && end <= right) return segTree[node];

        int mid = (start + end) / 2;

        return Math.min(findMin(node * 2, start, mid, left, right), findMin(node * 2 + 1, mid + 1, end, left, right));
    }

    static int init(int node, int start, int end) {
        if (start == end) return segTree[node] = list[start];

        int mid = (start + end) / 2;

        return segTree[node] = Math.min(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end));
    }

    public static void main(String[] args) throws Exception {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new int[N];
        segTree = new int[N * 4];

        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }

        init(1, 0, N - 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(findMin(1, 0, N - 1, a - 1, b - 1));
        }
    }

}
