# [2019 KAKAO BLIND RECURITEMENT] 실패율 - C++

## 분류
> 단순 구현

## 코드
```c++
#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

bool cmp(pair<int, double> a, pair<int, double> b)  {
    if (a.second > b.second)
        return true;
    else if (a.second == b.second)
        return a.first < b.first;
    else
        return false;
}


vector<int> solution(int N, vector<int> stages) {
    vector<int> answer;
    map<int, int> count;
    vector<pair<int, double>> ret;
    for (int i = 0; i < stages.size(); i++) {
        count[stages[i]] += 1;
    }
    
    int numOfMember = stages.size();
    for (int stage = 1; stage <= N; stage++) {
        double rate = 0.0;
        if (count[stage] != 0 && numOfMember != 0) {
            rate = (double)count[stage] / numOfMember;
            numOfMember -= count[stage];
        }
        ret.push_back({ stage, rate });
    }
    sort(ret.begin(), ret.end(), cmp);

    for (auto elem : ret)
        answer.push_back(elem.first);

    return answer;
}

int main(void) {
    int N = 4;
    vector<int> stages = { 4,4,4,4,4 };
    vector<int> ret = solution(N, stages);
    for (int elem : ret)
       cout << elem << ' ';
    return 0;
}
```

## 문제 풀이
1. 각 stage에 머물고 있는 사람을 count map에 담는다.
1. 각 스테이지에 실패율을 계산한다.
   - 실패율은 **스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수**
   - 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수는 count map에 저장되어 있고
   - 스테이지에 도달한 플레이어 수(numOfMember)는 처음에 stages vector의 크기로 초기화한다.
   - numOfMember의 값은 다음 스테이지의 실패율을 계산하기 전에 현재 스테이지에 머물고 있는 사람의 수를 빼준다.
      - numberMember -= count[currentStage];
   - 그리고 0/0과 같은 경우는 계산할 필요가 없으므로 현재 스테이지를 클리어하지 못한 사람의 수가 0이 아니고 스테이지에 도달한 플레이어의 수도 0이 아닌 경우에만 실패율을 계산하도록 했다.
1. 각 stage의 실패율을 계산 후, 실패율에 따라 내림차순으로 정렬한다.
   - 만약 실패율이 같다면 stage 번호로 오름차순으로 정렬한다.
1. 답을 answer에 담아서 리턴!

## 후기
하.. cmp에서 first랑 second랑 비교를 했다... 

이런 실수하지 말자ㅠ