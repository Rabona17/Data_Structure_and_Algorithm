from Tree import *
def map_tree(tree, func):
    """
    Map a function to every leaf of a tree
    >>> t = Tree(1, [Tree(2), Tree(3, [Tree(4, [Tree(5)])]), Tree(6)])
    >>> map_tree(t, lambda x:x**-1)
    Tree(1.0, [Tree(0.5), Tree(0.3333333333333333, [Tree(0.25, [Tree(0.2)])]), Tree(0.16666666666666666)])
    >>> map_tree(t, lambda x:x**3)
    Tree(1, [Tree(8), Tree(27, [Tree(64, [Tree(125)])]), Tree(216)])
    """
    if tree.is_leaf():
        return Tree(func(tree.label))
    else:
        new_branch = []
        for branch in tree.branches[:]:            
            branch = map_tree(branch, func)
            new_branch.append(branch)
        return Tree(func(tree.label), new_branch)
        
def sum_labels(t):
    """Sum the labels of a Tree instance, which may be None."""
    return t.label + sum([sum_labels(b) for b in t.branches])
    
def virtice_match(tree, number):
    """
    Given a tree, and a value k, vertice_match() returns the number of vertices in the tree.
    """
    count = 0
    if tree.is_leaf():
        if tree.label == number:
            count+=1
        return count
    else:
        if tree.label == number:
            count+=1
        for branch in tree.branches:
            count += virtice_match(branch, number)
        return count
    
def tree_height(tree):
    height = 1
    if tree.is_leaf():
        return height
    else:
        return height + max(tree_height(branch) for branch in tree.branches)
    
def leaf_change(tree, old, new):
    if tree.is_leaf():
        if tree.label == old:
            return Tree(new)
        return tree
    else:
        new_branches = []
        new_label = tree.label
        if tree.label == old:
            new_label = new
        for branch in tree.branches:
            new_branches.append(leaf_change(branch, old, new))
        return Tree(new_label, new_branches)

def smallest_leaf(tree):
    if tree.is_leaf():
        return tree.label
    else:
        leafs = []
        for branch in tree.branches:
            leafs.append(smallest_leaf(branch))
        return min(leafs)
    
