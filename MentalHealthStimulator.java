import java.util.*;

class StackDS {
    private List<String> stack = new ArrayList<>();
    void push(String mood) { 
        stack.add(mood);
     }
    String pop() { 
        return stack.isEmpty() ? null : stack.remove(stack.size()-1);
     }
    void display(){
         stack.forEach(System.out::println); 
        }
    boolean search(String mood) { 
        return stack.contains(mood); 
    }
}

class QueueDS {
    private LinkedList<String> queue = new LinkedList<>();
    void enqueue(String activity) {
         queue.addLast(activity); 
        }
    String dequeue() { 
        return queue.isEmpty() ? null : queue.removeFirst(); 
    }
    void display() { 
        queue.forEach(System.out::println);
     }
}

class LinkedListDS {
    static class Node {
        String data; 
        Node next;
        Node(String d) { 
            data = d; 
        }
    }
    Node head;
    void add(String data) {
        Node newNode = new Node(data);
        if (head == null) head = newNode;
        else { 
            Node temp = head; while (temp.next != null) temp = temp.next; temp.next = newNode; 
        }
    }
    void display() {
        Node temp = head;
        while (temp != null) { System.out.println(temp.data); temp = temp.next; }
    }
}

class ArrayDS {
    int[] stressLevels = new int[7];
    int index = 0;
    void addStressLevel(int level) {
        if (index < stressLevels.length) {
            stressLevels[index++] = level;
        } else {
            System.out.println("Stress log full (7 days max)");
        }
    }
    void displaySorted() {
        int[] copy = Arrays.copyOf(stressLevels, index);
        Arrays.sort(copy);
        System.out.println("Sorted Stress Levels:");
        for (int i : copy) System.out.println(i);
    }
}

class TreeDS {
    static class Node {
        String data; Node left, right;
        Node(String d) { data = d; }
    }
    Node root;
    void insert(String data) { root = insertRec(root, data); }
    Node insertRec(Node root, String data) {
        if (root == null) return new Node(data);
        if (data.compareTo(root.data) < 0) root.left = insertRec(root.left, data);
        else if (data.compareTo(root.data) > 0) root.right = insertRec(root.right, data);
        return root;
    }
    void inorder(Node root) {
        if (root != null) { inorder(root.left); System.out.println(root.data); inorder(root.right); }
    }
}

public class MentalHealthStimulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StackDS moodHistory = new StackDS();
        ArrayDS stressTracker = new ArrayDS();
        LinkedListDS gratitudeList = new LinkedListDS();
        QueueDS activityQueue = new QueueDS();
        TreeDS suggestionTree = new TreeDS();

        suggestionTree.insert("Meditate");
        suggestionTree.insert("Go for a walk");
        suggestionTree.insert("Listen to music");
        suggestionTree.insert("Talk to a friend");

        int choice;
        do {
            System.out.println("\n--- Mental Health Stimulator ---");
            System.out.println("1. Log Mood (Stack)");
            System.out.println("2. View Mood History");
            System.out.println("3. Log Stress Level (Array)");
            System.out.println("4. View Sorted Stress Report");
            System.out.println("5. Add Gratitude Note (LinkedList)");
            System.out.println("6. View Gratitude Notes");
            System.out.println("7. Add Wellness Activity (Queue)");
            System.out.println("8. Do Next Activity");
            System.out.println("9. View All Activities");
            System.out.println("10. View Coping Suggestions (Tree)");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt(); sc.nextLine();

            switch(choice) {
                case 1 -> { System.out.print("Enter mood: "); moodHistory.push(sc.nextLine()); }
                case 2 -> moodHistory.display();
                case 3 -> { System.out.print("Enter stress level (1-10): "); stressTracker.addStressLevel(sc.nextInt()); sc.nextLine(); }
                case 4 -> stressTracker.displaySorted();
                case 5 -> { System.out.print("Enter gratitude note: "); gratitudeList.add(sc.nextLine()); }
                case 6 -> gratitudeList.display();
                case 7 -> { System.out.print("Enter wellness activity: "); activityQueue.enqueue(sc.nextLine()); }
                case 8 -> { String act = activityQueue.dequeue(); System.out.println(act == null ? "No activities." : "Do now: " + act); }
                case 9 -> activityQueue.display();
                case 10 -> suggestionTree.inorder(suggestionTree.root);
                case 0 -> System.out.println("Exiting... Stay positive!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
