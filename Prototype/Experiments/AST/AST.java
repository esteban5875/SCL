//Main AST Class File
//Contains essential AST Nodes Records

package Prototype.Experiments.AST;

import java.util.Objects;

//Essential empty hierarchy interfaces for record classifying.

interface ASTNode {}
interface statement extends ASTNode{}
interface expression extends ASTNode{}

//Operation nodes sealed interfaces (Classification)

sealed interface numberExpr extends expression permits numberBinaryExpression, numberUnaryExpression {} //Numeric expressions
sealed interface boolExpr extends expression permits unaryBoolExpr, binaryBoolExpr {} //Boolean expressions
sealed interface compareExpr extends expression permits binaryCompareExpr {} //Compare expressions

record literal<T>(T value, literalTypes type) implements expression { //Literal Values node
    public literal { //Constructor
        //Require non-null values
        Objects.requireNonNull(value); 
        Objects.requireNonNull(type);
        typeCheck(); //Typecheck private method
    }

    private void typeCheck(){
        if (type == literalTypes.VOID){ //Checl type not void for literal values
            throw new IllegalArgumentException("Type Error::Invalid Literal Type -> A void cannot be a literal type");
        }

        Class<?> valueClass = value.getClass(); //Type class of value
        Class<?> expected = type.boxedClass(); //Type class of given type
        
        if (!(expected.isAssignableFrom(valueClass))){ //Error if type mismatch/
            throw new IllegalArgumentException(String.format("Type Error::Type Mismatch -> %s and %s", this.value().toString(), this.type().toString()));
        }
    }
}

//Operation nodes records

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