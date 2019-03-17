class Tree:
    def __init__(self, label, branches=()):
        self.label = label
        for branch in branches:
            assert isinstance(branch, Tree)
        self.branches = branches
    def __repr__(self):
        if self.branches:
            return 'Tree({0}, {1})'.format(self.label, repr(self.branches))
        else:
            return 'Tree({0})'.format(repr(self.label))
    def is_leaf(self):
        return not self.branches
        

class BSTree:
    empty = ()
    def __init__( self , label , left = empty, right = empty):
        self.label = label
        self.left = left
        self.right = right

    def is_leaf ( self ):
        if self . left == BSTree.empty and self.right == BSTree.empty:
            return True
        return False
