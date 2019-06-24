import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * The Huffman Coding Tree
 */
public class HCTree {

    private static final int NUM_CHARS = 256; // alphabet size of extended ASCII

    private HCNode root; // the root of HCTree
    private HCNode [] leaves = new HCNode[NUM_CHARS]; // the leaves of HCTree that contain all the symbols

    /**
     * The Huffman Coding Node
     */
    protected class HCNode implements Comparable<HCNode> {

        byte symbol; // the symbol contained in this HCNode
        int freq; // the frequency of this symbol
        HCNode c0, c1, parent; // c0 is the '0' child, c1 is the '1' child
        
        /**
         * Initialize a HCNode with given parameters
         * @param symbol the symbol contained in this HCNode
         * @param freq the frequency of this symbol
         */
        HCNode(byte symbol, int freq) {
            this.symbol = symbol;
            this.freq = freq;
        }

        /**
         * Getter for symbol
         * @return the symbol contained in this HCNode
         */
        byte getSymbol() {
            return this.symbol;
        }

        /**
         * Setter for symbol
         * @param symbol the given symbol
         */
        void setSymbol(byte symbol) {
            this.symbol = symbol;
        }

        /**
         * Getter for freq
         * @return the frequency of this symbol
         */
        int getFreq() {
            return this.freq;
        }

        /**
         * Setter for freq
         * @param freq the given frequency
         */
        void setFreq(int freq) {
            this.freq = freq;
        }

        /**
         * Getter for '0' child of this HCNode
         * @return '0' child of this HCNode
         */
        HCNode getC0() {
            return c0;
        }

        /**
         * Setter for '0' child of this HCNode
         * @param c0 the given '0' child HCNode
         */
        void setC0(HCNode c0) {
            this.c0 = c0;
        }

        /**
         * Getter for '1' child of this HCNode
         * @return '1' child of this HCNode
         */
        HCNode getC1() {
            return c1;
        }

        /**
         * Setter for '1' child of this HCNode
         * @param c1 the given '1' child HCNode
         */
        void setC1(HCNode c1) {
            this.c1 = c1;
        }

        /**
         * Getter for parent of this HCNode
         * @return parent of this HCNode
         */
        HCNode getParent() {
            return parent;
        }

        /**
         * Setter for parent of this HCNode
         * @param parent the given parent HCNode
         */
        void setParent(HCNode parent) {
            this.parent = parent;
        }

        /**
         * Check if the HCNode is leaf
         * @return if it's leaf, return 1. Otherwise, return 0.
         */
        boolean isLeaf() {
            return (c0 == null) && (c1 == null);
        }

        /**
         * Overriding the toString method from object.
         *
         * @return the string representation of the object
         */
        public String toString() {
            return "Symbol: " + this.symbol + "; Freq: " + this.freq;
        }

        /**
         * Overriding the compareTo method implementing the interface
         * @param o the HCNode we want ot compareTo
         * @return
         */
        public int compareTo(HCNode o) {
            if(freq - o.freq !=0) {
                return freq - o.freq;
            }
            //if frequency are equal, compare the
            //symbol
            else {
                return symbol - o.symbol;
            }
        }
    }

    /**
     * Getter get the root
     * @return the root
     */
    public HCNode getRoot() {
        return root;
    }

    /**
     * Setter set the root
     * @param root the root afetr setting
     */
    public void setRoot(HCNode root) {
        this.root = root;
    }

    /**
     * build the tree given an array of frequency. This
     * is the key function of the huffman tree.
     * @param freq an array of the frequency of each ascii value
     */
    public void buildTree(int[] freq) {

        // initialize a priority queue of HCNode
        PriorityQueue<HCNode> pq = new PriorityQueue<>();
        //initializing the leave array
        for(int i = 0;i<NUM_CHARS;i++){
            HCNode local = null;
            //we only update leave if the frequency is not null
            if(freq[i]!=0) {
                local = new HCNode((byte) i, freq[i]);
            }
            leaves[i] = local;
        }
        //offering to priority queue
        for(HCNode node : leaves){
            if(node!=null) {
                pq.offer(node);
            }
        }
        //manipulation on combining nodes and building tree
        while (pq.size()>1){
            HCNode first = pq.remove();
            HCNode second = pq.remove();
            //adding frequency
            HCNode newNode = new HCNode(first.getSymbol(),
                first.getFreq()+second.getFreq());
            //setting children
            newNode.setC0(first);
            newNode.setC1(second);
            first.setParent(newNode);
            second.setParent(newNode);
            //adding the combined node
            pq.offer(newNode);
        }
        setRoot(pq.peek());
    }

    /**
     * Encoding the symbol according to the Huffman Tree
     * @param symbol the byte of the symbol
     * @param out the stream we write the symbol to
     * @throws IOException If the file is not found
     */
    public void encode(byte symbol, BitOutputStream out) throws IOException{
        //converting to ascii value
        int ascii = symbol & 0xff;
        HCNode target = leaves[ascii];
        ArrayList<Integer> bits = new ArrayList<>();
        //iteratively getting the assigned value(bits) of the byte
        while (target != getRoot()) {
            if (target.getParent().getC0() == target) {
                bits.add(0, 0);
            }
            else if(target.getParent().getC1() == target){
                bits.add(0, 1);
            }
            target = target.getParent();
        }
        //write out the bits
        for(Integer i:bits){
            out.writeBit(i);
        }
    }

    /**
     * Decoding the symbol according to the Huffman Tree
     * @param in The input of bits that we want to decode to a sumbol
     * @return the byte of the symbol
     * @throws IOException If the file is not found
     */
    public byte decode(BitInputStream in) throws IOException{
        HCNode curr = getRoot();
        //going down to the leaf
        while (! curr.isLeaf()){
            int bit = in.readBit();
            //deciding where to go
            if(bit == 0){
                curr = curr.getC0();
            }
            else if(bit == 1){
                curr = curr.getC1();
            }
        }
        // found the leaf node and return the symbol
        return curr.getSymbol();
    }

    /**
     * Encoding the tree structure. This is important for a file to retain the
     * tree stucture for decoding
     * @param node The node to start encoding
     * @param out where to write teh result to
     * @throws IOException If the file is not found
     */
    public void encodeHCTree(HCNode node, BitOutputStream out) throws IOException{
        //using recursion. This is the base case
        if(node.isLeaf()){
            out.writeBit(1);
            out.writeByte(node.getSymbol());
        }
        //if it is a node with children, keep recursion
        else {
            out.writeBit(0);
            encodeHCTree(node.getC0(), out);
            encodeHCTree(node.getC1(), out);
        }

    }

    /**
     * Decoding the tree structure. This is important for a file to retain the
     * tree stucture for decoding.
     * @param in the input from which we want the encoded tree
     * @throws IOException If the file is not found
     */
    public HCNode decodeHCTree(BitInputStream in) throws IOException {
        //storing the bit
        int bit = in.readBit();
        // if it is root, keeping recursion
        if(bit == 0){
            HCNode center = new HCNode((byte) 0, 0);
            center.setC0(decodeHCTree(in));
            center.setC1(decodeHCTree(in));
            return center;
        }
        //if it is a leaf, read the byte followed
        else if(bit == 1){
            //reading byte
            byte sym = in.readByte();
            HCNode leaf = new HCNode(sym, 1);
            leaves[sym & 0xff] = leaf;
            return leaf;
        }
        return null;
    }
}
