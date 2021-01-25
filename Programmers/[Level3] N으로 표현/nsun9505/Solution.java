import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    public int solution(int N, int number) {
        ArrayList<Integer>[] nums = new ArrayList[9];
        HashSet<Integer> set = new HashSet<>();
        if(N == number)
            return 1;

        for(int i=0; i<nums.length; i++)
            nums[i] = new ArrayList<>();
        
        // N을 조합하는 방법 : N-1 + 1, N-2 + 2, N-3 + 3, N-4 + 4 ... N/2 + N/2
        nums[1].add(N);
        String ret = String.valueOf(N);
        for(int i=2; i<nums.length;i++){
            
            ret += String.valueOf(N);
            if(!set.contains(Integer.parseInt(ret))){
                nums[i].add(Integer.parseInt(ret));
                set.add(Integer.parseInt(ret));
            }
            
            if(ret.equals(String.valueOf(number)))
                return i;


            // 중복 제거 : set
            for(int j=1; j<=i/2; j++){
                for(int idx = 0; idx < nums[j].size(); idx++){
                    int num1 = nums[j].get(idx);
                    for(int k=0; k<nums[i-j].size(); k++){
                        int num2 = nums[i-j].get(k);
                        if(num1 * num2 > 0 && !set.contains(num1*num2)){
                            if(num1*num2 == number)
                                return i;
                            set.add(num1*num2);
                            nums[i].add(num1*num2);
                        }

                        if(num1 + num2 > 0 && !set.contains(num1+num2)){
                            if(num1+num2 == number)
                                return i;
                            set.add(num1+num2);
                            nums[i].add(num1+num2);
                        }
                        
                         if(num1 / num2 > 0 && !set.contains(num1/num2)){
                            if(num1/num2 == number)
                                return i;
                            set.add(num1/num2);
                            nums[i].add(num1/num2);
                        }
                        if(num2 / num1 > 0 && !set.contains(num2/num1)){
                            if(num2/num1 == number)
                                return i;
                            set.add(num2/num1);
                            nums[i].add(num2/num1);
                        }

                        if(num1 - num2 > 0 && !set.contains(num1-num2)){
                            if(num1-num2 == number)
                                return i;
                            set.add(num1-num2);
                            nums[i].add(num1-num2);
                        }
                        if(num2 - num1 > 0 && !set.contains(num2-num1)){
                            if(num2-num1 == number)
                                return i;
                            set.add(num2-num1);
                            nums[i].add(num2-num1);
                        }
                    }
                }
            }
        }


        return -1;
    }
}