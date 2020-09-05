public class week04_키패드누르기 {
	public String solution(int[] numbers, String hand) {
        int[][] phone = {{3,1},{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
        int[] lHand = {3, 0};
        int[] rHand = {3, 2};
		StringBuilder sb = new StringBuilder();
        for(int i=0; i<numbers.length; i++) {
        	if(numbers[i]==1 || numbers[i]==4 || numbers[i]==7) {
        		sb.append("L");
        		lHand = phone[numbers[i]];
        	} else if(numbers[i]==3||numbers[i]==6||numbers[i]==9) {
        		sb.append("R");
        		rHand = phone[numbers[i]];
        	} else {
        		int leftLen = Math.abs(lHand[0] - phone[numbers[i]][0]) + Math.abs(lHand[1] - phone[numbers[i]][1]);
        		int rightLen = Math.abs(rHand[0] - phone[numbers[i]][0]) + Math.abs(rHand[1] - phone[numbers[i]][1]);
        		if(leftLen < rightLen) {
        			sb.append("L");
            		lHand = phone[numbers[i]];
        		} else if(leftLen > rightLen) {
        			sb.append("R");
            		rHand = phone[numbers[i]];
        		} else {
        			if(hand.equals("left")) {
        				sb.append("L");
                		lHand = phone[numbers[i]];
        			} else {
        				sb.append("R");
                		rHand = phone[numbers[i]];
        			}
        		}
        	}
        }
        return sb.toString();
    }
}