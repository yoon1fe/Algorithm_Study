package Backjoon.BOJ14888;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] operator = new int[4];
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++)
            operator[i] = Integer.parseInt(st.nextToken());

        solution(1, arr[0]);
        sb.append(max + "\n" + min);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int idx, int sum){
        if(idx == arr.length){
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for(int i=0; i<operator.length; i++){
            if(operator[i] == 0)
                continue;

            int tmp = sum;
            if(i == 0)
                tmp += arr[idx];
            else if(i == 1)
                tmp -= arr[idx];
            else if(i == 2)
                tmp *= arr[idx];
            else if(i == 3)
                tmp /= arr[idx];

            operator[i] -= 1;
            solution(idx+1, tmp);
            operator[i] += 1;
        }
    }
}