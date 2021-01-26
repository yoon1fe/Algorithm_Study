import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        int size = (int)(max - min + 1);
        boolean[] arr = new boolean[size];
        
        for(long i=2; i<=1000000; i++) {
            // 제곱수
            long num = i*i;
            if(num > max)
                break;
            // 제곱의 배수 중 min ~ max 사이에 속하는지 체크
            for(long j = min / num; j * num <=max; j++)
                if(num * j >= min)
                    arr[(int)(j*num - min)] = true;
        }

        int cnt = 0;
        for(int i=0; i<size; i++)
            if(!arr[i])
                cnt++;
        System.out.println(cnt);
    }
}
