package sw4.ast.nodes;

import sw4.ast.AstVisitor;

import java.util.ArrayList;
import java.util.List;

public class IdentifierNode extends AstNode {
    public String name;
    public List<FlatNode> flatNodes;
    public List<SharpNode> sharpNodes;

    public IdentifierNode(String name, String lineInfo, String tokenInfo) {
        super(lineInfo, tokenInfo);
        this.name = name;
    }

    public IdentifierNode() {

    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
    @Override
    public IdentifierNode Clone(ICloneable node){
        IdentifierNode identifierNode = (IdentifierNode) node;
        IdentifierNode clone = new IdentifierNode();
        clone.lineInfo = identifierNode.lineInfo;
        clone.tokenInfo = identifierNode.tokenInfo;
        clone.name = identifierNode.name;
        clone.sharpNodes = new ArrayList<>(identifierNode.sharpNodes);
        clone.flatNodes = new ArrayList<>(identifierNode.flatNodes);
        return clone;
    }
}
