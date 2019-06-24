import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.ArrayList;

public class BSTree<T extends Comparable<? super T>> implements Iterable {

    private int nelems;
    private BSTNode root;
    public BSTNode currentNode;//keeping track of current node

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;
        
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            //initialize
            this.key = key;
            this.dataList = dataList;
            this.left = left;
            this.right = right;
        }

        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.dataList = new LinkedList<T>();
        }

        public T getKey() {
            return this.key;
        }

        public BSTNode getLeft() {
            return this.left;
        }

        public BSTNode getRight() {
            return this.right;
        }

        public LinkedList<T> getDataList() {
            return this.dataList;
        }

        public void setleft(BSTNode newleft) {
            this.left = newleft;
        }

        public void setright(BSTNode newright) {
            this.right = newright;
        }

        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        public void addNewInfo(T data) {
            this.dataList.add(data);
        }

        public boolean removeInfo(T data) {
            return this.dataList.remove(data);
        }

        private boolean addNode(T key){
            //if less than current node, add to left
            if(key.compareTo(this.key)<0){
                if(this.left == null){
                    this.left = new BSTNode(null, null, key);
                    return true;
                }

                //if left is full, continue
                else {
                    return this.left.addNode(key);
                }
            }

            //repetitive
            else if(key.compareTo(this.key) == 0){
                return false;
            }

            //if larger than current node, add to right
            else {
                if(this.right == null){
                    this.right = new BSTNode(null, null, key);
                    return true;
                }
                //if right is full, continue
                else {
                    return this.right.addNode(key);
                }
            }
        }

        private int findHeight(){
            //if has nothing
            if(left == null && right == null){
                return 0;
            }
            //only one side
            else if(left == null){
                return 1 + right.findHeight();
            }
            else if(right == null){
                return 1 + left.findHeight();
            }
            //recursively find the max height
            else {
                return 1 + Integer.max(left.findHeight(), right.findHeight());
            }
        }

        private int leafCount(){
            //if has nothing
            if(left == null && right == null){
                return 1;
            }
            //only one side
            else if(left == null){
                return right.leafCount();
            }
            else if(right == null){
                return left.leafCount();
            }
            //recursively find the total leaf
            else {
                return left.leafCount()+right.leafCount();
            }
        }

        private BSTNode remove(T data){
            //if found the key
            if(data.compareTo(key) == 0){
                //if the node has only one child
                if(this.left == null){
                    return this.right;
                }
                if(this.right == null){
                    return this.left;
                }
                //if the node has two children
                else {
                    BSTNode leftMost = this.right;
                    //find the left most element of the right node
                    while (leftMost.left != null){
                        leftMost = leftMost.left;
                    }
                    //copy the node
                    this.key = leftMost.key;
                    this.dataList = leftMost.dataList;
                    this.right = this.right.remove(key);
                }
            }
            //if is less than the key, go left
            else if(data.compareTo(key)<0){
                this.left = this.left.remove(data);
            }
            //if is larger than the key, go right
            else{
                this.right = this.right.remove(data);
            }
            return this;
        }
    }

    public BSTree() {
        this.root = null;
        this.nelems = 0;
    }

    public BSTNode getRoot() {
        return this.root;
    }

    public int getSize() {
        return nelems;
    }

    public boolean insert(T key) {
        //if the key is null
        if(key==null){
            throw new NullPointerException("..");
        }
        if(findKey(key)){
            return false;
        }
        //construct the first node as the root
        if(this.root == null){
            this.root = new BSTNode(null, null, key);
            nelems++;
            return true;
        }
        nelems++;
        return this.root.addNode(key);
    }

    public boolean findKey(T key) {
        //if the key is null
        if(key == null){
            throw new NullPointerException();
        }
        BSTNode curr = this.root;

        //looping through the tree using binary search
        while (curr!=null && curr.key.compareTo(key)!=0){
            if(curr.key.compareTo(key)<0){
                curr = curr.right;
            }
            else {
                curr = curr.left;
            }
        }
        //if found
        if(curr != null){
            currentNode = curr;
            return true;
        }
        return false;
    }

    public boolean remove(T key){
        //if has the key, go on
        if(findKey(key)) {
            root = root.remove(key);
            nelems--;
            return true;
        }
        else {
            return false;
        }
    }

    public void insertData(T key, T data) {
        //if invalid input
        if(key == null || data == null){
            throw new NullPointerException();
        }

        //if has the key
        if(findKey(key)){
            currentNode.addNewInfo(data);
        }

        //if doesn't find the key
        else {
            throw new IllegalArgumentException();
        }
    }

    public LinkedList<T> findDataList(T key) {
        //if invalid input
        if(key == null){
            throw new NullPointerException();
        }
        //if has the key
        if(findKey(key)){
            return currentNode.dataList;
        }
        //if doesn't find the key
        else {
            throw new IllegalArgumentException();
        }
    }

    public int findHeight() {
        if(root == null){
            return -1;
        }
        return root.findHeight();
    }

    public int leafCount() {
        if(root == null){
            return 0;
        }
        return root.leafCount();
    }

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2){
        ArrayList<T> array1 = new ArrayList<>();
        ArrayList<T> array2 = new ArrayList<>();
        //convert to conatiner object
        while (iter1.hasNext()){
            array1.add(iter1.next());
        }
        while (iter2.hasNext()){
            array2.add(iter2.next());
        }
        //find the intersection
        array1.retainAll(array2);
        return array1;
    }

    private int levelCountNode(BSTNode node, int level){
        //for the first level
        if(node==null){
            return 0;
        }
        if (level==0){
            return 1;
        }
        //recursively add all the nodes from left and right
        else {
            return levelCountNode(node.left, level-1)
                +levelCountNode(node.right, level-1);
        }
    }

    public int levelCount(int level){
        //if the level is too large, return -1
        if(findHeight()<level){
            return -1;
        }
        return levelCountNode(root, level);
    }

    public class BSTree_Iterator implements Iterator<T> {

        private Stack<BSTNode> stack;

        public BSTree_Iterator() {
            stack = new Stack<>();
            BSTNode curr = root;
            //initialize stack with leftmost path
            while (curr != null){
                stack.push(curr);
                curr=curr.left;
            }
        }
        
        public boolean hasNext() {
            if(stack.isEmpty()){
                return false;
            }
            return true;
        }

        public T next() {
            if(hasNext()){
                BSTNode currNode = stack.pop();
                //popping the element from the stack
                //if possible, push the leftmost path
                //of the right node
                if(currNode.right!=null){
                    BSTNode toPush =currNode.right;
                    while (toPush != null){
                        stack.push(toPush);
                        toPush=toPush.left;
                    }
                }
                return currNode.key;
            }
            //empty
            else {
                throw new NoSuchElementException();
            }
        }
    }

    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }
}
