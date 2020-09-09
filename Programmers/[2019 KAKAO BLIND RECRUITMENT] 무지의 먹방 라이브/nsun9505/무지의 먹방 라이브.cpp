#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

struct food {
    int idx;
    int sec;
};

bool compIdx(food a, food b) {
    return a.idx < b.idx;
}

bool compSec(food a, food b) {
    return a.sec < b.sec;
}

int solution(vector<int> food_times, long long k) {
    vector<food> foods;
    int N = food_times.size();

    int idx = 1;
    for (int f : food_times)
        foods.push_back({ idx++, f });
    sort(foods.begin(), foods.end(), compSec);
    
    long long before = 0L;
    for (vector<food>::iterator it = foods.begin(); it != foods.end(); it++, N--) {
        long long cur = (it->sec - before) * N;
        if (cur <= k) {
            k -= cur;
            before = it->sec;
        }
        else {
            sort(it, foods.end(), compIdx);
            return (it + (k % N))->idx;
        }
    }
    return -1;
}

int main(void) {
    long long K = 1833;
    vector<int> food_times = { 946, 314, 757, 322, 559, 647, 983, 482, 145 };
    cout << solution(food_times, K);
    return 0;
}