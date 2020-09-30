# [17140] 이차원 배열과 연산 - C++

## 분류
> 구현
>
> 시뮬레이션

## 코드
```c++
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#pragma warning(disable:4996)

using namespace std;

int R, C, K;
int board[101][101];

bool cmp(pair<int, int> a, pair<int, int> b) {
	if (a.second < b.second)
		return true;
	else if (a.second == b.second)
		return a.first < b.first;
	return false;
}

bool isEnd(int row, int col, int& ret) {
	if (ret > 100) {
		ret = -1;
		return true;
	}
	
	if (board[row][col] == K)
		return true;
	return false;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	freopen("input.txt", "rt", stdin);

	cin >> R >> C >> K;
	for (int i = 1; i <= 3; i++)
		for (int j = 1; j <= 3; j++)
			cin >> board[i][j];

	int ret = 0;
	int N = 3;
	int M = 3;
	while (!isEnd(R, C, ret)) {
		vector<vector<int>> tmpBoard;
		ret++;
		if (N >= M) {
			int tmpCol = M;
			for (int i = 1; i <= N; i++) {
				map<int, int> numCnt;
				vector<pair<int, int>> numPair;
				for (int j = 1; j <= M; j++)
					if (board[i][j] != 0)
						numCnt[board[i][j]] += 1;

				for (auto cur : numCnt)
					numPair.push_back({ cur.first, cur.second });
				sort(numPair.begin(), numPair.end(), cmp);
				vector<int> sortedNumCnt;
				for (auto p : numPair) {
					sortedNumCnt.push_back(p.first);
					sortedNumCnt.push_back(p.second);
				}
				if (tmpCol < sortedNumCnt.size())
					tmpCol = sortedNumCnt.size();
				tmpBoard.push_back(sortedNumCnt);
			}

			if (tmpCol > 100)
				M = 100;
			else
				M = tmpCol;

			for (int i = 1; i <= tmpBoard.size(); i++) {
				while (tmpBoard[i - 1].size() < M)
					tmpBoard[i - 1].push_back(0);

				for (int j = 1; j <= M; j++)
					board[i][j] = tmpBoard[i - 1][j - 1];
			}
		}
		else {
			int tmpRow = N;
			for (int j = 1; j <= M; j++) {
				map<int, int> numCnt;
				vector<pair<int, int>> numPair;
				for (int i = 1; i <= N; i++)
					if (board[i][j] != 0)
						numCnt[board[i][j]] += 1;

				for (auto cur : numCnt)
					numPair.push_back({ cur.first, cur.second });
				sort(numPair.begin(), numPair.end(), cmp);
				vector<int> sortedNumCnt;
				for (auto p : numPair) {
					sortedNumCnt.push_back(p.first);
					sortedNumCnt.push_back(p.second);
				}
				if (tmpRow < sortedNumCnt.size())
					tmpRow = sortedNumCnt.size();
				tmpBoard.push_back(sortedNumCnt);
			}

			if (tmpRow > 100)
				N = 100;
			else
				N = tmpRow;

			for (int i = 1; i <= tmpBoard.size(); i++) {
				while (tmpBoard[i - 1].size() < N)
					tmpBoard[i - 1].push_back(0);

				for (int j = 1; j <= N; j++)
					board[j][i] = tmpBoard[i - 1][j - 1];
			}
		}
	}
	cout << ret;
	return 0;
}
```

## 문제 풀이
우선 행의 크기를 N, 열의 크기를 M으로 두었다.
- N >= M이면 R연산을 하고, N < M이면 C연산을 하였다.

### R 연산
i번째 행에 존재하는 숫자들을 map을 이용해서 카운트한다.
- 카운트가 끝나면, vector\<pair\<int, int>>에 담아서 정렬한다.
- 정렬은 문제에서 하라는 방식으로 하면 된다.

정렬된 vector를 vector\<int>에 순서대로 담는다.
- 그리고 tmpBoard라는 곳에 추가한다.
- 만약 현재 tmpCol값보다 vector\<int>의 길이가 더 길다면 M값을 나중에 갱신하기 위해 tmpCol을 갱신한다.

tmpCol이 100이 넘을 경우 M을 100으로, 아니라면 M을 tmpCol의 값으로 갱신한다.

모든 행에 대해서 카운트를 하여 board에 대해서 R연산을 수행한 tmpBoard가 주어지게 된다.
- tmpBoard에 담긴 i번째 vector\<int>의 사이즈가 M보다 작다면 뒤에 0으로 채운다.
- 그리고 board의 i번째 행에 순서대로 값을 넣어준다.


### C 연산
- 위 R연산과 겹치는 부분이 많다.
- 그래서 굵직한 것만 설명

j번째 열에 존재하는 숫자들을 map을 이용해서 카운트

문제에서 요구하는 방식으로 카운트된 숫자들을 정렬하고 tmpBoard에 삽입

정렬한 크기 중 가장 큰 vector를 N으로 설정한다. 만약 가장 큰 크기가 100을 넘어선다면 N(행) 값을 100으로 한다.

tmpBoard의 값을 board에 열방향으로 담는다.

## 후기
코드에 중복되는 부분이 너무 많은 것 같다.

다음에 자바로 풀게 된다면 이 점을 고치도록 해보자.