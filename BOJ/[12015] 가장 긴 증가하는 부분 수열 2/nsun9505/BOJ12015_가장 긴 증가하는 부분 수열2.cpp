#include <iostream>
#include <vector>
using namespace std;
int N;

int getIndex(vector<int> arr, int value) {
	int left = 0;
	int right = arr.size() - 1;
	while (left < right) {
		int mid = (left + right) / 2;
		if (arr[mid] < value)
			left = mid + 1;
		else
			right = mid;
	}
	return left;
}

int main(void) {
	ios_base::sync_with_stdio(0);
	vector<int> arr;
	
	int num;
	cin >> N;
	cin >> num;
	arr.push_back(num);
	for (int i = 1; i < N; i++) {
		cin >> num;
		if (arr[arr.size() - 1] < num)
			arr.push_back(num);
		else {
			int index = getIndex(arr, num);
			arr[index] = num;
		}
	}
	cout << arr.size();
	return 0;
}