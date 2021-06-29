import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

/**
 * @class: CommentCollector，抽取注释
 */

public class CommentCollector extends VoidVisitorAdapter<List<String>> {

    @Override
    public void visit(MethodDeclaration md, List<String> collector){
        List<Comment> comments = md.getAllContainedComments();
        for(int i = 0; i < comments.size(); i++)
            collector.add(comments.get(i).toString());
    }

}
