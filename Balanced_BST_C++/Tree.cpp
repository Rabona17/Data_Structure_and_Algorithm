#include <cstdlib>
#include <string>
#include "Tree.h"
using namespace std;

template <class Whatever>
struct TNode {
        long balance;
        Whatever data;
        long height;
        TNode<Whatever> * left;
        long & occupancy;
        TNode<Whatever> * right;
        unsigned long & tree_count;
      
        TNode (const Whatever & element, Tree<Whatever> & theTree)
                : balance (0), data (element), height (0), left (0), 
                occupancy (theTree.occupancy), right (0), 
                tree_count (theTree.tree_count) {
                occupancy++;
        }
        
        TNode (const Whatever & element, TNode<Whatever> & parentTNode)
        : balance (0), data (element), height (0), left (0), 
                occupancy (parentTNode.occupancy), right (0), 
                tree_count (parentTNode.tree_count) {
		            occupancy++;
        }
        
        ~TNode (void) {
                occupancy--;
        }

        void delete_AllTNodes (void) {
                if(this){
			        //recursively deleting
			        left->delete_AllTNodes();
			        right->delete_AllTNodes();
			        delete this;
		        }
        }
        
        unsigned long Insert (const Whatever & element, 
                              TNode<Whatever> *& PointerInParent);


	

        void ReplaceAndRemoveMin (TNode<Whatever> & targetTNode, 
                TNode<Whatever> *& PointerInParent) {
		        //base case for recursion
          if(left == NULL){
			      //swapping the data
			      targetTNode.data = data;
			      Remove(*this, PointerInParent);
		      }
		      //if not the left most, continue
		      else{
			      left->ReplaceAndRemoveMin(targetTNode, left);
			      SetHeightAndBalance(PointerInParent);
		      }
        }
	
        unsigned long Remove (TNode<Whatever> & elementTNode, 
                TNode<Whatever> *& PointerInParent,
                long fromSHB = FALSE) {
		            long status = TRUE;//keeping track if the delete is successful
		            //if found the node, to delete
                if(elementTNode.data == data){
			          //if has two children
			               if(left!=NULL && right != NULL){
				                elementTNode.data = data;
				                //call helper ,ethod
				                right->ReplaceAndRemoveMin(*this, right);
			                }
			                else{
				              //if only one children
				              if(left != NULL && right == NULL){
					                PointerInParent = left;
				              }
				              //if only one children
				              else if(left == NULL && right != NULL){
					                PointerInParent = right;
				              }
				              //if is leaf
				              else if(left == NULL && right == NULL){
					                PointerInParent = NULL;
				              }
				              //store the data 
				              elementTNode.data = data;
				              delete this;
				              return TRUE;
			                }
                    //setting status
                    status = TRUE;
                    //return TRUE;
		          }
            //if current node is bigger, go left
            else if(elementTNode.data < data){
              //nothing to delete, fail
              if(!left){
                return FALSE;
              }
              status = left->Remove(elementTNode, left);
            }
            //if current nnode is smaller, go right
            else{
              //nothing to delete, false
              if(!right){
                return FALSE;
              }
              status = right -> Remove(elementTNode, right);
            }
            //setting height and balance
            if(! fromSHB && status==TRUE){
              SetHeightAndBalance(PointerInParent);
            }
            return status;
                }

