PROGDIR = src/cpsc2150/banking
TESTDIR = test/cpsc2150/banking
JC = javac
JVM = java
NAMES = *.java
CLASSES = $(NAMES):=.class
make: $(addprefix ${PROGDIR}, ${NAMES})
	$(JC) $(addprefix ${PROGDIR}, ${NAMES})

make run: $(addprefix ${PROGDIR}, ${CLASSES})
	$(JVM) src/cpsc2150.banking.MortgageApp


make test: $(addprefix ${TESTDIR}, ${NAMES})
    $(JC) $(addprefix ${TESTDIR}, ${NAMES})


run test: $(addprefix ${TESTDIR}, ${CLASSES})
    $(JVM) test/cpsc2150.banking.TestMortgage


clean:
	$(RM) $(addprefix ${PROGDIR}, ${CLASSES})
	$(RM) $(addprefix ${TESTDIR},${CLASSES})
