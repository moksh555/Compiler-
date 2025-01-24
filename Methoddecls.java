import java.util.*;
public class Methoddecls extends SuperToken implements Token
{
    Methoddecl methodDecl;
    Methoddecls methodDecls;

    public Methoddecls(Methoddecl md, Methoddecls mds){
        this.methodDecl = md;
        this.methodDecls = mds; 
    }

    public Methoddecls(Methoddecl md){
        this.methodDecl = md;
        methodDecls = null;
    }

    public Methoddecls(){
        this.methodDecl = null;
        this.methodDecls = null;
    }

    public String toString(int t){
        return (methodDecl == null ? "" : methodDecl.toString(t)) + (methodDecls == null ? "" : methodDecls.toString(t));
    }

    public void typeCheck() throws Exception{
        if (methodDecl != null)
            methodDecl.typeCheck();
        if (methodDecls != null)
            methodDecls.typeCheck();
    }
}