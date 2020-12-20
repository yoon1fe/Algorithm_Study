# [2019 카카오 개발자 겨울 인턴십] 불량 사용자 - JAVA

## 분류
> 구현
>
> 문자열

## 코드
```java
package 불량사용자;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Solution {
	static List<List<String>> banlist;
	static boolean[] isUsed;
	static int ans = 0;
	public int solution(String[] user_id, String[] banned_id) {
		banlist = new ArrayList<>();
        for(int i=0; i<banned_id.length; i++)
        	banned_id[i] = banned_id[i].replace('*', '.');
        
        List<String> list = new ArrayList<>();
        for(int i=0; i<user_id.length; i++) 
        	if(isBannedId(user_id[i], banned_id))
        		list.add(user_id[i]);
        isUsed = new boolean[list.size()];
        DFS(list, banned_id, 0, new ArrayList<String>()); 
        return banlist.size();
    }
	
	public static boolean isBannedId(String id, String[] banned_id) {
		for(int i=0; i<banned_id.length; i++)
			if(Pattern.matches(banned_id[i], id))
				return true;
		return false;
	}
	
	public static void DFS(List<String> list, String[] banned_id, int idx, List<String> ids) {
		if(idx >= banned_id.length) {
			if(isDuplicated(ids))
				return;
			for(String id : ids)
				System.out.print(id + " ");
			System.out.println();
			banlist.add(new ArrayList<>(ids));
			return;
		}
		
		for(int i=0; i<list.size();i++) {
			if(!isUsed[i] && Pattern.matches(banned_id[idx], list.get(i))) {
				isUsed[i] = true;
				ids.add(list.get(i));
				DFS(list, banned_id, idx+1, ids);
				ids.remove(ids.size()-1);
				isUsed[i] = false;
			}
		}
	}
	
	public static boolean isDuplicated(List<String> list) {
		for(List<String> ban : banlist) {
			int cnt = 0;
			for(int i=0; i<ban.size(); i++) {
				for(int j=0; j<list.size(); j++) {
					if(ban.get(i).equals(list.get(j))) {
						cnt++;
						break;
					}
				}
			}
			if(cnt == ban.size())
				return true;
		}
		return false;
	}
}
```

## 문제 풀이
1. 제재 아이디에서 *를 .으로 변경해서 정규표현식에 사용합니다.
   - banned_id[i].replace('*', '.')으로 바꿔줍니다.
   - 그리고 Pattern.matches(banned_id[i], user_id[j])를 통해서 user_id[j]가 제재 목록에 포함되는지 확인하고 리스트에 저장합니다.
1. 제제 아이디를 저장한 list에서 불량 사용자 목록 순서에 맞도록 리스트를 생성합니다.
1. 생성한 리스트가 중복되지 않는다면 banlist에 추가하고, 중복된다면 추가하지 않는다.

## 후기
중복을 어떻게 쉽게 찾을 수 있을까 고민했지만

가장 심플하게 풀었습니다..

아쉽!