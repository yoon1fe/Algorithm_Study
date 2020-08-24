## [2020 KAKAO BLIND RECRUITMENT] 문자열 압축 - Java

###   :abc: 분류

> 문자열 처리



###  :abc: ​코드

```java
class Solution {
    public int solution(String s) {
        int answer = s.length();

        for(int subLen=1;subLen<=s.length()/2; subLen++) {
            String newStr = "";
            String standard = s.substring(0, subLen);
            int cnt = 1;

            for(int i=subLen;i<s.length();i+=subLen) {
                if(i+subLen > s.length()) {
                    newStr+=s.substring(i);
                    break;
                }
                String comp = s.substring(i, i+subLen);

                if(standard.equals(comp)) cnt+=1;
                else {
                    if(cnt>1) newStr+=Integer.toString(cnt)+standard;                    
                    else newStr+=standard;

                    standard = comp;
                    cnt = 1;
                }
            }
            if(cnt>1) newStr+=Integer.toString(cnt)+standard;                    
            else newStr+=standard;

            answer = answer < newStr.length() ? answer : newStr.length();
        }
        return answer;
    }
}
```



### :abc: 풀이 방법

문자열을 다루는 문제입니다.

문자열을 몇 개 단위로 자를지 모두 봐줘야 하고, 문자열의 길이는 최대 1000이기 때문에 2중 for문으로 쉽게 풀 수 있습니다.

`subLen`: 자른 문자열의 길이 (1개부터 `s.length()/2`개 만큼)

`newStr`: 압축한 문자열

`standard`: 비교의 기준이 될 문자열

`cnt`: 반복된 횟수

`comp`: 비교할 문자열

 우선 `subLen`이 전체 문자열의 길이의 반보다 크다면 반복될 수 없으므로 `subLen`은 문자열의 반까지만 봐주면 됩니다.

 `standard`를 먼저 구해주고 그 다음부터 `comp`를 구해서 `standard`와 `comp`를 비교하면서 `cnt`를 구해줍니다.

 `i+subLen > s.length()`라면 마지막까지 체크를 한 것이므로 고대로 넣어주면 됩니다.

 `standard`와 `comp`가 다르다면 `cnt`가 2 이상일 때 `newStr`에 숫자와 함께 반복된 문자열을 추가해주고, `standard`를 `comp`로 업데이트 시켜줍니다.

 반복문이 다 돌았을 때 남은 것들을 `newStr`에 넣어주면 끝!

 문자열을 여러 개 단위로 잘랐을 때, 길이의 최소값을 구해야 하므로

`answer = answer < newStr.length() ? answer : newStr.length();` 로 answer를 구하면 끝입니다.



###  :abc: 후기

사실 풀 때 도움을 좀 받았습니다 하하 ^^;;