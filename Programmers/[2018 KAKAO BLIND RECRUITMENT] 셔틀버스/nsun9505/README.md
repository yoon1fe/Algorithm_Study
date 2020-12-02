# 셔틀버스 - JAVA

## 분류
> 구현

## 코드
```java
package Programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 셔틀버스 {
    static class Shuttle{
        int arriveTime;
        int maxSize;
        ArrayList<Integer> passengers;

        Shuttle(int maxSize, int arriveTime){
            this.maxSize = maxSize;
            this.arriveTime = arriveTime;
            passengers = new ArrayList<>();
        }
    }

    public static ArrayList<Integer> timelist = new ArrayList<>();
    public static Shuttle[] shuttles;
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "09:00";
        shuttles = new Shuttle[n];

        setTimeList(timetable);
        setShuttles(n, t, m);

        Collections.sort(timelist);

        for(int i=0; i<timelist.size(); i++){
            for(int j=0; j<shuttles.length; j++){
                if(timelist.get(i) <= shuttles[j].arriveTime && shuttles[j].passengers.size() < shuttles[j].maxSize) {
                    shuttles[j].passengers.add(timelist.get(i));
                    break;
                }
            }
        }

        Shuttle lastShuttle = shuttles[shuttles.length-1];

        if(lastShuttle.passengers.size() < m){
            answer = secToString(lastShuttle.arriveTime);
        } else {
            int lastPassenger = lastShuttle.passengers.get(m-1);
            answer = secToString(lastPassenger - 60);
        }

        return answer;
    }

    public static void setShuttles(int n, int t, int m){
        int sec = 9 * 3600;
        for(int i=0; i<n; i++)
            shuttles[i] = new Shuttle(m, sec + (i * (60 * t)));
    }

    public static int transHour(int sec){
        return sec / 3600;
    }

    public static int transMin(int sec){
        return (sec%3600)/60;
    }

    public static String secToString(int sec){
        String time = "";
        int h = transHour(sec);
        if(h < 10)
            time = "0" + String.valueOf(h);
        else
            time = String.valueOf(h);
        time += ":";

        int m = transMin(sec);
        if(m < 10)
            time += "0" + String.valueOf(m);
        else
            time += String.valueOf(m);

        return time;
    }

    public static void setTimeList(String[] timetable){
        for(int i=0; i<timetable.length; i++){
            StringTokenizer st = new StringTokenizer(timetable[i], ":");
            int sec = 3600 * Integer.parseInt(st.nextToken());
            timelist.add(sec + (60 * Integer.parseInt(st.nextToken())));
        }
    }

    public static void main(String[] args) {
        셔틀버스 s = new 셔틀버스();
        System.out.println(s.solution(2,10,2, new String[]{"09:10", "09:09", "08:00"}));
    }
}
```

## 문제 풀이
먼저 시간을 초단위로 변경합니다.
- 그리고 나중에 초를 시간으로 변경할 때는 secToString으로 해서 HH:mm 형식을 지켜줍니다.

먼저 셔틀버스의 배차시간? 도착시간들을 초 단위로 세팅합니다. 
- 또한, 크루들의 탑승 시간을 초 단위로 변경합니다.
- 크루들의 도착시간은 정렬되어 있지 않으므로 정렬해서 로직을 구현했습니다.

크루들의 도착 시간을 기반으로 셔틀버스에 탈 수 있는지 조사합니다.
- 셔틀버스 도착시간 보다 크루 도착시간이 작고, 해당 셔틀버스에 자리가 남아 있다면 탑승한 것으로 간주합니다.

문제에서는 가장 늦게 나가는 것이므로 마지막 셔틀버스를 조사하면 됩니다.
1. 마지막 셔틀 버스에서 아직 자리가 남는다면 해당 버스의 도착 시간을 답으로 정하면 됩니다.
1. 마지막 셔틀 버스에 남는 자리가 없다면 탑승한 크루들 중 가장 늦게 탑승한 크루의 도착시간 - 1분을 하면 됩니다. 그래야 가장 늦게 나가므로!

## 후기
로직은 그렇게 어려운 것은 아닌데 시간을 어떻게 나타낼 것인지랑 마지막 시간을 알아내는 데 시간을 좀 쓴거 같음!

카카오 문제는 시간을 조작(?) 가공(?)하는 문제들이 많이 나오는건가..?