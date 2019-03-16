class LinkNode:
    """
    >>> temp = LinkNode(None)
    >>> temp.get_value()
    >>> temp.get_next()
    >>> temp.set_value(1)
    >>> temp.get_value()
    1
    >>> temp.set_next(LinkNode(1, LinkNode(None)))
    >>> temp.get_next()
    1, None, None
    
    >>> temp = LinkNode(1, LinkNode(None, LinkNode(None)))
    >>> temp.get_next()
    None, None, None
    >>> temp.set_next(LinkNode(2, LinkNode(1)))
    >>> temp
    1, 2, 1, None
    """
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

def insert_first(elem, link):
    """
    :param elem: a LinkNode object
    :param link: a Linked List formed by one or more LinkNode object

    >>> temp = LinkNode(5)
    >>> insert_first(LinkNode(6),temp)
    6, 5, None
    >>> temp = None
    >>> insert_first(LinkNode(6),temp)
    6, None
    >>> temp = None
    >>> insert_first(LinkNode(None),temp)
    None, None
    >>> temp = LinkNode(4, LinkNode(2))
    >>> insert_first(LinkNode(5, LinkNode(3)),temp)
    5, 3, 4, 2, None
    >>> temp = LinkNode(8, LinkNode(8))
    >>> insert_first(LinkNode(None),temp)
    None, 8, 8, None
    """
    return insert_last(link, elem)

def insert_last(elem,link):
    """
    :param elem: a LinkNode object
    :param link: a Linked List formed by one or more LinkNode object

    >>> temp = LinkNode(5)
    >>> insert_last(LinkNode(6), temp)
    5, 6, None
    >>> temp = None
    >>> insert_last(LinkNode(6), temp)
    6, None
    >>> temp = LinkNode(5)
    >>> insert_last(LinkNode(6, LinkNode(5)), temp)
    5, 6, 5, None
    >>> temp = LinkNode(None)
    >>> insert_last(LinkNode(6), temp)
    None, 6, None
    >>> temp = LinkNode(5)
    >>> insert_last(LinkNode(6, LinkNode(7, LinkNode(5))), temp)
    5, 6, 7, 5, None
    """
    if link == None:
        return elem
    else:
        return LinkNode(link.get_value(), insert_last(elem,link.get_next()))

def remove_last(link):
    """
    :param link: a Linked List formed by one or more LinkNode object

    >>> temp = LinkNode(5,LinkNode(6,LinkNode(7)))
    >>> remove_last(temp)
    5, 6, None
    >>> temp = LinkNode(5,LinkNode(6))
    >>> remove_last(temp)
    5, None
    >>> temp = LinkNode(5)
    >>> remove_last(temp)
    >>> temp = LinkNode(None)
    >>> remove_last(temp)
    >>> temp = LinkNode(5,LinkNode(6,LinkNode(7, LinkNode(7, LinkNode(7)))))
    >>> remove_last(temp)
    5, 6, 7, 7, None
    >>> temp = LinkNode(5,LinkNode(None))
    >>> remove_last(temp)
    5, None
    """
    if link.get_next() == None:
        return None
    else:
        return LinkNode(link.get_value(), remove_last(link.get_next()))

def remove_first(link):
    """
    :param link: a Linked List formed by one or more LinkNode object

    >>> temp = LinkNode(5)
    >>> remove_first(temp)
    >>> temp = LinkNode(5,LinkNode(6))
    >>> remove_first(temp)
    6, None
    >>> temp = LinkNode(5,LinkNode(6,LinkNode(7)))
    >>> remove_first(temp)
    6, 7, None
    >>> temp = LinkNode(6)
    >>> remove_first(temp)
    >>> temp = LinkNode(5,LinkNode(6, LinkNode(7, LinkNode(8))))
    >>> remove_first(temp)
    6, 7, 8, None
    >>> temp = LinkNode(None,LinkNode(6, LinkNode(7)))
    >>> remove_first(temp)
    6, 7, None
    """
    return link.get_next()

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
