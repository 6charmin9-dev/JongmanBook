import java.util.Scanner;
/*
# input
1
hi

# output
Hello, hi!
 */
public class _0_helloworld {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while(cases-- > 0) {
            String name = sc.next();
            System.out.println("Hello, " + name + "!");
        }
    }
}