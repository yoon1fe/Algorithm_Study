# [2018 KAKAO BLIND RECRUITMENT] 추석트래픽 - Java

## 분류
> 구현

## 코드
```java
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 추석트랙픽 {
    static class Time{
        long start;
        long end;

        Time(long s, long e){
            this.start = s;
            this.end = e;
        }
    }

    public static ArrayList<Time> timeList = new ArrayList<>();
    public static int solution(String[] lines) {
        int answer = 0;

        for(int i=0; i<lines.length; i++)
            timeList.add(getTime(lines[i]));

        for(Time t : timeList){
            for(int i=0; i<2; i++){
                long time = t.start;
                if(i != 0)
                    time = t.end;

                int cnt = 0;
                for(Time s : timeList){
                    if(isContain(time, s))
                        cnt++;
                }

                answer = answer < cnt ? cnt : answer;
            }
        }

        return answer;
    }

    public static boolean isContain(long start, Time t){
        long end = start + 999;
        if(start >= t.start && start <= t.end)
            return true;
        else if(end >= t.start && end <= t.end)
            return true;
        else if(start <= t.start && end >= t.end)
            return true;
        return false;
    }

    public static Time getTime(String time){
        StringTokenizer st = new StringTokenizer(time, " ");
        String endLog = st.nextToken();
        endLog += " " + st.nextToken();
        String processingTime = st.nextToken();
        int processingSec = ((int)(Double.parseDouble(processingTime.substring(0, processingTime.lastIndexOf('s'))) * 1000));

        long endTime = Timestamp.valueOf(endLog).getTime();
        long startTime = endTime - processingSec + 1;

        return new Time(startTime, endTime);
    }

    public static void main(String[] args) {
        String[] lines = {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"};
        System.out.println(solution(lines));
    }
}
```

## 문제 풀이
처음에 문자열을 받은 각 로그의 처리완료 시간을 long형으로 바꾼다.
- 시작시간 = 처리완료시간 - 1000 + 1

그러면 각 로그의 시작시간과 끝 시간이 만들어진다.

### 핵심
> 요청량이 변하는 순간은 각 로그의 시작과 끝 부분!

각 로그의 시작 혹은 끝 시간이 start를 의미한다.
- end는 start + 999한 값으로 1초 간격의 끝을 의미한다.
- t는 비교할 로그의 시작 시간과 끝 시간을 담고 있다.
```java
// 1. case :  [ 1s ]
//          [ ]
if(start >= t.start && start <= t.end)
    return true;
// 2. case : [ 1s ]
//                [   ]
else if(end >= t.start && end <= t.end)
    return true;
// 3.case :  [ 1s ]
//            [  ]
else if(start <= t.start && end >= t.end)
    return true;
```
1. 시작 시간(start)이 비교하는 로그 구간(t)의 시작시간보다 크고 끝나는 시간보다 작은 경우 포함시킬 수 있다.
1. 끝 시간이 비교하는 로그 구간(t)의 시작 시간보다 크고 끝나는 시간 보다 작은 경우 포함시킬 수 있다.
1. 시작 시간이 비교하는 로그 구간(t)의 시작시간 보다 작지만, 끝 시간은 로그 구간의 끝 시간보다 큰 경우 포함시킬 수 있다.

위의 로직을 로그의 시작 시간과 끝 시간에 대해서 각각 돌려주면서 구간안에 포함되는 애들을 카운트해서 가장 큰 애를 답으로 출력하면 된다.

## 후기
후아 어렵다 어려워

처음에 2개가 실패 뜨더니 풀릴 느낌이 안 들었지만 계속 잡고 있다가 구글링~

참 얼마 안 되는 코드인데.. 시간만 int나 long으로 바꾸고 거기서 구현부분만 짜면 되는 것인데 아쉽다~