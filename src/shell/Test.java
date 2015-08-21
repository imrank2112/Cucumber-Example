package shell;

public class Test {

public static void main(String[] args) {

    try {
        System.out.println(fact(1 << 15));
    } catch (StackOverflowError e) {
        System.err.println("true recursion level was "+level);
        System.err.println("reported recursion level was "+e.getStackTrace().length);
    }
}

private static int level = 0;
public static long fact(int n) {
    level++;
    return n < 2 ? n : n * fact(n - 1);
}
}