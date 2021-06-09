class LinkedList<T>(vararg args : T) : List<T> {
  override var size = 0
  private lateinit var first : Node<T>
  private lateinit var last : Node<T>

  init {
    if (args.size == 1) add(args[0])
    else addAll(*args)
  }

  class Node<T>(var prev: Node<T>?, var elem: T, var next: Node<T>?)

  override fun isEmpty() : Boolean {
    return size == 0
  }

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

  override fun containsAll(elements: Collection<T>): Boolean {
    for (elem in elements) {
      if (!(contains(elem)))
        return false
    }

    return true
  }

  override fun iterator(): Iterator<T> {
    return LinkedListIterator(this)
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

  class LinkedListIterator<T>(private val list : LinkedList<T>) : Iterator<T> {
    private var i = 0
    private var current = list.first

    override fun next() : T {
      val result = current
      i++

      if (hasNext()) {
        current = current.next!!
      }

      return result.elem
    }

    override fun hasNext() : Boolean {
      if (i <= list.size - 1)
        return true

      return false
    }
  }
}