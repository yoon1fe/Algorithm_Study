import java.util.LinkedList;
import java.util.Queue;

public class 캐시 {
    static class Cache{
        int maxSize;
        Queue<String> data;

        Cache(int size){
            this.maxSize = size;
            data = new LinkedList<>();
        }

        public boolean isContain(String target){
            return data.contains(target);
        }

        public void insert(String city){
            data.offer(city);
        }

        public String delete(){
            return data.poll();
        }

        public String delete(String city){
            if(data.remove(city))
                return city;
            return "";
        }

        public boolean isFull(){
            if(data.size() == maxSize)
                return true;
            return false;
        }
    }

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if(cacheSize == 0)
            return cities.length * 5;

        Cache cache = new Cache(cacheSize);
        for(String city : cities){
            city = city.toLowerCase();
            if(cache.isContain(city)){
                answer += 1;
                cache.delete(city);
                cache.insert(city);
            } else {
                answer += 5;
                if(cache.isFull())
                    cache.delete();
                cache.insert(city);
            }
        }

        return answer;
    }

    public static void main(String[] args){
        캐시 c = new 캐시();
        System.out.println(c.solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
    }
}