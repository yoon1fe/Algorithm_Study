package Programmers;

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