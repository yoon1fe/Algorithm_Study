## [2018 KAKAO BLIND RECRUITMENT] 뉴스 클러스터링 - Java

###    :newspaper: ​분류

> 구현

​

###  :newspaper: 코드

```java
package Programmers;

import java.util.*;


class Solution {
	public int solution(String str1, String str2) {
        int intersection = 0, union = 0;
        
        // 대소문자 구별 X
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        Map<String, Integer> map1 = makeMap(str1);
        Map<String, Integer> map2 = makeMap(str2);

        // 합집합이 0인 경우 J(A, B) = 1
        if(map1.size() == 0 && map2.size() == 0) return (1<<16);
        
		for(String k : map1.keySet()) {
			if(map2.containsKey(k)) {
				intersection += Math.min(map1.get(k), map2.get(k));		// 교집합
				union += Math.max(map1.get(k), map2.get(k));			// 합집합
				map2.remove(k);
			}else {
				union += map1.get(k);
			}
		}
		
		for(String k : map2.keySet()) {
			union += map2.get(k);
		}
		
        return (int)(intersection / (double)union * (1 << 16));
    }
	
	public Map<String, Integer> makeMap(String str) {
		Map<String, Integer> map = new HashMap<>();
		
		for(int i = 0; i < str.length() - 1; i++) {
			String temp = str.substring(i, i+2);
			// 숫자, 특수문자 제외
			if(97 > temp.charAt(0) || temp.charAt(0) > 122 || temp.charAt(1) < 97 || temp.charAt(1) > 122) continue;
			
			if(map.containsKey(temp)) map.replace(temp, map.get(temp) + 1);
			else map.put(temp, 1);
		}
		
		return map;
	}
	
	public static void main(String[] args) {
		Solution s = new Solution();
		String str1 = "E=M*C^2";
		String str2 = "shake hands";
		
		System.out.println(s.solution(str1, str2));
		
	}
}
```



### :newspaper: ​풀이 방법

문자열 두 개가 주어졌을 때 두 문자열의 "자카드 유사도"를 구하는 문제입니다. 

Map을 사용해서 간단하게 구현했습니당 ㅎ.ㅎ



 

먼저 자카드 유사도는 문제를 읽고 이해했으리라 믿습미다.

그럼 결국 **n(A∩B)/n(\**A∪B)\**** 을 구하면 되는 것이죵. 이때 중복을 허용한다고 하니 (문자열 : 문자열의 개수)로 매핑되는 Map을 만들었습니다. 그 전에 대소문자 구분을 하지 않는다고 해서 모두 소문자로 바꾸고 시작해씀니당 ㅎㅎ. 이렇게 하면 아스키 코드 값으로 숫자나 특수 문자를 가뿐히 제낄 수가 있습죠.

 

이렇게 입력받은 문자열 두개로 Map 두개를 만들면 걍 끝입니다. 먼저 map1의 key 값을 갖고 돌면서 map2에도 포함이 되어 있따? 그럼 map1.get(key)와 map2.get(key) 중 작은 값이 교집합의 개수가 됩니다. 그리고 합집합에는 둘 중 큰 값이 들어가겠쥬. 그리고 map2의 key 값을 돌릴 때 쪼금이라도 덜 봐주기 위해 중복된 key는 map2 에서 제거해주었습니다. 이러면 map2 에 남은 친구들은 모두 합집합으로 들어가겠죠. 

 

교집합 / 합집합 x 2의 16제곱 을 구해주면 끝입니다.!!

다만 4번 테케처럼 문자열이 하나도 안만들어지는 경우 분모(합집합)가 0이 되므로 이 때는 J(A, B)의 값이 1이 되는 것을 유의하십셔.

 



###  :newspaper: 후기 

어제 추석 트래픽 문제한테 한 대 맞아서 바짞 쫄앗는데 생각보다 수월했습니당 ㅎ.ㅎ

감사합니다!!