                void SetHeightAndBalance (TNode<Whatever> *& PointerInParent) {
                        //setting
            if(PointerInParent==NULL){
              return;
            }
            //debug message on
            if(Tree<Whatever>::debug){
              cerr<< TREE <<tree_count
              << UPDATE << (const char*)data <<ENDING;
            }
            //if the node has two children
            if(left && right){
              //if left is taller
              if(left->height <= right->height){
                height = 1 + right->height;
              }
              //if right is taller
              else{
                height = 1 + left->height;
              }
              //setting balance
              balance = left->height - right->height;
            }
            //if it is a leaf
            else if(!left && !right){
              height = 0;
              balance = 0;
            }
            //if only has one node
            else if(!left){
              height = 1 + right->height;
              balance = -1 - right->height;
            }
            //if only has one node
            else if(!right){
              height = 1 + left->height;
              balance = left->height + 1;
            }		
            //remove and reinsert
            if(abs(balance) > THRESHOLD){
              TNode<Whatever> node(data, *PointerInParent);
              //call remove again, but fronSHB is True		
              Remove(*this, PointerInParent, TRUE);
              PointerInParent->Insert(node.data, PointerInParent);
            }
                }
        
        
        ostream & Write_AllTNodes (ostream & stream) const {
                if (left)
                        left->Write_AllTNodes (stream);
                stream << *this;
                if (right)
                        right->Write_AllTNodes (stream);

                return stream;
        }
};


template <class Whatever>
unsigned long TNode<Whatever> :: Insert (const Whatever & element, 
                                         TNode<Whatever> *& PointerInParent) {
	long status = TRUE;//keeping the status
	//if the element is already in
	if(element == data){
		//assign the value
		data = element;
                return FALSE;
        }
        //go right
        else if(data<element){
		//if nothingon right, start to insert
                if(!right){
			//creaint a new node
                        right = new TNode<Whatever>(element, *this);
			status = TRUE;
                }
		//else, continue recursion
                else{
                        status = right -> Insert(element, right);
                }
        }
        //go left
        else{
		//if nothing on left, start to insert
                if(!left){
			//create new node
                        left = new TNode<Whatever>(element, *this);
			status = TRUE;
                }
		//else, continue recursion
                else{
                        status = left -> Insert(element, left);
                }
        }
	//setting height and balance.
	if(status){
		SetHeightAndBalance(PointerInParent);
	}
	return status;
}

template <class Whatever>
ostream & operator << (ostream & stream, const TNode<Whatever> & nnn) {
        stream << "at height:  :" << nnn.height << " with balance:  "
                << nnn.balance << "  ";
        return stream << nnn.data << "\n";
}


template <class Whatever>
unsigned long Tree<Whatever> :: Insert (const Whatever & element) {
	//if root not there, create a new one
	if(!root){
		//new root
		root = new TNode<Whatever> (element, *this);
	}
	else{	
		//calling TNOde's insert
		root->Insert(element, root);
	}
	return 1;
}

template <class Whatever>
unsigned long Tree<Whatever> :: Lookup (Whatever & element) const {
	TNode<Whatever> * curr = root;
	while(curr){
		//if found, return true
		if(element == curr->data){
			element = curr->data;
			return TRUE;
		}
		//if smaller, go left
		else if(element < curr->data){
			//nothing left, not found
			if(curr->left == NULL){
				return FALSE;
			}
			//continue going
			else{
				curr = curr->left;
			}
		}
		//if bigger, go right
		else{
			//nothing right, not found
			if(curr->right == NULL){
				return FALSE;
			}
			//continue going if something's right
			else{
				curr = curr->right;
			}
		}
	}
	//not found after looping
	return FALSE;
}

template <class Whatever>
unsigned long Tree<Whatever> :: Remove (Whatever & element) {
	int status = 0;
	//if root is empty, return false
	if(!root){
		return FALSE;
	}
	//creating a rts objt holding data
	TNode<Whatever> node(element, *this);
	//calling root's remove
	status = root->Remove(node, root);
	element = node.data;
	return status;
}

template <class Whatever>
Tree<Whatever> :: Tree (void): occupancy (0), root (NULL)
{
        static long counter;
        tree_count = ++counter;
	//debug message
        if (debug) {
		cerr << TREE << tree_count << ALLOCATE;
	}
}

template <class Whatever>
Tree<Whatever> :: ~Tree (void)
{
	if(Tree<Whatever>::debug){
		cerr<<TREE<<tree_count<<DEALLOCATE;
	}
	//if root there, call the recursive delete_AllTNodes
        if(root!=NULL){
		root->delete_AllTNodes();
	}
}
