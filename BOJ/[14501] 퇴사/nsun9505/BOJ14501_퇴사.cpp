#include <iostream>
#include <vector>

using namespace std;

struct Task {
public:
	int day;
	int money;

	Task(int d, int m) : day(d), money(m) {}
};

int N;
int ans = -1;
vector<Task> list;

void DFS(int idx, int sum) {
	if (idx == N) {
		if (ans < sum)
			ans = sum;
		return;
	}
	else if (idx > N)
		return;

	DFS(idx + list[idx].day, sum + list[idx].money);
	DFS(idx + 1, sum);
}

int main(void) {
	cin >> N;

	int d, m;
	for (int i = 0; i < N; i++) {
		cin >> d >> m;
		list.push_back(Task(d, m));
	}

	DFS(0, 0);

	cout << ans;
	return 0;
}