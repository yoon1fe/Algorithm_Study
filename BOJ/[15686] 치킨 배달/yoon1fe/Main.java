import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 999999999;
    static int N, M;
    static int answer = MAX;
    static List<Dir> house = new ArrayList<>();
    static List<Dir> chicken = new ArrayList<>();
    static Dir[] selected;

    static class Dir{
        int y, x;
        Dir(int y, int x){
            this.y = y; this.x = x;
        }
    }

    static void comb(int cnt, int idx) {
        if(cnt == M) {
            answer = Math.min(answer, getChickenDistance());
            return;
        }

        for(int i = idx; i < chicken.size(); i++) {
            selected[cnt] = chicken.get(i);
            comb(cnt+1, i+1);
        }
    }

    static int getChickenDistance() {
        int sum = 0;
        for(int i = 0; i < house.size(); i++) {
            int chickenDist = MAX;
            for(int j = 0; j < selected.length; j++) {
                chickenDist = Math.min(chickenDist, getDistance(house.get(i), selected[j])); 
            }
            sum += chickenDist;
        }
        return sum;
    }

    static int getDistance(Dir d1, Dir d2) {
        return Math.abs(d1.y - d2.y) + Math.abs(d1.x - d2.x);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        selected = new Dir[M];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n == 1) house.add(new Dir(i, j));
                else if(n == 2) chicken.add(new Dir(i, j));
            }
        }

        comb(0, 0);
        System.out.println(answer);
    }
}