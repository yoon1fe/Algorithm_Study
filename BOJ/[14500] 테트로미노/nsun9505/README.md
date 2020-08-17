# BOJ 14500 테트로미노

## 분류
> 시뮬레이션
> DFS

## 코드
```c++
#include <iostream>
#include <vector>

using namespace std;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
int board[501][501];
bool isVisited[501][501];
int N, M;
int maxRet = -217400000;
vector<pair<int, int>> stack;

void DFS(int cnt, int sum, int row, int col) {
	sum += board[row][col];

	if (cnt == 3 && maxRet < sum) {
		maxRet = sum;
		return;
	}
	else if (cnt == 3)
		return;

	isVisited[row][col] = true;
	stack.push_back({ row, col });

	for (int i = 0; i < stack.size(); i++) {
		int tmp_row = stack[i].first;
		int tmp_col = stack[i].second;

		for (int dir = 0; dir < 4; dir++) {
			int nx = tmp_row + dx[dir];
			int ny = tmp_col + dy[dir];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;

			if (isVisited[nx][ny])
				continue;

			DFS(cnt + 1, sum, nx, ny);
		}
	}

	stack.pop_back();
	isVisited[row][col] = false;
}

int main(void) {
	cin >> N >> M;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			cin >> board[i][j];

	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			DFS(0, 0, i, j);

	cout << maxRet;
	return 0;
}
```

## 풀이방법
- DFS는 약간 BFS처럼 보일 수도 있다. 하지만 stack에 현재까지 방문한 위치들을 넣어준다.
- 이 위치에서 방문가능한 것들을 stack에 넣고 이동한다.
  - 이동 후에는 stack에 있는 원소들(이미 방문한 위치들)의 상하좌우를 살펴보면서 이동이 가능한지 체크 후 이동한다.
  - 한 위치에서 방문이 가능한 위치들이 모두 스택에 들어가게 되거나 실제로 이동한 위치에 포함되게 된다.
  - 위치로 이동할 경우에는 recusive로 구현하고, 방문이 가능한 위치들은 stack으로 구현했다.
- 예를 들어, 처음에는 시작위치에서 갈 수 있는 곳을 차례대로 방문하기 때문에 'ㅜ'와 같은 모양을 탐색한다.
  - 그 대음에는 두 번째로 방문한 곳에서 갈 수 있는 곳을 방문한다.
  - 그렇게 시작위치에서 만들 수 있는 모든 테트로미노의 모형으로 각 위치를 방문하고
  - 합을 계산하게되므로 출력값인 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값을 출력할 수 있다.

## 후기
시뮬레이션은 언제나 재미가 있다.
관련된 문제도 풀어보기
- 디저트카페(SWEA), 보호 필름(SWEA), 치킨배달(백준)