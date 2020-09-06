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