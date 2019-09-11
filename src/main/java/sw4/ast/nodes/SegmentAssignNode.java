package sw4.ast.nodes;

import sw4.ast.AstVisitor;

public class SegmentAssignNode extends AstNode {
    public String id;
    public SegmentNode node;

    public SegmentAssignNode(String left, SegmentNode right, String lineInfo, String tokenInfo) {
        super(lineInfo, tokenInfo);
        id = left;
        node = right;
    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
