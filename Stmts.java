public class Stmts extends SuperToken implements Token {
    Stmt st;
    Stmts sts;

    public Stmts(Stmt st, Stmts sts) {
        this.st = st;
        this.sts = sts;
    }

    public Stmts(){
        this.st = null;
        this.sts = null;
    }

    public String toString(int t){
        return (st == null ? "" : st.toString(t)) + (sts == null ? "" : sts.toString(t));
    }

    public void typeCheck() throws Exception{
        if (st != null) {
            st.typeCheck();
        }
        if (sts != null) {
            sts.typeCheck();
        }
    }
}
