import java.io.*;
import java.util.Scanner;

public class Lab7 {
    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Lab7 obj = new Lab7();
        System.out.println("Enter the names in list 1 (Enter # to terminate the list)");
        obj.readNames("list1.txt");
        System.out.println("Enter the names in list 2 (Enter # to terminate the list)");
        obj.readNames("list2.txt");
        obj.combineLists();
        obj.display();
    }

    public void readNames(String fileName) throws FileNotFoundException {
        String[] s = new String[50];
        PrintWriter pw = new PrintWriter(fileName);
        int i, j;
        for (i = 0; ; i++) {
            s[i] = scan.nextLine();
            if (s[i].equals("#"))
                break;
        }
        sort(s, i);
        for (j = 0; j < i; j++)
            pw.println(s[j]);
        pw.close();
    }

    public void sort(String[] s, int count) {
        String temp;
        for (int i = 0; i < count; i++)
            for (int j = i + 1; j < count; j++)
                if (s[i].compareTo(s[j]) > 0) {
                    temp = s[i];
                    s[i] = s[j];
                    s[j] = temp;
                }
    }

    public void combineLists() throws IOException {
        BufferedReader br1 = new BufferedReader(new FileReader("list1.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("list2.txt"));
        PrintWriter pw = new PrintWriter("list3.txt");
        String name1 = br1.readLine();
        String name2 = br2.readLine();
        while (name1 != null && name2 != null) {
            if (name1.equals(name2)) {
                pw.println(name1);
                name1 = br1.readLine();
                name2 = br2.readLine();
            } else if (name1.compareTo(name2) < 0)
                name1 = br1.readLine();
            else
                name2 = br2.readLine();
        }
        pw.close();
        br2.close();
        br1.close();
    }

    public void display() throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("list3.txt"));
        String l = b.readLine();
        if (l == null)
            System.out.println("No matching string");
        else {
            System.out.println("Common names in both lists are:");
            do {
                System.out.println(l);
            } while ((l = b.readLine()) != null);
        }
        b.close();
    }
}