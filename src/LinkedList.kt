import exceptions.*

class LinkedList<T> {
  private var size = 0
  private lateinit var first : Node<T>
  private lateinit var last : Node<T>

  class Node<T>(var prev: Node<T>?, var elem: T, var next: Node<T>?)

  /** Добавляет элемент типа T в конец списка */
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

//  fun add(i : Int, elem : T) {
//    var newNode =
//  }

  /** Добавляет несколько элеменов в конец списка в том порядке, в каком они поступили */
  fun addAll(vararg elems : T) {
    for (elem in elems)
      add(elem)
  }

  /** @return элемент списка типа T на позиции i */
  operator fun get(i : Int) : T {
    if (i >= size)
      throw IndexOutOfBoundsException(i)

    var x = first

    for (t in 0 until i)
      x = x.next!!

    return x.elem
  }

  /** Устанавливает элемент типа T в список на позицию i */
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

  fun size() : Int {
    return size
  }
}