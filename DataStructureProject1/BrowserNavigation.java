import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class BrowserNavigation implements Serializable {
    private static final long serialVersionUID = 1L;

    private final BrowserStack<String> backStack;
    private BrowserStack<String> forwardStack;
    private BrowserQueue<String> history;
    private String currentPage;
    
    public BrowserNavigation(int historyCapacity) {
        backStack = new BrowserStack<>();
        forwardStack = new BrowserStack<>();
        history = new BrowserQueue<>(historyCapacity);
        currentPage = null;
    }
    
    public String visitWebsite(String url) {
        if (currentPage != null) { // If first visit, don't add to backStack
            backStack.push(currentPage);
        }
        currentPage = url;
        forwardStack = new BrowserStack<>(); // Clear forward history when visiting a new page
        history.enqueue(url);

        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            }
        } catch (IOException | URISyntaxException e) {
            System.out.println("Error opening website: " + e.getMessage());
        }
        
        return "Now at " + url;
    }
    
    public String goBack() {
        if (backStack.isEmpty()) { // Check if there is a previous page
            return "No previous page available.";
        }
        forwardStack.push(currentPage);
        currentPage = backStack.pop();
        return "Now at " + currentPage;
    }
    
    public String goForward() {
        if (forwardStack.isEmpty()) { // Check if there is a forward page to pass through
            return "No forward page available.";
        }
        backStack.push(currentPage); // Add current page to backStack and then go to the forward page
        currentPage = forwardStack.pop();
        return "Now at " + currentPage;
    }
    
    public String showHistory() {
        if (history.isEmpty()) {
            return "No browsing history available.";
        }
        StringBuilder sb = new StringBuilder("Browsing History: \n");
        for (String site : history) { // Iterate through the history queue to display the sites
            sb.append(site).append("\n");
        }
        return sb.toString();
    }
    
    public String clearHistory() { // Create a new BrowserQueue to clear the history
        history = new BrowserQueue<>(history.size());
        return "Browsing history cleared.";
    }

    public String closeBrowser() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("session_data.txt"))){
    
            StackIterator stackIterator = new StackIterator(this);
            stackIterator.save();

            out.writeObject(this); // Save browser by serialization
            return "Browser session saved.";
    
        } catch (IOException e) {
            return "Error saving session: " + e.getMessage();
        }
    }    

    public String getCurrentPage() {
        return currentPage;
    }

    public BrowserStack<String> getBackStack() {
        return backStack;
    }

    public BrowserStack<String> getForwardStack() {
        return forwardStack;
    }

    public BrowserQueue<String> getHistory() {
        return history;
    }
}
