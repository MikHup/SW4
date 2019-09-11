package sw4.ast;

import sw4.ast.nodes.*;

public class AstBaseVisitor implements AstVisitor {

    public void Visit(ProgNode node) {}

    public void Visit(SegmentAssignNode node) {}

    public void Visit(ChordAssignNode node) {}

    public void Visit(TitleAssignNode node) {}

    public void Visit(KeyAssignNode node) {}

    public void Visit(TempoAssignNode node) {}

    public void Visit(TimeSignatureAssignNode node) {}

    public void Visit(IdentifierNode node) {}

    public void Visit(OctSwitchNode node) {}

    public void Visit(OctUpNode node) {}

    public void Visit(OctDownNode node) {}

    public void Visit(LengthSwitchNode node) {}

    public void Visit(VelocitySwitchNode node) {}

    public void Visit(SegmentNode node) {}

    public void Visit(ChordNode node) {}

    public void Visit(NoteNode node) {}

    public void Visit(DotNode node) {}

    public void Visit(SharpNode node) {}

    public void Visit(FlatNode node) {}

    public void Visit(RepeatNode node) {}

    public void Visit(TieNode node) {}

    public void Visit(InstrumentNode node) {}

    public void Visit(RestNode node) {}
}
