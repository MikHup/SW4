package sw4.ast.nodes;

import sw4.ast.AstVisitor;

public class ChordAssignNode extends AstNode {
    public String id;
    public ChordNode node;

    public ChordAssignNode(String left, ChordNode right, String lineInfo, String tokenInfo) {
        super(lineInfo, tokenInfo);
        id = left;
        node = right;
    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
