import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Math.abs;

public class HashTable implements IHashTable {
//You will need a HashTable of LinkedLists. 
private static final float MAXMUM_NUMBER = (float) (2.0/3.0);
private static final int DOUBLE = 2;
private static final int LEFT_SHIFT = 5;
private static final int RIGHTSHIFT = 27;
private int nelems;  //Number of element stored in the hash table
private int expand;  //Number of times that the table has been expanded
private int collision;  //Number of collisions since last expansion
private String statsFileName;     //FilePath for the file to write statistics upon every rehash
private boolean printStats = false;   //Boolean to decide whether to write statistics to file or not after rehashing
private int size;
private LinkedList<String> storage[];
private int longest;
private DecimalFormat df = new DecimalFormat("#.##");
/**
	 * constructor
	 * @param size the size of the initial table
	 */
public HashTable(int size) {
this.size = size;
		storage = new LinkedList[size];
	}
/**
	 * Constuctor with 2 args
	 * @param size the size of the table
	 * @param fileName where to print the statistic of the table to
	 */
public HashTable(int size, String fileName){
this.size = size;
this.statsFileName = fileName;
		printStats = true;
// Set printStats to true and statsFileName to fileName
		storage = new LinkedList[size];
	}
/**
	 * Insert a string value to hashtable according to its hashfunction
	 * @param value value to insert
	 * @return 1 if successfully inset, 0 otherwise
	 * @throws NullPointerException
	 */
public boolean insert(String value) {
//if invalid parameter
if(value==null){
throw new NullPointerException();
		}
int hashNum = hashVal(value);
//if not been store before
if(storage[hashNum] == null){
//create a new linkedlist
			storage[hashNum] = new LinkedList<>();
			storage[hashNum].add(value);
			nelems++;
//keeing track of the longest chain
if(storage[hashNum].size()>longest){
				longest = storage[hashNum].size();
			}
//if the element is larger than expected
if((float)size*MAXMUM_NUMBER<=nelems){
				rehash();
			}
return true;
		}
//if not been stored before
else if(!storage[hashNum].contains(value)){
			collision++;
			storage[hashNum].add(value);
			nelems++;
//keeing track of the longest chain
if(storage[hashNum].size()>longest){
				longest = storage[hashNum].size();
			}
//System.out.println((float)size*MAXMUM_NUMBER);
//if the element is larger than expected
if((float)size*MAXMUM_NUMBER<nelems){
				rehash();
			}
return true;
		}
//if that has been stored
else{
return false;
		}
	}
/**
	 * delete an element in the hashtable
	 * @param value value to delete
	 * @return 1 if successfullt delete, 0 if not
	 */
public boolean delete(String value) {
//if value is invalid
if(value==null){
throw new NullPointerException();
		}
int hashNum = hashVal(value);
//not found
if(storage[hashNum]==null){
return false;
		}
//found, then minus elements
else if(storage[hashNum].contains(value)){
			storage[hashNum].remove(value);
			nelems--;
return true;
		}
return false;
	}
/**
	 * Look up a value in the hashtable
	 * @param value value to look up
	 * @return 1 if the value is there, false otherwise
	 */
public boolean lookup(String value) {
//invalid input
if(value==null){
throw new NullPointerException();
		}
int hashNum = hashVal(value);
//not found, retuan false
if(storage[hashNum]==null){
return false;
		}
//found, then return true
else if(storage[hashNum].contains(value)){
return true;
		}
return false;
	}
/**
	 * Print out the string representation of the table
	 */
public void printTable() {
//iteratong through the hashtable
for(int i = 0;i<storage.length;i++){
System.out.print(i+": ");
if(storage[i]!=null) {
//if the slot is not empty
Iterator iter = storage[i].iterator();
if (iter.hasNext()) {
System.out.print(iter.next());
				}
//iterate through the linkedlist of the slot to print
while (iter.hasNext()){
System.out.print(", "+iter.next());
				}
			}
System.out.println();
		}
	}
/**
	 * Get the size of the table
	 * @return
	 */
public int getSize() {
return nelems;
	}
/**
	 * The hashfunction. It is the CRC function
	 * and is chosen from the letcture
	 * @param str the object to hash
	 * @return The hashvalue of the object
	 */
private int hashVal(String str) {
//iniialze to zero
int hashInt = 0;
for(int i = 0;i<str.length();i++){
//bits manipulation
int leftShiftValue = hashInt << LEFT_SHIFT;
int rightShiftValue = hashInt >>> RIGHTSHIFT;
//xoring
			hashInt = (leftShiftValue|rightShiftValue) ^ str.charAt(i);
		}
//return abs to avoid negative
return abs(hashInt % size);
	}
/**
	 * rehash the whole table when resizing
	 */
private void rehash() {
		expand++;
// if print is true, first print then rehash
if(printStats){
			printStatistics();
		}
//setting the instance variables
		nelems = 0;
		size*=DOUBLE;
LinkedList<String> new_arr[] = new LinkedList[this.size];
//store the old list
LinkedList<String> old_arr[] = storage;
		storage = new_arr;
		collision=0;
		longest = 0;
//iterating through all list elements to rehash everything
for(int i = 0;i<old_arr.length;i++){
if(!(old_arr[i]==null)){
Iterator iter = old_arr[i].iterator();
//using iterators
while (iter.hasNext()){
String value = (String) iter.next();
					insert(value);
				}
			}
		}
	}
/**
	 * Print out the statistics of the table to the file.
	 */
private void printStatistics() {
try {
//open a file
PrintWriter pw = new PrintWriter(new FileOutputStream(new
File(statsFileName),true));
//setting the variables
int resize = expand;
float load_factor = (float)nelems / size;
int num_collision = collision;
int longest_chain = longest;
//print according to the variables
			pw.println(resize+" resizes, load factor "+df.format(load_factor)
+", "+num_collision+" collisions, "+longest_chain
+" longest chain");
			pw.close();
		}
//catch exception
catch (IOException e) { // If the given file doesn’t exist
System.out.println("File does't exist");
		}
	}
public static void main(String[] args) {
HashTable hs  = new HashTable(7, "test.txt");
BufferedReader reader;
try {
			reader = new BufferedReader(new FileReader(
"long.dict.txt"));
String line = reader.readLine();
while (line != null) {
				hs.insert(line);
// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
