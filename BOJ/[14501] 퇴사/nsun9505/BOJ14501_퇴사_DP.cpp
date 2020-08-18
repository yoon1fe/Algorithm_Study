#include <iostream>
using namespace std;

int dp[1000000];

int main(void) {
	int N;
	cin >> N;

	int d, m;
	int ans = 0;
	for (int i = 0; i < N; i++) {
		cin >> d >> m;
		if (i + d <= N && dp[i + d] < dp[i] + m) {
			dp[i + d] = dp[i] + m;
			ans = ans < dp[i + d] ? dp[i + d] : ans;
		}
		dp[i + 1] = dp[i + 1] < dp[i] ? dp[i] : dp[i + 1];
		ans = ans < dp[i + 1] ? dp[i + 1] : ans;
	}

	cout << ans;
	return 0;
}