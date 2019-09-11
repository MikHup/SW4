package sw4.symboltable;

import sw4.ast.nodes.AstNode;

public class Symbol {
    public String name;
    public AstNode info;

    public Symbol(String name, AstNode info){
        this.name = name;
        this.info = info;
    }

}
