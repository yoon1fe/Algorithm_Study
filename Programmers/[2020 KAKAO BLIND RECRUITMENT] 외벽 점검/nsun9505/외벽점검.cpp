#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool isUsed[10];
vector<int> distances;
int distSize;


bool check(vector<int> dist, vector<int> weak) {
	int cnt = weak.size();
	vector<bool> isOk;
	for (int i = 0; i < weak.size(); i++)
		isOk.push_back(false);

	for (int d : dist) {
		for (int i = 0; i < weak.size(); i++) {
			if (isOk[i])
				continue;

			int maxRange = d + weak[i];
			for (int j = i; j < weak.size(); j++) {
				if (maxRange < weak[j])
					break;
				cnt--;
				isOk[j] = true;
			}

			if (cnt == 0)
				return true;
			break;
		}
	}
	return false;
}

void getPermutation(int depth, int maxDepth, vector<int> perm, vector<vector<int>>& list) {
	if (depth == maxDepth) {
		list.push_back(perm);
		return;
	}

	for (int i = 0; i < distances.size(); i++) {
		if (!isUsed[i]) {
			isUsed[i] = true;
			perm.push_back(distances[i]);
			getPermutation(depth + 1, maxDepth, perm, list);
			perm.pop_back();
			isUsed[i] = false;
		}
	}
}

int solution(int n, vector<int> weak, vector<int> dist) {
	distances = dist;
	distSize = dist.size();

	vector<int> perm;
	for (int num = 1; num <= dist.size(); num++) {
		vector<vector<int>> list;
		getPermutation(0, num, perm, list);

		for (auto cur : list) {
			vector<int> tmp(weak);
			for (int i = 0; i < weak.size(); i++) {
				if(check(cur, tmp))
					return num;
				int back = tmp[0];
				tmp.erase(tmp.begin());
				tmp.push_back(back + n);
			}
		}
	}
	
	return -1;
}

int main(void) {
	int N = 12;
	vector<int> weak;
	weak.push_back(1);
	weak.push_back(3);
	weak.push_back(4);
	weak.push_back(9);
	weak.push_back(10);

	vector<int> dist;
	dist.push_back(3);
	dist.push_back(5);
	dist.push_back(7);

	int ret = solution(N, weak, dist);
	cout << ret;

	return 0;
}