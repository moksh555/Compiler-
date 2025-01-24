public class Printlist extends SuperToken implements Token{
    Expr expr;
    Printlist printlist;

    public Printlist(Expr expr, Printlist p)
    {
        this.expr = expr;
        this.printlist = p;
    }
    public Printlist(Expr expr)
    {
        this.expr = expr;
        this.printlist = null;
    }

    public String toString(int t){
        if (printlist == null)
            return expr.toString(t);
        return expr.toString(t) + ", " + printlist.toString(t);
    }

    public VarType typeCheck() throws Exception {
        VarType type;
        if (expr.cond == 12 && expr.binaryOp.operator.equals("+"))
        {
            type = VarType.String;
        }else {
            type = expr.typeCheck();
            if (type.equals(VarType.Void)) {
                System.out.print(" Error: Cannot apply print on variables of type void" +".\nOn Line: \n"+ toString(0));
                System.exit(0);
            }
        }
        if (printlist != null)
            printlist.typeCheck();
        return type;
    }
    
}
