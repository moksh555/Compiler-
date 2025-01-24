import java.util.*;
public class Fielddecls extends SuperToken implements Token {
    Fielddecls fielddecls;
    Fielddecl fielddecl;

    public Fielddecls() {
        this.fielddecl = null;
        this.fielddecls = null;
    }

    public Fielddecls(Fielddecl f, Fielddecls fs) {
        this.fielddecl = f;
        this.fielddecls = fs;
    }

    public String toString(int t) 
    {
        if (fielddecl == null && fielddecls == null)
            return "";
        return fielddecl.toString(t) + (fielddecls == null ? "" : fielddecls.toString(t));
    }
    public void typeCheck() throws Exception {
        if (fielddecl != null)
        fielddecl.typeCheck();
        if (fielddecls != null)
        fielddecls.typeCheck();
    }
}