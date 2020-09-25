#include <iostream>
#include <vector>
#include <deque>
#include <algorithm>
#include <map>
#pragma warning(disable:4996)

using namespace std;

int N, M, K;
int board[11][11];
deque<int> treeBoard[11][11];
map<pair<int, int>, int> tmp;
int A[11][11];
int dx[8] = { -1, -1, -1, 0, 0, 1, 1, 1 };
int dy[8] = { -1, 0, 1, -1, 1, -1, 0, 1 };

int main(void) {
	freopen("input.txt", "rt", stdin);
	ios_base::sync_with_stdio(false);
	cin >> N >> M >> K;

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> A[i][j];
			board[i][j] = 5;
		}
	}	

	int x, y, z;
	for (int i = 0; i < M; i++) {
		cin >> x >> y >> z;
		treeBoard[x][y].push_back(z);
	}

	for (int year = 0; year < K; year++) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (treeBoard[i][j].size() == 0)
					continue;

				deque<int> tree;
				int deadSum = 0;
				for (int k = 0; k < treeBoard[i][j].size(); k++) {
					if (board[i][j] >= treeBoard[i][j][k]) {
						board[i][j] -= treeBoard[i][j][k];
						tree.push_back(treeBoard[i][j][k] + 1);
					}
					else {
						deadSum += (treeBoard[i][j][k] / 2);
					}
				}
				treeBoard[i][j].swap(tree);
				board[i][j] += deadSum;
			}
		}

		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (treeBoard[i][j].size() == 0)
					continue;
				
				for (int k = 0; k < treeBoard[i][j].size(); k++) {
					if (treeBoard[i][j][k] % 5 == 0){
						for (int dir = 0; dir < 8; dir++) {
							int nx = i + dx[dir];
							int ny = j + dy[dir];

							if (nx < 1 || ny < 1 || nx > N || ny > N)
								continue;

							tmp[{nx, ny}] += 1;
						}
					}
				}
			}
		}

		for (auto tree : tmp)
			for (int i = 0; i < tree.second; i++)
				treeBoard[tree.first.first][tree.first.second].push_front(1);
		tmp.clear();
		
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				board[i][j] += A[i][j];
	}

	int cnt = 0;
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			cnt += treeBoard[i][j].size();

	cout << cnt;
	
	return 0;
}