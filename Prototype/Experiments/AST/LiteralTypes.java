package Prototype.Experiments.AST;

public enum LiteralTypes {
    INTEGER("int"),
    STRING("String"),
    CHAR("char"), 
    BOOL("bool"),
    FLOAT("float"),
    VOID("void");

    private String stringType;

    private LiteralTypes(String stringType) {
        this.stringType = stringType;
    }

    @Override 
    public String toString(){
        return stringType;
    }

    public Class<?> boxedClass() {
        return switch (this) {
            case INTEGER -> Integer.class;
            case BOOL    -> Boolean.class;
            case CHAR    -> Character.class;
            case FLOAT   -> Float.class;
            case STRING  -> String.class;
            case VOID    -> Void.class;
        };
    }
}


