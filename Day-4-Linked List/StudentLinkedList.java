class StudentNode {
    int rollNumber;
    String name;
    int age;
    String grade;
    StudentNode next;
    StudentNode(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}
public class StudentLinkedList {
    private StudentNode head;
    public void addAtBeginning(int rollNumber, String name, int age, String grade) {
        StudentNode newNode = new StudentNode(rollNumber, name, age, grade);
        newNode.next = head;
        head = newNode;
    }
    public void addAtEnd(int rollNumber, String name, int age, String grade) {
        StudentNode newNode = new StudentNode(rollNumber, name, age, grade);
        if (head == null) {
            head = newNode;
        } else {
            StudentNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }
    public void addAtPosition(int position, int rollNumber, String name, int age, String grade) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        StudentNode newNode = new StudentNode(rollNumber, name, age, grade);
        if (position == 1) {
            newNode.next = head;
            head = newNode;
            return;
        }
        StudentNode current = head;
        for (int i = 1; i < position - 1 && current != null; i++) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Position out of bounds.");
            return;
        }
        newNode.next = current.next;
        current.next = newNode;
    }
    public void deleteByRollNumber(int rollNumber) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        if (head.rollNumber == rollNumber) {
            head = head.next;
            System.out.println("Deleted student with roll number: " + rollNumber);
            return;
        }

        StudentNode current = head;
        while (current.next != null && current.next.rollNumber != rollNumber) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Roll number not found.");
        } else {
            current.next = current.next.next;
            System.out.println("Deleted student with roll number: " + rollNumber);
        }
    }
    public void searchByRollNumber(int rollNumber) {
        StudentNode current = head;
        while (current != null) {
            if (current.rollNumber == rollNumber) {
                System.out.println("Student found:");
                System.out.println("Roll No: " + current.rollNumber);
                System.out.println("Name: " + current.name);
                System.out.println("Age: " + current.age);
                System.out.println("Grade: " + current.grade);
                return;
            }
            current = current.next;
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }
    public void updateGrade(int rollNumber, String newGrade) {
        StudentNode current = head;
        while (current != null) {
            if (current.rollNumber == rollNumber) {
                current.grade = newGrade;
                System.out.println("Updated grade for roll number: " + rollNumber);
                return;
            }
            current = current.next;
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }
    public void displayAll() {
        if (head == null) {
            System.out.println("No records to display.");
            return;
        }
        System.out.println("Student Records:");
        StudentNode current = head;
        while (current != null) {
            System.out.println("Roll No: " + current.rollNumber + ", Name: " + current.name +
                               ", Age: " + current.age + ", Grade: " + current.grade);
            current = current.next;
        }
    }
    public static void main(String[] args) {
        StudentLinkedList list = new StudentLinkedList();
        list.addAtBeginning(101, "Alice", 20, "A");
        list.addAtEnd(102, "Bob", 21, "B");
        list.addAtPosition(2, 103, "Charlie", 22, "A+");
        list.displayAll();
        list.searchByRollNumber(102);
        list.updateGrade(103, "B+");
        list.deleteByRollNumber(101);
        list.displayAll();
    }
}
