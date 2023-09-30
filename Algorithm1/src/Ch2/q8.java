package Ch2;
//Coding Section 3.2 (Q8)

public class q8 {

    public static void main(String[] args) {
        String string = "CABAAXBYA";//string 
        char[] str = string.toCharArray();
        int n = str.length;
        System.out.println(SubStringsCounter(str, n));
        System.out.println(SubStringsCounterBetter(str, n));
    }

// a- counting the number of substrings that start with A and end with B --> eficiency = n^2
    public static int SubStringsCounter(char[] str, int n) {
        int count = 0; // Initialize count

        // pass through the string
        for (int i = 1; i < n - 1; i++) {
            if (str[i] == 'A') {
                // count the number of all B after A founded 
                for (int j = i; j < n; j++) {
                    if (str[j] == 'B') {
                        count++;
                    }
                }
            }
        }
        return count;
    }

// b- counting the number of substrings that start with A and end with B --> eficiency = n
    public static int SubStringsCounterBetter(char[] str, int n) {
        int countSub = 0, countA = 0; // Initialize count for the desired substring and count for appearance of A

        // pass through the string
        for (int i = 1; i < n; i++) {
            if (str[i] == 'A') {
                countA++;
            }
            if (str[i] == 'B') {
                countSub += countA;
            }

        }
        return countSub;

    }
}
