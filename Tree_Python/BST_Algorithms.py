##################################################
##The Following algorithms are done by recursion##
##Implementation by stack and queues are coming ##
##################################################
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
      
