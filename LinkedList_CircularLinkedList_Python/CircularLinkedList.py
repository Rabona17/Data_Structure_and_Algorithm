from LinkedList import *

#Creating a CircularLinkedList
temp = LinkNode(1, LinkNode(2, LinkNode(3, LinkNode(4, LinkNode(5)))))
ptr = temp
while ptr.next is not None:
    ptr = ptr.next
ptr.next = temp
del ptr

#Permuting a CircularLinkedList
def permutation(link, k):
    first_val = link.get_value()
    length = 1
    result = []
    link_for_length = link
    while link_for_length.get_next().get_value() != first_val:
        length += 1
        link_for_length = link_for_length.get_next()
    
    if k == length:
        while length != 0:
            value = link.get_value()
            permuted = LinkNode(value, LinkNode(link.get_value()))
            link = link.get_next()
            result.append(permuted)
            length += -1
        return result
            
        
    while length != 0:
        value = link.get_value()
        for _ in range(0, k):
            link = link.get_next()
        permuted = LinkNode(value, LinkNode(link.get_value()))
        result.append(permuted)
        length += -1
    return result
    
