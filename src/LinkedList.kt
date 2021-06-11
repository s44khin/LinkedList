/**
 * Kotlin implementation of LinkedList (Java)
 * @author s44khin
 */
class LinkedList<T>(vararg args : T) : List<T>, Cloneable {
  /** List size */
  override var size = 0
  /** First element */
  private lateinit var first : Node<T>
  /** Last element */
  private lateinit var last : Node<T>

  init {
    addAll(*args)
  }

  /**
   * List entry
   * @param prev previous element (null - if element is first)
   * @param next next element (null - if element is last)
   */
  class Node<T>(var prev: Node<T>?, var elem: T, var next: Node<T>?)

  /** Iterator. Allows to sequentially access the elements */
  inner class LinkedListIterator : Iterator<T> {
    /** The current index of the list */
    private var i = 0
    /** Current list element */
    private var current = first

    /** @return the next element in the iteration */
    override fun next() : T {
      val result = current
      i++

      if (hasNext()) {
        current = current.next!!
      }

      return result.elem
    }

    /** @return `true` if the iteration has more elements `false` otherwise */
    override fun hasNext() : Boolean {
      if (i < size)
        return true

      return false
    }
  }

  /** @return `true` if the collection is empty (contains no elements), `false` otherwise */
  override fun isEmpty() : Boolean {
    return size == 0
  }

  /** @return `true` - if the [element] is contained in the specified collection, `false` otherwise */
  override fun contains(element : T) : Boolean {
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

  /** @return `true` - if the all [elements] of collection is contained in the specified collection, `false` otherwise */
  override fun containsAll(elements: Collection<T>): Boolean {
    for (elem in elements) {
      if (!(contains(elem)))
        return false
    }

    return true
  }

  /** @return an iterator over the elements of this object */
  override fun iterator(): Iterator<T> {
    return LinkedListIterator()
  }

  /** @return the element at the specified [index] in the list */
  override operator fun get(index : Int) : T {
    if (!(isIndex(index)))
      throw IndexOutOfBoundsException(index)

    val x = getNode(index)
    return x.elem
  }

  /** @return the index of the first occurrence of the specified [element] in the list, or -1 if the specified
   * element is not contained in the list */
  override fun indexOf(element: T): Int {
    var x = first

    for (t in 0 until size) {
      if (x.elem == element)
        return t

      x = x.next!!
    }

    return -1
  }

  /** @return the index of the last occurrence of the specified [element] in the list, or -1 if the specified
   * element is not contained in the list */
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

  /** @return a list iterator over the elements in this list (in proper sequence) */
  override fun listIterator(): ListIterator<T> {
    TODO("Not yet implemented")
  }

  /** @return a list iterator over the elements in this list (in proper sequence), starting at the specified [index] */
  override fun listIterator(index: Int): ListIterator<T> {
    TODO("Not yet implemented")
  }

  /** @return a view of the portion of this list between the specified [fromIndex] (inclusive) and [toIndex] (exclusive) */
  override fun subList(fromIndex: Int, toIndex: Int) : LinkedList<T> {
    if (!(isIndex(fromIndex)) || !(isIndex(toIndex - 1)))
      throw IndexOutOfBoundsException(fromIndex)

    val result = LinkedList<T>()
    var x = getNode(fromIndex)

    for (i in fromIndex until toIndex) {
      result.add(x.elem)

      if (i < size - 1)
        x = x.next!!
    }

    return result
  }

  /** Adds [element] to the end of the list */
  fun add(element : T) {
    if (size == 0) {
      first = Node(null, element, null)
      last = first
    } else {
      last.next = Node(last, element, null)
      last = last.next!!
    }

    size++
  }

  /** Adds an [element] to the list at the [index] position with an offset to the end of the list */
  fun add(index : Int, element : T) {
    if (!(isIndex(index)))
      throw IndexOutOfBoundsException(index)

    val x = getNode(index)
    val newNode : Node<T>

    when (index) {
      0 -> {
        newNode = Node(null, element, x)
        x.prev = newNode
        first = newNode
      }
      size - 1 -> {
        newNode = Node(x.prev, element, x)
        x.prev!!.next = newNode
      }
      else -> {
        newNode = Node(x.prev, element, x)
        x.prev!!.next = newNode
        x.next!!.prev = newNode
      }
    }

    size++
  }

  /** Adds many [elements] to the end of the list */
  fun addAll(vararg elements : T) {
    for (elem in elements)
      add(elem)
  }

  /** Writes a new [element] to the [index] position of the list */
  operator fun set(index: Int, element: T) {
    if (!(isIndex(index)))
      throw IndexOutOfBoundsException(index)

    getNode(index).elem = element
  }

  /** Removes the list item from position [index] */
  fun remove(index : Int) {
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
      else -> x.prev!!.next = x.next!!
    }

    size--
  }

  /** @return a copy of list */
  public override fun clone() : LinkedList<T> {
    val cloneList =  LinkedList<T>()

    for (element in this)
      cloneList.add(element)

    return cloneList
  }

  /** @return Node from position [index] */
  private fun getNode(index: Int) : Node<T> {
    var x : Node<T>

    if (index < size / 2) {
      x = first

      for (t in 0 until index)
        x = x.next!!
    } else {
      x = last

      for(i in size - 1 downTo index + 1)
        x = x.prev!!
    }

    return x
  }

  /** @return `true` if the argument is the index of an existing element, `false` otherwise */
  private fun isIndex(index: Int) : Boolean {
    return index in 0 until size
  }
}