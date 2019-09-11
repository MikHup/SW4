package sw4.symboltable;

import sw4.ast.nodes.AstNode;

import java.util.Hashtable;

public class SymbolTable {
    private Hashtable<String, AstNode> tbl;

    public SymbolTable(){
        tbl = new Hashtable<String, AstNode>();
    }

    public void AddSymbol(Symbol symbol) {
        tbl.put(symbol.name, symbol.info);
    }

    public void RemoveSymbol(Symbol symbol) {
        tbl.remove(symbol.name);
    }

    public boolean ContainsSymbol(String name) {
        return tbl.containsKey(name);
    }

    public int getSizeOfHashTable(){
        return tbl.size();
    }

    public boolean isEmpty(){
        return tbl.isEmpty();
    }

    public AstNode getSymbol(String name){
        return tbl.get(name);
    }
}