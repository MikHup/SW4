package sw4.ast.nodes;

import sw4.ast.AstVisitor;

public class TempoAssignNode extends AstNode {
    public int tempo;

    public TempoAssignNode(int tempo, String lineInfo, String tokenInfo) {
        super(lineInfo, tokenInfo);
        this.tempo = tempo;
    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
