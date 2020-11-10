package classes;

public class ParenChecker {
    // Class fields
    private static final String OPEN = "([{";
    private static final String CLOSE = ")]}";

    public static boolean isBalance(String expression) {
        //Complete
        StackSLL<Character> s = new StackSLL<Character>();
        int i = 0;
        boolean balanced = true;

        while (i<expression.length() && balanced) {
            if (isOpen(expression.charAt(i))) {
                // opening delimeter
                s.push(expression.charAt(i));
                } else {
                //closing delimeter
                balanced = !s.empty() && OPEN.indexOf(s.pop()) == CLOSE.indexOf(expression.charAt(i));
            }
            i++;
        }
        return balanced && s.empty();
    }

    private static boolean isOpen(char ch) {
        return OPEN.indexOf(ch) > -1;
    }

    private static boolean isClose(char ch) {
        return CLOSE.indexOf(ch) > -1;
    }

    public static void main(String[] args) {
        System.out.println(ParenChecker.isBalance("()[()]"));
        System.out.println(ParenChecker.isBalance("([()]"));
        System.out.println(ParenChecker.isBalance("()[()[[})]"));
    }


}
