class InventoryManagement {

    static class ItemNode {
        String itemName;
        int itemId;
        int quantity;
        double price;
        ItemNode next;

        ItemNode(String itemName, int itemId, int quantity, double price) {
            this.itemName = itemName;
            this.itemId = itemId;
            this.quantity = quantity;
            this.price = price;
            this.next = null;
        }
    }

    private ItemNode head;

    public void addAtBeginning(String name, int id, int quantity, double price) {
        ItemNode newNode = new ItemNode(name, id, quantity, price);
        newNode.next = head;
        head = newNode;
    }

    public void addAtEnd(String name, int id, int quantity, double price) {
        ItemNode newNode = new ItemNode(name, id, quantity, price);
        if (head == null) {
            head = newNode;
        } else {
            ItemNode temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
    }

    public void addAtPosition(String name, int id, int quantity, double price, int position) {
        if (position <= 0 || head == null) {
            addAtBeginning(name, id, quantity, price);
            return;
        }

        ItemNode newNode = new ItemNode(name, id, quantity, price);
        ItemNode temp = head;
        for (int i = 0; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            addAtEnd(name, id, quantity, price);
        } else {
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    public void removeById(int id) {
        if (head == null) return;

        if (head.itemId == id) {
            head = head.next;
            return;
        }

        ItemNode prev = head;
        while (prev.next != null && prev.next.itemId != id) {
            prev = prev.next;
        }

        if (prev.next != null) {
            prev.next = prev.next.next;
        }
    }

    public void updateQuantity(int id, int newQuantity) {
        ItemNode current = head;
        while (current != null) {
            if (current.itemId == id) {
                current.quantity = newQuantity;
                return;
            }
            current = current.next;
        }
    }

    public void searchById(int id) {
        ItemNode current = head;
        while (current != null) {
            if (current.itemId == id) {
                printItem(current);
                return;
            }
            current = current.next;
        }
        System.out.println("Item not found.");
    }

    public void searchByName(String name) {
        ItemNode current = head;
        boolean found = false;
        while (current != null) {
            if (current.itemName.equalsIgnoreCase(name)) {
                printItem(current);
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("Item not found.");
    }
    public double calculateTotalValue() {
        double total = 0;
        ItemNode current = head;
        while (current != null) {
            total += current.quantity * current.price;
            current = current.next;
        }
        return total;
    }
    public void printInventory() {
        ItemNode current = head;
        while (current != null) {
            printItem(current);
            current = current.next;
        }
    }
    private void printItem(ItemNode item) {
        System.out.println("Item ID: " + item.itemId + ", Name: " + item.itemName +
                ", Quantity: " + item.quantity + ", Price: $" + item.price);
    }
    public void sortInventory(String sortBy, boolean ascending) {
        head = mergeSort(head, sortBy.toLowerCase(), ascending);
    }
    private ItemNode mergeSort(ItemNode head, String sortBy, boolean ascending) {
        if (head == null || head.next == null)
            return head;

        ItemNode middle = getMiddle(head);
        ItemNode nextOfMiddle = middle.next;
        middle.next = null;

        ItemNode left = mergeSort(head, sortBy, ascending);
        ItemNode right = mergeSort(nextOfMiddle, sortBy, ascending);

        return sortedMerge(left, right, sortBy, ascending);
    }
    private ItemNode sortedMerge(ItemNode a, ItemNode b, String sortBy, boolean ascending) {
        if (a == null) return b;
        if (b == null) return a;
        ItemNode result;
        boolean condition;
        if (sortBy.equals("name")) {
            condition = ascending ?
                    a.itemName.compareToIgnoreCase(b.itemName) <= 0 :
                    a.itemName.compareToIgnoreCase(b.itemName) > 0;
        } else { // sortBy price
            condition = ascending ?
                    a.price <= b.price :
                    a.price > b.price;
        }

        if (condition) {
            result = a;
            result.next = sortedMerge(a.next, b, sortBy, ascending);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next, sortBy, ascending);
        }
        return result;
    }
    private ItemNode getMiddle(ItemNode head) {
        if (head == null) return head;
        ItemNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public static void main(String[] args) {
        InventoryManagement inventory = new InventoryManagement();

        inventory.addAtEnd("Mouse", 101, 25, 15.75);
        inventory.addAtBeginning("Keyboard", 102, 10, 45.50);
        inventory.addAtEnd("Monitor", 103, 5, 150.0);
        inventory.addAtPosition("USB Cable", 104, 50, 5.0, 2);

        System.out.println("=== Inventory ===");
        inventory.printInventory();

        System.out.println("\nTotal Inventory Value: $" + inventory.calculateTotalValue());

        System.out.println("\nSearch by ID (102):");
        inventory.searchById(102);

        System.out.println("\nSearch by Name (Monitor):");
        inventory.searchByName("Monitor");

        System.out.println("\nUpdate Quantity of ID 101 to 30:");
        inventory.updateQuantity(101, 30);
        inventory.searchById(101);

        System.out.println("\nSort by Price Descending:");
        inventory.sortInventory("price", false);
        inventory.printInventory();

        System.out.println("\nRemove Item with ID 104:");
        inventory.removeById(104);
        inventory.printInventory();
    }
}
