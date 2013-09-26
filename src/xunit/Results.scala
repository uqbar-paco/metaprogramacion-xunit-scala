package xunit

import scala.collection.mutable.ArrayBuffer
import java.lang.reflect.Method

class SuiteResult extends Reporter {
  
  val results = new ArrayBuffer[TestResult] 
  
  def testPassed(testClass: Class[_], testMethod: Method) = 
  	results += new PassedResult()

  def testFailed(testClass: Class[_], testMethod: Method, errorMessage: String) =
  	results += new FailedResult()
    
  def testThrewException(testClass: Class[_], testMethod: Method, exception: Throwable) =
    results += new ExceptionResult(exception)
    
}

class ConsoleReporter extends Reporter {
	def testPassed(testClass: Class[_], testMethod: Method) = println("PASO: " + testClass.getSimpleName() + "." + testMethod.getName())
	def testFailed(testClass: Class[_], testMethod: Method, errorMessage: String) = println("FALLO: " + testClass.getSimpleName() + "." + testMethod.getName())
	def testThrewException(testClass: Class[_], testMethod: Method, exception: Throwable) = {
		println(s"ERROR: ${testClass.getSimpleName}.${testMethod.getName}")
		println("Excepcion " + exception.getClass().getName() + " Mensaje: "+ exception.getMessage())
	}
}

trait TestResult {
 	def passed(): Boolean = false 
	def failed(): Boolean = false
	def threwException(): Boolean = false
}

class PassedResult extends TestResult {
	override def  passed(): Boolean = true
}

class FailedResult extends TestResult {
	override def failed(): Boolean = true
}

class ExceptionResult(exception: Throwable = new Throwable) extends TestResult {
	override def threwException(): Boolean = true
}
  
