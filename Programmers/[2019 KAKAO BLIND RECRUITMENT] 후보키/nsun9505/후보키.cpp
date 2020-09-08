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