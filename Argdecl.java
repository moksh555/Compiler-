import java.util.*;
public class Argdecl extends SuperToken implements Token{

  boolean isArray;
  String type;
  String ID;

  public Argdecl(String type, String id, boolean isArray){
      this.type = type;
      this.ID = id;
      this.isArray = isArray;
  }


  public String toString(int t){
      return type + " " + ID + (isArray ? "[]" : "");
  }
  public VarType typeCheck() throws Exception{

    VarType officialType = getTypeFromString(type);

    Boolean exists = !symbolTable.addVar(ID, officialType, isArray);
    if (exists) {
        System.out.print("Error: Variable name" + ID + " is already defined in this scope." +"\nOn Line: \n"+ toString(0));
        System.exit(0);
    }
    return officialType;
}
}

