package sw4.ast;

public interface AstVisitable {
    void Accept(AstVisitor v);
}
