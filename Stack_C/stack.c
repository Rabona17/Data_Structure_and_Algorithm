#include <malloc.h>
#include <stdio.h>
#include "mylib.h"
#include "stack.h"

#define STACK_POINTER_INDEX (-1)        /* Index of last used space */
#define STACK_SIZE_INDEX (-2)           /* Index of size of the stack */
#define STACK_COUNT_INDEX (-3)          /* Index of which stack allocated */
#define STACK_OFFSET 3  /* offset from allocation to where user info begins */

/* catastrophic error messages */
static int stack_counter = 0; /* number of stacks allocated so far */


/* start of true stack code */

/*---------------------------------------------------------------------------
Function Name:                delete_Stack
Purpose:                      This function exists to delete stack
Description:                  This function releases and delete the memory of
			      a stack allocated in heap, and reset the input
			      pointer to null;

Input:                        A pointer to a pointer to a stack

Output:                       None.
Result:                       Memory in heap released.  Nothing returned.
Side Effects:                 None.
---------------------------------------------------------------------------*/
void delete_Stack (Stack ** spp) {
    /*Edge case*/
    if(spp == NULL || *spp == NULL){
        fprintf(stderr, DELETE_NONEXIST);
    }
    else{
	/*dealing with debug message*/
        free(* spp - STACK_OFFSET);
        *spp = NULL;
	    spp = NULL;
        stack_counter--;

    }
}

/*---------------------------------------------------------------------------
Function Name:                empty_stack
Purpose:                      clear a stack
Description:                  This function reset the pointer inside a stack,
			      and make the stack to a stack with len 1.
Input:                        A pointer to a stack
Output:                       None
Result:                       Clear the stack
Side Effects:                 None.
---------------------------------------------------------------------------*/
void empty_Stack (Stack * this_Stack) {
    /*error checking*/
    if(this_Stack == NULL){
        fprintf(stderr, "EMPTY_NONEXIST");
    }
    /*if it is not empty*/
    else{
        this_Stack[STACK_POINTER_INDEX] = -1;
    }        
}

/*--------------------------------------------------------------------------
Function Name:                isEmpty
Purpose:                      Check if a stack is empty
Description:                  This function checks the pointerIndex of a stack
			      to decide if it is empty

Input:                        a pointer to a stack
Output:                       boolean True or false
Result:                       True is a stack is empty, false otherwise.
Side Effects:                 None.
---------------------------------------------------------------------------*/
long isempty_Stack (Stack * this_Stack) {
    /*if the pointer points to zero*/
    if(this_Stack == NULL){
        fprintf(stderr, "ISEMPTY_NONEXIST");
        return 0;
    }
    /*Tracking the pointer index*/
    if(this_Stack[STACK_POINTER_INDEX] < 0){
        return 1;
    }
    return 0;
}

/*--------------------------------------------------------------------------
Function Name:                isFull_stack
Purpose:                      Check if a stack is full
Description:                  This function checks the pointerIndex of a stack
			      to decide if it is full

Input:                        a pointer to a stack
Output:                       boolean True or false
Result:                       True is a stack is full, false otherwise.
Side Effects:                 None.
---------------------------------------------------------------------------*/
long isfull_Stack (Stack * this_Stack) {
    /*If the pointer points to null*/
    if(this_Stack == NULL){
        fprintf(stderr, "ISFULL_NONEXIST");
        return 0;
    }
    /*Not full*/
    if(this_Stack[STACK_POINTER_INDEX] < this_Stack[STACK_SIZE_INDEX] -1){
        return 0;
    }
    return 1;
}

/*---------------------------------------------------------------------------
Function Name:                new_Stack
Purpose:                      Constructor
Description:                  The function constructs a new stack by first
			      taking in a size and then allocate memory in a 
			      heap, with 3 of its first elements keeping the
			      information of the stack.
Input:                        stack_size
Output:                       the address of the staring of a stack
Side Effects:                 None.
---------------------------------------------------------------------------*/
Stack * new_Stack (unsigned long stacksize) {
    /* Allocate and initialize */
    void * memory = malloc((stacksize + STACK_OFFSET) * sizeof(long));
    /* 3 units */
    Stack * this_stack = (Stack*) memory + STACK_OFFSET;
    /* Initialize */
    this_stack[STACK_POINTER_INDEX] = -1;
    this_stack[STACK_SIZE_INDEX] = stacksize;
    /*Add counter number*/
    stack_counter++;
    this_stack[STACK_COUNT_INDEX] = stack_counter;
    return this_stack; 
}

