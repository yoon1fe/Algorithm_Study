#include <iostream>
#include <vector>
#include <queue>
#define RED 0
#define BLUE 1

using namespace std;

struct Node {
	int population;
	int color;
};
vector<int> graph[11];
Node section[11];

int N;
int ans = 2147000000;

int BFS(int num) {
	bool isVisited[11];
	for (int i = 1; i <= N; i++)
		isVisited[i] = false;

	queue<int> Q;
	vector<int> neighbor = graph[num];
	isVisited[num] = true;
	int ret = 1;
	for (int i = 0; i < neighbor.size(); i++) {
		if (section[num].color != section[neighbor[i]].color)
			continue;

		Q.push(neighbor[i]);
		isVisited[neighbor[i]] = true;
		ret++;
	}

	while (!Q.empty()) {
		int cur = Q.front();
		Q.pop();

		for (int i = 0; i < graph[cur].size(); i++) {
			if (section[cur].color != section[graph[cur][i]].color)
				continue;

			if (isVisited[graph[cur][i]])
				continue;

			ret++;
			Q.push(graph[cur][i]);
			isVisited[graph[cur][i]] = true;
		}
	}

	return ret;
}

void DFS(int idx, vector<int> red, vector<int> blue) {
	if (idx > N) {
		if (red.size() == 0 || blue.size() == 0)
			return;

		int redTotal = BFS(red[0]);
		int blueTotal = BFS(blue[0]);
		if (redTotal != red.size() || blueTotal != blue.size())
			return;

		int redSum = 0;
		int blueSum = 0;
		for (int i = 1; i <= N; i++) {
			if (section[i].color == RED)
				redSum += section[i].population;
			else
				blueSum += section[i].population;
		}

		if (abs(redSum - blueSum) < ans)
			ans = abs(redSum - blueSum);
		return;
	}

	red.push_back(idx);
	section[idx].color = RED;
	DFS(idx + 1, red, blue);
	red.pop_back();

	blue.push_back(idx);
	section[idx].color = BLUE;
	DFS(idx + 1, red, blue);
	blue.pop_back();
}

int main(void) {
	ios_base::sync_with_stdio(0);

	cin >> N;
	for (int i = 1; i <= N; i++)
		cin >> section[i].population;

	for (int i = 1; i <= N; i++) {
		int K, num;
		cin >> K;
		for (int j = 0; j < K; j++) {
			cin >> num;
			graph[i].push_back(num);
		}
	}

	vector<int> red, blue;
	DFS(1, red, blue);
	if (ans == 2147000000)
		cout << -1;
	else
		cout << ans;
	return 0;
}