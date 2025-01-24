import java.util.*;
public class Methoddecl extends SuperToken implements Token {
    Argdecls argdecls;
    Fielddecls fielddecls;
    Stmts sts;
    String type;
    String id;
    boolean isSemi;
 
    public Methoddecl(String var1, String var2, Argdecls var3, Fielddecls var4, Stmts var5, boolean var6) {
       this.type = var1;
       this.id = var2;
       this.argdecls = var3;
       this.fielddecls = var4;
       this.sts = var5;
       this.isSemi = var6;
    }
 
    public String toString(int t)
    {
        String s = "";
        return s  + type + " " + id + "(" + argdecls.toString(t) +")\n"+ s + "{\n" + fielddecls.toString(t+1) + sts.toString(t+1) + s + "}" + ( isSemi ? ";" : "" ) + "\n\n";
    }

    public void typeCheck() throws Exception {
      VarType methodType = getTypeFromString(type);
      setExpectedReturnType(methodType);

      if (!symbolTable.addVar(id, methodType, true, null)){
          System.out.println(" Error: Function " + id + " is already defined in this scope!");
          System.exit(0);
      }

      typeCheckMethod(methodType);

    }
    private void typeCheckMethod(VarType methodType)throws Exception {
      symbolTable.prependScope();
      System.out.print(type + " " + id + ":");
      symbolTable.findVar(id).methodArgs = new ArrayList<>();
      argdecls.typeCheck(id);
      fielddecls.typeCheck();
      sts.typeCheck();
      // VarType expectedReturnType = getExpectedReturnType();

      // check for return
      if (!methodType.equals(VarType.Void) && !CurrentFunction.containsRet){
          System.out.print(" Error: Missing return statement from function " + id +".\nOn Line: \n"+ toString(0));
          System.exit(0);
      }

      if (!isCoercible(methodType, getReturnType())){
          System.out.print(" Error: Incompatible types: " + getReturnType() + " doesn't match expected return type " + methodType +".\nOn Line: \n"+ toString(0));
          System.exit(0);
      }
      symbolTable.removeScope();
      setReturnType(null);
      setContainsRet(false);
  }
 }