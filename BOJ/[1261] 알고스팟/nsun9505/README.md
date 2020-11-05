# [1261] 알고스팟 - C++

## 분류
> 다익스트라

## 코드
```c++
#include <iostream>
#include <queue>
#define INF 2147000000;

using namespace std;

struct Element {
	int row;
	int col;
	int d;
};

struct comp {
	bool operator()(Element s, Element t) {
		return s.d < t.d;
	}
};

int N, M;
int graph[101][101];
int dist[101][101];
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
bool isVisited[101][101];

bool isOutOfIndex(int row, int col) {
	if (row < 1 || col < 1 || row > N || col > M)
		return true;
	return false;
}

int main(void) {
	ios_base::sync_with_stdio(0);
	
	cin >> M >> N;
	char input;
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= M; j++) {
			cin >> input;
			graph[i][j] = (input == '1') ? 1 : 0;
			dist[i][j] = INF;
		}

	dist[1][1] = 0;
	priority_queue<pair<int, pair<int, int>>> pq;
	pq.push({ 0,{1,1} });

	while (!pq.empty()) {
		pair<int, pair<int, int>> cur = pq.top();
		int curRow = cur.second.first;
		int curCol = cur.second.second;
		int distance = -cur.first;
		pq.pop();

		if (isVisited[curRow][curCol])
			continue;
		isVisited[curRow][curCol] = true;

		for (int dir = 0; dir < 4; dir++) {
			int nx = curRow + dx[dir];
			int ny = curCol + dy[dir];

			if (isOutOfIndex(nx, ny))
				continue;

			int nextDistance = distance + graph[nx][ny];

			if (nextDistance < dist[nx][ny]) {
				dist[nx][ny] = nextDistance;
				pq.push({ -dist[nx][ny], {nx, ny}});
			}
		}
	}

	cout << dist[N][M] << '\n';
	return 0;
}
```

## 문제 풀이
다익스트라로 해결!

언제나 인풋 숫자가 다닥 다닥 붙어있으면 char로 받아서 변경하기...

다익스트라를 사용해서 (1,1)에서 (N, M)까지의 최소 거리를 알아내면 된다.
- Edge의 정보를 미리 세팅할 필요는 없다. 왜냐하면 문제에서 한 지점에서 상하좌우로만 연결되었다고 했으니!
- 그러면 다익스트라를 돌리기 위해서 거리 배열(dist)를 N, M크기로 해서 각 값을 INF(무한)로 초기화한다.

다익스트라 시작
1. 시작 위치(1,1)의 거리 값을 0으로 변경한다.(dist[1][1] = 0)
   - 우선순위 큐에 넣는다.

1. 우선순위 큐가 빌 때까지 루프를 돈다.
   - 우선순위 큐에서 거리가 가장 작은 값을 가지는 위치(x, y)를 가져온다.
   - 그 위치를 이미 방문한 적이 없다면 방문했다고 표시하고, 방문한 적이 있으면 이미 가장 최소가 되는 값으로 갱신이 되었기에 안 봐도 된다.
   - 방문하지 않았다면! 그 위치에서 상하좌우를 살펴보면서 인덱스가 범위를 벗어나는지 검사한다.
   - 벗어나지 않는다면! (x, y)를 거쳐서 (nx, ny)를 가는 것이 기존에 (1,1)에서 (nx, ny)로 가는 것보다 짧다면 dist[nx][ny]의 값을 현재 노드(x, y)에서 다음 노드(nx, ny)로 가는 비용으로 갱신한다.
   - 그리고 해당 거리 값을 음수로 변경해서 우선순위 큐에 넣음으로써 중간에 만나는 벽의 개수가 최소가 되도록 할 수 있다!

1. 루프가 끝나면 dist[N][M]을 출력하면 답이다!

## 후기
다익스트라를 이해하고 처음으로 풀어본 문제! 물론 다익스트라가 생각이 안 났습니다 ㅋㅋㅋㅋ

그래도 이렇게 알고리즘을 찾아가면서 공부하고 문제에 어떻게 적용할지 고민해보는게 변태같지만 재밌습니다..ㅎ