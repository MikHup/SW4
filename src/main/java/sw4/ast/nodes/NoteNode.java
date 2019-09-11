package sw4.ast.nodes;

import sw4.ast.AstVisitor;

import java.util.ArrayList;
import java.util.List;

public class NoteNode extends AstNode {
    public Integer noteL;
    public Long tick;
    public String toneval;
    public Integer oct;
    public List<FlatNode> flatNodes;
    public List<SharpNode> sharpNodes;
    public List<DotNode> dots;

    public NoteNode(Integer noteL, String toneval, Integer oct, String lineInfo, String tokenInfo) {
        super(lineInfo, tokenInfo);
        this.noteL = noteL;
        this.toneval = toneval;
        this.oct = oct;
        this.sharpNodes = new ArrayList<>();
        this.flatNodes = new ArrayList<>();
        this.dots = new ArrayList<>();
    }

    public NoteNode() {
        this.sharpNodes = new ArrayList<>();
        this.flatNodes = new ArrayList<>();
        this.dots = new ArrayList<>();
    }

    @Override
    public NoteNode Clone(ICloneable node){
        NoteNode original = (NoteNode) node;
        NoteNode clone = new NoteNode();
        clone.lineInfo = original.lineInfo;
        clone.tokenInfo = original.tokenInfo;
        clone.noteL = original.noteL;
        clone.tick = original.tick;
        clone.toneval = original.toneval;
        clone.oct = original.oct;
        clone.sharpNodes = new ArrayList<>(original.sharpNodes);
        clone.flatNodes = new ArrayList<>(original.flatNodes);
        clone.dots = new ArrayList<>(original.dots);
        return clone;
    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
