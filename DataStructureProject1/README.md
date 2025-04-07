## Personal Details
Artin Seyrafi
axs220370
Sec# 005

## Implementation
The project implements a custom browser navigation system using stacks and queues. Instead of using built-in Java collections:

`BrowserLinkedList` (Custom Doubly Linked List) - Used for implementing the stack.

`BrowserArrayList` (Custom Circular Array) - Used for implementing the queue.

`BrowserStack` - A wrapper around BrowserLinkedList to handle back and forward navigation.

`BrowserQueue` - A wrapper around BrowserArrayList to handle browsing history.

`StackIterator` - Custom iterator for saving stack state in a human readable text file.

`BrowserNavigation` - Core logic for browser operations such as visiting websites, going back, going forward, and clearing history.

`Main.java` - Driver program with a command-line interface, including session persistence using file serialization.

## O-Complexities

**BrowserLinkedList:**
`O(1)`: addLast(), removeLast() - Always interacts on very end of list
`O(n)`: iterator() - Loop through all nodes in list

**BrowserArrayList:**
`O(1)`: add(), remove() - Keeps track of the end of list
`O(n)`: resize() - Loop through all elements in list to copy

**BrowserStack:**
`O(1)`: push(), pop(), peek(), isEmpty() - All methods work on end of list

**BrowserQueue:**
`O(1)`: enqueue(), dequeue(), isEmpty() - All methods keep track of list position

**BrowserNavigation:**
`O(1)`: visitWebsite(), goBack(), goForward(), clearHistory() - All utilize Queue or Stack which are O(1)
`O(n)`: showHistory() - Loop through all visited websites

## Test Cases
input -> 8 : Check for invalid input
input -> 1 : Check if website is visited
repeat 20x (input -> 1, "hi..[n]"); n++; : Check for dynamic resizing
input -> 1, "Hi", 6 : Check if data saves
input -> 1, "Hi", 5, 4 : Check if history is cleared
input -> 1, "Hi", 1, "Hi2", 4 : Check if history saves
input -> 1, "Hi", 1, "Hi2", 2 : Check if goes backwards
input -> 1, "Hi", 1, "Hi2", 2, 3 : Check if goes forward
input -> 6 : Check program exit
input -> 5, 6 : Check program exit with no data

