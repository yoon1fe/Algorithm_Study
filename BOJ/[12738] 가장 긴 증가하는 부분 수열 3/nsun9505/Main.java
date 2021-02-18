import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        list.add(Integer.parseInt(st.nextToken()) + 1000000000);

        for(int i=1; i<N; i++){
            int num = Integer.parseInt(st.nextToken()) + 1000000000;
            if(list.get(list.size()-1) < num)
                list.add(num);
            else {
                int idx = lowerBound(num);
                list.set(idx, num);
            }
        }

        sb.append(list.size());

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int lowerBound(int num){
        int left = 0;
        int right = list.size();

        while(left < right){
            int mid = (left + right) / 2;

            if(mid >= list.size()){
                left = mid;
                break;
            }

            if(list.get(mid) >= num)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }
}