/**
 * User must define a Base class as a generic class for this hashtable!!!!!!!!!!!!!!!!
 * Like java it must implement comparable(operator override here!!!!!!!!!!!)
 *
 */
#include <cstdlib>
#include <string.h>
#include "Hash.h"

#ifndef TRUE
#define TRUE 1
#endif

#ifndef FALSE
#define FALSE 0
#endif

using namespace std;


HashTable :: HashTable (long sz) : size (sz),
        table_count(++counter), occupancy (0), table (new Base *[sz])
{
	counter = 0;
        index = 0;
	/*initialize all values*/
        for (long index = 0; index < size; index++){
            table[index] = NULL;
        }

}


HashTable :: ~HashTable (void)
{
	/* call function to delete individual elements */
	for (long index = 0; index < size; index++){
           delete(table[index]);
        }
	/* call function to delete individual elements */

	delete[] table;
	/* delete table itself */
	/* end: ~HashTable */
}

long HashTable :: Insert (Base * element, long recursiveCall)

{   
    if(recursiveCall){
	index = (index +  (long) *element % (size - 1) + 1) % size;
    }
    else{
	index = (long) *element % size;
    }
    //returned value
    const Base* atLoc= Locate(element);
    //for future input
    Base * nonConst = table[index];
    //nothing
    if(atLoc == NULL){
        table[index] = element;   
        occupancy++;
        return TRUE;
    }
    //if equal
    if(*atLoc == *element){
	//free old
	    delete atLoc;
	    table[index] = element;
        return TRUE;
    }
    //if full
    if(occupancy >= size){
        return FALSE;
    }
    //bully?
    if(*atLoc < *element){
        table[index] = element;
        Insert(nonConst, TRUE);
        return TRUE;
    }
    return FALSE;
}


const Base * HashTable :: Locate (const Base * element) const
{
    //increment for algorithm
    int increment = (long) *element % (size - 1) + 1;
    //keeping track of whether is at end
    int locateCount = 0;
    //index = (long) *element % size;
    //for comparison
    const Base & ref = *element;
    while(!(table[index]==NULL || *table[index]== ref || *table[index] < ref 
	|| locateCount > size)){
	//update index
	index+=increment;
        if(index >= size){
            index = index % size;
        }
        locateCount++;
    }
    return table[index]; 
}


const Base * HashTable :: Lookup (const Base * element) const
{
    index = (long) *element % size;
    //returned value
    const Base * atLoc= Locate(element);
    if (atLoc==NULL){
        return NULL;
    }
    //if equal->found
    if(*atLoc == *element){
        return atLoc;
    }
    //not found
    if(*atLoc < *element){
        return NULL;
    }
    return NULL;
}


ostream & HashTable :: Write (ostream & stream) const
{
	stream << "Hash Table " << table_count << ":\n"
		<< "size is " << size << " elements, "
		<< "occupancy is " << occupancy << " elements.\n";

	/* go through all table elements */
	for (long index = 0; index < size; index++)
		if (table[index])
			table[index]->Write(stream << "at index "
			<< index << ":  ") << "\n";
	return stream;
}	/* end: Write */

