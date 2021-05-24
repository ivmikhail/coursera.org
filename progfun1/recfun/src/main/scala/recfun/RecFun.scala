package recfun

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }
  }

  /*
     1
   1 1
  1 2 1
 1 3 3 1
1 4 6 4 1
   ...

pascal(0,2)=1,pascal(1,2)=2 and pascal(1,3)=3.
pascal(c=1,r=2) 2


   */
  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) {
      1
    } else {
      val aboveRow = r - 1
      pascal(c - 1, aboveRow) + pascal(c, aboveRow)
    }
  }

  //(if (zero? x) max (/ 1 x))
  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    def balanced(chars: List[Char], open: Int): Boolean = {
      if (chars.isEmpty) {
        open == 0
      } else if (chars.head == '(') {
        balanced(chars.tail, open + 1)
      } else if (chars.head == ')') {
        open > 0 && balanced(chars.tail, open - 1)
      } else {
        balanced(chars.tail, open)
      }
    }

    balanced(chars, 0)
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    def count(moneyamount: Int, coinsorted: List[Int]): Int = {
      if (coinsorted.isEmpty) {
        0
      } else if (moneyamount - coinsorted.head == 0) {
        1
      } else if (moneyamount - coinsorted.head < 0) {
        0
      } else {
        countChange(moneyamount - coinsorted.head, coinsorted) +
          countChange(moneyamount, coinsorted.tail)
      }
    }
    count(money, coins.sorted)
  }
}
