import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

/**
 * @class: VarNameCollector，抽取方法体内声明的所有变量名
 */

public class VarNameCollector extends VoidVisitorAdapter<List<String>> {

    @Override
    public void visit(VariableDeclarationExpr vde, List<String> collector){
        super.visit(vde, collector);
        NodeList<VariableDeclarator> variables = vde.getVariables();
        for(VariableDeclarator variable: variables)
            collector.add(variable.getNameAsString());
    }

}
