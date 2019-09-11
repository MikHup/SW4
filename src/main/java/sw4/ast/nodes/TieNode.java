package sw4.ast.nodes;

import sw4.ast.AstVisitor;

import java.util.ArrayList;
import java.util.List;

public class TieNode extends AstNode {
    public List<NoteNode> notes;

    public TieNode(List<NoteNode> notes, String lineInfo, String tokenInfo) {
        super(lineInfo, tokenInfo);
        this.notes = notes;
    }

    public TieNode() {
    }

    @Override
    public TieNode Clone(ICloneable node){
        TieNode original = (TieNode) node;
        TieNode clone = new TieNode();
        clone.lineInfo = original.lineInfo;
        clone.tokenInfo = original.tokenInfo;
        clone.notes = new ArrayList<>();
        for (NoteNode note: original.notes) {
            clone.notes.add(note.Clone(note));
        }
        return clone;
    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