/*---------------------------------------------------------------------------
Function Name:                get_occupqncy
Purpose:                      Check the size of the stack
Description:                  This function takes in a pointer to a stack and
			      use the information(pointers) to get the size 
			      of the stack

Input:                        a pointer to a stack
Output:                       The size of the stack
Side Effects:                 None.
---------------------------------------------------------------------------*/
long get_occupancy (Stack * this_Stack) {
    if(this_Stack == NULL){
        fprintf(stderr, "NUM_NONEXIST");
        return 0;
    }
    /*using the internal pointer to decide the size*/
    return this_Stack[STACK_POINTER_INDEX] + 1;
}

/*---------------------------------------------------------------------------
Function Name:                pop
Purpose:                      Pop a stack
Description:                  This function popped a =n element from a stack,
			      according to its first in last out property. The
			      value returned is tracked by its pointer to last
			      value.
.
Input:                        A stack and an pointer to a long 
Output:                       The value being popped successfully or not
Result:                       An element get popped
Side Effects:                 None.
---------------------------------------------------------------------------*/
long pop (Stack * this_Stack, long * item) {
    if(this_Stack == NULL){
        fprintf(stderr, "POP_NONEXIST");
        return 0;
    }
    /*If the incoing doen't exit*/
    if(item == NULL){
	fprintf(stderr, "INCOMING_NONEXIST");
	return 0;
    }
    /*If stack is empty*/
    if(isempty_Stack(this_Stack)){
        fprintf(stderr, "POP_EMPTY");
        return 0;
    }
    /*assign to output parameter*/	
    *item = this_Stack[this_Stack[STACK_POINTER_INDEX]];
    this_Stack[STACK_POINTER_INDEX] -= 1;
    return 1;
}

/*---------------------------------------------------------------------------
Function Name:                push
Purpose:                      Push an element to a stack
Description:                  This function push an element into a stack,
			      according to its first in last out property. The
			      value returned is either successful or not.

Input:                        A stack and an pointer to a long for output
Output:                       The value being pushed successful or not
Result:                       An element get pushed if possible.
Side Effects:                 None.
---------------------------------------------------------------------------*/
long push (Stack * this_Stack, long item) {
    /*If the pointer is null, fail*/
    if(this_Stack == NULL){
        fprintf(stderr, "PUSH_NONEXIST");
        return 0;
    } 
    /*If the stack is full, don't push!*/
    if(isfull_Stack(this_Stack)){
        fprintf(stderr, "PUSH_FULL");
        return 0;
    }
    this_Stack[++this_Stack[STACK_POINTER_INDEX]] = item;

    return 1;
}

/*---------------------------------------------------------------------------
Function Name:                top
Purpose:                      Top a stack
Description:                  This function topped a =n element from a stack,
			      according to its first in last out property. The
			      value returned is tracked by its pointer to last
			      value.
.
Input:                        A stack and an pointer to a long 
Output:                       The value being topped successfully or not
Result:                       An element get topped
Side Effects:                 None.
---------------------------------------------------------------------------*/
long top (Stack * this_Stack, long * item) {
    /* If the pointer is null, fail*/
    if(this_Stack == NULL){
        fprintf(stderr, "TOP_NONEXIST");
        return 0;
    }
    /*If the incoing doen't exit*/
    if(item == NULL){
	fprintf(stderr, "INCOMING_NONEXIST");
	return 0;
    }
    /*If the stack is empty*/
    if(isempty_Stack(this_Stack)){
        fprintf(stderr, TOP_EMPTY);
        return 0;
    }
    
    *item = this_Stack[this_Stack[STACK_POINTER_INDEX]];

    return 1;
}
