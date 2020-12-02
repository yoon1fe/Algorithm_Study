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
