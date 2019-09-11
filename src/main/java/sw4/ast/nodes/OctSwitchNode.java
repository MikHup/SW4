package sw4.ast.nodes;

import sw4.ast.AstVisitor;

public class OctSwitchNode extends AstNode {
    public int value;

    public OctSwitchNode(int value, String lineInfo, String tokenInfo) {
        super(lineInfo, tokenInfo);
        this.value = value;
    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
