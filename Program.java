public class Program extends SuperToken implements Token
{
    String className;
    Memberdecls memberdecls;
    public Program(String id, Memberdecls m) {
      className = id;
      memberdecls = m;
      symbolTable = new SymbolTable();

    }
    public String toString(int t) 
    {
      return "class " + className + " {\n" + memberdecls.toString(t+1) + "}\n";
      // return ("Program:\n"+"class " + className + " {\n" + memberdecls.toString(t+1) + "}");
    }
    public void typeCheck() throws Exception {
      symbolTable.prependScope();
      System.out.print("class:<"+ className + ">: ");
      memberdecls.typeCheck();
      symbolTable.removeScope();
  }
      
}
