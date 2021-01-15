# [9251] LCS - JAVA

## 분류
> DP

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        int[][] dp = new int[str1.length()+1][str2.length()+1];

        for(int i=0; i<str1.length(); i++){
            int row = i+1;
            for(int j=0; j<str2.length(); j++){
                int col = j + 1;
                if(str1.charAt(i) == str2.charAt(j))
                    dp[row][col] = dp[row-1][col-1] + 1;
                else
                    dp[row][col] = Math.max(dp[row-1][col], dp[row][col-1]);
            }
        }
        System.out.println(dp[str1.length()][str2.length()]);
    }
}
```

## 문제 풀이
위키보고 풀었습니다!
   - 개념 설명이 자세히 설명되어 있어서 이해하기 좋았습니다.

이차원 배열을 사용해서 DP로 풀면 됩니다!
   - 먼저 0행과 0열은 0으로 모두 초기화합니다.
   - 1행과 1열부터 비교를 하면 됩니다.

비교는 2가지입니다.

s1가 i번째에서, s2는 j번째에서 끝난다고 합시다.
   1. s1[i] == s2[j]인 경우
       - s1이 i번째에서 끝나고, s2가 j번째에서 끝난다고 생각해봅시다.
       - 그러면 s1[i] == s2[j]는 끝문자가 동일하므로 이전까지 나온 최장 공통 수열(i-1, j-1)에 +1하면 됩니다.
       - 이전까지 나온 최장 공통 수열은 (i-1, j-1)에 존재합니다!

   1. s1[i] != s2[j]인 경우
      - 두 수열 s1과 s2가 같은 문자로 끝나지 않는 경우입니다.
      - 이 경우 s1과 s2의 LCS는 LCS[i][j-1], LCS[i-1][j] 중 더 긴 수열입니다.
      - 그러면 s1="ABC", s2="AD"라고 할 때 C로 끝나는 경우 D로 끝날 수 없으므로 수열 s2에서 D를 제거해도 손실이 일어나지 않습니다!
      - 그래서 LCS[i][j] = LCS[i][j-1]입니다.
      - 또한, C로 끝나지 안을 경우 수열 s1에서 C를 제거해도 손실이 일어나지 않습니다.
      - 그래서 LCS[i][j] = LCS[i-1][j];
      - 그러므로 LCS[i][j] = max(LCS[i-1][j], LCS[i][j-1])입니다.

그러면 식을 아래와 같이 정의할 수 있습니다.
```java
LCS[i][j] = LCS[i-1][j-1] + 1             if s1[i] == s2[j]
            max(LCS[i-1][j], LCS[i][j-1]) if s1[i] != s2[j]
```

## 후기
DP는 역시 식을 잘 만드는 것이 중요?!!

오늘 하루도 파이팅