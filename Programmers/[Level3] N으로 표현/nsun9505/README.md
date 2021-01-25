# [Level3] N으로 표현

## 분류
> DP

## 코드
```java
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
```

## 문제 풀이
N을 총 8번까지 사용할 수 있습니다.

그래서 N을 X번 사용할 경우 (X-1, 1), (X-2, 2) ... (X/2, X/2)으로 표현이 가능합니다.
   - N을 X-1번 사용한 것과 1번 사용한 것을 합치면 X번째에 사용한 것이 표현된다는 것입니다.
   - 그래서 X번째에 나올 수 있는 수를 알아내기 위해서 (X-1, 1), (X-2, 2), ... , (X/2, X/2)를 모두 검사하면 됩니다.
   - 모두 검사하면서 중복되는 숫자가 있을 수 있기 때문에 set으로 중복처리를 해줍니다.
   - 그리고 빼기는 음수가 나올 수 있고, 나눗셈은 0이 나올 수 있으므로 두 수를 바꿔가면서 계산합니다.

바텀-업이므로 i번째에 number에 맞는 수가 나오면 바로 i를 리턴하면 됩니다.

## 후기
DP스럽게 푼것인가?! 다른 풀이를 보면 로직은 엇비슷한것 같당

오늘도 파이팅!!!