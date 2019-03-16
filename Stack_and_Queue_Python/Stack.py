from Utils import *

class Stack():
    """
    >>> stack = Stack()
    >>> stack.size
    0
    >>> stack.push(1)
    >>> stack.push(2)
    >>> stack.items
    2 -> 1 -> None
    >>> stack.pop()
    2
    >>> stack.items
    1 -> None
    >>> stack.pop()
    1
    >>> stack.pop() is None
    True
    """
    def __init__(self):        
        self.items = None
        self.size = 0
    
    def pop(self):
        if (self.items!=None):
            copy = self.items
            self.items = clip_list(copy, 0)
            self.size += -1
            return copy.get_value()
        else: 
            return None
    
    def push(self, value):
        self.items = LinkNode(value, self.items)
        self.size += 1
