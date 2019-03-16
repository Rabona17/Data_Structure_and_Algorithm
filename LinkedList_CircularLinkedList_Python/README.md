# LinkedList and CircularLinkedList in Python
Since Python, unlike Java or other languages, doesn't have built-in `LinkedList` data type, I build this ADT from scratch using `list`.
## Complexity
* For `insert_at_first` and `insert_at_last`, the runtime complexity is O(n). Notice, However, when inserting a single node with **length 1** at first, the complexity is O(1).
* For `remove_first`, the complexity is O(1), and for `remove_last`, the complexity is O(n).
* For `insert_at` and `remove_at`, the complexity is O(n).
## CircularLinkedList
* The key is to find the entry to the circular and the length of the circle.
* More on `CircularLinkedList`, `DoublyLinkedList` will be introduced. 
