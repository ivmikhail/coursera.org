package recfun

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()

  /*

    1
   1 1
  1 2 1
 1 3 3 1
1 4 6 4 1
   ...
   */
  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if(r == c || c == 0) {
      1
    } else {
      val rAbove = r - 1
      pascal(c - 1, rAbove) + pascal(c, rAbove)
    }
  }

  /*
  should return true for the following strings:

(if (zero? x) max (/ 1 x))
I told him (that it's not (yet) done). (But he wasn't listening)
The function should return false for the following strings:

:-)
())(

   */
  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    balance(chars, 0)
  }

  private def balance(chars: List[Char], open: Int): Boolean = {
    if(open < 0 ){
      false
    } else if(chars.isEmpty){
      open == 0
    } else if(chars.head == '(') {
      balance(chars.tail,open + 1)
    } else if(chars.head == ')') {
      balance(chars.tail, open - 1)
    } else {
      balance(chars.tail, open)
    }
  }

  /*
  For example, there are 3 ways to give change for 4 if you have coins with denomiation 1 and 2: 1+1+1+1, 1+1+2, 2+2.

   */
  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    countChangeSorted(money, coins.sorted)
  }

  private def countChangeSorted(money: Int, coins: List[Int]) : Int = {
    if(coins.isEmpty) {
      0
    } else if(money - coins.head == 0) {
      1
    } else if(money - coins.head < 0) {
      0
    } else {
      countChangeSorted(money, coins.tail) + countChangeSorted(money - coins.head, coins)
    }
  }
