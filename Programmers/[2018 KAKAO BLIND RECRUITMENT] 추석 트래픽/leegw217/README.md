# [2018 KAKAO BLIND RECRUITMENT] 추석 트래픽 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.Arrays;
import java.util.Comparator;
public class week17_추석트래픽 {
	int translate(String t) {
		String[] times = t.split(":");
    	int hour = Integer.parseInt(times[0]);
    	int minute = Integer.parseInt(times[1]);
    	int second = Math.round(Float.parseFloat(times[2])*1000);
    	int time = hour*60*60*1000 + minute*60*1000 + second;
    	return time;
	}
	
	public int solution(String[] lines) {
        int answer = 0;
        int[][] times = new int[lines.length][2];
        for(int i=0; i<lines.length; i++) {
        	times[i][1] = translate(lines[i].split(" ")[1]);
        	String sec = lines[i].split(" ")[2].replace("s", "");
        	times[i][0] = times[i][1] - (int)(Float.parseFloat(sec)*1000)+1;
        }
        
        Arrays.sort(times, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] - o2[1] == 0)
					return o1[0] - o2[0];
				return o1[1] - o2[1];
			}
		});
        
        for(int idx=0; idx<lines.length; idx++) {
    		int startCnt = 0;
    		int endCnt = 0;
        	int start = times[idx][0];
        	int end = times[idx][1];
        	for(int i=0; i<lines.length; i++) {
		// 시작지점 + 1초 내 검사
        		if(start <= times[i][1] && start+999 >= times[i][0])
        			startCnt++;
		// 종료지점 + 1초 내 검사
        		if(end <= times[i][1] && end+999 >= times[i][0])
        			endCnt++;
        	}
        	answer = Math.max(answer, startCnt);
        	answer = Math.max(answer, endCnt);
        }
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. 입력받은 문자열을 파싱해서 요청 별 시작시간과 종료시간을 구한다.
2. 시작시간과 종료시간을 초단위로 바꾸고 1000을 곱해 int형으로 만든다.
3. 종료시간을 기준으로 오름차순 정렬하고 같은 수가 있으면 시작시간을 기준으로 정렬한다.
4. 각 요청별로 (시작시간 + 1초) 범위 내에 다른 요청들이 있는지 검사하고 cnt 증가하고 answer 최신화
5. (종료시간 + 1초) 범위 내에 다른 요청들이 있는지 검사하고 cnt 증가하고 answer 최신화
6. 모든 요청을 다 검사하고 answer 출력

### :octocat: 후기

진짜 간만에 카카오 문제푸는데 진짜 증말루 어렵다;; 기술블로그 보고 시작시간하고 종료시간에 검사하는데
테케 3개 안돼서 그거때문에 몇시간을 날린건지 모르겠다.. int로 바꿔주고 정렬해주고 오만짓을 다해서 겨우 풀었다 ㅜㅜ
