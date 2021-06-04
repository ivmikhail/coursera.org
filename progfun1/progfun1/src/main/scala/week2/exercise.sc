import math.abs

object exercise {
  def factorial(n: Int): Int = {
    def loop(acc: Int, n: Int): Int =
      if (n == 0) acc
      else loop(acc * n, n - 1)

    loop(1, n)
  }

  factorial(4)

  // a min b max
  def sum(f: Int => Int, a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, acc + f(a))
    }
    loop(a, 0)
  }

  sum(x => x * x, 3, 5)

  def mapReduce(mapF: Int => Int,
                reduceF: (Int, Int) => Int,
                zero: Int)(a: Int, b: Int): Int = {
    if (a > b) {
      zero
    } else {
      reduceF(mapF(a), mapReduce(mapF, reduceF, zero)(a + 1, b))
    }
  }

  def product(f: Int => Int)(a: Int, b: Int): Int = {
    mapReduce(f, (x, y) => x * y, 1)(a, b)
  }

  product(x => x * x)(3, 4)

  //3 * product(4, 4)
  // 3*3 * 4*4 * 1

  def fact(n: Int): Int = {
    product(x => x)(1, n)
  }

  fact(5)
  fact(0)
  // 1*2*3*4*5 = 120

  val tolerance = 0.001

  def isCloseEnough(x: Double, y: Double) = {
    abs((x - y) / x) / x < tolerance
  }

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }

  fixedPoint(x => 1 + x / 2)(1)

  def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2

  def sqrt(x: Double) = {
    fixedPoint(averageDamp(y => x / y))(1)
  }

  sqrt(4)
}
