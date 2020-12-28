# [17609] 회문 - JAVA

## 문제풀이
> 완전탐색

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            String str = br.readLine();
            int ret = isPalindrome(0, str);
            if(ret == 0 || ret == 1) System.out.println(ret);
            else System.out.println(2);
        }
    }

    public static int isPalindrome(int depth, String str){
        if(depth >= 2)
            return 2;

        int len = str.length() / 2;
        if(str.length() % 2 != 0)
            len += 1;

        boolean flag = true;
        int cnt = 0;
        for(int i=0, j = str.length()-1; i<len;) {
            if (str.charAt(i) == str.charAt(j)) {
                i++;
                j--;
            } else if(str.charAt(i+1) == str.charAt(j) || str.charAt(i) == str.charAt(j-1)) {
                return Math.min(isPalindrome(depth + 1, str.substring(0, i) + str.substring(i+1)),
                        isPalindrome(depth + 1, str.substring(0, j) + str.substring(j+1))) + 1;
            } else
                return 2;
        }
        return cnt;
    }
}
```

## 문제 풀이
isPalindrome이 리턴하는 값에 따라 회문인지, 유사회문인지 아니면 둘 다 아닌지를 출력합니다.
   - 리턴 값이 0이면 회문
   - 리턴 값이 1이면 유사회문
   - 리턴 값이 0과 1도 아니라면 둘 다 해당되지 않는다고 2를 출력하면 됩니다.

isPalindrome은 DFS를 통해서 깊이가 2까지만 내려갑니다.
   - 깊이가 0인 경우에는 아직 다른 알파벳을 발견하지 못한 상태입니다.
   - 깊이가 1인 경우에는 i+1번째와 j번째가 같거나, i번째와 j-1번째가 같음으로써 둘 중 하나를 제거한 것입니다.
      - 위 두 경우에 대해서 모두 살펴보기 때문에 재귀 함수를 호출합니다.
      - 입력 문자열에서 i번째 문자를 제거한 뒤에 검사한 것과 j번째 문자를 제거한 것을 비교합니다.
      - 그 둘 중에서 가장 작은 값이 유사 회문인지 아니면 회문인지 아니면 둘 다 아닐지를 판단할 수 있다.
      - +1을 하는 이유는 현재 상태에서 이미 1개의 문자가 다르기 때문에 재귀호출의 리턴 값에 +1을 함으로써 문자 하나가 다르다는 것을 표시하기 위함이다.
   - 깊이가 2이상인 경우에는 문자를 2개이상 지웠으므로 유사회문에도 해당되지 않기에 더이상 내려가지 않고 2를 리턴
   - 그리고 회문인지 검사하는 포문에서 완전히 서로 다른 문자가 나오면 : i+1번째와 j번째도 틀리고, i번째와 j-1번째도 틀린 경우
      - 회문으로 만들기 위해서 2개 이상의 문자를 지워야하므로 리턴 2를 해서 더이상 탐색하지 않도록 합니다.

## 후기
여러가지 반례를 해보면서 풀 수 있었습니다~

2020 얼마 남지 않았네용

화이팅!!!