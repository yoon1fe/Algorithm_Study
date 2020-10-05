# [17140] 이차원 배열과 연산 - Java

###  :heavy_plus_sign: 분류

> 시뮬레이션
>

​

### :heavy_plus_sign: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int no, count;
		Node(int no, int cnt){
			this.no = no; this.count = cnt;
		}
		public int compareTo(Node o) {
			if(this.count == o.count) return this.no - o.no;
			return this.count - o.count;
		}
	}
	
	static int r, c, k, rowNum = 3, colNum = 3;
	static int[][] A = new int[101][101];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken()); c = Integer.parseInt(st.nextToken()); k = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= 3; j++) A[i][j] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(solve());
	}
	public static int solve() {
		for(int cnt = 0; cnt <= 100; cnt++) {
			if(A[r][c] == k) return cnt;
			if(rowNum >= colNum) {							// R 연산
				int newColNum = 0;
				for(int i = 1; i <= rowNum; i++) {
					Map<Integer, Integer> sortMap = new HashMap<>();
					List<Node> list = new ArrayList<>();
					for(int j = 1; j <= colNum; j++) {
						if(A[i][j] == 0) continue;
						if(!sortMap.containsKey(A[i][j])) sortMap.put(A[i][j], 1);
						else sortMap.replace(A[i][j], sortMap.get(A[i][j]) + 1);
						A[i][j] = 0;
					}

					for(int key : sortMap.keySet()) {
						list.add(new Node(key, sortMap.get(key)));
					}
					newColNum = Math.max(newColNum, list.size() * 2);
					Collections.sort(list);
					
					for(int j = 0, idx = 1; j < list.size(); j++, idx += 2) {
						if(idx > 100) break;
						A[i][idx] = list.get(j).no;
						A[i][idx + 1] = list.get(j).count;
					}
				}
				colNum = newColNum;
			}else {											// C 연산
				int newRowNum = 0;
				for(int i = 1; i <= colNum; i++) {
					Map<Integer, Integer> sortMap = new HashMap<>();
					List<Node> list = new ArrayList<>();
					for(int j = 1; j <= rowNum; j++) {
						if(A[j][i] == 0) continue;
						if(!sortMap.containsKey(A[j][i])) sortMap.put(A[j][i], 1);
						else sortMap.replace(A[j][i], sortMap.get(A[j][i]) + 1);
						A[j][i] = 0;
					}
					
					for(int key : sortMap.keySet()) {
						list.add(new Node(key, sortMap.get(key)));
					}
					newRowNum = Math.max(newRowNum, list.size() * 2);
					Collections.sort(list);
					
					for(int j = 0, idx = 1; j < list.size(); j++, idx += 2) {
						if(idx > 100) break;
						A[idx][i] = list.get(j).no;
						A[idx + 1][i] = list.get(j).count;
					}
				}
				rowNum = newRowNum;
			}
		}

		return -1;
	}
}
```



### :heavy_plus_sign: 풀이 방법

빡!구현입니당.

얼핏 보면 어려워 보이지만 하라는 대로 하면 됩니다 허허



A 배열을 `[101][101]` 크기 박아놓고 시작합시다.

그렇기 때문에 A 배열이 갱신되고난 시점에서의 row의 크기, col의 크기 변수를 챙겨놔야하겠죠~!

 

100번을 수행해도 해당하는 위치에 해당하는 수가 아니면 -1을 출력해야 하기 때문에 solve() 메소드에서 100번 반복문 수행합니다.

 

그 안에선 간단합니다. row의 크기와 col의 크기를 비교해서 R 연산이나 C 연산을 수행하면 되죵.

각각 행, 열을 보면서 연산하기 때문에 하나만 짜면 인덱스 스왑해서 갖다 박으면 됩니다.

 

간단히 R 연산만 봅시다.

각 수의 등장 횟수를 세기 위해 Map<숫자, 횟수> sortMap 변수를 두었습니다. 행을 탐색하면서 sortMap을 만들어줍니다. 이때 탐색하고나면 기존의 위치에 0으로 초기화를 해줍시다!

R 연산에서는 col의 개수가 변경되므로 newColNum 변수를 갖고 갱신해줍시다. 숫자-횟수 쌍이 새로 들어가니깐 sortMap.size() 의 두배 중 가장 큰 놈이 되겠네요.

 

이제 sortMap를 갖고 횟수에 대해 오름차순, 횟수가 같으면 숫자에 대해 오름차순으로 정렬을 해야 합니다..

Node 클래스를 하나 만들어서 고대로 List에 옮기고, 정렬해주었습니다... 다른 좋은 방법있으시면 댓글 달아주세용 ㅎ.ㅎ

 

마지막으로 A 배열에 새로운 값을 넣어주면 됩니다. 위에서 말했듯이 숫자-횟수 쌍이므로 idx를 2씩 증가시켜가며 쏙쏙 넣어줍시다. 그리고 만약 idx 가 100을 넘어가버리면 반복문 종료!



### :heavy_plus_sign: 후기

처음에 문제보고 살짝 쫄았는데 생각보다 꽤 빨리 풀었습니당 히히

감사합니다! 화이팅~!~!