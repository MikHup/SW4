package sw4.ast.nodes;

import sw4.ast.AstVisitor;

public class LengthSwitchNode extends AstNode{
    public int value;

    public LengthSwitchNode(int value, String lineInfo, String tokenInfo) {
        super(lineInfo, tokenInfo);
        this.value = value;
    }


    @Override
    public void Accept(AstVisitor v) { v.Visit(this); }
}
