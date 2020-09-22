# [15686] 치킨 배달 - C++

## 분류
> 구현
> 
> 완전탐색

## 코드
```c++
#include <iostream>
#include <vector>
#pragma warning(disable:4996)

using namespace std;
int N, M;
int map[51][51];
int ans = 2147000000;
vector<pair<int, int>> houses;
vector<pair<int, int>> chickens;

void solution(int cur, int cnt, vector<pair<int, int>> list) {
	if (cnt >= M) {
		if (cnt == M) {
			int sum = 0;
			for (auto house : houses) {
				int dist = 2147000000;
				for (auto chicken : list) {
					int tmp = abs(chicken.first - house.first) + abs(chicken.second - house.second);
					if (tmp < dist)
						dist = tmp;
				}
				sum += dist;
			}

			if (sum < ans)
				ans = sum;
		}
		return;
	}

	if (chickens.size() <= cur)
		return;

	list.push_back(chickens[cur]);
	solution(cur + 1, cnt + 1, list);
	list.pop_back();
	solution(cur + 1, cnt, list);
}

int main(void) {
	ios_base::sync_with_stdio(false);
	freopen("input.txt", "rt", stdin);
	cin >> N >> M;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> map[i][j];
			if (map[i][j] == 1)
				houses.push_back({ i, j });
			else if (map[i][j] == 2)
				chickens.push_back({ i,j });
		}
	}

	vector<pair<int, int>> list;
	solution(0, 0, list);

	cout << ans;

	return 0;
}
```

## 문제풀이
1. 치킨집이 K가 있다면, M개를 선택한다.(조합)
1. 각 집에서 선택된 M개의 치킨집 사이의 거리를 구한다.
   - 거기서 가장 짧은 거리를 dist에 저장하고, 각 집에서 가장 짧은 거리에 있는 치킨 집과의 거리 합인 sum에 더해준다.
   - 답으로 출력할 ans와 sum을 비교하여, ans보다 작다면 ans의 값을 sum 값으로 갱신한다.

## 후기
3달전, 1달전, 그리고 지금! 3번째 풀어보는 문제!

그럼에도 헷갈린 부분이 있었다. 더 열심히 공부합시다..