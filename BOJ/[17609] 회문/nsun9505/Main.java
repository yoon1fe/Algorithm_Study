import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            String str = br.readLine();
            int ret = isPalindrome(0, str);
            if(ret == 0 || ret == 1) System.out.println(ret);
            else System.out.println(2);
        }
    }

    public static int isPalindrome(int depth, String str){
        if(depth >= 2)
            return 2;

        int len = str.length() / 2;
        if(str.length() % 2 != 0)
            len += 1;

        boolean flag = true;
        int cnt = 0;
        for(int i=0, j = str.length()-1; i<len;) {
            if (str.charAt(i) == str.charAt(j)) {
                i++;
                j--;
            } else if(str.charAt(i+1) == str.charAt(j) || str.charAt(i) == str.charAt(j-1)) {
                return Math.min(isPalindrome(depth + 1, str.substring(0, i) + str.substring(i+1)),
                        isPalindrome(depth + 1, str.substring(0, j) + str.substring(j+1))) + 1;
            } else
                return 2;
        }
        return cnt;
    }
}