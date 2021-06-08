fun main() {
  val list = LinkedList<String>()

  list.addAll("ноль", "один", "два", "три")
  list.add("четыре")
  println(list)

  list.remove(2)
  println(list)
}