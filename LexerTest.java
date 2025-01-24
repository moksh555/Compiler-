import java.io.*;
import java_cup.runtime.*;

public class LexerTest{
    public static void main(String[] args) {
        Symbol sym;
        try {
        lexer lexer1 = new lexer(new FileReader(args[0]));
            for (sym = lexer1.next_token(); sym.sym != 0;
                    sym = lexer1.next_token()) {

                System.out.println("Token " + sym +
                    ", with value = " + sym.value + 
                    "; at line " + sym.left + ", column " + sym.right);

            }
        }
        catch (Exception e) {
        }
    }
}
