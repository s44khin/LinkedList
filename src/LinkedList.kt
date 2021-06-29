/**
 * Kotlin implementation of LinkedList (Java)
 * @author s44khin
 */
class LinkedList<T>(vararg args: T): MutableList<T>, Cloneable {
  /** List size */
  override var size = 0

  /** First element */
  private lateinit var first: Node<T>

  /** Last element */
  private lateinit var last: Node<T>

  /** @return the range of valid indices for the list */
  val indices: IntRange
    get() = IntRange(0, lastIndex())

  init {
    addAll(*args)
  }

  /**
   * List entry
   * @param prev previous element (null - if element is first)
   * @param next next element (null - if element is last)
   */
  class Node<T>(var prev: Node<T>?, var elem: T?, var next: Node<T>?)

  /** Adds [element] to the end of the list. */
  override fun add(element: T): Boolean {
    if (size == 0) {
      first = Node(null, element, null)
      last = first
    } else {
      last.next = Node(last, element, null)
      last = last.next!!
    }

    size++
    return true
  }

  /** Adds an [element] to the list at the [index] position with an offset to the end of the list. */
  override fun add(index: Int, element: T) {
    if (!(isIndex(index)))
      throw IndexOutOfBoundsException(index)

    val x = getNode(index)

    when (index) {
      0 -> {
        x.prev = Node(null, element, x)
        first = x.prev!!
      }
      size - 1 -> x.prev!!.next = Node(x.prev, element, x)
      else -> {
        x.prev!!.next = Node(x.prev, element, x)
        x.prev = x.prev!!.next!!
      }
    }

    size++
  }

  /** Inserts all of the elements of the specified collection [elements] into this list. */
  fun addAll(vararg elements: T): Boolean {
    for (element in elements)
      add(element)

    return true
  }

  /**
   * Inserts all of the elements of the specified collection [elements] into this list at the specified [index].
   * @return `true` if the list was changed as the result of the operation.
   */
  override fun addAll(index: Int, elements: Collection<T>): Boolean {
    var i = index

    for (element in elements) {
      add(i, element)
      i++
    }

    return true
  }

   /**
   * Adds all of the elements of the specified collection to the end of this list.
   * The elements are appended in the order they appear in the elements collection.
   * @return `true` if the list was changed as the result of the operation.
   */
  override fun addAll(elements: Collection<T>): Boolean {
    for (element in elements)
      add(element)

    return true
  }

  /** Removes the list item from position [index] */
  override fun removeAt(index: Int): T {
    if (!(isIndex(index)))
      throw IndexOutOfBoundsException(index)

    val x = getNode(index)

    when (index) {
      0 -> {
        first = x.next!!
        first.prev = null
      }
      size - 1 -> {
        last = x.prev!!
        last.next = null
      }
      else -> {
        x.prev!!.next = x.next
        x.next!!.prev = x.prev
      }
    }

    size--
    return x.elem!!
  }

  /**
   * Removes a single instance of the specified element from this
   * collection, if it is present.
   * @return `true` if the element has been successfully removed; `false` if it was not present in the collection.
   */
  override fun remove(element: T): Boolean {
    val check = indexOf(element)

    return if (check != -1) {
      removeAt(check)
      true
    } else
      false
  }

  /**
   * Removes all elements in this collection that are also in the set of arguments passed
   * @return `true` if any of the specified items have been removed from the collection, `false` if the collection has not changed.
   */
  fun removeAll(vararg elements: T): Boolean {
    var i = 0
    var check = false

    while (i in 0 until size) {
      if (this[i] in elements) {
        remove(this[i])
        i--
        check = true
      }

      i++
    }

    return check
  }

  /**
   * Removes all of this collection's elements that are also contained in the specified collection.
   * @return `true` if any of the specified elements was removed from the collection, `false` if the collection was not modified.
   */
  override fun removeAll(elements: Collection<T>): Boolean {
    var i = 0
    var check = false

    while (i in 0 until size) {
      if (this[i] in elements) {
        remove(this[i])
        i--
        check = true
      }

      i++
    }

    return check
  }

