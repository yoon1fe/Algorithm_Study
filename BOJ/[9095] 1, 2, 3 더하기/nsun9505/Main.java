import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] arr = new int[11];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 4;

        for(int i=4; i<11; i++)
            arr[i] = arr[i-1] + arr[i-2] + arr[i-3];

        for(int i=0; i<T; i++){
            int N = Integer.parseInt(br.readLine());
            System.out.println(arr[N]);
        }
    }

    /*public static void solution(int num, int N){
        if(num >= N) {
            if(num == N)
                ans++;
            return;
        }
        solution(num+1, N);
        solution(num+2, N);
        solution(num+3, N);
    }*/
}