JAVA=java
JAVAC=javac
JFLEX=$(JAVA) -jar jflex-full-1.8.2.jar
CUPJAR=./java-cup-11b.jar
CUP=$(JAVA) -jar $(CUPJAR)
CP=.:$(CUPJAR)

default: run

.SUFFIXES: $(SUFFIXES) .class .java

.java.class:
		$(JAVAC) -cp $(CP) $*.java

FILE=   Lexer.java parser.java sym.java \
        ScannerTest.java TypeCheckingTest.java \
		SuperToken.java SymbolTable.java VarType.java \
        Program.java Memberdecls.java Fielddecls.java \
        Methoddecls.java Fielddecl.java Methoddecl.java \
        Argdecls.java ArgdeclList.java Argdecl.java \
        Stmts.java Stmt.java Name.java \
        Args.java Readlist.java Printlist.java \
        Printlinelist.java Expr.java BinaryOp.java Token.java\

all: Lexer.java parser.java $(FILE:java=class)

dump: parserD.java $(FILE:java=class)

run: badDec.as badInc.as badNegation.as badString.as badTernaryCond.as badTernaryTypes.as boolToFloat.as boolToInt.as callNonExistFunc.as charToFloat.as charToInt.as floatToInt.as fullValidProgram.as incompatBinary.as intArrayToBoolArray.as noReturn.as reassignFinal.as redefMethod.as redefVar.as redefVarAsMethod.as returnTypeBad.as

badDec.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest badDec.as > badDec-output.as

badInc.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest badInc.as > badInc-output.as

badNegation.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest badNegation.as > badNegation-output.as

badString.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest badString.as > badString-output.as

badTernaryCond.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest badTernaryCond.as > badTernaryCond-output.as

badTernaryTypes.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest badTernaryTypes.as > badTernaryTypes-output.as

boolToFloat.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest boolToFloat.as > boolToFloat-output.as

boolToInt.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest boolToInt.as > boolToInt-output.as

callNonExistFunc.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest callNonExistFunc.as > callNonExistFunc-output.as

charToFloat.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest charToFloat.as > charToFloat-output.as

charToInt.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest charToInt.as > charToInt-output.as

floatToInt.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest floatToInt.as > floatToInt-output.as

fullValidProgram.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest fullValidProgram.as > fullValidProgram-output.as

incompatBinary.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest incompatBinary.as > incompatBinary-output.as

intArrayToBoolArray.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest intArrayToBoolArray.as > intArrayToBoolArray-output.as

noReturn.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest noReturn.as > noReturn-output.as

reassignFinal.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest reassignFinal.as > reassignFinal-output.as

redefMethod.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest redefMethod.as > redefMethod-output.as

redefVar.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest redefVar.as > redefVar-output.as

redefVarAsMethod.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest redefVarAsMethod.as > redefVarAsMethod-output.as

returnTypeBad.as: all
	$(JAVA) -cp $(CP) TypeCheckingTest returnTypeBad.as > returnTypeBad-output.as

clean:
		rm -f *.class *~ *.bak Lexer.java parser.java sym.java

Lexer.java: tokens.jflex
		$(JFLEX) tokens.jflex

parser.java: grammar.cup
		$(CUP) -interface < grammar.cup

parserD.java: grammar.cup
		$(CUP) -interface -dump < grammar.cup
