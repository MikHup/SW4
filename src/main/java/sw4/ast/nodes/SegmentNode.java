package sw4.ast.nodes;

import sw4.ast.AstVisitor;

import java.util.ArrayList;
import java.util.List;

public class SegmentNode extends AstNode {
    public List<AstNode> contents;
    public List<FlatNode> flatNodes;
    public List<SharpNode> sharpNodes;

    public SegmentNode(List<AstNode> contents, List<SharpNode> sharpNodes, List<FlatNode> flatNodes, String lineInfo, String tokenInfo) {
        super(lineInfo, tokenInfo);
        this.contents = contents;
        this.sharpNodes = sharpNodes;
        this.flatNodes = flatNodes;
    }

    public SegmentNode(List<AstNode> contents, String lineInfo, String tokenInfo) {
        super(lineInfo, tokenInfo);
        this.contents = contents;
        this.sharpNodes = new ArrayList<>();
        this.flatNodes = new ArrayList<>();
    }

    public SegmentNode() {
        this.sharpNodes = new ArrayList<>();
        this.flatNodes = new ArrayList<>();
        this.contents = new ArrayList<>();
    }

    @Override
    public SegmentNode Clone(ICloneable node) {
        SegmentNode original = (SegmentNode) node;
        SegmentNode clone = new SegmentNode();
        clone.lineInfo = original.lineInfo;
        clone.tokenInfo = original.tokenInfo;
        for (ICloneable content: original.contents) {
            clone.contents.add(content.Clone(content));
        }
        for (ICloneable sharpNode: original.sharpNodes) {
            clone.sharpNodes.add( (SharpNode) sharpNode.Clone(sharpNode));
        }
        for (ICloneable flatNode: original.flatNodes) {
            clone.flatNodes.add((FlatNode) flatNode.Clone(flatNode));
        }

        return clone;
    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
