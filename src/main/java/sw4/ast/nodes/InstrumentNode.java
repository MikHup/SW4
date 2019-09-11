package sw4.ast.nodes;

import sw4.ast.AstVisitor;

public class InstrumentNode extends AstNode {
    public String instrument;

    public InstrumentNode(String instrument, String lineInfo, String tokenInfo){
        super(lineInfo, tokenInfo);
        this.instrument = instrument;
    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
