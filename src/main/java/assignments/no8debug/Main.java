package assignments.no8debug;

public class Main {
    static final String vowels = "aeiou";
    static final String bob = "bob";

    //Code your solution to problem number one here
    static int problemOne(String s) {
        //TODO
        int vowel = 0;

        for (int i = 0; i < s.length(); i++) {
            if (vowels.indexOf(s.charAt(i)) != -1)
                vowel++;
        }

        return vowel;
    }

    //Code you problem number two here
    static int problemTwo(String s) {
        //TODO
        int bobCount = 0;

        for (int i = 0; i < s.length() - 2; i++) {
            if (s.startsWith(bob, i))
                bobCount++;
        }

        return bobCount;
    }

    //Code your solution to problem number 3 here
    static String problemThree(String s) {
        //TODO
        int start = 0, len = 1, curLen = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) >= s.charAt(i - 1))
                curLen++;
            else {
                if (curLen > len) {
                    start = i - curLen;
                    len = curLen;
                }

                curLen = 1;
            }
        }

        if (curLen > len) {
            start = s.length() - curLen;
            len = curLen;
        }

        return s.substring(start, start + len);
    }

    public static void main(String[] args) {
        /*
        Set s to a string and run your method using s as the parameter
        Run your method in a println statement to determine what the output was
        Once you think you have it working try running the tests.
        The tests will put your method through several different Strings to test
        all possible cases.  If you have 100% success then there is no bugs in your methods.
         */
        System.out.println("Number of vowels: " + problemOne("azcbobobegghakl"));
        System.out.println("Number of bobs: " + problemTwo("azcbobobegghakl"));
        System.out.println("Longest: " + problemThree("azcbobobegghakl"));
    }
}