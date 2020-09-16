# [14888] 스타트와 링크 - C++

## 분류
> 조합

## 코드
```c++
#include <iostream>
#include <vector>

using namespace std;
int N;
int board[21][21];
int ans = 2147000000;
vector<int> members;

void solved(int cur, vector<int> startTeam, vector<int> linkTeam) {
	if (cur >= N) {
		if (startTeam.size() == N / 2) {
			int start = 0;
			int link = 0;
			for (auto i : startTeam)
				for (auto j : startTeam)
					start += board[i][j];

			for (auto i : linkTeam)
				for (auto j : linkTeam)
					link += board[i][j];

			int diff = abs(start - link);
			if (diff < ans)
				ans = diff;
		}
		return;
	}
	

	startTeam.push_back(members[cur]);
	solved(cur + 1, startTeam, linkTeam);
	startTeam.pop_back();
	linkTeam.push_back(members[cur]);
	solved(cur + 1, startTeam, linkTeam);
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin >> N;
	for (int i = 1; i <= N; i++) {
		members.push_back(i);
		for (int j = 1; j <= N; j++)
			cin >> board[i][j];
	}
	vector<int> start;
	vector<int> link;
	solved(0, start, link);
	cout << ans;
	
	return 0;
}
```

## 풀이 방법
1. N명의 스타트링크 사원이 있다면, N/2로 두 팀으로 나눈다.
- 조합을 사용해서 팀을 나누면 된다.

2. start, link 팀에 속한 선수들에 따라 능력치를 계산한다.

3. 두 팀간의 능력치 차이가 현재 답보다 작다면 현재 답을 갱신

## 후기
이 문제를 푸는데 스터디를 하면서 조합, 순열 이런 문제를 풀어보면서 많이 도움이되었다!!