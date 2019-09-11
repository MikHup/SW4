package sw4.ast;

import sw4.ast.nodes.*;

public interface AstVisitor {

    void Visit(ProgNode node);

    void Visit(SegmentAssignNode node);

    void Visit(ChordAssignNode node);

    void Visit(TitleAssignNode node);

    void Visit(KeyAssignNode node);

    void Visit(TempoAssignNode node);

    void Visit(TimeSignatureAssignNode node);

    void Visit(IdentifierNode node);

    void Visit(OctSwitchNode node);

    void Visit(OctUpNode node);

    void Visit(OctDownNode node);

    void Visit(LengthSwitchNode node);

    void Visit(VelocitySwitchNode node);

    void Visit(SegmentNode node);

    void Visit(ChordNode node);

    void Visit(NoteNode node);

    void Visit(DotNode node);

    void Visit(SharpNode node);

    void Visit(FlatNode node);

    void Visit(RepeatNode node);

    void Visit(TieNode node);

    void Visit(InstrumentNode node);

    void Visit(RestNode node);
}