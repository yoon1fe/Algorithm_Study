#include <iostream>
using namespace std;

int getMin(int a, int b) {
	if (a < b)
		return a;
	return b;
}

int main(void) {
	int N;
	cin >> N;

	long long target;
	cin >> target;

	int start = 1;
	int end = target;
	int ret = 0;
	while (start <= end) {
		int mid = (start + end) / 2;
		long long num = 0;
		for (int i = 1; i <= N; i++)
			num += getMin(mid / i, N);

		if (num < target)
			start = mid + 1;
		else {
			ret = mid;
			end = mid - 1;
		}
	}

	cout << ret;
	return 0;
}