  /** @return the element at the specified [index] in the list. */
  override operator fun get(index: Int): T {
    if (!(isIndex(index)))
      throw IndexOutOfBoundsException(index)

    val x = getNode(index)
    return x.elem!!
  }

  /** @return the first element of the list. */
  fun getFirst() = first.elem!!

  /** @return the last element of the list. */
  fun getLast() = last.elem!!

  /** Writes a new [element] to the [index] position of the list. */
  override operator fun set(index: Int, element: T): T {
    if (!(isIndex(index)))
      throw IndexOutOfBoundsException(index)

    val node = getNode(index)
    val oldElem = node.elem
    node.elem = element

    return oldElem!!
  }

  /**
   * @return the index of the first occurrence of the specified [element] in the list, or -1 if the specified
   * element is not contained in the list.
   */
  override fun indexOf(element: T): Int {
    var x = first

    for (t in 0 until size) {
      if (x.elem == element)
        return t

      x = x.next!!
    }

    return -1
  }

  /**
   * @return the index of the last occurrence of the specified [element] in the list, or -1 if the specified
   * element is not contained in the list.
   */
  override fun lastIndexOf(element: T): Int {
    var x = last

    for (i in size - 1 downTo 0) {
      if (x.elem == element)
        return i

      if (i > 0)
        x = x.prev!!
      else
        return -1
    }

    return -1
  }

  /** @return the last index of the list */
  fun lastIndex() = size - 1

  /** @return `true` if the collection is empty (contains no elements), `false` otherwise. */
  override fun isEmpty() = size == 0

  /** @return `true` - if the [element] is contained in the specified collection, `false` otherwise. */
  override fun contains(element: T): Boolean {
    var x = first

    for (node in 0 until size) {
      if (x.elem == element)
        return true

      if (node < size - 1)
        x = x.next!!
      else
        return false
    }

    return false
  }

  /** @return `true` - if the all [elements] of collection is contained in the specified collection, `false` otherwise. */
  override fun containsAll(elements: Collection<T>): Boolean {
    for (elem in elements) {
      if (!(contains(elem)))
        return false
    }

    return true
  }

  /** @return a view of the portion of this list between the specified [fromIndex] (inclusive) and [toIndex] (exclusive). */
  override fun subList(fromIndex: Int, toIndex: Int): LinkedList<T> {
    if (!(isIndex(fromIndex)) || !(isIndex(toIndex - 1)))
      throw IndexOutOfBoundsException(fromIndex)

    val result = LinkedList<T>()
    var x = getNode(fromIndex)

    for (i in fromIndex until toIndex) {
      result.add(x.elem!!)

      if (i < size - 1)
        x = x.next!!
    }

    return result
  }

  /** Removes all elements from this collection. */
  override fun clear() {
    first = Node(null, null, null)
    last = first
    size = 0
  }

  /**
   * Retains only the elements in this collection that are contained in the specified collection.
   * @return `true` if any element was removed from the collection, `false` if the collection was not modified.
   */
  override fun retainAll(elements: Collection<T>): Boolean {
    var i = 0
    var check = false

    while (i in 0 until size) {
      if (this[i] !in elements) {
        remove(this[i])
        i--
        check = true
      }

      i++
    }

    return check
  }

  /** @return a copy of list. */
  public override fun clone(): LinkedList<T> {
    val cloneList = LinkedList<T>()

    for (element in this)
      cloneList.add(element)

    return cloneList
  }

  /** @return a string representation of the object. */
  override fun toString(): String {
    var result = "["
    var x = first

    for (i in indices) {
      if (i != size - 1) {
        result += "${x.elem}, "
        x = x.next!!
      } else
        result += "${x.elem}]"
    }

    return result
  }

  /** Reverse the current list. */
  fun reverse() {
    var nodeStart = first
    var nodeEnd = last

    for (i in 0 until size / 2) {
      val temp = nodeStart.elem
      nodeStart.elem = nodeEnd.elem
      nodeEnd.elem = temp
      nodeStart = nodeStart.next!!
      nodeEnd = nodeEnd.prev!!
    }
  }

