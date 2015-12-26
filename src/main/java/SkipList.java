import java.util.Arrays;

/**
 * Created by aartika.rai on 12/26/15.
 */
public class SkipList<T extends Comparable<? super T>> {

    private final int MAX_LEVEL = 32;
    private final double P = 0.5;
    Node<T> head;
    Node<T> tail;
    int level = 0;
    int size;

    public SkipList() {
        head = new Node<T>(null, MAX_LEVEL);
    }

    public static class Node<E extends Comparable<? super E>> {

        private E ele;
        private Node<E>[] next;
        private Node<E> prev;

        Node(E ele, int numLevels) {
            this.ele = ele;
            this.next = new Node[numLevels];
        }

        public E getEle() {
            return ele;
        }

        public Node<E> getNext() {
            return next[0];
        }

        public Node<E> getPrev() {
            return prev;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "ele=" + ele +
                    ", next=" + Arrays.toString(next) +
                    ", prev=" + prev +
                    '}';
        }
    }

    private int randomLevel() {
        int lvl = 1;
        while (Math.random() < P && lvl < MAX_LEVEL) {
            lvl++;
        }
        return lvl;
    }

    public void insert(T ele) {
        Node[] update = new Node[MAX_LEVEL];
        Node<T> x = head;
        for (int i = level - 1; i >= 0; i--) {
            while (x.next[i] != null && x.next[i].ele.compareTo(ele) < 0) {
                x = x.next[i];
            }
            update[i] = x;
        }
        int lvl = randomLevel();
        x = new Node<T>(ele, lvl);
        if (lvl > this.level) {
            for (int i = this.level; i < lvl; i++)
                update[i] = this.head;
        }

        this.level = Math.max( this.level, lvl );
        if (update[0].next[0] == null) {
            tail = x;
        } else {
            update[0].next[0].prev = x;
        }
        x.prev = update[0] == head ? null : update[0];
        for (int i = 0; i < lvl; i++) {
            x.next[i] = update[i].next[i];
            update[i].next[i] = x;
        }
        size++;
    }

    public Node<T> search(T ele) {
        Node<T> x = this.head;
        for (int i = level - 1; i >= 0; i--) {
            while (x.next[i] != null && x.next[i].ele.compareTo(ele) < 0) {
                x = x.next[i];
            }
        }
        x = x.next[0];
        return (x == null || !x.ele.equals(ele)) ? null : x;
    }

    public void delete(T ele) {
        Node[] update = new Node[MAX_LEVEL];
        Node<T> x = this.head;
        for (int i = level - 1; i >= 0; i--) {
            while (x.next[i] != null && x.next[i].ele.compareTo(ele) < 0) {
                x = x.next[i];
            }
            update[i] = x;
        }

        x = x.next[0];
        if ( x != null && x.ele.equals(ele)) {
            if( x.next[0] == null)
                tail = x.prev;
            else
                x.next[0].prev = x.prev;
            for (int i = 0; i < level; i++) {
                if (update[i].next[i] == null || !update[i].next[i].ele.equals(x.ele)) {
                    break;
                } else {
                    update[i].next[i] = x.next[i];
                }
            }

            while (level > 0 && this.head.next[level-1] == null) {
                level--;
            }
            size--;
        }
    }

    public Node<T> firstEntry(){
        return head.next[0];
    }

    public Node<T> lastEntry(){
        return tail;
    }

    public int getSize() {
        return size;
    }

    public void print() {
        Node<T> curr = head;
        while (curr.next[0] != null) {
            System.out.print(curr.next[0].ele + " ");
            curr = curr.next[0];
        }
        System.out.println();
    }
}
