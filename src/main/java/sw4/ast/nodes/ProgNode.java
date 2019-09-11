package sw4.ast.nodes;

import sw4.ast.AstVisitor;

import java.util.List;

public class ProgNode extends AstNode {
   public List<AstNode> parts;

   public ProgNode(List<AstNode> parts) {
       this.parts = parts;
   }

   @Override
   public void Accept(AstVisitor v) {
       v.Visit(this);
   }
}
