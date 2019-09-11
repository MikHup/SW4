package sw4.ast.nodes;

import sw4.ast.AstVisitor;

public class TitleAssignNode extends AstNode {
    public String title;

    public TitleAssignNode(String title, String lineInfo, String tokenInfo){
        super(lineInfo, tokenInfo);
        this.title = title;
    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
