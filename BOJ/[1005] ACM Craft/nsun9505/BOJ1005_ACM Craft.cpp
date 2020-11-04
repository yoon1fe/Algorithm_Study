#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int T;
int N, K;
int weight[1001];
int cost[1001];
int numOfPre[1001];
vector<int> graph[1001];

int getMax(int x, int y) {
	if (x < y)
		return y;
	return x;
}

void construct() {
	queue<int> Q;
	int tmpMax = 0;
	for (int i = 1; i <= N; i++) {
		if (numOfPre[i] == 0) {
			Q.push(i);
			cost[i] = weight[i];
		}
	}

		while (!Q.empty()) {
			int u = Q.front();
			Q.pop();

			for (int i = 0; i < graph[u].size(); i++) {
				int v = graph[u][i];

				cost[v] = getMax(cost[v], cost[u] + weight[v]);

				numOfPre[graph[u][i]] -= 1;
				if (numOfPre[v] == 0)
					Q.push(v);
			}
		}
}

int main(void) {
	ios_base::sync_with_stdio(0);
	cin >> T;
	for (int t = 0; t < T; t++) {
		cin >> N >> K;
		for (int w = 1; w <= N; w++)
			cin >> weight[w];

		for (int i = 0; i < K; i++) {
			int u, v;
			cin >> u >> v;
			graph[u].push_back(v);
			numOfPre[v] += 1;
		}
		int target;
		cin >> target;
		construct();
		cout << cost[target] << '\n';

		for (int i = 1; i <= N; i++) {
			graph[i].clear();
			numOfPre[i] = 0;
			weight[i] = 0;
			cost[i] = 0;
		}
	}

	return 0;
}