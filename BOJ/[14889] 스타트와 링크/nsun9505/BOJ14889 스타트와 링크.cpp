#include <iostream>
#include <vector>
#include <set>

using namespace std;
int N;
int board[21][21];
int ans = 2147000000;
vector<int> members;

void solved(int cur, vector<int> startTeam, vector<int> linkTeam) {
	if (cur >= N) {
		if (startTeam.size() == N / 2) {
			int start = 0;
			int link = 0;
			for (auto i : startTeam)
				for (auto j : startTeam)
					start += board[i][j];

			for (auto i : linkTeam)
				for (auto j : linkTeam)
					link += board[i][j];

			int diff = abs(start - link);
			if (diff < ans)
				ans = diff;
		}
		return;
	}
	

	startTeam.push_back(members[cur]);
	solved(cur + 1, startTeam, linkTeam);
	startTeam.pop_back();
	linkTeam.push_back(members[cur]);
	solved(cur + 1, startTeam, linkTeam);
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin >> N;
	for (int i = 1; i <= N; i++) {
		members.push_back(i);
		for (int j = 1; j <= N; j++)
			cin >> board[i][j];
	}
	vector<int> start;
	vector<int> link;
	solved(0, start, link);
	cout << ans;
	
	return 0;
}