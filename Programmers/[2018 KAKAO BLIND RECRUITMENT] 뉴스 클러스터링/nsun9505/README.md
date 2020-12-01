# [2018 KAKAO BLIND RECRUITMENT] 뉴스클러스터링 - JAVA

## 분류
> 구현

## 코드
```java
package Programmers;

import java.util.ArrayList;

public class 뉴스클러스터링 {
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        if(str1.equals(str2))
            return 65536;

        ArrayList<String> str1MultiSet = getMultiSet(str1);
        ArrayList<String> str2MultiSet = getMultiSet(str2);

        int intersecSize = getIntersectionSize(str1MultiSet, str2MultiSet);
        int unionSize = getUnionSize(str1MultiSet, str2MultiSet);
        double ret = intersecSize / (unionSize*1.0);

        return (int)(ret * 65536);
    }

    public static void print(ArrayList<String> strlist){
        for(String str : strlist)
            System.out.print(str + " ");
        System.out.println();
    }

    public static int getIntersectionSize(ArrayList<String> str1, ArrayList<String> str2) {
        int cnt = 0;
        boolean isUsed[] = new boolean[str2.size()];
        for(int i=0; i<str1.size(); i++) {
            for (int j = 0; j < str2.size(); j++) {
                if (str1.get(i).equals(str2.get(j)) && !isUsed[j]) {
                    cnt++;
                    isUsed[j] = true;
                    break;
                }
            }
        }
        return cnt;
    }

    public static int getUnionSize(ArrayList<String> str1, ArrayList<String> str2) {
        int cnt = 0;
        boolean isUsed[] = new boolean[str2.size()];
        for(String s1 : str1){
            boolean flag = true;
            for(int j=0; j<str2.size(); j++){
                if(s1.equals(str2.get(j)) && !isUsed[j]){
                    isUsed[j] = true;
                    flag = false;
                    break;
                }
            }
            if(flag)
                cnt++;
        }

        return cnt + str2.size();
    }

    public static ArrayList<String> getMultiSet(String str){
        ArrayList<String> result = new ArrayList<>();
        for(int i=2; i<=str.length(); i++) {
            String subStr = str.substring(i-2, i).toLowerCase();
            if(subStr.matches("[a-z]*$"))
                result.add(subStr);
        }
        return result;
    }

    public static void main(String[] args){
        뉴스클러스터링 s = new 뉴스클러스터링();
        System.out.println(s.solution("E=M*C^2", "e=m*c^2"));
    }
}
```

## 문제 풀이
멀티 셋에서 합집합, 교집합만 구하면 끝인 문제!

대신, 두 문자열이 아예 동일하면 65536 리턴!
- 특수문자가 포함되어도 상관없이!

str1, str2를 가지고 각각의 다중집합을 만들어서 ArrayList를 유지한다.

### 교집합 구하기
1. str1 다중집합의 각 원소를 str2 다중집합의 각 원소와 비교한다.
1. str1의 i번째 원소와 str2의 j번째 원소가 동일
   1. 동일하지만, str2의 j번째 원소가 이미 비교되었다면 교집합에 포함될 수 없음.
   1. 동일하고, str2의 j번째 원소가 이미 비교되지 않았다면 교집합에 포함
1. 교집합의 수를 리턴

### 합집합 구하기
1. str1 다중집합의 각 원소를 str2 다중집합의 각 원소와 비교한다.
1. str1의 i번째 원소와 str2의 j번째 원소가 동일
   + 동일하지만, str2의 j번째 원소가 이미 비교되었다면 j+1로 넘어가면서 동일하면서 아직 비교되지 않은 str2의 원소를 찾는다.
   + 그런 원소를 찾는다면, 합집합에 추가할 필요가 없으므로 flag를 false로 변경하고 for문을 멈춘다.
   + 만약 동일하면서 아직 비교되지 않은 원소가 없다면, 합집합에 포함될 수 있으므로 flag는 true를 유지하게 되고, cnt값을 증가시키면 된다.
1. cnt값과 str2의 원소 개수를 더해서 리턴하면 합집합의 원소 개수가 된다.
   + cnt값은 str1의 원소중에서 str2에 추가되어서 합집합을 만들 원소의 수를 의미한다.

## 후기
추석보다는 쉬웠당..ㅎ