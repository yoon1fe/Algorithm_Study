# [2019 KAKAO BLIND RECURITMENT] 후보키 - C++

## 분류
> 조합(?)

## 코드
```c++
#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <set>

using namespace std;
int N;
vector<vector<int>> candidateKeys;
vector<vector<string>> rows;

void DFS(int cur, vector<int> keys) {
    if (cur == N) {
        set<vector<string>> ret;
        // 유일성 만족 검사
        for (auto row : rows) {
            vector<string> prime;
            for (int i = 0; i < keys.size(); i++) {
                for (int j = 0; j < row.size(); j++) {
                    if (keys[i] == j) {
                        prime.push_back(row[j]);
                        break;
                    }
                }
            }
            if (ret.count(prime) == 1)
                return;
            ret.insert(prime);
        }

        // 최소성 만족 검사
        // ckey : 후보키 집합의 원소
        // keys : 유일성을 만족하는 슈퍼키
        for (int idx = 0; idx < candidateKeys.size(); idx++) {
            vector<int> ckey = candidateKeys[idx];
            if (ckey.size() <= keys.size())
                continue;

            int cnt = 0;
            for(int i=0; i<keys.size(); i++)
                for(int j=0; j<ckey.size(); j++)
                    if (keys[i] == ckey[j]) {
                        cnt++;
                        break;
                    }

            if (cnt == keys.size())
                candidateKeys.erase(candidateKeys.begin() + idx--);
        }

        candidateKeys.push_back(keys);
        return;
    }

    keys.push_back(cur);
    DFS(cur + 1, keys);
    keys.pop_back();
    DFS(cur + 1, keys);
}

int solution(vector<vector<string>> relation) {
    int answer = 0;
    N = relation[0].size();
    rows = relation;
    vector<int> keys;
    DFS(0, keys);

    return (int)candidateKeys.size();
}

int main(void) {
    vector<vector<string>> relation;
    relation.push_back({ "100","ryan","music","2" });
    relation.push_back({ "200","apeach","math","2" });
    relation.push_back({ "300","tube","computer","3" });
    relation.push_back({ "400","con","computer","4" });
    relation.push_back({ "500","muzi","music","3" });
    relation.push_back({ "600","apeach","music","2" });

    cout << solution(relation);

    return 0;
}
```

## 문제 풀이
먼저 컬럼의 모든 조합을 생각한다.

컬럼의 조합이 완성이 되면
1. 해당 컬럼의 조합이 유일성을 만족하는지 검사한다.
   - 키 조합이 유일한지 점검하면 된다.
1. 해당 컬럼의 조합이 유일성을 만족한다면 최소성을 만족하는지 검사한다.
   - 유일성을 만족하면 일단 찾은 컬럼의 조합은 슈퍼키가 된다.
   - 그러면 현재 후보키 집합 중 어떤 원소가 지금 찾은 슈퍼키의 모든 컬럼을 포함하면고, 슈퍼키에 포함되지 않은 컬럼들도 포함한다면
   - 해당 원소를 삭제한다.
   - 결론은 현재 슈퍼키로 인해 후보키 집합 원소 중에서 최소성을 만족하지 않는 것들을 찾아서 삭제하면서 답을 찾아간다.

## 후기
데이터베이스 복습이 되는 문제였다..