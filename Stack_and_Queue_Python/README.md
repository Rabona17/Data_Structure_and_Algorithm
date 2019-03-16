# Stack and Queue
Like `LinkedList`, there is no built in ADT in Python for `Stack` and `Queue`, so here I am building `Stack` using `LinkedList`, and `Queue` using `Circular Array` to optimize complexity.
## Why Circular Array?
Since Queue is a FIFO data type, using regular array will result in either `enqueue` or `dequeue` to have a complexity of O(n). However, using `Circular Array` and two pointers (on point to front and the other to rear), the complexity will both be O(1).
