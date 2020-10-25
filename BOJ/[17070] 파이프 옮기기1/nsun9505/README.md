# [17070] 파이프 옮기기 1 - C++

## 분류
> DP
> 
> BFS
>
> DFS
>
> 완전탐색

## 코드
```c++
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

struct Element {
	int r1;
	int c1;
	int r2;
	int c2;
	int type;
};

int dx[3] = { 0, 1, 1 };
int dy[3] = { 1, 0, 1 };
vector<int> moves[3] = { {0, 2}, {1, 2}, {0, 1, 2} };
int board[16][16];
int N;
int cnt = 0;
void DFS(int row, int col, int type) {
	vector<int> move = moves[type];
	for (int i = 0; i < move.size(); i++) {
		int nx = row + dx[move[i]];
		int ny = col + dy[move[i]];

		if (nx < 0 || ny < 0 || nx >= N || ny >= N)
			continue;

		if (board[nx][ny] == 1)
			continue;

		if (move[i] == 2 && (board[nx - 1][ny] == 1 || board[nx][ny - 1] == 1))
			continue;

		if (nx == (N - 1) && ny == (N - 1)) {
			cnt++;
			return;
		}

		DFS(nx, ny, move[i]);
	}
}

int BFS() {
	int ret = 0;
	queue<Element> Q;
	Q.push({ 0,0,0,1,0 });

	while (!Q.empty()) {
		Element elem = Q.front();
		Q.pop();

		vector<int> move = moves[elem.type];
		for (int i = 0; i < move.size(); i++) {
			int nx = elem.r2 + dx[move[i]];
			int ny = elem.c2 + dy[move[i]];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;

			if (board[nx][ny] == 1)
				continue;

			if (move[i] == 2 && (board[nx-1][ny] == 1 || board[nx][ny-1] == 1))
				continue;

			if (nx == (N - 1) && ny == (N - 1)) {
				ret++;
				continue;
			}

			Q.push({ elem.r2, elem.c2, nx, ny, move[i] });
		}
	}
	return ret;
}

int main(void) {
	cin >> N;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			cin >> board[i][j];

	//DFS(0, 1, 0);
	cout << BFS();

	return 0;
}
```

## 문제 풀이
파이프가 두 개의 인덱스를 차지하는데 움직임에 대해서는 두 개의 위치를 볼 필요 없고, 앞에 위치한 즉, 처음 (0, 1)에 대해서만 체크하면 된다.

### DFS
현재 TYPE(가로, 세로, 대각선)에 따라서 갈 수 있는 방향이 정해지므로 각 TYPE에 따라 이동할 수 있는 곳을 moves라는 곳에 정의하였다.

방향을 결정
- board를 벗어나는 경우에는 파이프를 움직이지 못한다.
- 파이프의 현재 TYPE에서 움직일 수 있는 곳에 벽(1)이 있다면 움직이지 못한다.
- 만약 파이프가 대각선 모양으로 이동할 경우, 이동하는 위치에서 [nx-1][ny], [nx][ny-1]이 모두 빈칸인 경우만 가능하므로 이에 해당하지 않으면 이동하지 못하도록 한다.
- 만약 이동한 칸이 (N,N)이라면 cnt를 하나 증가시키고 더 이상 진행하지 않는다.
- 위의 조건들에 모두 해당되지 않을 경우, TYPE을 변경시켜 DFS를 돌린다.


### BFS
방문 배열을 하나씩 체크해야 할 필요가 없었다. 그러니 시간이 DFS보다 오래 걸렸다..
- 메모리도 더 쓰고.. 무슨 문제일까..?

방법은 DFS와 거의 동일하다. 
- 방향을 결정하는 것은 DFS와 동일
- 대신 이동이 가능한 경우에 스택으로 해당 방향으로 쭉 진행하는 것이 아니라 큐에 넣어서 진행했다.

## 후기
후아후아!!