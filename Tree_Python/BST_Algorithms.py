##################################################
##The Following algorithms are done by recursion##
##Implementation by stack and queues are coming ##
##################################################
from Tree import *
from Stack_and_Queue.Queue import *

def PreOrder(bst):
  """
  Return the PreOrder Traversal of a bst
  """
    traversal=[]
    if bst == BSTree.empty:
        return traversal
    elif bst.is_leaf():
        traversal.extend([bst.label])
        return traversal
    else:
        traversal.extend([bst.label])
        traversal.extend(PreOrder(bst.left))        
        traversal.extend(PreOrder(bst.right))
        return traversal

def InOrder(bst):
  """
  Return the InOrder Traversal of a BST
  """
    traversal=[]
    if bst == BSTree.empty:
        return traversal
    elif bst.is_leaf():
        traversal.extend([bst.label])
        return traversal
    else:
        traversal.extend(InOrder(bst.left))  
        traversal.extend([bst.label])              
        traversal.extend(InOrder(bst.right))
        return traversal

def PostOrder(bst):
    traversal=[]
    if bst == BSTree.empty:
        return traversal
    elif bst.is_leaf():
        traversal.extend([bst.label])
        return traversal
    else:
        traversal.extend(PostOrder(bst.left))    
        traversal.extend(PostOrder(bst.right))
        traversal.extend([bst.label])            
        return traversal
      

##############################################
##The Following algorithms are done by stack##
##############################################     
def PreOrder_Stack(bst):
  """
  Using stack for preorder DFT
  """
    stack = Stack()
    traversal = []
    stack.push(bst)
    while stack.size!=0:
        bst = stack.pop()
        traversal.append(bst.label)
        if bst.right != BSTree.empty:
            stack.push(bst.right)
        if bst.left != BSTree.empty:
            stack.push(bst.left)
    return traversal
  
def InOrder_Stack(bst):
  """
  Using stack for inorder DFT
  """
    stack = Stack()
    traversal = []
    while stack.size!=0 or bst:
        if not bst:
            bst=stack.pop()
            traversal.append(bst.label)
            bst=bst.right
        else:
            stack.push(bst)
            bst=bst.left
    return traversal
  
def search(bst, toFind):
    if bst==[]:
        return False
    if bst.label==toFind:
        return True
    elif bst.is_leaf():
        return bst.label==toFind
    elif bst.left ==[]:
        return search(bst.right, toFind)
    elif bst.right ==[]:
        return search(bst.left, toFind)
    else:
        return search(bst.left, toFind) or search(bst.right, toFind)

def insert(bst, toInsert):
    if search(bst, toInsert):
        print('Already exists')
    elif bst==[]:
        print(1)
        bst = BSTree(toInsert)
        return bst
    else:
        if toInsert<bst.label:
            print(4)
            bst.right = insert(bst.right, toInsert)
        else:
            print(5)
            bst.left = insert(bst.left, toInsert)
        return bst
