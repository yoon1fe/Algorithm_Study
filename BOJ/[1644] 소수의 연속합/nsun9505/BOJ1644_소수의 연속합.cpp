#include <iostream>
#include <vector>

using namespace std;
int N;
bool isNotPrime[4000001];

vector<int> getPrime() {
	vector<int> primes;

	isNotPrime[0] = true;
	isNotPrime[1] = true;

	for (int i = 2; i <= N; i++) {
		if (isNotPrime[i]) continue;

		for (int j = 2 * i; j <= N; j += i)
			if (!isNotPrime[i])
				isNotPrime[j] = true;
	}

	for (int i = 2; i <= N; i++)
		if (!isNotPrime[i])
			primes.push_back(i);

	return primes;
}

int main(void) {
	ios_base::sync_with_stdio(0);
	cin >> N;
	if (N == 1) {
		cout << 0;
		return 0;
	}
	vector<int> primes = getPrime();

	int start = 0;
	int end = 0;
	int cnt = 0;
	int sum = primes[0];
	while (true) {
		if (sum >= N) {
			if (sum == N)
				cnt++;
			sum -= primes[start++];
		}
		else {
			end++;
			if (end < primes.size())
				sum += primes[end];
			else
				break;
		}
	}
	cout << cnt;
	return 0;
}