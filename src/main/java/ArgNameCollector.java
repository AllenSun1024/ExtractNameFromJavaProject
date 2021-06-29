import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

/**
 * @class ArgNameCollector，抽取方法的形参名
 */

public class ArgNameCollector extends VoidVisitorAdapter<List<String>> {

    @Override
    public void visit(MethodDeclaration md, List<String> collector){
        super.visit(md, collector);
        for(int i = 0; i < md.getParameters().size(); i++){
            collector.add(md.getParameter(i).getNameAsString());
        }
    }

}
