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

        answer = list.toArray(new int[list.size()]);
        return answer;
    }
}