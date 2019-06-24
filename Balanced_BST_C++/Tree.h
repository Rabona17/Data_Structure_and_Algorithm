//=========================================================================
// class Tree
//
// Description: Implements the polymorphic generic container binary search
//		tree. It can contain any type of objects under constraints,
//		and it uses template.
//
// Data Fields:
//		occupancy(long) how many nodes are in the tree
//		root(*TNode) the entry to the tree
//		tree_count (unsigned long)how many trees exists.
//		debug(int) if the debug message is on
//		
// Public functions:
//		Tree (void);constrcutor of the tree
//        	~Tree (void);destructor of the tree
//
//        	static void Set_Debug_On (void);set the debug on
//        	static void Set_Debug_Off (void);det the debug off
//
//        	unsigned long Insert (const Whatever &);insert a whatever in 
//			tree
//        	unsigned long Lookup (Whatever &) const;LOOKUP whatever in tree
//        	unsigned long Remove (Whatever &);remove whatever in tree
//        	ostream & Write (ostream &) const;write out the tree.
//		
//==========================================================================
#ifndef TREE_H
#define TREE_H

/* DO NOT ADD CODE: This file is used in evaluation
 * Changing function signatures will result in a 25% deduction.
 * adding comments is ok.
 */

#include <iostream>
using namespace std;

// debug message
static const char ALLOCATE[] = " - Allocating]\n";
static const char TREE[] = "[Tree ";

template <class Whatever>
struct TNode;

template <class Whatever>
class Tree {
        friend struct TNode<Whatever>;
        long occupancy;
        TNode<Whatever> * root;
        unsigned long tree_count;
        static int debug;
public:
        
        Tree (void);
        ~Tree (void);
        
        static void Set_Debug_On (void);
        static void Set_Debug_Off (void);
        
        unsigned long Insert (const Whatever &);
        unsigned long Lookup (Whatever &) const;
        unsigned long Remove (Whatever &);
        ostream & Write (ostream &) const;
};

#include "Tree.c"

#endif
