package test

import collection.mutable.Stack
import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class XUnitTesterTest extends ShouldMatchers {

  def testPass = 1 should equal (1)
  def testFail = 1 should equal (2)
  def testThrowException = throw new NullPointerException()
  
}