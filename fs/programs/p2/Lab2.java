import java.io.*;
import java.util.Scanner;

public class Lab2 {
    final int size = 50;
    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException, NullPointerException {
        Lab2 obj = new Lab2();
        int choice;
        while (true) {
            System.out.println("**********************");
            System.out.println("1.Pack()");
            System.out.println("2.Unpack()");
            System.out.println("3.Search()");
            System.out.println("4.Modify()");
            System.out.println("5.Exit");
            System.out.println("**********************");
            System.out.println("Please enter your choice:");
            choice = obj.scan.nextInt();
            obj.scan.nextLine();
            switch (choice) {
                case 1:
                    obj.pack();
                    break;
                case 2:
                    obj.unpack();
                    break;
                case 3:
                    obj.search();
                    break;
                case 4:
                    obj.modify();
                    break;
                case 5:
                    System.out.println("You chose exit!");
                    System.exit(0);
                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    public void pack() throws FileNotFoundException {
        System.out.println("Enter Name, USN, Sem and Branch ");
        String name = scan.nextLine();
        String usn = scan.nextLine();
        String sem = scan.nextLine();
        String branch = scan.nextLine();
        PrintWriter pw = new PrintWriter(new FileOutputStream("student.txt", true));
        String b = name + "|" + usn + "|" + sem + "|" + branch + "|";
        int len = b.length();
        String s1 = "-";
        if (len < size) {
            for (int j = len; j < size; j++)
                b = b.concat(s1);
        }
        pw.println(b);
        pw.flush();
        pw.close();
    }

    public void unpack() throws IOException {
        String name, usn, sem, branch, s;
        BufferedReader br = new BufferedReader(new FileReader("student.txt"));
        while ((s = br.readLine()) != null) {
            String[] result = s.split("\\|");
            name = result[0];
            usn = result[1];
            sem = result[2];
            branch = result[3];
            System.out.println("The details are: " + name + " " + usn + " " + sem + " " +
                    branch);
        }
        br.close();
    }

    public void search() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("student.txt"));
        String name, usn, sem, branch, r;
        System.out.println("Enter the usn");
        String usn1 = scan.nextLine();
        while ((r = br.readLine()) != null) {
            String[] result = r.split("\\|");
            name = result[0];
            usn = result[1];
            sem = result[2];
            branch = result[3];
            if (usn.equals(usn1)) {
                System.out.println("Match found. The details of the record are:");
                System.out.println(name + " " + usn + " " + sem + " " + branch);
                br.close();
                return;
            }
        }
        System.out.println("Record not found");
        br.close();
    }

    public void modify() throws IOException, NullPointerException {
        String name, usn, sem, branch, r;
        File file = new File("student.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        File temp = new File("temp.txt");
        PrintWriter pw = new PrintWriter(temp);
        System.out.println("Enter usn");
        String usn1 = scan.nextLine();
        while ((r = br.readLine()) != null) {
            String[] result = r.split("\\|");
            name = result[0];
            usn = result[1];
            sem = result[2];
            branch = result[3];
            if (usn.equals(usn1)) {
                System.out.println("The details are: " + name + " " + usn + " " + sem +
                        " " + branch);
                System.out.println("enter name, usn,sem and branch");
                String name11 = scan.nextLine();
                String usn11 = scan.nextLine();
                String sem11 = scan.nextLine();
                String branch11 = scan.nextLine();
                String b = name11 + "|" + usn11 + "|" + sem11 + "|" + branch11 + "|";
                int le = b.length();
                String s1 = "-";
                if (le < 50) {
                    for (int j = le; j <= 50; j++)
                        b = b.concat(s1);
                    pw.println(b);
                }
            } else {
                pw.println(r);
            }
        }
        pw.flush();
        pw.close();
        br.close();
        file.delete();
        temp.renameTo(file);
        System.out.println("File Modified");
    }
}