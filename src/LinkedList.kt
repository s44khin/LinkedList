class LinkedList<T> : List<T> {
  override var size = 0
  private lateinit var first : Node<T>
  private lateinit var last : Node<T>

  class Node<T>(var prev: Node<T>?, var elem: T, var next: Node<T>?)

  override fun isEmpty() : Boolean {
    return size == 0
  }

  override fun contains(element : T) : Boolean {
    var x = first

    for (node in 0 until size) {
      if (x.elem == element)
        return true

      x = x.next!!
    }

    return false
  }

  override fun containsAll(elements: Collection<T>): Boolean {
    TODO("Not yet implemented")
  }

  override fun iterator(): Iterator<T> {
    TODO("Not yet implemented")
  }

  override operator fun get(index : Int) : T {
    if (index >= size)
      throw IndexOutOfBoundsException(index)

    var x = first

    for (t in 0 until index)
      x = x.next!!

    return x.elem
  }

  override fun indexOf(element: T): Int {
    TODO("Not yet implemented")
  }

  override fun lastIndexOf(element: T): Int {
    TODO("Not yet implemented")
  }

  override fun listIterator(): ListIterator<T> {
    TODO("Not yet implemented")
  }

  override fun listIterator(index: Int): ListIterator<T> {
    TODO("Not yet implemented")
  }

  override fun subList(fromIndex: Int, toIndex: Int): List<T> {
    TODO("Not yet implemented")
  }

  fun add(elem : T) {
    if (size == 0) {
      last = Node(null, elem, null)
      first = last
      first.next = last
    } else {
      val one = last
      val newElem = Node(one, elem, null)
      one.next = newElem
      last = newElem
    }

    size++
  }

  fun add(i : Int, elem : T) {
    TODO("Not yet implemented")
  }

  fun addAll(vararg elems : T) {
    for (elem in elems)
      add(elem)
  }

  operator fun set(i: Int, elem: T) {
    if (i >= size)
      throw IndexOutOfBoundsException(i)

    var x = first

    for (t in 0 until i)
      x = x.next!!

    x.elem = elem
  }

  fun remove(i : Int) {
    if (i >= size)
      throw IndexOutOfBoundsException(i)

    if (size == 1)
      first.elem = null!!

    var x = first

    for (t in 0 until i)
      x = x.next!!

    x.prev!!.next = x.next!!

    size--
  }
}