package test;

import collection.mutable.Stack
import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import xunit.XUnitTester
import xunit.SuiteResult

class XunitTestAsTester extends FlatSpec with ShouldMatchers {
  val tester = new XUnitTester()
  val result = new SuiteResult()
  tester.test(classOf[XUnitTesterTest], result)

  "test" should "pass" in {
	  result.results(0) should be('passed)
  }

  "test" should "fail" in {
	  result.results(1) should be('failed)
  }
  
  "test" should "throw an exception" in {
	  result.results(2) should be('threwException)
  }
  
}