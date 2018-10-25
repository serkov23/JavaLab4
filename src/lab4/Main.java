package lab4;

import java.util.Scanner;

class MyNumber implements Comparable<MyNumber> {
    private char c;

    MyNumber(char c) {
        this.c = c;
    }


    public String toString() {
        return Character.toString(c);
    }

    @Override
    public int compareTo(MyNumber o) {
        return c - o.c;
    }

    public boolean equals(MyNumber o) {
        return c == o.c;
    }
}

public class Main {
    private static final String HELP_MESSAGE = "Next commands are available:\n" +
            "\tadd <char>\n" +
            "\tfind <char>\n" +
            "\tprintLVR\n" +
            "\tprintVLR\n" +
            "\tprintLRV\n" +
            "\tdelete <char>\n" +
            "\tshowTree\n" +
            "\thelp\n" +
            "\tquit\n" +
            "Please enter command";
    private static final String ERROR_MESSAGE = "No such command";

    public static void main(String[] args) {
        var tree = new Tree<MyNumber>();
        /*
            var tree = new Tree<MyNumber>();

            int[] arr = {'5', '4', '3', '2', '1', '7', '9', '0', '4', '6'};
            for (int i = 0; i < 10; i++)
                tree.add(arr[i]);
            System.out.println(tree.goLRV());
            System.out.println(tree.goVLR());
            System.out.println(tree.goLVR());
        */
        //TODO: make function for int(
        Scanner reader = new Scanner(System.in);
        System.out.println(HELP_MESSAGE);
        while (true) {
            var message = reader.next();
            switch (message) {
                case "add":
                    tree.add(new MyNumber(reader.next().charAt(0)));
                    break;
                case "delete":
                    tree.delete(new MyNumber(reader.next().charAt(0)));
                    break;
                case "find":
                    tree.find(new MyNumber(reader.next().charAt(0)));
                    break;
                case "printLVR":
                    System.out.println(tree.goLVR());
                    break;
                case "printLRV":
                    System.out.println(tree.goLRV());
                    break;
                case "printVLR":
                    System.out.println(tree.goVLR());
                    break;
                case "showTree":
                    (new Printer<MyNumber>()).printPyramid(tree, new MyNumber('\0'));
                    break;
                case "help":
                    System.out.println(HELP_MESSAGE);
                    break;
                case "q":
                case "quit":
                    return;
                default:
                    System.out.println(ERROR_MESSAGE);
                    System.out.println("\n" + HELP_MESSAGE);
            }
            System.out.println("ok");
        }
    }


}

class Printer<T extends Comparable<T>> {
    private static void printFillers(int n, String filler) {
        for (int i = 0; i < n; i++)
            System.out.print(filler);
    }

    void printPyramid(Tree<T> tree, T filler) {
        var a = tree.getPyramid(filler);
        int n = a.size() - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < a.elementAt(i).size(); j++) {
                printFillers((1 << (n - i - 1)) - 1, "\t");
                printFillers(1 << (n - i - 1), a.elementAt(i + 1).elementAt(j << 1).equals(filler) ? "\t" : "____");
                System.out.print(a.elementAt(i).elementAt(j).equals(filler) ? '\t' : widen(a.elementAt(i).elementAt(j)));
                printFillers(1 << (n - i - 1), a.elementAt(i + 1).elementAt(1 + (j << 1)).equals(filler) ? "\t" : "____");
                printFillers(1 << (n - i - 1), "\t"); //2^(n-i-1) -1
            }
            System.out.println();
        }
        for (var i : a.elementAt(n))
            System.out.print((i.equals(filler) ? '\t' : widen(i)) + "\t");
        System.out.println();
    }

    String widen(Object val) {
        var str = new StringBuilder(val.toString());
        str = str.reverse();
        while (str.length() < 4)
            str.append(' ');
        return str.reverse().toString();

    }
}