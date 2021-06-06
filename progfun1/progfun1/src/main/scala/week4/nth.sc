import week4._

object nth {
  def nth[T](n: Int, list: List[T]): T = {
    if (list.isEmpty) throw new IndexOutOfBoundsException("")
    else if (n == 0) list.head
    else nth(n - 1, list.tail)
  }

  val list = new Cons(0, new Nil)
  nth(0, list)
  nth(-1, list)
}
