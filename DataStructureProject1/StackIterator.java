import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class StackIterator {
    private final BrowserNavigation browser;

    public StackIterator(BrowserNavigation browser) {
        this.browser = browser;
    }

    public void save() {

        try(PrintWriter debugWriter = new PrintWriter(new FileWriter("session_debug.txt"))){
            debugWriter.println("Session Debug Information");
            debugWriter.println("Current Site: " + browser.getCurrentPage());
            debugWriter.println("\nBack Stack: ");
            for (String site : browser.getBackStack()) { // Iterate through the backStack to display the sites
                debugWriter.println(site);
            }
            debugWriter.println("\nForward Stack: ");
            for (String site : browser.getForwardStack()) { // Iterate through the forwardStack to display the sites
                debugWriter.println(site);
            }
            debugWriter.println("\nHistory: ");
            for (String site : browser.getHistory()) { // Iterate through the history queue to display the sites
                debugWriter.println(site);
            }

            debugWriter.flush();

        } catch (IOException e) {
        }
    }
}
