from LinkedList_CircularLinkedList_Python.LinkedList import *

def LinkedList_from_list(ls):
    """
    >>> print(LinkedList_from_list([2, 4, 6, 8, 10]))
    2 -> 4 -> 6 -> 8 -> 10 -> None

    >>> print(LinkedList_from_list([x % 3 for x in range(5)]))
    0 -> 1 -> 2 -> 0 -> 1 -> None

    >>> print(LinkedList_from_list(['m', 'a', 'r']))
    'm' -> 'a' -> 'r' -> None
    """
    if len(ls)==1:
        return LinkNode(ls[0])
    else:
        return LinkNode(ls[0], LinkedList_from_list(ls[1:]))

def list_from_LinkedList(link):
    """
    >>> list_from_LinkedList(LinkNode('0', LinkNode('m', \
    LinkNode('a', LinkNode('r', LinkNode('1'))))))
    ['0', 'm', 'a', 'r', '1']

    >>> list_from_LinkedList(LinkNode(None, None))
    []

    >>> list_from_LinkedList(LinkNode(LinkNode(17)))
    [17 -> None]

    """
    lst = []
    if link.get_value()==None:
        return []
    elif link.get_next()==None:
        lst = [link.get_value()]
        return lst
    
    else:
        lst+= link.get_value()
        lst+= list_from_LinkedList(link.get_next())
        return lst

def clip_list (s , index):
    """
    >>> s1 = LinkNode (1 , LinkNode (2 , LinkNode (3)))
    >>> clip_list ( s1 , 0)
    2 -> 3 -> None
    >>> s1
    1 -> None
    >>> s2 = LinkNode (1 , LinkNode (2 , LinkNode (3)))
    >>> clip_list ( s2 , 2) == None # No elements after index 2
    True
    >>> s2
    1 -> 2 -> 3 -> None
    >>> s3 = LinkNode (1 , LinkNode (2 , LinkNode (3)))
    >>> try:clip_list ( s3 , 3) #no index 3 available
    ... except(IndexError):
    ...   print("good job")
    ...
    good job
    """
    if index<0 or s==None:
        raise IndexError
    elif index == 0:
        next_val = s.get_next()
        s.set_next(None)
        return next_val
    else:
        index += -1
        next_val = s.get_next()       
        return clip_list(next_val, index)
        
