package PS;
import java.util.Scanner;

class Main{
    static int[][] map;
    static int L;
    static int ans = 0;

    static void solve(int[] line) {
        int start = line[0];
        boolean[] checked = new boolean[line.length];
        boolean possible = true;

        outer:
        for(int i = 1; i< line.length; i++){
            int diff = Math.abs(start - line[i]);
            if(diff > 1) {
                possible = false; break;
            }
            if(start > line[i]){
                for(int j = i; j < i+L; j++){
                    if(j >= line.length || checked[j] || line[j] != line[i]){
                        possible = false;  break outer;
                    }
                    checked[j] = true;
                }
            }else if(start == line[i]) continue;
            else{
                for(int j = i-1; j > i-1 - L; j--){
                    if(j < 0 || checked[j] || line[j] != start){
                        possible = false; break outer;
                    }
                    checked[j] = true;
                }
            }
            start = line[i];
        }

        ans = possible == true ? ans+1 : ans;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        L = sc.nextInt();
        map = new int[N*2][N];

        for(int i= 0; i< N ;i ++)
            for(int j = 0; j< N; j++)
                map[i][j] = sc.nextInt();

        for(int i = 0; i< N; i++)
            for(int j = 0; j< N; j++)
                map[i+N][j] = map[j][i];

        for(int i = 0; i< 2*N; i++) solve(map[i]);

        System.out.println(ans);
    }
}