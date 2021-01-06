# [Level2] 전화번호 목록

## 분류
> 해시

## 코드
```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        Set<Integer> lenSet = new HashSet<>();
        Set<String> phSet = new HashSet<>();
        for (String phnum : phone_book) {
            if (phSet.contains(phnum))
                return false;

            for(int i : lenSet){
                if (phSet.contains(phnum.substring(0, i)))
                    return false;
            }
            phSet.add(phnum);
            lenSet.add(phnum.length());
        }
        return true;
    }
}
```

## 문제 풀이
phone_book을 문자열 길이를 기준으로 오름차순으로 정렬합니다.

for문에서는 현재 문자열의 길이에서 i만큼 자른 문자열이 존재하는지 살펴봅니다.

만약 i만큼 잘랐는데 그 문자열이 존재한다는 것은 이전에 나왔던 문자열을 접두사로 가진다는 의미가 됩니다.

지금까지 나온 문자열들의 길이만큼 잘라서 이미 존재하는지를 검사했는데 겹치는 Set에 존재하지 않는다면 해당 문자열의 길이와 문자열을 각각 Set에 담으면 됩니다.

만약 이미 존재한다면 바로 false를 리턴하여 끝내면 됩니다.

## 후기
해시를 사용하면 쉽게 풀 수 있는 문제였던 것 같습니다.

오늘도 파이팅!!

