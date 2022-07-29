import java.io.*;
import java.util.Scanner;

public class Lab8 {
    int k = 8;

    public static void main(String[] args) throws IOException {
        Lab8 m = new Lab8();
        m.create();
        m.mergeFiles();
        m.display();
    }

    public void create() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        for (int i = 1; i <= k; i++) {
            System.out.println("Enter the of names in list " + i + ". Enter # to terminate list");
            PrintWriter pw = new PrintWriter("list" + i + ".txt");
            String[] temp = new String[50];
            String str;
            int j = 0;
            while (!((str = scan.nextLine()).equals("#")))
                temp[j++] = str;
            sort(temp, j);
            for (int k = 0; k < j; k++)
                pw.println(temp[k]);
            pw.flush();
            pw.close();
        }
        scan.close();
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

    public void mergeFiles() throws IOException {
        int n = k;
        while (n > 1) {
            int count = 1;
            for (int i = 1; i <= n; i += 2) {
                File f1 = new File("list" + i + ".txt");
                File f2 = new File("list" + (i + 1) + ".txt");
                File f3 = new File("list" + i + (i + 1) + ".txt");
                BufferedReader br1 = new BufferedReader(new FileReader(f1));
                BufferedReader br2 = new BufferedReader(new FileReader(f2));
                PrintWriter pw = new PrintWriter(f3);
                String name1 = br1.readLine();
                String name2 = br2.readLine();
                while (name1 != null && name2 != null) {
                    if (name1.equals(name2)) {
                        pw.println(name1);
                        pw.println(name2);
                        name1 = br1.readLine();
                        name2 = br2.readLine();
                    } else if (name1.compareTo(name2) < 0) {
                        pw.println(name1);
                        name1 = br1.readLine();
                    } else {
                        pw.println(name2);
                        name2 = br2.readLine();
                    }
                }
                if (name1 == null) {
                    while (name2 != null) {
                        pw.println(name2);
                        name2 = br2.readLine();
                    }
                }
                if (name2 == null) {
                    while (name1 != null) {
                        pw.println(name1);
                        name1 = br1.readLine();
                    }
                }
                pw.close();
                br1.close();
                br2.close();
                f1.delete();
                f2.delete();
                f3.renameTo(new File("list" + count + ".txt"));
                count++;
            }
            n /= 2;
        }
    }

    public void display() throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(new
                FileInputStream("list1.txt")));
        String l;
        System.out.println("\nThe merged list is:");
        while ((l = b.readLine()) != null)
            System.out.println(l);
        b.close();
    }
}