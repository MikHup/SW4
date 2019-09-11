package sw4.ast.nodes;

import sw4.ast.AstVisitor;

public class TimeSignatureAssignNode extends AstNode {
    public int numerator;
    public int denominator;

    public TimeSignatureAssignNode(int numerator, int denominator, String lineInfo, String tokenInfo) {
        super(lineInfo, tokenInfo);
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
