class TextEditor {
    private class Node {
        String text;
        Node prev, next;

        Node(String text) {
            this.text = text;
        }
    }

    private Node head;      
    private Node current;  
    private int size;        
    private final int LIMIT = 10;

    
    public void type(String newText) {
        Node newNode = new Node(newText);
 // If we had undone some actions and now type, clear forward history
        if (current != null && current.next != null) {
            current.next.prev = null;
            current.next = null;
        }

        if (head == null) {
            head = newNode;
            current = newNode;
            size = 1;
        } else {
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
            size++;

            // Maintain history limit
            if (size > LIMIT) {
                head = head.next;
                head.prev = null;
                size--;
            }
        }
    }

    // Undo operation
    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    // Redo operation
    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        } else {
            System.out.println("Nothing to redo.");
        }
    }

    // Display current text
    public void printCurrentState() {
        if (current != null) {
            System.out.println("Current Text: " + current.text);
        } else {
            System.out.println("Editor is empty.");
        }
    }

    // Utility to print the full history (for testing)
    public void printHistory() {
        Node temp = head;
        System.out.print("History: ");
        while (temp != null) {
            if (temp == current) {
                System.out.print("[" + temp.text + "] ");
            } else {
                System.out.print(temp.text + " ");
            }
            temp = temp.next;
        }
        System.out.println();
    }
}
