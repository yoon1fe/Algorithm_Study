# [2018 KAKAO BLIND RECRUITMENT] 비밀지도 - JAVA

## 분류 
> 구현

## 코드
```java
public class 비밀지도 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for(int i=0; i<n; i++)
            answer[i] = decoder(arr1[i] | arr2[i], n);
        return answer;
    }

    public String decoder(int num, int n){
        int compBit = (int)Math.pow(2, n-1);
        String ret = "";
        while(compBit != 0){
            if((num & compBit) == 0) ret += " ";
            else ret += "#";
            compBit >>= 1;
        }
        return ret;
    }
}
```

## 문제 풀이
1. arr1[i]와 arr2[i]를 OR 연산한 결과를 통해 전체지도의 i번째 행을 얻는다.

1. arr1[i] | arr2[i] 결과를 해독하기 위해 decoder 함수에 매개변수로 전달
- compBit라는 것을 사용하였는데, 이것은 arr1[i] | arr2[i]에서 한 비트씩과 AND 연산하여 0인지 1인지 알아오기 위해 사용한다.
   + 예를 들면, n이 5이면 compbit는 10000이 된다. 그리고 arr1[i] | arr2[i] 값이 10101인 경우
   + 10000부터 >> 1로 1 위치를 왼쪽으로 한 칸씩 옮겨가면서 암호화된 배열의 값의 각 비트를 알아온다.
   + 즉, 10101 | 10000, 10101 | 01000, 10101 | 00100, 10101 | 00010, 10101 | 00001으로 각 비트의 값을 알아와서 0이면 " "빈칸을, 1이면 "#"을 리턴할 String에 추가한다.
- compBit는 >> 1을 하다가 0이 될것이고 그러면 while을 빠져나오고 해독된 값을 리턴한다.
- decoder 결과를 answer[i]에 저장하고, 리턴한다.

## 후기
잠자기 전에 한 문제 풀고! 

정보기를 위해 달리기...

홧팅~