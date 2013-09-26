package xunit.example

import xunit.XUnitTester
import xunit.ConsoleReporter
import test.XUnitTesterTest


object MainTester {
	def main(args: Array[String]) {
		val tester = new XUnitTester()
		tester.test(classOf[XUnitTesterTest], new ConsoleReporter)
	}
}