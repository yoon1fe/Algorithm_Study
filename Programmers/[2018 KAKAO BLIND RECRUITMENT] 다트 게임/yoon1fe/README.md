## [2018 KAKAO BLIND RECRUITMENT] 다트 게임 - Java

###    :dart: ​분류

> 문자열 처리



###  :dart: 코드

```java
class Solution {
    static class Result{
        int score, total;
        String bonus;
    }

    public int solution(String dartResult) {
        int answer = 0;
        Result[] results = new Result[3];
        for(int i = 0; i < 3; i++) results[i] = new Result();

        getTurn(dartResult, results);

        for(int i = 0; i < results.length; i++) {
            Result result = results[i];

            for(int j = 0; j < result.bonus.length(); j++) {
                switch(result.bonus.charAt(j)){
                case 'S': result.total = result.score; break;
                case 'D': result.total = (int)Math.pow(result.score, 2); break;
                case 'T': result.total = (int)Math.pow(result.score, 3); break;
                case '*':
                    result.total *= 2;
                    if(0 < i) results[i-1].total *= 2;
                    break;
                case '#': result.total *= -1; break;
                }
            }
        }

        for(Result r : results) answer += r.total;

        return answer;
    }

    private void getTurn(String dartResult, Result[] results) {
        int idx = -1;
        StringBuilder temp = new StringBuilder();

        for(int i = 0; i < dartResult.length(); i++) {
            int score = getScore(dartResult, i);
            if(score != -1) {
                if(temp.length() != 0) {
                    results[idx].bonus = temp.toString();
                    temp.delete(0, temp.length());
                }

                results[++idx].score = score;
                i = score == 10 ? i+1 : i;
                continue;
            }
            temp.append(dartResult.charAt(i));
        }
        results[idx].bonus = temp.toString();
    }

    public int getScore(String dartResult, int idx) {
        if('0' > dartResult.charAt(idx) || dartResult.charAt(idx) > '9') return -1;
        // 10 체크
        if(dartResult.charAt(idx) == '1' && dartResult.charAt(idx + 1) == '0') return 10;
        else return dartResult.charAt(idx) - '0';
    }
}
```



### :dart: ​풀이 방법

문자열을 다루는 문제입니다.

정규 표현식을 연습 많이 해보아야겠습니다 ㅜㅜ



코드가 정말 안이쁨니다. 맘에 안드네여

어떻게 하면 result 세개를 이쁘게 갖고 올 수 있을까 고민을 정말 많이 햇는데... ㅜ

 

암튼.. Result 란 클래스를 만들어서 다뤘습니다. 각 턴마다 주어진 문자열을 getTurn() 메소드를 통해 점수|보너스(옵션) 으로 나누었습니다.

요렇게 이쁘게 빼주는게 이 문제의 핵심이라고 생각합니당. 그 뒤에는 문제에 주어진 조건대로 총 점수를 구하면 됩니다.!!!



###  :dart: 후기 

문자열 처리 연습 많이!!!

감사합니다!!!!!!