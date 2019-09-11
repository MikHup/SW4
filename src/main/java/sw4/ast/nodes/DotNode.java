package sw4.ast.nodes;

import sw4.ast.AstVisitor;

public class DotNode extends AstNode {
    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
