public class MinHeap {

    /**
     * A min heap node that stores an element and its priority.
     */
    class Node {
        HeapElement value;
        int priority;

        public Node(HeapElement value, int priority) {
            this.value = value;
            this.priority = priority;
        }
    }

    private Node[] array;
    private int size;

    /**
     * Initialize the min heap.
     */
    public MinHeap() {
        // start with space for ten strings
        this.array = new Node[10];
        this.size = 0;
    }

    // UTILITY METHODS

    /**
     * Double the size of the Node array.
     */
    private void resize() {
        Node[] newArray = new Node[2 * this.array.length];
        for (int i = 0; i < this.array.length; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

    /**
     * Calculate the index of the parent node.
     *
     * This method assumes the child index is valid,
     * and does not need to perform error checking.
     *
     * @param index The index of the child node.
     */
    private int parent(int index) {
        if (index == 0){
            return 0;
        }
        if (index % 2 == 0){
            return (index-2)/2;
        }
        return (index-1)/2;
    }

    /**
     * Calculate the index of the left child.
     *
     * This method assumes the parent index is valid,
     * and does not need to perform error checking.
     *
     * @param index The index of the parent node.
     * @return The index of the left child node.
     */
    private int leftChild(int index) {
        if (((index*2) +1) > size) {
            return size;
        }
        return index*2+1; // FIXME (Challenge Q2)
    }

    /**
     * Calculate the index of the right child.
     *
     * This method assumes the parent index is valid,
     * and does not need to perform error checking.
     *
     * @param index The index of the parent node.
     * @return The index of the right child node.
     */
    private int rightChild(int index) {
        if (((index*2) +2) > size) {
            return size;
        }
        return index*2+2; // FIXME (Challenge Q2)
    }// 0, 1, 2, 3,  4,  5,  6,
    // [1, 2, 3, 2l, 2r, 3l, 3R]

    /**
     * Swap the array contents of the given indices.
     *
     * @param index1 The first index.
     * @param index2 The second index.
     */
    private void swap(int index1, int index2) {
        Node temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }


    /**
     * Add an element to the min heap.
     *
     * @param element The element to add.
     * @param priority The priority of the element.
     */
    public void add(HeapElement element, int priority) {
        Node newNode = new Node(element, priority);
        if (size == array.length){
            resize();
        }
        array[size] = newNode;
        int index = parent(size);
        int nodeIndex = size;
        size++;
        while (newNode.priority < array[index].priority) {
            swap(index, nodeIndex);
            nodeIndex = index;
            index = parent(index);
        }
    }

    /**
     * Remove and return the element with the lowest priority number.
     *
     * @return The HeapElement with the lowest priority number.
     */
    public HeapElement poll() {
        if (size == 0){
            return null;
        }
        Node removed = array[0];
        swap(0, size-1);
        array[size-1] = null;
        size--;
        int currNodeIndex = 0;
        while (true) {
            int childIndex = leftChild(currNodeIndex);
            int otherChild = rightChild(currNodeIndex);
            if (array[childIndex] == null){
                break;
            } else if (array[otherChild] == null){
                break;
            } else if (array[childIndex].priority > array[currNodeIndex].priority){
                break;
            } else if (array[otherChild].priority < array[childIndex].priority){
                swap(otherChild, currNodeIndex);
                currNodeIndex = otherChild;
            } else {
                swap(childIndex, currNodeIndex);
                currNodeIndex = childIndex;
            }
        }
        return removed.value;
    }

    // OTHER HEAP METHODS

    /**
     * Get the size of the min heap.
     *
     * @return The size of the min heap.
     */
    public int size() {
        return this.size;
    }

    /**
     * Return (but not remove) the element with the lowest priority number.
     *
     * @return The HeapElement with the lowest priority number.
     */
    public HeapElement peek() {
        if (this.array[0] == null){
            return null;
        }
        return this.array[0].value;
    }

    // DEBUG METHODS

    /**
     * Print the array of the min heap, as is.
     */
    public void printArray() {
        if (this.size == 0) {
            System.out.println("{}");
        }
        String result = "{" + this.array[0].value;
        for (int i = 1; i < this.size; i++) {
            result += ", " + this.array[i].value;
        }
        System.out.println(result + "}");

    }

    /**
     * Print the min heap as a binary tree.
     */
    public void printTree() {
        this.printTree(0, "");
    }

    private void printTree(int index, String indent) {
        if (index >= this.size) {
            return;
        }
        this.printTree(this.rightChild(index), indent + "    ");
        System.out.println(indent + this.array[index].value);
        this.printTree(this.leftChild(index), indent + "    ");
    }

    // MAIN
    /**
    public static void main(String[] args) {
        // create the heap
        MinHeap heap = new MinHeap();
        // add some numbers

        }
        // print the heap as an array
        heap.printArray();
        // print the heap as a binary tree
        heap.printTree();
        // poll everything out
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(heap.poll());
        }
    }
     */

}
