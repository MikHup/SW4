package sw4.ast.nodes;

import sw4.ast.AstVisitable;


public abstract class AstNode implements AstVisitable, ICloneable{
    public String lineInfo;
    public String tokenInfo;

    public AstNode(String lineInfo, String tokenInfo){
        this.lineInfo = lineInfo;
        this.tokenInfo = tokenInfo;
    }

    public AstNode(){}

    //Most child classes do not need to be cloned, so "return this" is fine
    @Override
    public AstNode Clone(ICloneable original){
        return this;
    }
}

