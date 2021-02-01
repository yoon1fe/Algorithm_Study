import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] belt = new int[2*N+1];
        int[] robots = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=2*N; i++)
            belt[i] = Integer.parseInt(st.nextToken());


        for(int level = 1;; level++) {
            int prev = belt[2*N];
            int save = belt[2*N];

            for (int i = 1; i <= 2 * N; i++) {
                save = belt[i];
                belt[i] = prev;
                prev = save;
            }

            for(int i=N; i>=1; i--){
                if(i == N && robots[i] == 1){
                    robots[i] = 0;
                    continue;
                }

                int next = i + 1;
                if(robots[i] == 1) {
                    robots[next] = 1;
                    robots[i] = 0;
                }
            }

            for(int i=N; i>=1; i--){
                if(i == N){
                    robots[i] = 0;
                    continue;
                }
                if(robots[i] == 0)
                    continue;
                if(belt[i+1] == 0 || robots[i+1] == 1)
                    continue;

                robots[i] = 0;
                robots[i + 1] = 1;
                belt[i+1] -= 1;
            }

            if(belt[1] > 0 && robots[1] == 0){
                robots[1] = 1;
                belt[1] -=1;
            }

            int cnt = 0;
            for(int i=1; i<=2*N; i++)
                if(belt[i] == 0)
                    cnt++;

            if(cnt >= K){
                System.out.println(level);
                break;
            }
        }
    }
}
