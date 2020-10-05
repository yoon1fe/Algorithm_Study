#include <iostream>
#include <vector>
#include <map>
#pragma warning(disable:4996)
using namespace std;

struct Node {
	int index;
	int value;
	int red;
	int blue;
};

map<int, Node> nodeMap;
int dice[10];
int markers[4];
int ans = -2147000000;

void solution(int idx, int sum) {
	if (idx >= 10) {
		if (ans < sum)
			ans = sum;
		return;
	}

	for (int i = 0; i < 4; i++) {
		int start = markers[i];
		int next = nodeMap[start].red;
		if (nodeMap[start].blue)
			next = nodeMap[start].blue;

		for (int i = 1; i < dice[idx]; i++) {
			next = nodeMap[next].red;
		}

		bool flag = true;
		for (int mIdx = 0; mIdx < 4; mIdx++)
			if (next != 21 && markers[mIdx] == next)
				flag = false;

		if (flag) {
			markers[i] = next;
			solution(idx + 1, sum + nodeMap[markers[i]].value);
			markers[i] = start;
		}
	}
}

void makeMap() {
	int idx = 0;
	nodeMap[idx] = { idx, 0, idx + 1, 0 };
	idx++;
	for (int i = 2; i <= 40; i+=2) {
		Node node = { idx, i, idx + 1, 0 };	
		nodeMap[idx++] = node;
	}
	nodeMap[idx] = { idx, 0, idx, idx };

	nodeMap[5].blue = 22;
	nodeMap[22] = { 22, 13, 23, 0 };
	nodeMap[23] = { 23, 16, 24, 0 };
	nodeMap[24] = { 24, 19, 30, 0 };

	nodeMap[15].blue = 25;
	nodeMap[25] = { 25, 28, 26, 0 };
	nodeMap[26] = { 26, 27, 27, 0 };
	nodeMap[27] = { 27, 26, 30, 0 };

	nodeMap[10].blue = 28;
	nodeMap[28] = { 28, 22, 29, 0 };
	nodeMap[29] = { 29, 24, 30, 0 };
	nodeMap[30] = { 30, 25, 31, 0 };
	nodeMap[31] = { 31, 30, 32, 0 };
	nodeMap[32] = { 32, 35, 20, 0 };
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	freopen("input.txt", "rt", stdin);

	makeMap();
	for (int i = 0; i < 10; i++)
		cin >> dice[i];

	solution(0, 0);

	cout << ans;

	return 0;
}