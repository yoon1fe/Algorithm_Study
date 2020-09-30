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