package xunit

import java.lang.reflect.Method
import java.lang.reflect.InvocationTargetException
import scala.collection.mutable.ArrayBuffer
import org.scalatest.exceptions.TestFailedException

class XUnitTester(reader: TestReader = new ConventionTestReader) {
  
  
  def test(testClass: Class[_], reporter: Reporter) = 
		testMethods(testClass) foreach evaluarTest(testClass, reporter)	
			
  def testMethods(testClass: Class[_]) = testClass.getMethods filter isTestMethod
 
  def isTestMethod(method: Method) = hasNoParameters(method) && reader.isTestMethod(method)
  
  def hasNoParameters(method: Method) = method.getParameterTypes isEmpty
  
  def evaluarTest(testClass: Class[_], reporter: Reporter) (testMethod: Method) = {
	  try {
	    val test = testClass.newInstance
	    testMethod.invoke(test)
	    reporter.testPassed(testClass, testMethod)
	  } 
	  catch {
	    case e: InvocationTargetException => e.getCause match {
	      case e: TestFailedException => reporter.testFailed(testClass, testMethod, e.getMessage)
	      case e => reporter.testThrewException(testClass, testMethod, e)
	    }
	  }
  }
  
  
}

trait Reporter {
  def testPassed(testClass: Class[_], testMethod: Method)
  def testFailed(testClass: Class[_], testMethod: Method, errorMessage: String)
  def testThrewException(testClass: Class[_], testMethod: Method, exception: Throwable)
}

