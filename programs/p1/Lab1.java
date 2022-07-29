import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lab1 {
    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Lab1 obj = new Lab1();
        int choice;
        while (true) {
            System.out.println("**********************");
            System.out.println("1. Accept Input from Standard Input Device");
            System.out.println("2. Accept Input from File");
            System.out.println("3. Exit");
            System.out.println("**********************");
            System.out.println("Please enter your choice:");
            choice = obj.scan.nextInt();
            switch (choice) {
                case 1:
                    obj.StdInput();
                    break;
                case 2:
                    obj.FileInput();
                    break;
                case 3:
                    System.out.println("You chose exit!");
                    System.exit(0);
                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    void StdInput() {
        String org, rev;
        int n;
        System.out.println("Enter the number of names to be Reversed");
        n = scan.nextInt();
        scan.nextLine();
        while (n != 0) {
            System.out.println("Enter the name to be Reversed");
            org = scan.nextLine();
            rev = Rev_String(org);
            System.out.println("Reverse of entered string is: " + rev);
            n = n - 1;
        }
    }

    void FileInput() throws IOException {
        System.out.println("Enter the input file name(with extension)");
        String infile = scan.next();
        System.out.println("Enter the output file name(with extension)");
        String outfile = scan.next();
        String org, rev;
        BufferedReader br = new BufferedReader(new FileReader(infile));
        PrintWriter pw = new PrintWriter(outfile);
        while ((org = br.readLine()) != null) {
            rev = Rev_String(org);
            pw.println(rev);
        }
        pw.flush();
        System.out.println("All Reverse Names written to " + outfile);
        br.close();
        pw.close();
    }

    String Rev_String(String org) {
        StringBuilder sb = new StringBuilder(org);
        sb.reverse();
        return sb.toString();
    }
}