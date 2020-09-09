# [2019 KAKAO BLIND RECURITMENT] 무지의 먹방 라이브 - C++

## 분류
> ???

## 코드
```c++
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
```

## 문제 풀이
처음에는 일단 정확성을 위해 k초까지 루프를 돌도록 작성했다.
- 당연히 효율성은 빵점

효율성 테스트를 맞추기 위해 처음 생각한 것은 큐와 맵이였다.
- 큐는 음식들의 순서를 유지하기 위해 사용했다.
- 맵에 현재 음식들의 시간을 저장해서 첫 번째 원소의 시간을 가져오면 가장 최소가 되는 값이 되고
- 최솟값과 현재 음식들의 수를 곱해서 k보다 작다면 음식들에서 최솟값만큼 뺴주고 다시 큐에 넣는다.
- 만약 k보다 크다면 최소시간만큼 돌지 못하기에 k를 현재 음식의 수로 나눈 나머지에 해당하는 인덱스의 음식 번호를 리턴하면 된다.

위와 같은 방식으로 했을 때 효율성 테스트를 하나 맞았다...

그래서 구글링해서 찾아본 방법은 아래와 같다.
1. 음식들을 먹는데 걸리는 시간순으로 오름차순 정렬한다.
1. 시간순으로 정렬된 음식에 대해 for문을 돌린다.
   - **cur = (it->sec - before) * N** 부분에서 it-sec은 현재 남은 음식들 중에서 가장 낮은 sec값이고, 
   - before는 it 이전에 음식들 중에서 가장 오래 걸리는 시간이다.
   - 만약에 N이 5이고, 음식이 시간순으로 1,2,3,4,5 정렬되었다면 0번째 음식을 먹는데 1초 걸렸으므로 다음 음식을 먹을 때는
   - 이전에 음식을 먹는데 걸렸던 시간만큼 빼주고, 거기에 현재 음식의 수를 곱함으로써 현재 남은 k초 안에 cur만큼 돌 수 있는지 점검하면 되는 것이다.
   - 만약 다 먹을 수 있다면 k를 cur만큼 감소시켜주고, before에는 현재 음식을 먹는데 걸리는 시간을 저장하면서 진행하면 된다.
   - 만약 k가 작다면, 현재 위치에서 끝까지 음식의 인덱스 번호에 따라 정렬한 뒤에
   - 현재 남은 k초를 현재 남은 음식의 수로 나눴을 때의 나머지에 해당하는 음식의 인덱스를 리턴하면 된다.
1. 만약 for문이 정상적으로 종료되었다면 모든 음식을 다 먹은 것이므로 -1을 리턴하면 된다.

가장 최솟값을 전체적으로 빼주는 것이 아니라 다음 원소에서만 빼주는 것이 효율성을 통과하는데 핵심인 것 같다.

## 후기
내가 접근했던 방법에서 중복되는 것을 찾아서 제거 했다면 내힘으로도 풀 수 있었지 않을까

재미있는 문제였음!