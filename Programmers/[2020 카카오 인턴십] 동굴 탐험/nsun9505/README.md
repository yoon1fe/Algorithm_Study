# [2020 카카오 인턴십] 동굴 탐험 - C++

## 분류
> BFS
>
> DFS
> 
> 위상 정렬(?)

## 코드
```c++
#include <iostream>
#include <vector>
#include <queue>
#include <map>
#include <set>

#define MAX 200001
using namespace std;
vector<int> graph[MAX];
bool isVisited[MAX];
map<int, int> prevVisit;
map<int, int> nextVisit;
set<int> notVisit;
int save[MAX];


void BFS(int start) {
	queue<int> Q;
	Q.push(start);
	isVisited[start] = true;

	while (!Q.empty()) {
		int u = Q.front();
		Q.pop();

		if (notVisit.count(nextVisit[u])) {
			notVisit.erase(nextVisit[u]);
			isVisited[nextVisit[u]] = true;
			Q.push(nextVisit[u]);
		}
		for (auto v : graph[u]) {
			if (isVisited[v]) continue;
			if (prevVisit.count(v) && !isVisited[prevVisit[v]]) {
				notVisit.insert(v);
				continue;
			}
			isVisited[v] = true;
			Q.push(v);
		}
	}
}

bool solution(int n, vector<vector<int>> path, vector<vector<int>> order) {
	bool answer = true;

	for (auto e : path) {
		graph[e[0]].push_back(e[1]);
		graph[e[1]].push_back(e[0]);
	}

	for (auto prev : order) {
		prevVisit[prev[1]] = prev[0];
		nextVisit[prev[0]] = prev[1];
	}

	if (prevVisit.count(0))
		return false;

	BFS(0);

	for (int i = 0; i < n; i++)
		if (!isVisited[i])
			return false;

	return answer;
}

/*
void DFS(int v) {
	if (isVisited[v])
		return;

	if (isVisited[prevVisit[v]] == false) {
		save[prevVisit[v]] = v;
		return;
	}
	
	isVisited[v] = true;
	DFS(save[v]);
	for (int next : graph[v])
		DFS(next);
}

bool solution(int n, vector<vector<int>> path, vector<vector<int>> order) {
	bool answer = true;

	for (auto e : path) {
		graph[e[0]].push_back(e[1]);
		graph[e[1]].push_back(e[0]);
	}

	for (auto prev : order)
		prevVisit[prev[1]] = prev[0];

	isVisited[0] = true;
	for (int next : graph[0])
		DFS(next);

	for (int i = 0; i <= N; i++)
		if (!isVisited[i])
			return false;

	return answer;
}
*/
int main(void) {
	vector<vector<int>> path;
	vector<vector<int>> order;
	int n = 9;
	path.push_back({ 0,1 });
	path.push_back({ 0,3 });
	path.push_back({ 0,7 });
	path.push_back({ 8,1 });
	path.push_back({ 3,6 });
	path.push_back({ 1,2 });
	path.push_back({ 4,7 });
	path.push_back({ 7,5 });

	order.push_back({ 5,2 });
	order.push_back({ 4,1 });

	cout << solution(n, path, order);

	return 0;
}
```

## 문제 풀이
입력값에 따라 그래프를 만든 다음 

prev -> next, next -> prev와 같이 A -> B로 갈 때, A 다음에 가야할 위치, B를 방문하기 전에 들여야할 A를 map에 저장한다.
- 이렇게 저장한 이유는
- A -> B에서 B를 방문했는데 아직 A를 방문하지 않았을 때 일단 B를 set에 넣어준다. A -> B에서 B를 먼저 만난 것이다. B->A에 대한 정보 필요
- 그리고 BFS로 탐색 중에 set에 지금 위치를 방문했을 때 다음에 방문할 값이 있는지 찾아본다. A -> B에서 A를 만난 것이다. A->B에 대한 정보 필요
- 현재 위치는 A인 것이고, A에서 B로 가는 정보가 들어있는 map을 참조하여 B가 set에 있는지 체크하고,
- 만약 있다면 A를 방문한 것이므로 B를 방문하면 되기에 queue에 넣어주면 된다.

BFS로 탐색이 끝난 뒤 모든 정점이 방문되었는지 체크해서
- 아직 방문되지 않은 곳이 있다면 false를 리턴
- 모든 정점을 방문했다면 true 리턴

## 후기
카카오 해설과 DFS, BFS에 관한 풀이를 봤는데 가장 이해가 잘 되는 부분이 BFS였다.

그래서 BFS를 이용해서 풀어봤다!

카카오 해설이 이해는 되는데 구현하기가 막막했다. 그래서 BFS로!