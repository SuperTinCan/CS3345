import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BrowserNavigation browser = restoreLastSession();
        
        while (true) {
            System.out.println("\nBrowser Navigation System:");
            System.out.println("1. Visit Website");
            System.out.println("2. Go Back");
            System.out.println("3. Go Forward");
            System.out.println("4. Show Browsing History");
            System.out.println("5. Clear Browsing History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt(); // Get user choice
            scanner.nextLine();
            
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter URL: ");
                    String url = scanner.nextLine();
                    System.out.println(browser.visitWebsite(url));
                }
                case 2 -> System.out.println(browser.goBack());
                case 3 -> System.out.println(browser.goForward());
                case 4 -> System.out.println(browser.showHistory());
                case 5 -> System.out.println(browser.clearHistory());
                case 6 -> {
                    System.out.println(browser.closeBrowser());
                    System.out.println("Exiting Browser...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }

    }
    
    // Attempt to load the session data from a file, or create a new session if failed
    private static BrowserNavigation restoreLastSession() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("session_data.txt"))) {
            return (BrowserNavigation) in.readObject(); // Return previous session data
        } catch (IOException | ClassNotFoundException e) {
            return new BrowserNavigation(5); // Creates a completley new session
        }
    }
}
