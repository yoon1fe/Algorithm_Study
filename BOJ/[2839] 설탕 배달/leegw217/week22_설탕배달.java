import java.util.Scanner;
public class week22_설탕배달 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int answer = 0;
		int num = sc.nextInt();
		int q = num/5;
		for(int i=q; i>=0; i--) {
			int r = num - i*5;
			if(r%3 == 0) {
				answer += i;
				answer += r/3;
				break;
			}
		}
		if(answer != 0) System.out.println(answer);
		else System.out.println(-1);
	}
}