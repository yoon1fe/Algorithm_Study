import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        Set<Integer> lenSet = new HashSet<>();
        Set<String> phSet = new HashSet<>();
        for (String phnum : phone_book) {
            if (phSet.contains(phnum))
                return false;

            for(int i : lenSet){
                if (phSet.contains(phnum.substring(0, i)))
                    return false;
            }
            phSet.add(phnum);
            lenSet.add(phnum.length());
        }
        return true;
    }
}