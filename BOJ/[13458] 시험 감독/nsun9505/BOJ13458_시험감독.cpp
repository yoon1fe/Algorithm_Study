#include <iostream>

using namespace std;

int arr[1000001];
int ans[1000001];

int main(void) {
	int N, A, B;
	cin >> N;

	long long sum = 0;
	for (int i = 0; i < N; i++)
		cin >> arr[i];

	cin >> A >> B;
	sum += N;
	for (int i = 0; i < N; i++) {
		if (ans[arr[i]] != 0) {
			sum += ans[arr[i]];
			continue;
		}

		int ret = 0;
		int tmp = arr[i] - A;
		if (tmp > 0) {
			ret += (tmp / B);
			if (tmp % B != 0)
				ret++;
		}

		ans[arr[i]] = ret;
		sum += ret;
	}

	cout << sum;
	return 0;
}