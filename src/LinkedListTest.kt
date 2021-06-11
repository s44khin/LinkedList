import org.junit.jupiter.api.Assertions

internal class LinkedListTest {

  @org.junit.jupiter.api.Test
  fun isEmpty() {
    val list1 = LinkedList<Int>()
    Assertions.assertEquals(true, list1.isEmpty())

    val list2 = LinkedList(1)
    Assertions.assertEquals(false, list2.isEmpty())
  }

  @org.junit.jupiter.api.Test
  fun contains() {
    val list1 = LinkedList("Раз", "Два", "Три", "Четрые", "Пять")

    Assertions.assertEquals(true, list1.contains("Раз"))
    Assertions.assertEquals(true, list1.contains("Три"))
    Assertions.assertEquals(true, list1.contains("Пять"))
    Assertions.assertEquals(false, list1.contains("Шесть"))
  }

  @org.junit.jupiter.api.Test
  fun containsAll() {
    val list1 = LinkedList('1','2','3','4','5','f', 'a', 'b', 'c', 'd')

    Assertions.assertEquals(true, list1.containsAll(listOf('1', 'f', 'd')))
    Assertions.assertEquals(true, list1.containsAll(listOf('1')))
    Assertions.assertEquals(true, list1.containsAll(listOf('d')))
    Assertions.assertEquals(false, list1.containsAll(listOf('0')))
  }

  @org.junit.jupiter.api.Test
  fun get() {
    val list1 = LinkedList(1, 2, 3, 4, 5, 6, 7, 8, 9)

    Assertions.assertEquals(1, list1[0])
    Assertions.assertEquals(9, list1[8])
    Assertions.assertEquals(5, list1[4])
  }

  @org.junit.jupiter.api.Test
  fun indexOf() {
    val list1 = LinkedList("Раз", "Два", "Три", "Четрые", "Пять", "Два", "Шесть")

    Assertions.assertEquals(1, list1.indexOf("Два"))
    Assertions.assertEquals(0, list1.indexOf("Раз"))
    Assertions.assertEquals(6, list1.indexOf("Шесть"))
  }

  @org.junit.jupiter.api.Test
  fun lastIndexOf() {
    val list1 = LinkedList("Раз", "Два", "Три", "Четрые", "Пять", "Два", "Шесть", "Шесть", "Шесть", "Шесть")

    Assertions.assertEquals(5, list1.lastIndexOf("Два"))
    Assertions.assertEquals(0, list1.lastIndexOf("Раз"))
    Assertions.assertEquals(9, list1.lastIndexOf("Шесть"))
  }

  @org.junit.jupiter.api.Test
  fun subList() {
    val list1 = LinkedList(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val list2 = list1.subList(2, 6)
    val list3 = list1.subList(0, 9)

    Assertions.assertEquals( list1[2], list2[0])
    Assertions.assertEquals( list1[3], list2[1])
    Assertions.assertEquals( list1[4], list2[2])
    Assertions.assertEquals( list1[5], list2[3])

    Assertions.assertEquals(list1[0], list3[0])
    Assertions.assertEquals(list1[1], list3[1])
    Assertions.assertEquals(list1[2], list3[2])
    Assertions.assertEquals(list1[3], list3[3])
    Assertions.assertEquals(list1[4], list3[4])
    Assertions.assertEquals(list1[5], list3[5])
    Assertions.assertEquals(list1[6], list3[6])
    Assertions.assertEquals(list1[7], list3[7])
    Assertions.assertEquals(list1[8], list3[8])
  }

  @org.junit.jupiter.api.Test
  fun add() {
    val list1 = LinkedList(0, 1, 2)
    list1.add(3)

    Assertions.assertEquals(0, list1[0])
    Assertions.assertEquals(1, list1[1])
    Assertions.assertEquals(2, list1[2])
    Assertions.assertEquals(3, list1[3])

    list1.add(0, -10)

    Assertions.assertEquals(-10, list1[0])
    Assertions.assertEquals(0, list1[1])
    Assertions.assertEquals(1, list1[2])
    Assertions.assertEquals(2, list1[3])
    Assertions.assertEquals(3, list1[4])
  }

  @org.junit.jupiter.api.Test
  fun addAll() {
    val list1 = LinkedList(0, 1)
    list1.addAll(2, 3, 4, 5)

    Assertions.assertEquals(0, list1[0])
    Assertions.assertEquals(1, list1[1])
    Assertions.assertEquals(2, list1[2])
    Assertions.assertEquals(3, list1[3])
    Assertions.assertEquals(4, list1[4])
    Assertions.assertEquals(5, list1[5])
  }

  @org.junit.jupiter.api.Test
  fun set() {
    val list1 = LinkedList(0, 1, 2, 3, 4, 5)
    list1[0] = 10

    Assertions.assertEquals(10, list1[0])
    Assertions.assertEquals(1, list1[1])
    Assertions.assertEquals(2, list1[2])
    Assertions.assertEquals(3, list1[3])
    Assertions.assertEquals(4, list1[4])
    Assertions.assertEquals(5, list1[5])

    list1[5] = 20

    Assertions.assertEquals(10, list1[0])
    Assertions.assertEquals(1, list1[1])
    Assertions.assertEquals(2, list1[2])
    Assertions.assertEquals(3, list1[3])
    Assertions.assertEquals(4, list1[4])
    Assertions.assertEquals(20, list1[5])

    list1[3] = 30

    Assertions.assertEquals(10, list1[0])
    Assertions.assertEquals(1, list1[1])
    Assertions.assertEquals(2, list1[2])
    Assertions.assertEquals(30, list1[3])
    Assertions.assertEquals(4, list1[4])
    Assertions.assertEquals(20, list1[5])
  }

  @org.junit.jupiter.api.Test
  fun remove() {
    val list1 = LinkedList(0, 1, 2, 3, 4, 5, 6)
    list1.remove(0)

    Assertions.assertEquals(1, list1[0])
    Assertions.assertEquals(2, list1[1])
    Assertions.assertEquals(3, list1[2])
    Assertions.assertEquals(4, list1[3])
    Assertions.assertEquals(5, list1[4])
    Assertions.assertEquals(6, list1[5])

    list1.remove(5)

    Assertions.assertEquals(1, list1[0])
    Assertions.assertEquals(2, list1[1])
    Assertions.assertEquals(3, list1[2])
    Assertions.assertEquals(4, list1[3])
    Assertions.assertEquals(5, list1[4])

    list1.remove(2)

    Assertions.assertEquals(1, list1[0])
    Assertions.assertEquals(2, list1[1])
    Assertions.assertEquals(4, list1[2])
    Assertions.assertEquals(5, list1[3])
  }

  @org.junit.jupiter.api.Test
  fun clone() {
    val list1 = LinkedList(0, 1, 2, 3, 4, 5, 6)
    val list2 = list1.clone()

    Assertions.assertEquals(list1[0], list2[0])
    Assertions.assertEquals(list1[1], list2[1])
    Assertions.assertEquals(list1[2], list2[2])
    Assertions.assertEquals(list1[3], list2[3])
    Assertions.assertEquals(list1[4], list2[4])
    Assertions.assertEquals(list1[5], list2[5])
    Assertions.assertEquals(list1[6], list2[6])
  }
}