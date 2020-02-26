PROGDIR = cpsc2150/banking/
JC = javac
JCP = javac -cp
JVM = java
PROGNAMES = AbsMortgage.java Mortgage.java IMortgage.java AbsCustomer.java ICustomer.java Customer.java MortgageApp.java
PROGCLASSES = AbsMortgage.class Mortgage.class IMortgage.class AbsCustomer.class ICustomer.class Customer.class MortgageApp.class 

TESTNAMES = TestMortgage.java
TESTCLASSES = TestMortgage.class


default: $(addprefix ${PROGDIR}, ${PROGNAMES})
	$(JC) $(addprefix ${PROGDIR}, ${PROGNAMES})

run: $(addprefix ${PROGDIR}, ${PROGCLASSES})
	 $(JVM) cpsc2150.banking.MortgageApp

test: $(addprefix ${PROGDIR}, ${TESTNAMES})
	$(JC) $(addprefix ${PROGDIR}, ${PROGNAMES})
	$(JCP) junit-4.13.jar:hamcrest-core-1.3.jar:${PROGDIR}: ${PROGDIR}${TESTNAMES} 

runtest: $(addprefix ${PROGDIR},  ${TESTCLASSES})
	$(JVM) -cp junit-4.13.jar:hamcrest-core-1.3.jar:${PROGDIR}: org.junit.runner.JUnitCore cpsc2150.banking.TestMortgage

clean:
	$(RM) ${PROGDIR}*.class
	
