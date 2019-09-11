package sw4.ast.nodes;

import sw4.ast.AstVisitor;

import java.util.ArrayList;
import java.util.List;

public class ChordNode extends AstNode {
    public List<NoteNode> notes;

    public ChordNode(List<NoteNode> notes, String lineInfo, String tokenInfo) {
        super(lineInfo, tokenInfo);
        this.notes = notes;
    }

    public ChordNode() {
    }

    @Override
    public ChordNode Clone(ICloneable node){
        ChordNode original = (ChordNode) node;
        ChordNode clone = new ChordNode();
        clone.lineInfo = original.lineInfo;
        clone.tokenInfo = original.tokenInfo;
        clone.notes = new ArrayList<>();
        for (NoteNode noteNode: original.notes) {
            clone.notes.add(noteNode.Clone(noteNode));
        }
        return clone;
    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
