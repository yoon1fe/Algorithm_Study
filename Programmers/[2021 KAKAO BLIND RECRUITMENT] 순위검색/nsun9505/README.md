# [2021 KAKAO BLIND RECRUITMENT] 순위검색 - JAVA

## 분류
> 구현
>
> 이분탐색
>
> Hash

## 코드
```java
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
```

## 문제 풀이
먼저 각 info에 대해서 만들 수 있는 쿼리를 모두 만듭니다.

예를 들어 `java backend junior pizza 150`를 검색할 수 있는 모든 쿼리를 만들어 보겠습니다.
   - 먼저 info 그대로인 `java and backend and junior and pizza 150`입니다.
   - 이제 여기에 언어, 직군, 경력, 음식에 `-`를 넣었을 경우를 생각해야 합니다.
   - `- and backend and junior and pizza 150`
   - `java and - and junior and pizza 150`
   - `java and backend and - and pizza 150`
   - `java and backend and junior and - 150`
   - ...
   - `- and - and - and - 150`까지 만들 수 있을 것입니다.
   - 그래서 모든 info에 대해서 "-"가 들어갈 경우도 생각해줘야 합니다.
   - 이를 `addQuery` 메서드로 구현을 했습니다.

그리고 info에 의해 만들어진 쿼리들을 hashMap으로 저장했습니다.

hashMap에서 key부분의 비교시 참조값을 비교하는대신 Query inner class에 equals, hashCode를 오버라이딩해서 참조값이 아닌 가지고 있는 값을 비교하는 동등성 비교로 바꿔서 했습니다.
   - 굳이 Query 클래스 안 만들고 hashMap의 key부분을 String으로 해도 충분할 것 같습니다.

HashMap의 value 부분은 ArrayList로 해서 해당 쿼리에 속하는 점수들을 추가하였습니다.
   - 그리고 info에 대해서 모든 쿼리를 만든 후에 HashMap에 존재하는 모든 ArrayList를 오름차순 정렬하였습니다.
   - 그래서 query 배열의 각 쿼리에 대해서 hashMap을 조회하고, ArrayList를 가져왔을 때 정렬이 되어있으므로 lower bound로 해당 점수가 나오는 가장 왼쪽 위치를 찾습니다.
   - 가장 왼쪽 위치는 해당 점수를 포함하지 않을 수 있지만, 해당 점수의 이상인 값의 위치가 됩니다.
   - 가장 왼쪽 위치를 리스트 길이에서 뺀 결과를 답으로 출력할 ans에 추가하면 됩니다.

query[i]에 해당하는 결과가 없을 경우는 ans에 0을 추가하고 다음 쿼리로 넘어가면 됩니다.

## 후기
후아 카카오 제발 붙고 싶다.

### 문자열을 HashMap의 Key로 준 경우
![image](https://user-images.githubusercontent.com/43994964/106551822-666f5880-6559-11eb-9ba6-c147fe26a5e4.png)

### Query 객체를 HashMap의 key로 준 경우
![image](https://user-images.githubusercontent.com/43994964/106551851-7a1abf00-6559-11eb-8955-c6c50bb07501.png)

스트링이 연산을 덜 해서 더 빠를 줄 알았는데..?
    - 더하는 연산때문에 느린건가?
    - 내가 잘못 짠건가ㅠ

<details>
<summary> HashMap의 Key를 String으로 설정한 경우 </summary>
<div markdown="1">

```java
import java.util.*;

public class Solution {
    static HashMap<String, ArrayList<Integer>> queries;
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

        Set<String> keys = queries.keySet();
        for(String key : keys){
            ArrayList<Integer> list = queries.get(key);
            Collections.sort(list);
        }

        for(int i=0; i<query.length; i++){
            StringTokenizer st = new StringTokenizer(query[i], " ");
            int queryEnd = query[i].lastIndexOf(" ");

            String curQuery = query[i].substring(0, queryEnd);
            int score = Integer.parseInt(query[i].substring(queryEnd+1));

            if(!queries.containsKey(curQuery)){
                ans.add(0);
                continue;
            }

            ArrayList<Integer> list = queries.get(curQuery);
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
                        ArrayList<Integer> list = null;
                        String query = l + " and " + j + " and " + c + " and " + f;
                        if(!queries.containsKey(query))
                            queries.put(query, new ArrayList<>());
                        list = queries.get(query);
                        list.add(score);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ans = s.solution(new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"});
        for (int num : ans)
            System.out.print(num + " ");
    }
}
```
</div>
</details>