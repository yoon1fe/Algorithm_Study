# [12738] 가장 긴 증가하는 부분 수열 3

## 분류
> 이분 탐색

## 코드
```java
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
```

## 문제 풀이
이전에 가장 긴 증가하는 부분 수열 2를 풀어봤던 기억이 있어서 이진 탐색으로 풀었습니다!

입력으로 들어오는 숫자를 모두 양수로 맞춰주기 위해서 10억씩 다 더했습니다.

그리고 미리 첫 번째를 list에 담았습니다.

for문을 돌면서 입력되는 숫자가 리스트의 마지막 원소보다 크다면 lower bound를 돌릴 필요 없이 바로 끝에 삽입합니다.
   - 그게 아니라 계속 숫자가 들어갈 위치를 찾는다면 시간초과가 뜹니다ㅠㅠ

그것이 아니라 0 ~ size - 1 사이에 속한다면 lower bound를 돌려서 해당 숫자가 어디에 위치할지를 알아내고 원래 있던 값과 바꿔주면 됩니다.

N까지 모두 돌고나면 list의 사이즈를 출력하면 됩니다.

## 후기
가장 긴 증가하는 부분 수열 2를 풀 때는 이분 탐색에서 lower bound나 upper bound에 대해서 잘 몰랐는데 이후에 공부해서 잘 해결할 수 있었습니다.