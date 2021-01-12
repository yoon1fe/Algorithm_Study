import java.util.Scanner;
public class week21_토너먼트 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int A = sc.nextInt();
		int B = sc.nextInt();
		int round = 1;
		if(A > B) {
			int temp = A;
			A = B;
			B = temp;
		}
		while(true) {
			if(A%2!=0 && B%2==0 && A<B && B-A==1) {
				System.out.println(round);
				break;
			}
			A = (A%2 == 0) ? A/2 : A/2+1;
			B = (B%2 == 0) ? B/2 : B/2+1;
			round++;
		}
	}
}