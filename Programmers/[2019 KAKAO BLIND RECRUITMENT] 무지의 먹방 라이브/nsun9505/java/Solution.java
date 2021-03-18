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

        Collections.sort(foods, sortByTime);

        long size = foods.size();
        long prev = 0;
        for(int i=0; i<foods.size(); i++, size--){
            Food food = foods.get(i);
            long sec = food.time - prev;
            prev = sec;

            long curTime = sec * size;
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