def map_tree(tree, func):
    """
    Map a function to every leaf of a tree
    >>> t = Tree(2, (Tree(2), Tree(2, (Tree(2), Tree(2)))))
    >>> map_tree(t, lambda x:x**-1)
    Tree(0.5, (Tree(0.5), Tree(0.5, (Tree(0.5), Tree(0.5)))))
    >>> map_tree(t, lambda x:x**3)
    Tree(8, (Tree(8), Tree(8, (Tree(8), Tree(8)))))
    """
    if tree.is_leaf():
        return Tree(func(tree.label))
    else:
        new_branch = []
        for branch in tree.branches[:]:            
            branch = map_tree(branch, func)
            new_branch.append(branch)
        return Tree(func(tree.label), tuple(new_branch))
        
def sum_labels(t):
    """Sum the labels of a Tree instance, which may be None."""
    return t.label + sum([sum_labels(b) for b in t.branches])
    
