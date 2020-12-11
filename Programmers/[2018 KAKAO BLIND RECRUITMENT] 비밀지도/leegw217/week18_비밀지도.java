import java.util.LinkedList;
public class week18_비밀지도 {
	LinkedList<Integer> makeBinary(int n, int num) {
		LinkedList<Integer> binary = new LinkedList<Integer>();
		int q = num;
		int r = 0;
		while(true) {
			if(q < 2) {
				binary.addFirst(q);
				break;
			}
			r = q % 2;
			binary.addFirst(r);
			q = q / 2;
		}
		int m = n - binary.size();
		for(int i=0; i<m; i++) binary.addFirst(0);
		return binary;
	}
	
	public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for(int i=0; i<n; i++) {
        	LinkedList<Integer> l1 = makeBinary(n, arr1[i]);
        	LinkedList<Integer> l2 = makeBinary(n, arr2[i]);
        	String s = "";
        	for(int j=0; j<n; j++) {
        		if(l1.get(j) == 0 && l2.get(j) == 0) s += " ";
        		else s += "#";
        	}
        	answer[i] = s;
        }        
        return answer;
    }
}