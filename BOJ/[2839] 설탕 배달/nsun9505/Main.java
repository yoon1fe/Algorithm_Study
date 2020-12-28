import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        int ret = solution(N);
        if(ret > 5001)
            System.out.println(-1);
        else
            System.out.println(ret);
    }

    public static int solution(int N){
        if(N == 0)
            return 0;
        else if(N < 0)
            return 5001;

        if(arr[N] != 0)
            return arr[N];

        return arr[N] = Math.min(solution(N - 5), solution(N - 3)) + 1;
    }
}