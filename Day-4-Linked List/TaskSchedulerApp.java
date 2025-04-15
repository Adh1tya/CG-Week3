import java.util.Scanner;
class Task {
    int id;
    String name;
    int priority;
    String dueDate;
    Task next;

    public Task(int id, String name, int priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}
class TaskScheduler {
    private Task head = null;
    private Task current = null;
    public void addAtBeginning(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            newTask.next = head;
            current = head;
        } else {
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            newTask.next = head;
            head = newTask;
            temp.next = head;
        }
    }
    public void addAtEnd(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            newTask.next = head;
            current = head;
        } else {
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTask;
            newTask.next = head;
        }
    }
    public void addAtPosition(int position, int id, String name, int priority, String dueDate) {
        if (position <= 1) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }
        Task newTask = new Task(id, name, priority, dueDate);
        Task temp = head;
        for (int i = 1; i < position - 1 && temp.next != head; i++) {
            temp = temp.next;
        }
        newTask.next = temp.next;
        temp.next = newTask;
    }
    public void removeById(int id) {
        if (head == null) return;
        Task temp = head, prev = null;
        if (head.id == id) {
            if (head.next == head) {
                head = null;
                current = null;
                return;
            }
            Task last = head;
            while (last.next != head) {
                last = last.next;
            }
            head = head.next;
            last.next = head;
            if (current.id == id) current = head;
            return;
        }
        do {
            prev = temp;
            temp = temp.next;
            if (temp.id == id) {
                prev.next = temp.next;
                if (current.id == id) current = prev.next;
                return;
            }
        } while (temp != head);
    }
    public void displayTasks() {
        if (head == null) {
            System.out.println("No tasks in the list.");
            return;
        }
        Task temp = head;
        do {
            System.out.println("ID: " + temp.id + ", Name: " + temp.name + ", Priority: " + temp.priority + ", Due: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }
    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks in the list.");
            return;
        }
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("ID: " + temp.id + ", Name: " + temp.name + ", Due: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No tasks found with priority " + priority);
        }
    }
    public void viewCurrentTask() {
        if (current == null) {
            System.out.println("No current task.");
            return;
        }
        System.out.println("Current Task -> ID: " + current.id + ", Name: " + current.name + ", Priority: " + current.priority + ", Due: " + current.dueDate);
    }
    public void moveToNextTask() {
        if (current != null) {
            current = current.next;
        }
    }
}

public class TaskSchedulerApp {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Task Scheduler");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Position");
            System.out.println("4. Remove Task by ID");
            System.out.println("5. View Current Task");
            System.out.println("6. Move to Next Task");
            System.out.println("7. Display All Tasks");
            System.out.println("8. Search Task by Priority");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                case 2:
                case 3: {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Priority: ");
                    int priority = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Due Date: ");
                    String dueDate = sc.nextLine();
                    if (choice == 1) {
                        scheduler.addAtBeginning(id, name, priority, dueDate);
                    } else if (choice == 2) {
                        scheduler.addAtEnd(id, name, priority, dueDate);
                    } else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt();
                        scheduler.addAtPosition(pos, id, name, priority, dueDate);
                    }
                    break;
                }
                case 4: {
                    System.out.print("Enter ID to remove: ");
                    int id = sc.nextInt();
                    scheduler.removeById(id);
                    break;
                }
                case 5:
                    scheduler.viewCurrentTask();
                    break;
                case 6:
                    scheduler.moveToNextTask();
                    break;
                case 7:
                    scheduler.displayTasks();
                    break;
                case 8:
                    System.out.print("Enter Priority to search: ");
                    int priority = sc.nextInt();
                    scheduler.searchByPriority(priority);
                    break;
                case 9:
                    System.out.println("Exiting Task Scheduler.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 9);
    }
}
