import numpy as np

class Queue:
    """
    A queue, dequeues from front and enqueues at rear
    >>> a=Queue()
    >>> a.enqueue(1)
    >>> a.enqueue(2)
    >>> a.enqueue(3)
    >>> a.enqueue(4)
    >>> a.enqueue(5)
    >>> a.front
    0
    >>> a.rear
    5
    >>> a.data
    array([1, 2, 3, 4, 5, None], dtype=object)
    >>> a.capacity
    6
    >>> a.dequeue()
    1
    >>> a.front
    1
    >>> a.rear
    5
    
    >>> a=Queue(10)
    >>> a.capacity
    10
    
    >>> b=Queue()
    >>> b.dequeue()
    Traceback (most recent call last):
    ...
    AssertionError: attempt to dequeue from an empty queue
    
    >>> b.enqueue(1)
    >>> b.enqueue(max)
    >>> b.dequeue()
    1
    >>> b.dequeue()
    <built-in function max>
    >>> b.front
    2
    >>> b.rear
    2
    >>> b.dequeue()
    Traceback (most recent call last):
    ...
    AssertionError: attempt to dequeue from an empty queue
    """
    
    def __init__(self,capacity=3):
        """
        :param capacity: a positive integer
        >>> q = Queue()
        >>> q = Queue(4)
        >>> q.capacity
        4
        >>> q = Queue(10)
        >>> q.capacity
        10
        >>> q = Queue(4)
        >>> q.data
        array([None, None, None, None], dtype=object)
        """
        self.front = 0
        self.rear = 0
        self.num_elems = 0
        self.capacity = capacity
        self.data = np.empty(self.capacity, dtype = object)
    
    def dequeue(self):
        """
        dequeues from the front of the queue
        
        >>> q = Queue()
        >>> q.dequeue()
        Traceback (most recent call last):
        ...
        AssertionError: attempt to dequeue from an empty queue
        >>> q = Queue(capacity = 1)
        >>> q.enqueue(5)
        >>> q.enqueue(6)
        >>> q.dequeue()
        5
        >>> q.dequeue()
        6
        >>> q.dequeue()
        Traceback (most recent call last):
        ...
        AssertionError: attempt to dequeue from an empty queue
        """
        assert (self.num_elems != 0), 'attempt to dequeue from an empty queue'
        elem = self.data[self.front]
        self.front += 1
        if (self.front == len(self.data)):
            self.front = 0
        self.num_elems -= 1
        return elem
    
    def enqueue(self,elem):
        """
        enqueue at the rear of the queue
        :param elem: a value that is not None

        >>> q = Queue()
        >>> q.enqueue("a")
        >>> q = Queue(capacity = 1)
        >>> q.enqueue(5)
        >>> q.enqueue(6)
        >>> q.num_elems
        2
        """
        def enqueue_one(elem):
            if self.is_full():
                self.expand()
            self.num_elems += 1
            self.data[self.rear] = elem
            self.rear += 1
            if (self.rear == len(self.data)):
                self.rear = 0
        if not iter(elem):
            enqueue_one(elem)
        else:
            for i in elem:
                enqueue_one(i)

    def expand(self):
        """
        expand the capacity of the circular array when needed
        >>> q = Queue()
        >>> q.capacity
        3
        >>> q.expand()
        >>> q.capacity
        6
        >>> q.expand()
        >>> q.capacity
        12
        >>> q.expand()
        >>> q.capacity
        24
        >>> q.expand()
        >>> q.capacity
        48
        """
        double_size = 2
        
        new_arr = np.empty(self.capacity, dtype = object)
        for i in range(self.front, len(self.data)):
            newArr[i] = self.data[i]
            
        self.rear = self.data.length
        for i in range(0, front):
            rear += 1
            newArr[rear-1] = data[i]
        
        self.data = new_arr
        self.capacity *= double_size
#         self.rear = self.capacity
#         self.capacity *= double_size
#         new_arr = np.empty(self.capacity, dtype = object)
#         for i in np.arange(0, len(self.data)):
#             new_arr[i] = self.data[i]
#         self.data = new_arr

    def is_full(self):
        """
        checks if circular array is full
        >>> q = Queue()
        >>> for i in range(4): q.enqueue(i)
        >>> q.data
        array([0, 1, 2, 3, None, None], dtype=object)
        >>> q.is_full()
        False
        
        >>> q = Queue(capacity = 3)
        >>> for i in range(4): q.enqueue(i)
        >>> q.data
        array([0, 1, 2, 3, None, None], dtype=object)
        >>> q.is_full()
        False
        
        >>> q = Queue()
        >>> for i in range(5): q.enqueue(i)
        >>> q.data
        array([0, 1, 2, 3, 4, None], dtype=object)
        >>> q.is_full()
        False
        
        >>> q = Queue()
        >>> for i in range(6): q.enqueue(i)
        >>> q.data
        array([0, 1, 2, 3, 4, 5], dtype=object)
        >>> q.is_full()
        True
        """
        return self.num_elems == self.capacity


    def is_empty(self):
        """
        checks if circular array is full
        >>> q = Queue()
        >>> q.is_empty()
        True
        
        >>> q = Queue()
        >>> q.enqueue("a")
        >>> q = Queue(capacity = 4)
        >>> q.enqueue(5)
        >>> q.enqueue(6)
        >>> q.enqueue(5)
        >>> q.enqueue(6)
        >>> q.is_empty()
        False
        >>> for i in range(4): q.dequeue()
        5
        6
        5
        6
        >>> q.is_empty()
        True
        """
        return self.num_elems == 0
 
