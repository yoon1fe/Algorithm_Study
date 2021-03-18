# [2019 KAKAO BLIND RECRUITMENT] 무지의 먹방

## 분류
> 정렬
>
> 시뮬레이션

## 코드
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solution {

    Comparator<Food> sortByIdx = new Comparator<Food>() {
        @Override
        public int compare(Food o1, Food o2) {
            return o1.idx - o2.idx;
        }
    };

    Comparator<Food> sortByTime = new Comparator<Food>() {
        @Override
        public int compare(Food o1, Food o2) {
            return o1.time - o2.time;
        }
    };

    public int solution(int[] food_times, long k) {
        int answer = -1;

        int idx = 1;
        ArrayList<Food> foods = new ArrayList<>();
        for(int i=0; i<food_times.length; i++)
            foods.add(new Food(idx++, food_times[i]));

        // 먹는 시간순으로 정렬
        Collections.sort(foods, sortByTime);

        long size = foods.size();
        long prev = 0;
        for(int i=0; i<foods.size(); i++, size--){
            Food food = foods.get(i);
            long sec = food.time - prev;
            long curTime = sec * size;
            prev = food.time;

            if(curTime <= k)
                k -= curTime;
            else{
                k %= size;
                foods.subList(i, foods.size()).sort(sortByIdx);
                answer = foods.get(i + (int)k).idx;
                break;
            }
        }

        return answer;
    }

    static class Food{
        int idx;
        int time;

        public Food(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
}
```

## 문제 풀이
이전 코드랑 다른겨 없습니다! 정렬해서 푸는 방법이 생각날까 말까 하다가 너무 시간을 끄는거 같아서 카카오 해설보고 해결했습니다.(나중에 풀어볼 문제 추가!)

먼저 먹는 시간 순으로 음식들을 오름차순 정렬합니다.
   - 오름차순으로 정렬하면 음식을 먹는데 시간을 한 번에 해결이 가능합니다.

한 번에 해결이 가능하다는 것은 예를 들어, 3개의 음식이 있고 각각 먹는데 걸리는 시간이 3(1), 2(2), 1(3)이라고 해보겠습니다.
   - 먼저 오름차순으로 정렬합니다! 그러면 1(3), 2(2), 3(1)으로 정렬이 됩니다. 괄호안에는 인덱스 번호입니다.
   - 3번 음식을 먹는데 걸리는 시간이 1초입니다. 즉, 1초니깐 전체를 한 번씩만 먹으면 3번은 다 먹을 수 있는 것입니다!
   - 그러니깐 1 * 3(음식 수)의 시간이 지나면 3번 음식은 다 먹었다는 것은 자명한 사실입니다.
   - 그러면 2번째 음식은 2초가 걸리지만, 이미 앞에서 1초가 걸리는 음식을 먹었기 때문에 2초가 걸리던 2번 음식은 1초가 걸리게 될 것입니다.
   - 이를 위해서 이전에 먹었던 음식의 시간인 1초를 2번째 음식을 먹는데 걸리는 시간에서 빼주면 되겠져!
   - 그러면 그 뺀 시간! 2 - 1 = 1초입니다. 
   - 그러면 전체적으로 1초씩 지나면 2번 음식도 다 먹을 수 있습니다!
   - 전체 사이즈 * 1초 = 2 * 1 = 2이므로 2초가 지나면 2번 음식도 다 먹을 수 있습니다!
   - 전체 사이즈가 2인 이유는 이미 1초가 걸리던 3번 음식을 다 먹었기 때문에 포함시키지 않기 위해서 줄인 것입니다.

그러면 왜 정렬을 해서 푸는지를 알 수 있게 되었습니다.

이제 로직은 다음과 같습니다.

1. i번째 음식을 먹기 위해 걸리는 시간 = (현재 음식을 먹는 시간 - 이전에 먹었던 음식들 중 먹는데 오래걸리는 시간) * 남은 음식의 수
   - 이전에 먹었던 음식들 중 먹는데 오래걸리는 시간은 정렬되어 있으므로 바로 이전 음식을 먹는데 걸리는 시간이 됩니다.
   - 따로 구하지 않아도 돕니다.
   - 그리고 현재 음식을 먹는 시간은 다음 음식을 먹는 시간에서 빼줘야 하므로 이 값을 이전에 먹었던 음식들 중 먹는데 오래걸리는 시간에 저장하면 됩니다.
1. 1번에서 현재 음식을 먹는데 걸리는 시간이 현재 남은 시간!(K)보다 작거나 같다면 충분히 그 음식을 다 먹을 수 있다는 것입니다.
   - i번째 음식을 먹기 위해 걸린 시간을 K에서 빼줘서 시간이 지났음을 표현합니다.
1. 만약에 i번째 음식을 먹는데 걸리는 시간이 현재 남은 시간보다 크다면!
   - 지금까지 먹은 음식들은 모두 제외하고 남은 음식들의 번호에 따라 정렬을 합니다.
   - 왜냐하면 이전까지는 시간에 따라 다 먹을 수 있냐 없냐로 계산했다면, 이제는 남은 시간에 현재 음식을 다 먹을 수 없으므로 정확하게
   - 몇 번 음식을 먹는지 알아내기 위해 번호에 따라 정렬한 것입니다.
   - 즉, 남은 시간이 2초이고, 남은 음식이 각각 {3, 4, 5}초가 남았다면 3초 * 3개(남은 음식의 수)= 9초이므로 2초만에 어떤 음식도 다 먹지 못하죠!
   - 그래서 K초가 흘렀을 때 어디에 위치해 있느냐만 구하면 정답을 알 수 있는 것입니다.
   - 번호에 따라 정렬했다면, 현재 남은 시간을 현재 남은 음식의 수로 나눴을 때 나머지번째에 있는 음식이 장애 복구 후 먹기 시작해야 할 음식이 됩니다.
1. 전체를 다 먹을 수 있다면 3번으로 들어가는 일이 없기 때문에 answer는 -1에서 값이 변하지 않으므로 다 먹을 경우에 대한 답도 구할 수 있습니다.

## 후기
와우 어려워유