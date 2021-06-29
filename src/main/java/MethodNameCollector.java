import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

/**
 * @class MethodNameCollector，抽取方法名
 */

public class MethodNameCollector extends VoidVisitorAdapter<List<String>> {

    @Override
    public void visit(MethodDeclaration md, List<String> collector){
        super.visit(md, collector);
        collector.add(md.getNameAsString());
    }

}
