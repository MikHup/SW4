package sw4.ast.nodes;

import sw4.ast.AstVisitor;

public class RepeatNode extends AstNode {
    public AstNode operand;
    public int repeatValue;

    public RepeatNode(AstNode operand, int repeatValue, String lineInfo, String tokenInfo) {
        super(lineInfo, tokenInfo);
        this.operand = operand;
        this.repeatValue = repeatValue;
    }

    public RepeatNode() {
    }

    @Override
    public RepeatNode Clone(ICloneable node){
        RepeatNode original = (RepeatNode) node;
        RepeatNode clone = new RepeatNode();
        clone.lineInfo = original.lineInfo;
        clone.tokenInfo = original.tokenInfo;
        clone.operand = original.operand.Clone(original.operand);
        clone.repeatValue = original.repeatValue;
        return clone;
    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
