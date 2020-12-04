# [2018 KAKAO BLIND RECRUITMENT] 캐시 - JAVA

## 분류
> 구현

## 코드
```java
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
```

## 문제 풀이
핵심은 캐시!

캐시는 Queue로 구현하였습니다.
- 가장 최근에 참조된 것은 rear에 있습니다.
- 가장 오랫동안 참조되지 않은 것은 front에 있습니다.
- Full인 상태에서 데이터를 지울 때는 front에 있는 것을 지우고, rear에 추가하면 됩니다.

1. 캐시에 target이 있는 경우
- target을 지우고, target을 다시 추가하면 target은 가장 최근에 참조되었다는 표시로 rear에 위치하게 됩니다.
- 이 경우 hit이므로 answer에 +1

1. 캐시에 target이 없는 경우
- 캐시가 Full인 경우 가장 오랫동안 참조되지 않은 데이터(front)를 지우고 rear에 추가합니다.
- 캐시가 Full이 아닌 경우 단지 rear에 추가만 하는 것으로 끝납니다.
- 이 경우 miss이므로 answer에 +5

## 후기
OS에서 배운 페이지 교체 알고리즘 LRU가 도움이 되었습니다~