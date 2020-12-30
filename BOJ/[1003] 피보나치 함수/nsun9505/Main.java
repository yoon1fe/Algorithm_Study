import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] cntOfZero = new int[41];
        int[] cntOfOne = new int[41];
        cntOfZero[0] = 1;
        cntOfOne[1] = 1;
        for(int i=2; i<=40; i++){
            cntOfZero[i] = cntOfZero[i-1] + cntOfZero[i-2];
            cntOfOne[i] = cntOfOne[i-1] + cntOfOne[i-2];
        }

        for(int i=0; i<T; i++){
            int N = Integer.parseInt(br.readLine());
            System.out.println(cntOfZero[N] + " " + cntOfOne[N]);
        }
    }
}