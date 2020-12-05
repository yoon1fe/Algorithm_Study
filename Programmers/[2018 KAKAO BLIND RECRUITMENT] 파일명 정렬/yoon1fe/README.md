## [2018 KAKAO BLIND RECRUITMENT] 파일명 정렬 - Java

###    :file_folder: ​분류

> 문자열 처리
>
> PQ



###  :file_folder: 코드

```java
import java.util.*;

class Solution {
    static class File implements Comparable<File> {
        String name, head, tail;
        int order, number;

        File(String filename, int order){
            this.name = filename;
            this.order = order;

            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < filename.length(); i++) {
                if('0' <= filename.charAt(i) && filename.charAt(i) <= '9') {
                    this.head = sb.toString();
                    sb.delete(0, sb.length());


                    for(int j = i; j < i + 5; j++) {
                        if(filename.length() <= j) {                        // TAIL 없는 경우
                            this.number = Integer.parseInt(sb.toString());
                            this.tail = "";
                            return;
                        }
                        if('0' <= filename.charAt(j) && filename.charAt(j) <= '9') {
                            sb.append(filename.charAt(j));
                        }else {                                                // NUMBER 끝
                            i = j;
                            break;
                        }
                    }

                    this.tail = sb.length() == 5 ? filename.substring(i+5) : filename.substring(i);
                    this.number = Integer.parseInt(sb.toString());

                    break;
                }
                sb.append(filename.charAt(i));
            }
        }

        // 정렬 기준: HEAD -> NUMBER -> 들어온 순서 
        public int compareTo(File o) {
            if(this.head.toLowerCase().equals(o.head.toLowerCase())) {
                if(this.number == o.number) return this.order - o.order;
                return this.number - o.number;
            }
            return this.head.toLowerCase().compareTo(o.head.toLowerCase());
        }
    }

    public String[] solution(String[] files) {
        String[] answer;
        PriorityQueue<File> pq = new PriorityQueue<>();

        for(int i = 0; i < files.length; i++) {
            File file = new File(files[i], i);
            pq.offer(file);
        }

        answer = new String[files.length];
        int idx = 0;

        while(!pq.isEmpty()) {
            answer[idx++] = pq.poll().name;
        }

        return answer;
    }
}
```



### :file_folder: ​풀이 방법

많이 어렵지 않은 문제입니다. 문자열을 이쁘게 자르는게 관건입니다.



File 이란 클래스를 선언해서 써먹었습니다. 멤버 변수로는 answer에 넣을 **name, HEAD, NUMBER, TAIL**, 그리고 마지막 정렬 기준인 들어온 순서(**order**)가 있습니다.

 

정답은 files의 원소인 문자열(파일 이름)들을 하나씩 갖고와서 File 인스턴스로 만들어서 우선순위 큐에 넣어주고 순서대로 빼주면 쉽게 구할 수 있습니다.

 

관건은 앞서 말했듯이 문자열 처리입니다.. 제 코드는 상당히 더럽습니다. ㅜ

TAIL이 없는 경우와 중간의 숫자의 길이가 5를 넘어가는 경우를 신경써야 합니다!! 

 

###  :file_folder: 후기 

문자열을 갖고 노는건 아직 서툽니다 ㅜ

감사합니다 화이팅!!