package sw4.ast;

//import sun.security.util.Length;

import sw4.ast.nodes.*;

public class PrettyPrintVisitor extends AstBaseVisitor{

    @Override
    public void Visit(ProgNode node) {
        for (AstNode part: node.parts) {
            part.Accept(this);
        }
        System.out.println();
    }

    @Override
    public void Visit(SegmentAssignNode node){
        System.out.print(node.id);
        System.out.print(" = ");
        node.node.Accept(this);
    }

    @Override
    public void Visit(ChordAssignNode node){
        System.out.print(node.id);
        System.out.print(" = ");
        node.node.Accept(this);
    }

    @Override
    public void Visit(TitleAssignNode node) {
        System.out.print("Title");
        System.out.print(" = ");
        System.out.print("\"" + node.title+ "\" ");
    }

    @Override
    public void Visit(KeyAssignNode node) {
        System.out.print("Key");
        System.out.print(" = ");
        System.out.print(node.key + " ");
    }

    @Override
    public void Visit(TempoAssignNode node) {
        System.out.print("Tempo");
        System.out.print(" = ");
        System.out.print(node.tempo + " ");
    }

    @Override
    public void Visit(TimeSignatureAssignNode node) {
        System.out.print("TimeSignature");
        System.out.print(" = ");
        System.out.print(node.numerator + "/" + node.denominator + " ");
    }

    @Override
    public void Visit(IdentifierNode node) {
        System.out.print(node.name + " ");
    }

    @Override
    public void Visit(OctSwitchNode node) {
        System.out.print("O" + node.value + " ");
    }

    @Override
    public void Visit(OctUpNode node) {
        System.out.print("> ");
    }

    @Override
    public void Visit(OctDownNode node) {
        System.out.print("< ");
    }

    @Override
    public void Visit(LengthSwitchNode node){
        System.out.print("L" + node.value + " ");
    }

    @Override
    public void Visit(VelocitySwitchNode node) {
        System.out.print("V" + node.value + " ");
    }

    @Override
    public void Visit(SegmentNode node) {

        System.out.print("(");
        for (AstNode content: node.contents) {
            content.Accept(this);
        }
        System.out.print(") ");
        for (SharpNode sharp: node.sharpNodes) {
            sharp.Accept(this);
        }
        for (FlatNode flat: node.flatNodes) {
            flat.Accept(this);
        }
    }

    @Override
    public void Visit(ChordNode node) {
        for(int i = 0; i < node.notes.size(); i++) {
            node.notes.get(i).Accept(this);
            if(!(i == node.notes.size() - 1))
                System.out.print("/");
        }
    }

    @Override
    public void Visit(NoteNode node) {
        System.out.print(
                (node.noteL == null ? "" : Integer.toString(node.noteL))+
                node.toneval+
                (node.oct == null ? "" : node.oct.toString())
        );
        if (!(node.flatNodes == null)){
            for (AstNode accidental: node.flatNodes) {
                accidental.Accept(this);
            }
        }

        if (!(node.sharpNodes == null)){
            for (AstNode accidental: node.sharpNodes) {
                accidental.Accept(this);
            }
        }

        if (!(node.dots == null)){
            for (AstNode dot: node.dots) {
                dot.Accept(this);
            }
        }
        System.out.print(" ");
    }

    @Override
    public void Visit(DotNode node) {
        System.out.print(".");
    }
    @Override
    public void Visit(SharpNode node) {
        System.out.print("+");
    }
    @Override
    public void Visit(FlatNode node) {
        System.out.print("-");
    }

    @Override
    public void Visit(RepeatNode node) {
        node.operand.Accept(this);
        System.out.print("* "+node.repeatValue);
    }

    @Override
    public void Visit(TieNode node) {
        for(int i = 0; i < node.notes.size(); i++) {
            node.notes.get(i).Accept(this);
            if(!(i == node.notes.size() - 1))
                System.out.print("_");
        }
    }

    @Override
    public void Visit(InstrumentNode node) {
        System.out.print(node.instrument + ": ");

    }

    @Override
    public void Visit(RestNode node) {
        System.out.print((node.length == null? "" : node.length));
        System.out.print(node.restVal);
        if (!(node.dots == null)){
            for (AstNode dot: node.dots) {
                dot.Accept(this);
            }
        }
        System.out.print(" ");
    }
}
