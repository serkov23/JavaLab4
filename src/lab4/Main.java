package lab4;

/*
class Int {
    int a;

    Int(int a) {
        this.a = a;
    }
}
*/

public class Main {
    public static void main(String[] args) {
        var tree = new Tree<Character>();
        char[] arr = {'9','0','3','8','6','9','3','4','5','3'};
        for (int i = 0; i < 10; i++)
            tree.add(arr[i]);
        System.out.println(tree.goLRV());
        System.out.println(tree.goVLR());
        System.out.println(tree.goLVR());
        System.out.println(tree.getPyramid(' '));
    }
}
