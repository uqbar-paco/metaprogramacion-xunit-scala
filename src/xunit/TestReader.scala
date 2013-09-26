package xunit

import java.lang.reflect.Method

trait TestReader {
  def isTestMethod(method: Method): Boolean
}

class ConventionTestReader extends TestReader {
  def isTestMethod(method: Method) =  method.getName startsWith "test"
}

class AnnotationReader extends TestReader {
  def isTestMethod(method: Method) = method isAnnotationPresent classOf[XTest]
}