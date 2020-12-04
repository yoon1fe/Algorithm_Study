public class 비밀지도 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for(int i=0; i<n; i++)
            answer[i] = decoder(arr1[i] | arr2[i], n);
        return answer;
    }

    public String decoder(int num, int n){
        int compBit = (int)Math.pow(2, n-1);
        String ret = "";
        while(compBit != 0){
            if((num & compBit) == 0) ret += " ";
            else ret += "#";
            compBit >>= 1;
        }
        return ret;
    }
}