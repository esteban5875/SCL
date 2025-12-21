package Prototype.Experiments.AST;

import java.util.Objects;

interface ASTNode {}
interface Statement extends ASTNode{}
interface Expression extends ASTNode{}

record Literal<T>(T value, LiteralTypes type) implements Expression {
    public Literal {
        Objects.requireNonNull(value);
        Objects.requireNonNull(type);
        typeCheck();
    }

    private void typeCheck(){
        if (type == LiteralTypes.VOID){
            throw new IllegalArgumentException("Type Error::Invalid Literal Type -> A void cannot be a literal type");
        }

        Class<?> valueClass = value.getClass();
        Class<?> expected = type.boxedClass();
        
        if (!(expected.isAssignableFrom(valueClass))){
            throw new IllegalArgumentException(String.format("Type Error::Type Mismatch -> %s and %s", this.value().toString(), this.type().toString()));
        }
    }
}



record functionBlock(

) {}

public class AST {}