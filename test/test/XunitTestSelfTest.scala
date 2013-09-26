package test

import org.scalatest.matchers.ShouldMatchers
import xunit.XUnitTester
import xunit.SuiteResult
import xunit.XTest
import xunit.ConsoleReporter
import xunit.AnnotationReader

object XUnitTestSelfTestRunner extends App {
  val tester = new XUnitTester(new AnnotationReader)
  tester.test(classOf[XUnitTestSelfTest], new ConsoleReporter)
}

class XUnitTestSelfTest extends ShouldMatchers {
  val tester = new XUnitTester
  val result = new SuiteResult()
  tester.test(classOf[XUnitTesterTest], result)

  @XTest
  def passedTest {
    result.results(0) should be('passed)
  }

  @XTest
  def failedTest {
    result.results(1) should be('failed)
  }

  @XTest
  def testThrewException {
    result.results(2) should be('threwException)
  }

}