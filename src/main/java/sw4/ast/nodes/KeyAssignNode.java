package sw4.ast.nodes;

import sw4.ast.AstVisitor;

public class KeyAssignNode extends AstNode {
    public String key;

    public KeyAssignNode(String key, String lineInfo, String tokenInfo){
        super(lineInfo, tokenInfo);
        this.key = key;
    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
