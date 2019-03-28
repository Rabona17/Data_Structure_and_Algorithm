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
