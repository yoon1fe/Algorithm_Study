## [2018 KAKAO BLIND RECRUITMENT] 추석 트래픽 - Java

###    :full_moon: ​분류

> 구현

​

###  :full_moon: 코드

```java
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Solution {
    public int solution(String[] lines) throws ParseException {
        int answer = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        Date[] start = new Date[lines.length];
        Date[] end = new Date[lines.length];
        
        // 시작 - 끝 시간 구하기 
        for(int i = 0; i < lines.length; i++) {
        	String[] time = lines[i].split(" ");
        	int throughput = (int)(Double.parseDouble(time[2].substring(0, time[2].length() - 1)) * -1000);
        	
        	end[i] = sdf.parse(time[1]);
        	start[i] = getMillisecond(end[i], throughput + 1);
        }
        
        // 끝 구간 ~ 1초동안에 포함된 개수 구하기
        for(int i = 0; i < lines.length; i++) {
        	int cnt = 0; 
        	long e = end[i].getTime();
        	long term = getMillisecond(end[i], 1000).getTime();	// 끝나는 시간으로부터 1초 후

        	for(int j = 0; j < lines.length; j++) {
        		if((term <= start[j].getTime()) || (end[j].getTime() < e)) continue;

        		cnt++;
        	}
        	
        	answer = Math.max(answer, cnt);
        }
        
        return answer;
    }
	
	public Date getMillisecond(Date date, int t) {
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MILLISECOND, t);

        return c.getTime();
	}
}
```



### :full_moon: ​풀이 방법

문제를 풀기에 앞서서 Date 클래스랑 Calendar 클래스에 대해 공부하고 풀었습니다. Date 형식 문자열을 다루는 연습을 많이 해야겠습니다.

 

맨 처음에는 무식하게 배열에다가 cnt를 더해주는 식으로 하려고 했는데 이러면 카카오 문제가 아니겠져;; 그래서 좀 찾아보고 풀어씀니다 ㅎ.ㅎ

이 문제에서는 년-월-일 은 모두 동일하기 때문에 그냥 "HH:mm:ss.sss" 형식으로 Date 객체를 만들었습니다. 

 

**초당 최대 처리량**을 구해야 하는데, 이는 결국 트래픽이 시작하고 끝나는 부분에서만 값이 바뀌겠지용. 고로 끝나는 시간부터 1초동안에 존재하는 트래픽의 양을 구하는 식으로 답을 찾아줄 수 있습니다.

 

그 전에 먼저 lines 배열을 갖고 start, end 라는 Date 형식의 배열을 만들어 줍시다. 

그리고 end[i] ~ 1초 동안에 들어가 잇는 트래픽의 양을 구해줍시다.

**e**(end[i]) ~ **term**(end[i] + 1초) 범위 안에 해당되는 트래픽들을 어떻게 구하냐?!

만약 시작 시간이 term보다 크거나, 끝나는 시간이 e 보다 작으면 해당이 안되는 것이겠지영.

요렇게 cnt를 세주고 가장 큰 값을 갱신해주면 됩니당!!



###  :full_moon: 후기 

카카오 넘모 어렵다....힁

감사합니다!!!