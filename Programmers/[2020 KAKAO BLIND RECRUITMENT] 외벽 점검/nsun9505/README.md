# ## [2020 KAKAO BLIND RECRUITMENT] 외벽 점검 - C++

## 분류
> 완전탐색

## 코드
```c++
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool isUsed[10];
vector<int> distances;
int distSize;


bool check(vector<int> dist, vector<int> weak) {
	int cnt = weak.size();
	vector<bool> isOk;
	for (int i = 0; i < weak.size(); i++)
		isOk.push_back(false);

	for (int d : dist) {
		for (int i = 0; i < weak.size(); i++) {
			if (isOk[i])
				continue;

			int maxRange = d + weak[i];
			for (int j = i; j < weak.size(); j++) {
				if (maxRange < weak[j])
					break;
				cnt--;
				isOk[j] = true;
			}

			if (cnt == 0)
				return true;
			break;
		}
	}
	return false;
}

void getPermutation(int depth, int maxDepth, vector<int> perm, vector<vector<int>>& list) {
	if (depth == maxDepth) {
		list.push_back(perm);
		return;
	}

	for (int i = 0; i < distances.size(); i++) {
		if (!isUsed[i]) {
			isUsed[i] = true;
			perm.push_back(distances[i]);
			getPermutation(depth + 1, maxDepth, perm, list);
			perm.pop_back();
			isUsed[i] = false;
		}
	}
}

int solution(int n, vector<int> weak, vector<int> dist) {
	distances = dist;
	distSize = dist.size();

	vector<int> perm;
	for (int num = 1; num <= dist.size(); num++) {
		vector<vector<int>> list;
		getPermutation(0, num, perm, list);

		for (auto cur : list) {
			vector<int> tmp(weak);
			for (int i = 0; i < weak.size(); i++) {
				if(check(cur, tmp))
					return num;
				int back = tmp[0];
				tmp.erase(tmp.begin());
				tmp.push_back(back + n);
			}
		}
	}
	
	return -1;
}

int main(void) {
	int N = 12;
	vector<int> weak;
	weak.push_back(1);
	weak.push_back(3);
	weak.push_back(4);
	weak.push_back(9);
	weak.push_back(10);

	vector<int> dist;
	dist.push_back(3);
	dist.push_back(5);
	dist.push_back(7);

	int ret = solution(N, weak, dist);
	cout << ret;

	return 0;
}
```

## 문제 풀이
> [카카오 해설 참조](https://tech.kakao.com/2019/10/02/kakao-blind-recruitment-2020-round1/)

dist, weak의 최대 크기가 각 8, 15라서 완전탐색으로 가능하다.
- dist에 존재하는 친구를 1, 2, 3, ... 명만 쓸 경우에 대해서 weak를 완전탐색하면 된다.

weak에 대해서 완전탐색 할 때는 해설에서와 같은 기발한 아이디어를 사용했다.
- weak를 하나의 긴 직선으로 보는 것이다.
- 예를 들어weak가 1, 3, 4, 9, 10이라고 주어지게 되면
- 시작위치를 바꿔 가면서 내가 선택한 dist의 수만큼 완점탐색을 수행한다.
- 시작위치를 바꿔야 하는 이유는 친구의 배정 방식에 따라 1번에서 시작한 경우에 점검이 되지 않을 수 있지만 3번부터 시작했을 때 점검을 완료할 수도 있다.
- 즉, 시작점을 1,3,4,9,10 모두 다 해봐야 하는 것이다.
- 이렇게 하기 위해서 시작 지점을 1로 했을 때 완전탐색이 끝나면 3이 시작 위치가 되어야 하므로 1을 제거한 다음에 맨 끝에 1 + 12(12는 n을 뜻함)를 해주면 3에서 1로 갈때 반시계 방향이 아닌 시계방향으로 갔을 때의 거리가 나오게 된다.
- 그러면 오른쪽, 왼쪽 나눠서 생각할 필요 없이 하나의 방향으로만 점검하면 된다.

요약하자면
1. 친구의 배정 방식을 1명일 때의 가능한 모든 경우를 구한다.
   - 1명만 배정하고, dist가 3, 5, 7이라면
   - 각 3, 5, 7에 대해서 완전탐색을 수행
1. 친구를 배정한 방식에 따라서 weak 배열을 완전탐색 하는데 시작 위치를 변경해가면서 완전탐색을 돌려본다.
   - weak를 하나의 긴 직선으로 보고
   - k번째를 시작 지점으로 했을 때 k번째 부터 시계 방향으로 돌았을 때의 각 지점까지의 거리를 기록
1. 만약 해당 친구 배정 방식에 대해서 모든 취약 지점 점검이 가능하다면 해당 친구 배정 수를 리턴
1. 만약 점검이 모두 이루어지지 않는다면 친구를 배정하는 방식의 수를 하나 늘려서 2번부터 다시 수행한다.
1. dist의 크기까지 친구를 배정하는 수의 크기를 증가시켰지만, 즉 모든 친구를 투입했는데 점검이 이루어지지 않는다면 -1을 리턴

## 후기
와우 와우 weak를 직선으로 생각하면서 시작 위치를 변경할 때 사용한 시작위치를 제거하고 마지막에 +N을 해서 넣고 다시 완전탐색을 돌리는 것에 대해서 박수가 나왔다. 이렇게 하면 반시계방향, 시계방향 모두 살펴볼 것이 아니라 시계방향만 살펴보면 되니깐 말이다.

참 어려운 문제였다. 이래저래 혼자 힘으로 풀었을 때는 80점이 최고점이었다. 그래도 카카오 해설을 보고 풀 수 있어서 다행이다.