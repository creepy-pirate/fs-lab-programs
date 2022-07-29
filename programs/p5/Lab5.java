import java.io.*;
import java.util.Scanner;

public class Lab5 {
    public static int count;
    public static final int[] Address_list = new int[100];
    public static final String[] usn_list = new String[100];
    public static Scanner s = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Lab5 obj = new Lab5();
        obj.create_index();
        int ch;
        System.out.println("******Menu******");
        System.out.println("1. Add Record");
        System.out.println("2. Search Record");
        System.out.println("3. Remove Record");
        System.out.println("4. Exit");
        System.out.println("****************");
        while (true) {
            System.out.println("\nPlease enter your choice:");
            ch = s.nextInt();
            s.nextLine();
            switch (ch) {
                case 1:
                    obj.insert();
                    break;
                case 2:
                    obj.search();
                    break;
                case 3:
                    obj.remove();
                    break;
                case 4:
                    System.out.println("Do you want to exit? (Y/N)");
                    if (s.next().equalsIgnoreCase("y")) {
                        System.out.println("Program Ended");
                        System.exit(0);
                    }
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    public void create_index() throws IOException, ArrayIndexOutOfBoundsException {
        count = -1;
        long pos;
        RandomAccessFile file = new RandomAccessFile("f1.txt", "r");
        pos = file.getFilePointer();
        String s;
        while ((s = file.readLine()) != null) {
            String[] result = s.split("\\|");
            count++;
            usn_list[count] = result[0];
            Address_list[count] = (int) pos;
            pos = file.getFilePointer();
        }
        file.close();
        sort_index();
    }

    public void sort_index() {
        for (int i = 0; i <= count; i++) {
            for (int j = i + 1; j <= count; j++) {
                if (usn_list[i].compareTo(usn_list[j]) > 0) {
                    String temp = usn_list[i];
                    usn_list[i] = usn_list[j];
                    usn_list[j] = temp;
                    int temp1 = Address_list[i];
                    Address_list[i] = Address_list[j];
                    Address_list[j] = temp1;
                }
            }
        }
    }

    public void insert() throws IOException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(new File("f1.txt"), true));
        System.out.println("Enter USN, Name, Sem and Branch ");
        String usn = s.nextLine();
        String name = s.nextLine();
        String sem = s.nextLine();
        String branch = s.nextLine();
        String b = usn + "|" + name + "|" + sem + "|" + branch + "|" + "$";
        pw.println(b);
        pw.close();
        create_index();
    }

    public void search() throws IOException {
        int pos;
        System.out.println("Enter the usn to be searched");
        String key = s.nextLine();
        pos = search_index(key);
        if (pos != -1)
            display_record(pos);
        else
            System.out.println("Record not found");
    }

    public int search_index(String key) {
        int low = 0, high = count, mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (usn_list[mid].equals(key))
                return mid;
            if (usn_list[mid].compareTo(key) > 0)
                high = mid - 1;
            if (usn_list[mid].compareTo(key) < 0)
                low = mid + 1;
        }
        return -1;
    }

    public void display_record(int pos) throws IOException {
        RandomAccessFile file = new RandomAccessFile("f1.txt", "r");
        int address = Address_list[pos];
        String usn, sem, branch, name;
        file.seek(address);
        String s = file.readLine();
        while (s != null) {
            String[] result = s.split("\\|");
            usn = result[0];
            name = result[1];
            sem = result[2];
            branch = result[3];
            System.out.println("\nRecord Details");
            System.out.println("USN: " + usn);
            System.out.println("Name: " + name);
            System.out.println("Sem: " + sem);
            System.out.println("Branch: " + branch);
            break;
        }
        file.close();
    }

    public void remove() throws IOException {
        System.out.println("Enter the key to be deleted");
        String key = s.nextLine();
        int pos = search_index(key);
        if (pos != -1) {
            delete_from_file(pos);
            create_index();
        } else
            System.out.println("Record not found");
    }

    public void delete_from_file(int pos) throws IOException {
        display_record(pos);
        RandomAccessFile file = new RandomAccessFile("f1.txt", "rw");
        System.out.println("Are you sure you want to delete? (Y/N)");
        String ch = s.nextLine();
        if (ch.equalsIgnoreCase("y")) {
            int address = Address_list[pos];
            String del_ch = "*";
            file.seek(address);
            file.writeBytes(del_ch);
            System.out.println("Record is deleted");
        }
        file.close();
    }
}