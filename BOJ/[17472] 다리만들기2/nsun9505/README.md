# [17472] 다리 만들기 2 - c++

## 분류
> BFS
> 
> MST
>
> 완전탐색

## 코드
```c++
#include <iostream>
#include <queue>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

struct Element {
	int row;
	int col;
};

struct Edge {
	int src;
	int dest;
	int dist;
};

map<int, vector<Element>> countries;
map<pair<int, int>, int> belongTo;
map<pair<int, int>, int> edges;
vector<int> graph[7];
bool isVisited[11][11];
int board[11][11];
int parent[7];
int num[7];
int N, M;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };

bool comp(Edge e1, Edge e2) {
	return e1.dist < e2.dist;
}

int BFS() {
	bool isVisited[7] = { false, false, false, false, false, false, false };
	queue<int> Q;
	Q.push(1);
	isVisited[1] = true;
	int ret = 1;
	for (int i = 0; i < graph[1].size(); i++) {
		Q.push(graph[1][i]);
		isVisited[graph[1][i]] = true;
		ret++;
	}

	while (!Q.empty()) {
		int cur = Q.front();
		Q.pop();

		for (int i = 0; i < graph[cur].size(); i++) {
			if (isVisited[graph[cur][i]])
				continue;

			ret++;
			Q.push(graph[cur][i]);
			isVisited[graph[cur][i]] = true;
		}
	}

	return ret;
}

void setNumber(int row, int col, int index) {
	queue<Element> Q;
	Q.push({ row, col });
	isVisited[row][col] = true;

	while (!Q.empty()) {
		Element elem = Q.front();
		Q.pop();

		countries[index].push_back({ elem.row, elem.col });
		belongTo[{elem.row, elem.col}] = index;

		for (int dir = 0; dir < 4; dir++) {
			int nx = elem.row + dx[dir];
			int ny = elem.col + dy[dir];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;

			if (isVisited[nx][ny] || board[nx][ny] == 0)
				continue;

			isVisited[nx][ny] = true;
			Q.push({ nx, ny });
		}
	}
}

void searchEdges() {
	for (auto country : countries) {
		for (int i = 0; i < country.second.size(); i++) {
			Element cur = country.second[i];

			for (int dir = 0; dir < 4; dir++) {
				int mul = 1;
				while (true) {
					int nx = cur.row + (dx[dir] * mul);
					int ny = cur.col + (dy[dir] * mul);

					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						break;

					if (belongTo[{nx, ny}] == country.first)
						break;

					if (board[nx][ny] == 0) {
						mul++;
						continue;
					}

					if (mul == 2)
						break;

					int src = country.first;
					int dest = belongTo[{nx, ny}];
					if (dest < src) {
						int tmp = src;
						src = dest;
						dest = tmp;
					}

					if (edges.count({ src, dest })) {
						if (mul - 1 < edges[{src, dest}])
							edges[{src, dest}] = mul - 1;
					}
					else
						edges[{src, dest}] = mul - 1;

					break;
				}
			}
		}
	}
}

int setFind(int v) {
	int p, s, i = -1;
	for (i = v; (p = parent[i]) >= 0; i = p);

	s = i;

	for (i = v; (p = parent[i]) >= 0; i = p)
		parent[i] = s;

	return s;
}

void setUnion(int s1, int s2) {
	if (num[s1] < num[s2]) {
		parent[s1] = s2;
		num[s2] += num[s1];
	}
	else {
		parent[s2] = s1;
		num[s1] += num[s2];
	}
}

int main(void) {
	ios_base::sync_with_stdio(0);
	
	cin >> N >> M;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			cin >> board[i][j];

	int index = 1;
	for(int i=0; i<N; i++)
		for (int j = 0; j < M; j++) {
			if (board[i][j] == 0 || isVisited[i][j])
				continue;
			setNumber(i, j, index++);
		}

	searchEdges();
	if (edges.size() == 0) {
		cout << -1;
	}
	else {
		vector<Edge> sortedEdges;
		for (auto edge : edges)
			sortedEdges.push_back({ edge.first.first, edge.first.second, edge.second });
		sort(sortedEdges.begin(), sortedEdges.end(), comp);

		for (int i = 1; i < index; i++) {
			parent[i] = -1;
			num[i] = 1;
		}

		int sum = 0;
		int edgeCnt = 0;
		for (int i = 0; i < sortedEdges.size(); i++) {
			if (edgeCnt == (index - 2))
				break;

			Edge e = sortedEdges[i];

			int u = setFind(e.src);
			int v = setFind(e.dest);

			if (u != v) {
				sum += e.dist;
				graph[u].push_back(v);
				graph[v].push_back(u);
				edgeCnt++;
				setUnion(u, v);
			}
		}
		if (index - 1 == BFS())
			cout << sum << '\n';
		else
			cout << -1 << '\n';
	}
	return 0;
}
```

## 문제 풀이
먼저 1로 상하좌우에 인접한 영역을 구하고 해당 영역에 index를 부여

각 영역에서 다른 영역으로 모든 다리를 찾아본다.
- 이미 영역 사이의 거리가 존재한다면 거리가 작은 값으로 갱신한다.
- 영역 사이의 거리가 존재하지 않는다면 추가

영역 사이의 거리를 오름차순으로 정렬하고, 유니온-파인드를 사용해서 MST를 찾는다.

각 영역을 연결한 후에 모든 영역이 연결되었다면 추가한 다리(영역 사이의 거리)의 길이를 모두 더한 값을 출력
- 모든 영역이 연결되어 있지 않다면, -1을 출력
- 이를 구하기 위해 BFS를 사용

## 후기
```
1 6
1 0 1 0 0 1
```
와 같은 예제를 돌려보니 내가 틀린 부분을 알 수 있었다.