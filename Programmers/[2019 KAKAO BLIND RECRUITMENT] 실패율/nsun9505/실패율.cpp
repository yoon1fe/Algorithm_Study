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