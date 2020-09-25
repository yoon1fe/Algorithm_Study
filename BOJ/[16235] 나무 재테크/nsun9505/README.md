# [16235] 나무 재테크 - C++

## 분류
> 구현

## 코드
```c++
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
```

## 문제 풀이
NxN의 각 원소를 vector\<int>로 하여 해당 위치에 속하는 나무들을 관리한다.

봄과 여름을 같이
- [i][j]에 나무가 심어져 있다면 나무가 담겨 있는 vector를 탐색한다.
- board[i][j]의 양분이 나무의 나이보다 크거나 작다면 나이를 하나 증가시킨다.
- board[i][j]에 있는 양분이 나무의 나이보다 작다면, 해당 나무는 죽어서 양분이 되므로 deadSum에 저장하여 (i, j)에 있는 나무들을 다 탐색한 뒤에 board[i][j]에 더해준다.

가을
- NxN을 탐색하면서 (i, j)에 나무가 심어져 있고, 심어져 있는 나무들 중에 나이가 5의 배수라면
- 8방향으로 번식시키기 위해 map을 사용해서 i, j위치에 추가할 나무의 개수를 카운트한다.
- 바로 심는 것이 아니라, 일단 map에 번식될 위치에 몇 개의 나무가 번식되는지 카운트하여 가을에 대한 연산이 끝나면 추가한다.

겨울
- board에 추가할 양분을 더한다.

## 후기
후아 "틀렸습니다", "시간초과"만 몇 번을 본건지 모르겠다.

런타임 에러도 보고, 메모리 초과도 보고 혼돈의 카오스..

틀렸습니다 원인은 내가 잘못 짠 것이었고, 잘만 짜면 시간 초과는 안 뜨는거 같다.
