import static java.lang.Math.abs;

public class BloomFilter {
    public static final int NUMBITS = 8;
    public static final int CRC_LEFT = 5;
    public static final int CRC_RIGHT = 27;
    private final int PJW_HASH_SHIFT = 4;
    private final int PJW_HASH_RIGHT_SHIFT = 24;
    private final int PJW_HASH_MASK = 0xf0000000;

    byte[] table; // the byte array used as hash table of bits
    int numSlots; // the number of slots (bits) in the hash table

    /**
     * The constructor that creates a bloom filter of size numBytes in byte and 
     * with 8 * numBytes slots (in bits).
     *
     * @param numBytes the number of bytes allocated for the byte array
     */
    public BloomFilter(int numBytes) {
        table = new byte[numBytes];
        numSlots = NUMBITS*numBytes;
    }

    /**
     * Insert an element to bloom filter
     * @param str the element to insert
     */
    public void insert(String str) {
        int pos1 = hashFunc1(str);
        int pos2 = hashFunc2(str);
        int pos3 = hashFunc3(str);
        //setting bits to one
        setBit(pos1/NUMBITS, pos1%NUMBITS);
        setBit(pos2/NUMBITS, pos2%NUMBITS);
        setBit(pos3/NUMBITS, pos3%NUMBITS);
    }

    /**
     * Helper method to set a bit in the table to 1, which is specified by the given byteIndex 
     * and bitIndex
     * @param byteIndex the index of the byte in hash table
     * @param bitIndex the index of the bit in the byte at byteIndex. Range is [0, 7]
     */
    private void setBit(int byteIndex, int bitIndex) {
        // set the bit at bitIndex of the byte at byteIndex
        table[byteIndex] = (byte) (table[byteIndex] | ((1 << (NUMBITS - 1)) >> bitIndex));
    }

    /**
     * Find if the parametr exists in filter, might have
     * false positive rate
     * @param str the string to find
     * @return 1 if it is in, 0 otherwise
     */
    public boolean find(String str) {
        int pos1 = hashFunc1(str);
        int pos2 = hashFunc2(str);
        int pos3 = hashFunc3(str);
        //if any one of them is zero, the for sure doesn't exist
        if(getSlot(pos1/NUMBITS, pos1%NUMBITS) == 0
        || getSlot(pos2/NUMBITS, pos2%NUMBITS) == 0
        || getSlot(pos3/NUMBITS, pos3%NUMBITS) == 0) {
            return false;
        }
        return true;
    }

    /**
     * The fisrt hash function from lecture
     * The CRC hashfunction
     * @param str the object to hashing
     * @return the hash value
     */
    private int hashFunc1(String str){
        int hashInt = 0;
        for(int i = 0;i<str.length();i++){
            //iterating thorugh all char
            int leftShiftValue = hashInt << CRC_LEFT;
            int rightShiftValue = hashInt >>> CRC_RIGHT;
            //xoring
            hashInt = (leftShiftValue|rightShiftValue) ^ str.charAt(i);
        }
        return abs(hashInt % numSlots);
    }

    /**
     * The second hash function from the sample hash function doc
     * The BASE256
     * @param str the object to hashing
     * @return the hash value
     */
    private int hashFunc2(String str){
        int hashInt = 0;
        //iterating through every single element
        for(int i = 0;i<str.length();i++){
            hashInt = (hashInt << NUMBITS) + str.charAt(i);
            //decrease size
            hashInt %= numSlots;
        }
        return abs(hashInt);
    }

    /**
     * The third hash function from the sample hash function doc
     * The PJW hashfunction
     * @param str the object to hashing
     * @return the hash value
     */
    private int hashFunc3(String str){
        int hashInt = 0;
        //iterating through every single element
        for(int i = 0;i<str.length();i++){
            //left shifting
            hashInt = (hashInt << PJW_HASH_SHIFT) + str.charAt(i);
            int rotate_bits = hashInt & PJW_HASH_MASK;
            //xoring
            hashInt ^= rotate_bits | (rotate_bits >> PJW_HASH_RIGHT_SHIFT);
        }
        //decrease size
        return abs(hashInt%(numSlots));
    }
    
    /**
     * Helper method to get the bit value at the slot, which is specified by the given byteIndex 
     * and bitIndex
     * @param byteIndex the index of the byte in hash table
     * @param bitIndex the index of the bit in the byte at byteIndex. Range is [0, 7]
     */
    private int getSlot(int byteIndex, int bitIndex) {
        return (table[byteIndex] >> ((NUMBITS - 1) - bitIndex)) & 1;
    }
}
