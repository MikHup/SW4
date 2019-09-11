package sw4.ast.nodes;

import sw4.ast.AstVisitor;

import java.util.ArrayList;
import java.util.List;

public class RestNode extends AstNode {
    public String restVal;
    public Integer length;
    public Long tick;
    public List<DotNode> dots;

    public RestNode(String restVal, Integer length, String lineInfo, String tokenInfo){
        super(lineInfo, tokenInfo);
        this.restVal = restVal;
        this.length = length;
        this.dots = new ArrayList<>();
    }

    public void Accept(AstVisitor v) {
        v.Visit(this);
    }
}
