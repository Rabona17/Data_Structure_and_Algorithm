class LinkNode:
   
    def __init__(self,value,nxt=None):
        assert isinstance(nxt,LinkNode) or nxt is None
        self.value = value
        self.next = nxt
    def get_value(self):
        """
        :param self: A LinkNode object
        :return the value of the object
        """
        return self.value
    def set_value(self,value):
        """
        setter for the LinkNode calss
        :param value: A value to be added to the LinkNode object
        """
        self.value=value
    def get_next(self):
        """
        :param self: A LinkNode object
        :return the next LinkNode obj of the object
        """
        return self.next
    def set_next(self,nxt):
        """
        setter for the LinkNode calss
        :param value: A Node to be added to the next of LinkNode object
        """
        self.next=nxt
    def __repr__(self):
        return repr(self.value)+ ", "  + repr(self.next)

def insert_at(elem, pos, link):
    """
    :param elem: a LinkNode object
    :param pos: a nonegative integer
    :param link: a Linked List formed by one or more LinkNode object
    >>> temp = None
    >>> insert_at(LinkNode(6),0, temp)
    6, None
    >>> temp = LinkNode(5)
    >>> insert_at(LinkNode(6),0, temp)
    6, 5, None
    >>> temp = LinkNode(5)
    >>> insert_at(LinkNode(6),1, temp)
    5, 6, None
    >>> temp = LinkNode(5, LinkNode(6, LinkNode(7)))
    >>> insert_at(LinkNode(8),2, temp)
    5, 6, 8, 7, None
    >>> temp = LinkNode(5, LinkNode(6, LinkNode(7)))
    >>> insert_at(LinkNode(8),0, temp)
    8, 5, 6, 7, None
    >>> temp = LinkNode(5, LinkNode(6, LinkNode(7)))
    >>> insert_at(LinkNode(8, LinkNode(9)),0, temp)
    8, 9, 5, 6, 7, None
    >>> temp = LinkNode(5, LinkNode(6, LinkNode(7)))
    >>> insert_at(LinkNode(8, LinkNode(9)),2, temp)
    5, 6, 8, 9, 7, None
    """
    if pos == 0:
        return insert_first(elem, link)
    else:
        pos += -1
        return LinkNode(link.get_value(),insert_at(elem, pos, link.get_next()))

def remove_at(pos, link):
    """
    :param pos: a nonegative integer
    :param link: a Linked List formed by one or more LinkNode object
    >>> temp = LinkNode(5,LinkNode(6,LinkNode(7)))
    >>> remove_at(0,temp)
    6, 7, None
    >>> temp = LinkNode(5,LinkNode(6,LinkNode(7)))
    >>> remove_at(2,temp)
    5, 6, None
    >>> temp = LinkNode(5,LinkNode(6,LinkNode(7)))
    >>> remove_at(1,temp)
    5, 7, None
    >>> temp = LinkNode(5,LinkNode(6,LinkNode(7)))
    >>> remove_at(0,temp)
    6, 7, None
    >>> temp = LinkNode(5)
    >>> remove_at(0,temp)
    >>> temp = LinkNode(5,LinkNode(6,LinkNode(7)))
    >>> remove_at(2,temp)
    5, 6, None
    """
    if pos == 0:
        return link.get_next()
    else:
        pos += -1
        return LinkNode(link.get_value(), remove_at(pos, link.get_next()))
        
