package sw4.ast.nodes;

import sw4.ast.AstVisitor;

public class OctDownNode extends AstNode {
    public OctDownNode(String lineInfo, String tokenInfo){
        super(lineInfo, tokenInfo);
    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
