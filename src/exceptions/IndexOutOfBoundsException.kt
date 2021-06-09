package exceptions

class IndexOutOfBoundsException(s : String) : Exception(s) {
  constructor(i : Int) : this("Index out of range: $i")
  constructor() : this("")
}