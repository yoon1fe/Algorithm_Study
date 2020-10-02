# [17142] 연구소 3 - C++

## 코드
> BFS
> 
> 완전탐색

## 코드
```c++
#include <iostream>
#include <queue>
#include <vector>
#pragma warning(disable:4996)
using namespace std;

struct Virus {
	int row;
	int col;
	int t;
};

bool isVisited[51][51];
int board[51][51];
int N, M;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
vector<Virus> virusList;
int zeroCnt = 0;
int ans = 2147000000;

void DFS(int cur, int cnt, vector<Virus> list) {
	if (cnt == M) {
		queue<Virus> Q;
		for (int i = 0; i < M; i++) {
			Q.push(list[i]);
			isVisited[list[i].row][list[i].col] = true;
		}

		int ret = 0;
		int tmpCnt = zeroCnt;
		while (!Q.empty()) {
			Virus virus = Q.front();
			Q.pop();

			for (int dir = 0; dir < 4; dir++) {
				int nx = virus.row + dx[dir];
				int ny = virus.col + dy[dir];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (isVisited[nx][ny] || board[nx][ny] == 1)
					continue;

				Q.push({ nx, ny, virus.t + 1 });
				isVisited[nx][ny] = true;
				if (board[nx][ny] == 0 && ret < virus.t + 1)
					ret = virus.t + 1;
				if(board[nx][ny] == 0)
					tmpCnt--;
			}
		}
		if (tmpCnt == 0 && ret < ans)
			ans = ret;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				isVisited[i][j] = false;
		return;
	}
	if (cur >= virusList.size())
		return;
	
	list.push_back(virusList[cur]);
	DFS(cur + 1, cnt + 1, list);
	list.pop_back();
	DFS(cur + 1, cnt, list);
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	freopen("input.txt", "rt", stdin);

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> board[i][j];
			if (board[i][j] == 2)
				virusList.push_back({ i, j, 0 });
			if (board[i][j] == 0)
				zeroCnt++;
		}
	}
	vector<Virus> list;
	DFS(0, 0, list);

	if (ans == 2147000000)
		cout << -1;
	else
		cout << ans;
	return 0;
}
```

## 문제 풀이
입력 받은 M의 수만큼 바이러스의 조합을 구함
- 또한, 입력 받을 떄 0의 개수를 카운트하여 
- M개의 선택된 바이러스를 BFS 돌릴 때 0인 곳을 방문하면 0의 개수를 1씩 감소하게 되는데 
- BFS를 다 돌았을 때 0이 되지 않으면 정답을 계산할 수 없음.

선택된 바이러스를 BFS를 사용해서 퍼뜨림!

바이러스가 퍼질 때 주의할 점
- 비활성화 바이러스를 만났을 때는 0을 카운트한 값을 감소시키지 않음.
- 퍼진 시간을 결과값(ret)에 넣을 때는 비활성 바이러스의 경우 계산되지 않도록 한다.

만약 ans가 2147000000이라면 어떤 바이러스 조합에 대해서도 모든 구역으로 바이러스가 퍼지지 않으므로 -1을 출력한다.
- ans가 2147000000이 아니라면 바이러스를 퍼뜨리는 최소 시간을 출력한다.

## 후기
추석이라 그런가.. 나타해져서 이렇게 풀면 되겠지 했지만 틀렸습니다를 보고 다시 집중해서 문제를 읽고 이해하니 틀린 부분을 찾을 수 있었습니다. 

문제 풀 때 만큼은 열심히!