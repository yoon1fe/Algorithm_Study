# [Level2] 베스트앨범 - JAVA

## 분류
> 해시
>
> 정렬

## 코드
```java
import java.util.*;

class Solution {
    static class Song implements Comparable<Song>{
        int idx;
        int play;

        Song(int idx, int play){
            this.idx = idx;
            this.play = play;
        }

        @Override
        public int compareTo(Song song) {
            if(this.play < song.play)           // 내림 차순
                return 1;
            else if(this.play == song.play){
                if(this.idx > song.idx)         // 오름 차순
                    return 1;
            }
            return -1;
        }
    }

    static class Genre implements Comparable<Genre>{
        int plays;
        PriorityQueue<Song> songs;

        public Genre(){
            this.plays = 0;
            this.songs = new PriorityQueue<>();
        }

        public void insert(Song song){
            this.plays += song.play;
            this.songs.offer(song);
        }

        @Override
        public int compareTo(Genre g) {
            if(this.plays < g.plays)        // 내림차순
                return 1;
            return -1;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        HashMap<String, Genre> genreMap = new HashMap<>();
        for(int i=0; i< genres.length; i++){
            Song song = new Song(i, plays[i]);
            if (!genreMap.containsKey(genres[i])) {
                Genre g = new Genre();
                genreMap.put(genres[i], g);
            }
            genreMap.get(genres[i]).insert(song);
        }

        Genre[] genresArr = new Genre[genreMap.size()];
        Set<String> keys = genreMap.keySet();
        int idx = 0;
        for(String key : keys)
            genresArr[idx++] = genreMap.get(key);
        Arrays.sort(genresArr);
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<genresArr.length; i++){
            int cnt = 0;
            PriorityQueue<Song> songs = genresArr[i].songs;
            while(!songs.isEmpty() && cnt < 2) {
                list.add(songs.poll().idx);
                cnt++;
            }
        }

        answer = new int[list.size()];
        for(int i=0; i<list.size(); i++)
            answer[i] = list.get(i);
        return answer;
    }
}
```

## 문제 풀이
일단 장르 별로 가장 많이 재생된 노래를 `두 개씩` 모아서 출력한다는 것을 주의해야 합니다.

문제에서 원하는대로 정렬을 합니다.

장르는 노래들의 재생 시간을 모두 더해서 내림차순으로 정렬하게끔 `Comparable`의 compareTo()를 구현했습니다.

노래는 재생된 노래 시간으로 오름차순하고 재생 시간이 같다면 고유 번호를 기준으로 오름차순 정렬하면 됩니다!
   - 노래는 장르의 우선순위 큐에 들어가서 정렬을 해줍니다.
   - 장르에 여러 개의 노래가 있어도 2개만 꺼내면 되기 때문에 전체를 볼 필요가 없습니다!
   - 정렬 방법은 장르와 동일하게 `Comparable`의 compareTo()를 구현하였습니다.

## 후기
오랜만에 풀어봅니다.

다시 힘내서 파이팅!!!

Comparable과 Comparator의 차이는 무엇인가..

공부할게 생겼슴둥!