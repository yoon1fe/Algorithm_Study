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
