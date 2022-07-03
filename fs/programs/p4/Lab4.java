import java.io.*;
import java.util.Scanner;

public class Lab4 {
    public static int count;
    public static final int[] rrn = new int[20];
    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Lab4 obj = new Lab4();
        obj.createRRN();

        int choice;

        while (true) {
            System.out.println("**********************");
            System.out.println("1. Pack()");
            System.out.println("2. Unpack()");
            System.out.println("3. Search()");
            System.out.println("4. Exit");
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
                    System.out.println("Enter the rrn number to search the record");
                    int r = obj.scan.nextInt();
                    obj.search(r);
                    break;
                case 4:
                    System.out.println("You chose exit!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public void createRRN() throws IOException {
        count = -1;
        long pos;
        RandomAccessFile file = new RandomAccessFile("student1.txt", "r");
        pos = file.getFilePointer();
        String s;
        while ((s = file.readLine()) != null) {
            count++;
            rrn[count] = (int) pos;
            pos = file.getFilePointer();
            String[] result = s.split("\\|");
            String name = result[0];
            System.out.println("The rrn for " + name + " is " + count);
        }
        file.close();
    }

    public void pack() throws IOException {
        System.out.println("Enter Name, USN, Sem and Branch: ");
        String name = scan.nextLine();
        String usn = scan.nextLine();
        String sem = scan.nextLine();
        String branch = scan.nextLine();
        PrintWriter pw = new PrintWriter(new FileOutputStream(new File("student1.txt"), true));
        String b = name + "|" + usn + "|" + sem + "|" + branch + "|" + "$";
        pw.println(b);
        pw.flush();
        pw.close();
        createRRN();
    }

    public void unpack() throws IOException {
        String name = "", usn = "", sem = "", branch = "", s;
        BufferedReader br = new BufferedReader(new FileReader("student1.txt"));
        while ((s = br.readLine()) != null) {
            String[] result = s.split("\\|");
            name = result[0];
            usn = result[1];
            sem = result[2];
            branch = result[3];
            System.out.println("The details are: " + name + " " + usn + " " + sem + " " + branch);
        }
        br.close();
    }

    public void search(int x) throws IOException {
        RandomAccessFile file = new RandomAccessFile("student1.txt", "rw");
        if (x > count) {
            System.out.println("Record not found");
            file.close();
            return;
        } else {
            int pos = rrn[x];
            file.seek(pos);
            String a = file.readLine();
            System.out.println("the Record is :" + a);
        }
        file.close();
    }
}