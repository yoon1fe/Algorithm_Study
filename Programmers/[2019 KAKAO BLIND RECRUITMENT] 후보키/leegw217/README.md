# [2019 KAKAO BLIND RECRUITMENT] 후보키 - Java

###  :octocat: 분류

> 조합
>
> HashSet

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
public class week05_후보키 {
	List<List<Integer>> candidate = new ArrayList<>();
	void makeComb(int n, int begin, int cnt, int[] arr, List<Integer> result, String[][] relation) {
		if(cnt == n) {
			if(checkMinimality(result)) // 최소성 검사
				checkUniqueness(relation, result); // 유일성 검사
			return;
		}
		
		for(int i=begin; i<arr.length; i++) {
			result.add(arr[i]);
			makeComb(n, i+1, cnt+1, arr, result, relation);
			result.remove(result.indexOf(arr[i]));
		}
	}
	
	boolean checkMinimality(List<Integer> result) {
		for(int i=0; i<candidate.size(); i++) {
			if(candidate.get(i).size() == result.size()) break;
			boolean flag = true;
			for(int j=0; j<candidate.get(i).size(); j++)
				if(!result.contains(candidate.get(i).get(j))) {
					flag = false;
					break;
				}
			if(flag) return false;
		}
		return true;
	}
	
	void checkUniqueness(String[][] relation, List<Integer> result) {
		HashSet<String> set = new HashSet<String>();
		for(int j=0; j<relation.length; j++) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<result.size(); i++)
				sb.append(relation[j][result.get(i)]);
			set.add(sb.toString());
		}
		if(set.size() == relation.length) {
			List<Integer> temp = new ArrayList(result);
			candidate.add(temp);
		}
	}

	public int solution(String[][] relation) {
        int[] arr = new int[relation[0].length];
        for(int i=0; i<relation[0].length; i++) arr[i] = i;
        for(int i=1; i<=relation[0].length; i++) {
        	List<Integer> result = new ArrayList<Integer>();
        	makeComb(i, 0, 0, arr, result, relation);
        }
        return candidate.size();
    }
}
```

### :octocat: 풀이 방법

1. 1부터 컬럼 수만큼 조합을 만든다.
2. 만든 조합을 최소성 검사하고 유일성 검사해서 둘다 통과하면 후보키 목록에 넣는다.
3. 최소성 검사는 기존 후보키 목록 중 검사하는 조합의 길이보다 짧은 후보키가 조합내에
포함되어 있으면 넘어가고 없으면 유일성 검사한다.
4. 유일성 검사는 조합에 해당하는 컬럼내 로우값들을 합친 문자열을 set에 넣는다.
중복되는게 있으면 set의 크기와 row의 길이가 다르기 때문에 넘어가고 같으면 후보키 목록에 넣는다.

### :octocat: 후기

예전에 파이썬으로 풀었을 때도 그냥 조합만들어서 일일이 다 비교해보니까 돌아가서 시간 생각 안하고
그냥 짰다. 만약 코테에 비슷한 문제가 나오면 빨리 풀고 넘겨야함!
