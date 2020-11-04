# [1005] ACM Craft - C++

## 분류
> 위상정렬

## 코드
```c++
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
```

## 문제 풀이
위상 정렬로 하면 풀리는 문제!

처음에는 목표지점까지 가는 경로에 포함되는 애들을 알아내기 위해서 BFS를 돌리고, 그 포함되는 경우에만 위상정렬 돌릴 때 큐에 넣도록 했는데
- 틀림!!!!

내가 시도 했던 방식
1. 입력을 받는데 u, v라는 엣지 정보를 받을 때 u -> v로 하지 않고 v -> u로 엣지를 세팅해서 목표 정점에서 BFS를 돌린다.
1. BFS를 돌린 결과는 목표 정점으로 가는데 포함되는 정점들에 대해서 True로 체크한다.(contains라는 배열)
1. 위상 정렬을 돌린다!
   1. contains[i]가 true이고 indegree값이 0인 애들을 큐에 넣는다. 그리고 거기서 가장 큰 값을 출력할 답에 저장한다.
   1. 여기서부터는 전형적인 위상정렬!(해당 노드로 갈 수 있는 곳의 indegree값을 1빼주고 0이면 큐에 넣는 방식)
   1. 대신, 큐에 넣기 전에 indegree가 0인 애들을 따로 vector에 담는다.
   1. vector를 돌면서 weight가 가장 큰 값을 출력할 답에 더한다.(이 부분이 틀린걸까..?)

**틀림**
- 물론, 내가 잘못한 것이 있을 것이다.

구글링 결과, 답은 매우 간단했다.
- 위상정렬을 위한 세팅을 한다.(Indegree 계산)
- 각 지점에 도착하기 위한 최대 비용을 계산하면 되는 것이다.
   - 목표지점을 가야만 해!!!이러면서 문제를 생각하면 어려워지는 것 같다.
   - 각 정점으로 가기 위한 최댓값을 계산하고, 마지막에 문제에서 원하는 노드에 대해서 위상정렬을 통해 계산된 배열에서 해당 정점 인덱스에 있는 값을 출력하면 된다.

## 후기
위상정렬을 제대로 배울 수 있었다!! 재미있당

어렵게 생각하면 어렵게!

쉽게 생각하면 쉽게! 하지만 쉽게 생각하기가 쉽지 않지 ㅋㅋㅋㅋ