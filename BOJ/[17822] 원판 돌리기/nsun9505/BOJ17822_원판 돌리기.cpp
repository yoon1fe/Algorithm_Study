#include <iostream>
#include <deque>
#include <map>
#pragma warning(disable:4996)

using namespace std;
int N, M, T;
deque<deque<int>> board;
int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	freopen("input.txt", "rt", stdin);

	cin >> N >> M >> T;

	for (int i = 0; i < N; i++) {
		deque<int> tmp;
		int val;
		for (int j = 0; j < M; j++) {
			cin >> val;
			tmp.push_back(val);
		}
		board.push_back(tmp);
	}

	int x, d, k;
	for (int i = 0; i < T; i++) {
		cin >> x >> d >> k;
		for (int i = 1; x*i-1 < board.size(); i++) {
			int row = x * i - 1;
			for (int loop = 0; loop < k; loop++) {
				if (d == 0) {
					int tmp = board[row].back();
					board[row].pop_back();
					board[row].push_front(tmp);
				} 
				else {
					int tmp = board[row].front();
					board[row].pop_front();
					board[row].push_back(tmp);
				}
			}
		}

		map<pair<int, int>, bool> changes;
		int sum = 0;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 0)
					continue;
				sum += board[i][j];
				cnt++;

				int prev = j - 1;
				int next = j + 1;
				int down = i + 1;

				if (prev < 0)
					prev = M - 1;
				if (next >= M)
					next = 0;

				bool flag = false;
				if (board[i][j] == board[i][prev]) {
					flag = true;
					changes[{i, prev}] = true;
				}
				if (board[i][j] == board[i][next]) {
					flag = true;
					changes[{i, next}] = true;
				}
				if (down < N && board[i][j] == board[down][j]) {
					flag = true;
					changes[{down, j}] = true;
				}
				if (flag)
					changes[{i, j}] = true;
			}
		}
		if (changes.size() > 0) {
			for (auto cur : changes)
				board[cur.first.first][cur.first.second] = 0;
		}
		else {
			double avg = (double)sum / cnt;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[i][j] == 0)
						continue;

					if (board[i][j] > avg)
						board[i][j] -= 1;
					else if(board[i][j] < avg)
						board[i][j] += 1;
				}
			}
		}
	}

	int sum = 0;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			sum += board[i][j];

	cout << sum;

	return 0;
}