  /** @return a new list containing the current reversing list. */
  fun getReverse(): LinkedList<T> {
    val newList = clone()
    var nodeStart = newList.first
    var nodeEnd = newList.last

    for (i in 0 until size / 2) {
      val temp = nodeStart.elem
      nodeStart.elem = nodeEnd.elem
      nodeEnd.elem = temp
      nodeStart = nodeStart.next!!
      nodeEnd = nodeEnd.prev!!
    }

    return newList
  }

  /**
   * Replaces each element of this LinkedList with the result of applying the [operator] to that element.
   * @return a new LinkedList.
   */
  fun <S> replaceAll(operator: (T) -> S): LinkedList<S> {
    val result = LinkedList<S>()

    for (elem in this)
      result.add(operator(elem))

    return result
  }

  /**
   * Replaces the elements of this LinkedList from [fromIndex] to [toIndex] this LinkedList with the
   * result of applying the [operator] operator to this element.
   * @return a new LinkedList.
   */
  fun <S> replace(fromIndex: Int, toIndex: Int, operator: (T) -> S): LinkedList<S> {
    val result = LinkedList<S>()

    for (i in fromIndex until toIndex)
      result.add(operator(this[i]))

    return result
  }

  /**
   * Checks equality of two LinkedLists.
   * @return `true` if the LinkedLists are equal, `false` otherwise.
   */
  override fun equals(other: Any?): Boolean {
    if (other !is LinkedList<*>)
      return false

    for (i in indices)
      if (this[i] != other[i])
        return false

    return true
  }

  /** @return Node from position [index]. */
  private fun getNode(index: Int): Node<T> {
    var x : Node<T>

    if (index < size / 2) {
      x = first
      for (i in 0 until index)
        x = x.next!!
    } else {
      x = last
      for (i in index until size - 1)
        x = x.prev!!
    }

    return x
  }

  /** @return `true` if the argument is the index of an existing element, `false` otherwise. */
  private fun isIndex(index: Int) = index in 0 until size

  /** @return an iterator over the elements of this object. */
  override fun iterator(): MutableIterator<T> = LinkedListIterator()

  /** @return a list iterator over the elements in this list (in proper sequence). */
  override fun listIterator(): MutableListIterator<T> = LinkedListIterator()

  /** @return a list iterator over the elements in this list (in proper sequence), starting at the specified [index]. */
  override fun listIterator(index: Int): MutableListIterator<T> = LinkedListIterator()

  /** Iterator. Allows to sequentially access the elements. */
  inner class LinkedListIterator: MutableListIterator<T> {
    var i = 0
    private var current = first

    /** @return `true` if there are elements in the iteration before the current element. */
    override fun hasPrevious(): Boolean {
      if (i > 0)
        return true

      return false
    }

    /** @return the index of the element that would be returned by a subsequent call to [previous]. */
    override fun previousIndex() = i - 1

    /** @return the previous element in the iteration and moves the cursor position backwards. */
    override fun previous(): T {
      if (i != size - 1)
        current = current.prev!!

      i--
      return current.elem!!
    }

    /** @return `true` if the iteration has more elements. `false` otherwise. */
    override fun hasNext(): Boolean {
      if (i < size)
        return true

      return false
    }

    /** @return the index of the element that would be returned by a subsequent call to [next]. */
    override fun nextIndex() = i + 1

    /** @return the next element in the iteration. */
    override fun next(): T {
      if (i != 0)
        current = current.next!!

      i++
      return current.elem!!
    }

    /** Adds the specified element element into the underlying collection. */
    override fun add(element: T) = add(i, element)

    /** Removes from the underlying collection the last element returned by this iterator. */
    override fun remove() {
      removeAt(i)
    }

    /** Replaces the last element returned by [next] or [previous] with the specified element [element]. */
    override fun set(element: T) {
      set(i, element)
    }
  }
}