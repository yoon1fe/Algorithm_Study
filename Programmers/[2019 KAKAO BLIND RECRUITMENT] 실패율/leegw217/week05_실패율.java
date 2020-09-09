package week05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class week05_실패율 {
	public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] peopleInStage = new int[N+1];
        int[] reach = new int[N];
        ArrayList<double[]> failureLate = new ArrayList<double[]>();
        for(int i=0; i<stages.length; i++)
        	peopleInStage[stages[i]-1]++;
        reach[0] = stages.length;
        for(int i=1; i<N; i++)
        	reach[i] = reach[i-1] - peopleInStage[i-1];
        for(int i=0; i<N; i++)
        	failureLate.add(new double[] {(double)peopleInStage[i]/(double)reach[i], i});
        Collections.sort(failureLate, new Comparator<double[]>() {
			@Override
			public int compare(double[] o1, double[] o2) {
				if(o2[0] - o1[0] > 0) return 1;
				else if(o2[0] - o1[0] < 0) return -1;
				else return 0;
			}
		});
        for(int i=0; i<N; i++) answer[i] = (int) failureLate.get(i)[1]+1;
        return answer;
    }
}