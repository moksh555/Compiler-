public class Readlist extends SuperToken implements Token{
    Name name;
    Readlist readlist;

    public Readlist(Name name, Readlist r) {
        this.name = name;
        this.readlist = r;
    }

    public Readlist(Name name) {
        this.name = name;
        this.readlist = null;
    }

    public String toString(int t) {
        if (readlist == null)
            return name.toString(t);
        return name.toString(t) + ", " + readlist.toString(t);
        // if(readlist != null)
        // {
        // return (name.toString()+ " , " + readlist.toString());
        // }
        // else
        // {
        // return name.toString();
        // }
    }

    public VarType typeCheck() throws Exception {
        SymbolTable.VarData nameStatus = symbolTable.findVar(name.ID);
        if (nameStatus == null){
            System.out.print(" Error: Variable " + name.ID + " not found in the scope" +".\nOn Line: \n"+ toString(0));
            System.exit(0);
        }

        if (nameStatus.isFinal){
            System.out.print(" Error: Cannot use read with final variable" +".\nOn Line: \n"+ toString(0));
            System.exit(0);
        } else if (nameStatus.isMethod){
            System.out.print(" Error: Cannot use read with method" +".\nOn Line: \n"+ toString(0));
            System.exit(0);
        } else if (nameStatus.isArray && name.expr == null){
            System.out.print(" Error: Cannot use read with array" +".\nOn Line: \n"+ toString(0));
            System.exit(0);
        }

        if (readlist != null){
            return readlist.typeCheck();
        }

        return name.typeCheck();
    }

}
