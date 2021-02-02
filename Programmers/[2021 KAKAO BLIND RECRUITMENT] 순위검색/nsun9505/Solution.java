import java.util.*;

public class Solution {
    static HashMap<Query, ArrayList<Integer>> queries;
    public int[] solution(String[] info, String[] query) {
        int[] answer = {};
        queries = new HashMap<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0; i<info.length; i++){
            StringTokenizer st = new StringTokenizer(info[i]);
            String lang = st.nextToken();
            String job = st.nextToken();
            String career = st.nextToken();
            String food = st.nextToken();
            int score = Integer.parseInt(st.nextToken());
            addQuery(lang, job, career, food, score);
        }

        Set<Query> keys = queries.keySet();
        for(Query key : keys){
            ArrayList<Integer> list = queries.get(key);
            Collections.sort(list);
        }

        for(int i=0; i<query.length; i++){
            StringTokenizer st = new StringTokenizer(query[i], " ");
            String lang = st.nextToken(); st.nextToken();
            String job = st.nextToken(); st.nextToken();
            String career = st.nextToken(); st.nextToken();
            String food = st.nextToken();
            int score = Integer.parseInt(st.nextToken());

            Query q = new Query(lang, job, career, food);
            if(!queries.containsKey(q)){
                ans.add(0);
                continue;
            }

            ArrayList<Integer> list = queries.get(q);
            int left = 0;
            int right = list.size();
            while(left < right){
                int mid = (left + right) / 2;
                if(list.get(mid) < score)
                    left = mid + 1;
                else
                    right = mid;
            }

            int ret = list.size() - left;
            ans.add(ret);
        }

        answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++)
            answer[i] = ans.get(i);

        return answer;
    }

    static void addQuery(String lang, String job, String career, String food, int score){
        String[] langs = {lang, "-"};
        String[] jobs = {job, "-"};
        String[] careers = {career, "-"};
        String[] foods = {food, "-"};

        for(String l : langs){
            for(String j : jobs){
                for(String c : careers){
                    for(String f : foods){
                        Query newQuery = new Query(l, j, c, f);
                        ArrayList<Integer> list = null;
                        if(!queries.containsKey(newQuery))
                            queries.put(newQuery, new ArrayList<>());
                        list = queries.get(newQuery);
                        list.add(score);
                    }
                }
            }
        }
    }

    static class Query{
        String language;
        String job;
        String career;
        String food;

        public Query(String language, String job, String career, String food) {
            this.language = language;
            this.job = job;
            this.career = career;
            this.food = food;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Query query = (Query) o;
            return Objects.equals(language, query.language) &&
                    Objects.equals(job, query.job) &&
                    Objects.equals(career, query.career) &&
                    Objects.equals(food, query.food);
        }

        @Override
        public int hashCode() {
            return Objects.hash(language, job, career, food);
        }
    }
}