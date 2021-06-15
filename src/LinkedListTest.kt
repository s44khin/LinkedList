import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class LinkedListTest {
  private fun randomList(min: Int) : LinkedList<Int> {
    val list = LinkedList<Int>()
    val n = (min..20).random()

    for (i in 0..n)
      list.add((0..100).random())

    return list
  }

  @Test
  fun testIndices() {
    for (i in 0..10) {
      val list1 = randomList(10)
      assertEquals(0 until list1.size, list1.indices)
    }
  }

  @Test
  fun add() {
    val list = randomList(0)
    list.add(-100)

    assertEquals(-100, list.getLast())
  }

  @Test
  fun testAdd() {
    val list = randomList(15)
    list.add(0, -100)
    list.add(1, -200)
    list.add(2, -300)
    list.add(3, -400)

    assertEquals(-100, list[0])
    assertEquals(-200, list[1])
    assertEquals(-300, list[2])
    assertEquals(-400, list[3])
  }

  @Test
  fun addAll() {
    val list = randomList(0)
    list.addAll(1, 2, 3, 4, 5, 6)

    assertEquals(6, list.getLast())
    assertEquals(5, list[list.size - 2])
    assertEquals(4, list[list.size - 3])
    assertEquals(3, list[list.size - 4])
    assertEquals(2, list[list.size - 5])
    assertEquals(1, list[list.size - 6])
  }

  @Test
  fun testAddAll() {
    val list = randomList(10)
    list.addAll(1, listOf(1, 2, 3, 4, 5))

    assertEquals(1, list[1])
    assertEquals(2, list[2])
    assertEquals(3, list[3])
    assertEquals(4, list[4])
    assertEquals(5, list[5])

    list.addAll(0, listOf(1, 2, 3, 4, 5))

    assertEquals(1, list[0])
    assertEquals(2, list[1])
    assertEquals(3, list[2])
    assertEquals(4, list[3])
    assertEquals(5, list[4])
  }

  @Test
  fun testAddAll1() {
    val list = randomList(10)
    list.addAll(listOf(1, 2, 3, 4, 5))

    assertEquals(5, list[list.size - 1])
    assertEquals(4, list[list.size - 2])
    assertEquals(3, list[list.size - 3])
    assertEquals(2, list[list.size - 4])
    assertEquals(1, list[list.size - 5])

    list.addAll(listOf(1, 2, 3, 4, 5))

    assertEquals(5, list[list.size - 1])
    assertEquals(4, list[list.size - 2])
    assertEquals(3, list[list.size - 3])
    assertEquals(2, list[list.size - 4])
    assertEquals(1, list[list.size - 5])
  }

  @Test
  fun removeAt() {
    val list1 = LinkedList(0, 1, 2, 3, 4, 5, 6, 7)
    val list2 = LinkedList(1, 2, 4, 5, 6)

    list1.removeAt(3)
    list1.removeAt(0)
    list1.removeAt(5)

    for (i in 0 until list1.size)
      assertEquals(list1[i], list2[i])
  }

  @Test
  fun remove() {
    val list1 = LinkedList(6, 2, 3, 4, 5, 6, 6, 6)
    val list2 = LinkedList(2, 3, 4, 5, 6, 6, 6)

    list1.remove(6)

    for (i in 0 until list1.size)
      assertEquals(list1[i], list2[i])

    val list3 = LinkedList(2, 3, 4, 5, 6, 6, 6, 7)
    val list4 = LinkedList(2, 3, 4, 5, 6, 6, 6)

    list3.remove(7)

    for (i in 0 until list3.size)
      assertEquals(list3[i], list4[i])

    val list5 = LinkedList(2, 3, 4, 6, 6, 6)

    list3.remove(5)

    for (i in 0 until list3.size)
      assertEquals(list3[i], list5[i])
  }

  @Test
  fun removeAll() {
    val list1 = LinkedList(0, 1, 2, 3, 4, 5, 6, 6)
    val list2 = LinkedList(0, 1, 4, 5, 6, 6)

    list1.removeAll(2, 3)

    for (i in 0 until list1.size)
      assertEquals(list1[i], list2[i])

    val list3 = LinkedList(4, 5, 6, 6)
    list1.removeAll(0, 1)

    for (i in 0 until list1.size)
      assertEquals(list1[i], list3[i])

    val list4 = LinkedList(4, 5)
    list1.removeAll(6)

    for (i in 0 until list1.size)
      assertEquals(list1[i], list4[i])
  }

  @Test
  fun testRemoveAll() {
    val list1 = LinkedList(0, 1, 2, 3, 4, 5, 6, 6)
    val list2 = LinkedList(0, 1, 4, 5, 6, 6)

    list1.removeAll(listOf(2, 3))

    for (i in 0 until list1.size)
      assertEquals(list1[i], list2[i])

    val list3 = LinkedList(4, 5, 6, 6)
    list1.removeAll(listOf(0, 1))

    for (i in 0 until list1.size)
      assertEquals(list1[i], list3[i])

    val list4 = LinkedList(4, 5)
    list1.removeAll(listOf(6))

    for (i in 0 until list1.size)
      assertEquals(list1[i], list4[i])
  }

  @Test
  fun get() {
    val list1 = LinkedList("zero", "one", "two", "three", "four", "five")

    assertEquals("zero", list1[0])
    assertEquals("one", list1[1])
    assertEquals("two", list1[2])
    assertEquals("three", list1[3])
    assertEquals("four", list1[4])
    assertEquals("five", list1[5])
  }

  @Test
  fun set() {
    val list1 = LinkedList("zero", "one", "two", "three", "four", "five")

    list1[0] = "six"
    list1[4] = "seven"

    assertEquals(list1[0], "six")
    assertEquals(list1[1], "one")
    assertEquals(list1[2], "two")
    assertEquals(list1[3], "three")
    assertEquals(list1[4], "seven")
    assertEquals(list1[5], "five")
  }

  @Test
  fun getFirst() {
    val list1 = LinkedList(0, 1, 2, 3, 4, 5, 6)

    assertEquals(0, list1.getFirst())
  }

  @Test
  fun getLast() {
    val list1 = LinkedList(0, 1, 2, 3, 4, 5, 6)

    assertEquals(6, list1.getLast())
  }

  @Test
  fun indexOf() {
    val list1 = LinkedList("zero", "four", "five", "one", "two", "zero", "one", "five", "one", "two", "three")

    assertEquals(0, list1.indexOf("zero"))
    assertEquals(3, list1.indexOf("one"))
    assertEquals(4, list1.indexOf("two"))
    assertEquals(10, list1.indexOf("three"))
    assertEquals(1, list1.indexOf("four"))
    assertEquals(2, list1.indexOf("five"))
  }

  @Test
  fun lastIndexOf() {
    val list1 = LinkedList("zero", "four", "five", "one", "two", "zero", "one", "five", "one", "two", "three")

    assertEquals(5, list1.lastIndexOf("zero"))
    assertEquals(8, list1.lastIndexOf("one"))
    assertEquals(9, list1.lastIndexOf("two"))
    assertEquals(10, list1.lastIndexOf("three"))
    assertEquals(1, list1.lastIndexOf("four"))
    assertEquals(7, list1.lastIndexOf("five"))
  }

  @Test
  fun lastIndex() {
    val list1 = LinkedList(0, 1, 2, 3, 4, 5)

    assertEquals(5, list1.lastIndex())
  }

  @Test
  fun isEmpty() {
    val list1 = LinkedList<Int>()
    val list2 = LinkedList(0, 1)

    assertEquals(true, list1.isEmpty())
    assertEquals(false, list2.isEmpty())
  }

  @Test
  fun contains() {
    val list1 = LinkedList(0, 1, 2, 3, 4)

    assertEquals(true, list1.contains(0))
    assertEquals(true, list1.contains(4))
    assertEquals(true, list1.contains(2))
    assertEquals(false, list1.contains(10))
  }

  @Test
  fun containsAll() {
    val list1 = LinkedList(0, 1, 2, 3, 4)

    assertEquals(true, list1.containsAll(listOf(0, 2, 4)))
    assertEquals(true, list1.containsAll(listOf(0, 1, 3)))
    assertEquals(true, list1.containsAll(listOf(0)))
    assertEquals(false, list1.containsAll(listOf(7, 2, 4)))
  }

  @Test
  fun subList() {
    val list1 = LinkedList(0, 1, 2, 3, 4, 5, 6, 7)
    val list2 = LinkedList(0, 1, 2, 3)
    val list3 = list1.subList(0, 4)

    for (i in 0 until list2.size)
      assertEquals(list2[i], list3[i])
  }

  @Test
  fun clear() {
    val list1 = randomList(20)
    list1.clear()

    assertEquals(0, list1.size)
  }

  @Test
  fun retainAll() {
    val list1 = LinkedList(0, 1, 2, 3, 7, 4, 5, 6, 7, 8, 3, 9, 7, 5, 0)
    val list2 = LinkedList(0, 3, 7, 5, 7, 3, 7, 5, 0)
    val list3 = listOf(0, 3, 5, 7)

    list1.retainAll(list3)

    for (i in 0 until list1.size)
      assertEquals(list2[i], list1[i])
  }

  @Test
  fun testClone() {
    val list1 = randomList(20)
    val list2 = list1.clone()

    for (i in 0 until list1.size)
      assertEquals(list1[i], list2[i])
  }

  @Test
  fun testToString() {
    val list1 = LinkedList(1, 2, 3, 4, 5, 6)
    assertEquals("[1, 2, 3, 4, 5, 6]", list1.toString())

    val list2 = LinkedList("one", "two", "three", "four", "five", "six")
    assertEquals("[one, two, three, four, five, six]", list2.toString())

    val list3 = LinkedList(1, 2, "three", "four", "five", "six")
    assertEquals("[1, 2, three, four, five, six]", list3.toString())
  }

  @Test
  fun reverse() {
    val list1 = LinkedList(0, 1, 2, 3, 4, 5, 6)
    list1.reverse()

    assertEquals(6, list1[0])
    assertEquals(5, list1[1])
    assertEquals(4, list1[2])
    assertEquals(3, list1[3])
    assertEquals(2, list1[4])
    assertEquals(1, list1[5])
    assertEquals(0, list1[6])
  }

  @Test
  fun getReverse() {
    val list1 = randomList(5)
    val list2 = list1.getReverse()

    for (i in 0 until list1.size)
      assertEquals(list1[i], list2[list2.size - 1 - i])
  }
}