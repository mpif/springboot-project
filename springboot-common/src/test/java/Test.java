/**
 * @Author: codefans
 * @Date: 2022-07-28 16:18
 */

public class Test {

    @org.junit.Test
    public void test() {




    }

    public static void main(String[] args) {
        for(int i = 1; i <= 10; i ++) {
            System.out.println(toNum(i));
        }
    }

    private static int toNum(int n) {
        return ((n & 1) != 0) ? (n + 1) : n;
    }
}
