import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

/**
 * @class MemberNameCollector，抽取类的成员变量名
 */

public class MemberNameCollector extends VoidVisitorAdapter<List<String>> {

    @Override
    public void visit(ClassOrInterfaceDeclaration n, List<String> collector){
        super.visit(n, collector);
        for(final FieldDeclaration field: n.getFields()){
            // 一行声明中，可能声明了多个成员变量，所以需要对getVariables列表进行迭代
            for(int i = 0; i < field.getVariables().size(); i++){
                collector.add(field.getVariable(i).getNameAsString());
            }
        }
    }

}
