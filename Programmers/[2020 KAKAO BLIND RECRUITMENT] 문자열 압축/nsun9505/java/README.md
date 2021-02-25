# [2020 KAKAO BLIND RECRUITMENT] 문자열 압축 - JAVA

## 분류
> 문자열

## 코드
```java
import java.util.ArrayList;

public class Solution {
    static ArrayList<String> list = new ArrayList<>();
    public int solution(String string){
        int ans = string.length();
        for(int len=1; len<string.length(); len++)
            ans = Math.min(ans, compress(string, len));

        return ans;
    }

    public int compress(String str, int len) {
        list.clear();
        for (int i = 0; i < str.length(); i += len) {
            String ret = "";
            if (i + len > str.length())
                ret = str.substring(i);
            else
                ret = str.substring(i, i + len);
            list.add(ret);
        }

        String ret = "";
        for (int i = 0; i < list.size(); ) {
            String comp = list.get(i);
            int cnt = 1;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).equals(comp))
                    cnt++;
                else
                    break;
            }
            i += cnt;
            if (cnt > 1)
                ret += String.valueOf(cnt) + comp;
            else
                ret += comp;
        }
        return ret.length();
    }
}
```

## 문제 풀이
1 ~ string 길이만큼 잘라보면서 자른 길이만큼으로 압축했을 때 가장 많이 짧은 문자열이 답입니다.

예를 들어, 길이를 3만큼 자른다면 주어진 문자열을 처음부터 길이가 3인 문자열씩 자릅니다.

그리고 자른 문자열 중에서 연속하면서 같은 것들의 갯수를 카운트하여 카운트한 숫자와 해당 문자열을 합쳐서 결과에 더하면 됩니다.

마지막으로 압축된 문자열의 길이를 리턴하여 기존의 답과 비교하여 더 작으면 답을 갱신하면 됩니다.

## 후기
오늘도 화이팅!