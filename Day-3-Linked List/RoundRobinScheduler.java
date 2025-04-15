import java.util.Scanner;

class Process {
    int pid;
    int burstTime;
    int remainingTime;
    int priority;
    int waitingTime = 0;
    int turnAroundTime = 0;
    Process next;

    public Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }
}

class CircularQueue {
    private Process tail = null;

    public void addProcess(Process newProcess) {
        if (tail == null) {
            tail = newProcess;
            tail.next = tail;
        } else {
            newProcess.next = tail.next;
            tail.next = newProcess;
            tail = newProcess;
        }
    }

    public void removeProcess(int pid) {
        if (tail == null) return;

        Process current = tail.next;
        Process prev = tail;
        do {
            if (current.pid == pid) {
                if (current == tail && current.next == tail) {
                    tail = null;
                } else {
                    prev.next = current.next;
                    if (current == tail) {
                        tail = prev;
                    }
                }
                return;
            }
            prev = current;
            current = current.next;
        } while (current != tail.next);
    }

    public boolean isEmpty() {
        return tail == null;
    }

    public Process getHead() {
        return (tail == null) ? null : tail.next;
    }

    public void displayProcesses() {
        if (tail == null) {
            System.out.println("No processes in the queue.");
            return;
        }
        Process current = tail.next;
        System.out.println("Current Queue:");
        do {
            System.out.println("PID: " + current.pid + ", Remaining Time: " + current.remainingTime +
                    ", Priority: " + current.priority);
            current = current.next;
        } while (current != tail.next);
    }

    public void simulateRoundRobin(int timeQuantum) {
        if (tail == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        int currentTime = 0;
        Process current = tail.next;

        while (!isEmpty()) {
            if (current.remainingTime > 0) {
                int executionTime = Math.min(timeQuantum, current.remainingTime);
                current.remainingTime -= executionTime;
                currentTime += executionTime;

       
                Process temp = tail.next;
                do {
                    if (temp != current && temp.remainingTime > 0) {
                        temp.waitingTime += executionTime;
                    }
                    temp = temp.next;
                } while (temp != tail.next);

                System.out.println("\nProcess " + current.pid + " executed for " + executionTime + " units.");
                displayProcesses();

                if (current.remainingTime == 0) {
                    current.turnAroundTime = currentTime;
                    System.out.println("Process " + current.pid + " completed at time " + currentTime);
                    removeProcess(current.pid);
                    if (isEmpty()) break;
                    current = tail.next;
                } else {
                    current = current.next;
                }
            } else {
                current = current.next;
            }
        }
    }

    public void calculateAndDisplayAverages() {
        if (tail == null) return;

        double totalWT = 0, totalTAT = 0;
        int count = 0;
        Process current = tail.next;
        do {
            totalWT += current.waitingTime;
            totalTAT += current.turnAroundTime;
            count++;
            current = current.next;
        } while (current != tail.next);

        System.out.println("\nAverage Waiting Time: " + (totalWT / count));
        System.out.println("Average Turnaround Time: " + (totalTAT / count));
    }
}

public class RoundRobinScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularQueue queue = new CircularQueue();

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter PID for process " + (i + 1) + ": ");
            int pid = sc.nextInt();
            System.out.print("Enter Burst Time for PID " + pid + ": ");
            int bt = sc.nextInt();
            System.out.print("Enter Priority for PID " + pid + ": ");
            int pr = sc.nextInt();
            queue.addProcess(new Process(pid, bt, pr));
        }

        System.out.print("Enter Time Quantum: ");
        int timeQuantum = sc.nextInt();

        queue.simulateRoundRobin(timeQuantum);
        queue.calculateAndDisplayAverages();
        sc.close();
    }
}
