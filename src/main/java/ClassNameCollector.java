import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

/**
 * @class: ClassNameCollector，获取Java类名
 */

public class ClassNameCollector extends VoidVisitorAdapter<List<String>> {

    @Override
    public void visit(ClassOrInterfaceDeclaration n, List<String> collector){
        super.visit(n, collector);
        collector.add(n.getNameAsString());
    }

}
