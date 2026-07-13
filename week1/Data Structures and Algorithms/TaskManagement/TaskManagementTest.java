public class TaskManagementTest {
    private static class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
        }
    }

    private Node head = null;

    public void addTask(Task t) {
        Node newNode = new Node(t);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public Task searchTask(String id) {
        Node temp = head;
        while (temp != null) {
            if (temp.task.getTaskId().equals(id)) {
                return temp.task;
            }
            temp = temp.next;
        }
        return null;
    }

    public void traverse() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.task);
            temp = temp.next;
        }
    }

    public void deleteTask(String id) {
        if (head == null) return;
        if (head.task.getTaskId().equals(id)) {
            head = head.next;
            return;
        }
        Node temp = head;
        while (temp.next != null && !temp.next.task.getTaskId().equals(id)) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    public static void main(String[] args) {
        TaskManagementTest queue = new TaskManagementTest();
        queue.addTask(new Task("T01", "Design Database", "Pending"));
        queue.addTask(new Task("T02", "Implement APIs", "In Progress"));

        System.out.println("All Tasks:");
        queue.traverse();

        System.out.println("\nSearch T02:");
        System.out.println(queue.searchTask("T02"));

        System.out.println("\nDeleting T01:");
        queue.deleteTask("T01");
        queue.traverse();
    }
}
