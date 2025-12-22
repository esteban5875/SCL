package Prototype.Experiments.AST;

import java.util.Objects;

interface ASTNode {}
interface statement extends ASTNode{}
interface expression extends ASTNode{}

sealed interface numberExpr extends expression permits numberBinaryExpression, numberUnaryExpression {}
sealed interface boolExpr extends expression permits unaryBoolExpr, binaryBoolExpr {}
sealed interface compareExpr extends expression permits binaryCompareExpr {}

record literal<T>(T value, literalTypes type) implements expression {
    public literal {
        Objects.requireNonNull(value);
        Objects.requireNonNull(type);
        typeCheck();
    }

    private void typeCheck(){
        if (type == literalTypes.VOID){
            throw new IllegalArgumentException("Type Error::Invalid Literal Type -> A void cannot be a literal type");
        }

        Class<?> valueClass = value.getClass();
        Class<?> expected = type.boxedClass();
        
        if (!(expected.isAssignableFrom(valueClass))){
            throw new IllegalArgumentException(String.format("Type Error::Type Mismatch -> %s and %s", this.value().toString(), this.type().toString()));
        }
    }
}

record numberBinaryExpression(
    expression left,
    expression right,
    binaryArithmeticExpressions BinaryOp,
    expressionMode mode
) implements numberExpr {

}

record numberUnaryExpression(
    expression left,
    expression right,
    unaryArithmeticExpressions unaryOp
) implements numberExpr {

}

record unaryBoolExpr(
    expression exp,
    unaryBoolExpressions unaryOp
) implements boolExpr {}

record binaryBoolExpr(
    expression left,
    expression right,
    binaryBoolExpressions op
) implements boolExpr {}

record binaryCompareExpr(
    expression left,
    expression right,
    expressionMode mode,
    compareExpressions op
) implements compareExpr {}

record functionBlock(

) implements ASTNode{}

public class AST {}