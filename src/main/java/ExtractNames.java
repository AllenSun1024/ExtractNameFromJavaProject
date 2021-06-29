import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExtractNames {

    /**
     * @param rootPath 存储待解析Java文件的根目录
     */
    private static final String rootPath = "/home/ubuntu/zfy/spring";

    /**
     * @param classNames 存储Java文件的类名
     * @param memberNames 存储类的成员变量名
     * @param methodNames 存储每个方法名
     * @param argNames 存储方法的形参名
     * @param varNames 存储方法内的局部变量名
     * @param methodComments 存储方法的注释
     */
    private static List<String> classNames = new ArrayList<>();
    private static List<String> memberNames = new ArrayList<>();
    private static List<String> methodNames = new ArrayList<>();
    private static List<String> argNames = new ArrayList<>();
    private static List<String> varNames = new ArrayList<>();
    private static List<String> methodComments = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File(rootPath);
        parse_file(f);
        write_txt();
    }

    private static void parse_file(File f) throws FileNotFoundException {
        File[] files = f.listFiles();
        for(File file: files){
            if(file.isDirectory()){
                parse_file(file);
            }
            else if(file.isFile() && file.getName().endsWith("java")){
                CompilationUnit cu = StaticJavaParser.parse(new File(file.getAbsolutePath()));

                // 获取类名，包括主类和内部类
                VoidVisitor<List<String>> classNameVisitor = new ClassNameCollector();
                classNameVisitor.visit(cu, classNames);

                // 获取类的成员变量
                VoidVisitor<List<String>> memberNameVisitor = new MemberNameCollector();
                memberNameVisitor.visit(cu, memberNames);

                // 获取类中每个方法
                VoidVisitor<List<String>> methodNameVisitor = new MethodNameCollector();
                methodNameVisitor.visit(cu, methodNames);

                // 获取方法的各参数名
                VoidVisitor<List<String>> argNameVisitor = new ArgNameCollector();
                argNameVisitor.visit(cu, argNames);

                // 获取方法内的变量名
                VoidVisitor<List<String>> varNameVisitor = new VarNameCollector();
                varNameVisitor.visit(cu, varNames);

                // 获取可能有的注释
                VoidVisitor<List<String>> commentVisitor = new CommentCollector();
                commentVisitor.visit(cu, methodComments);

            }
            else{
                System.out.println(file.getName() + " -> 不应处理的文件");
            }
        }
    }

    private static void write_txt(){

        try{
            FileWriter fileWriter = new FileWriter("names.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            // write class names
            for(String s: classNames)
                printWriter.print(s + '\n');
            printWriter.print('\n');

            // write member variable names
            for(String s: memberNames)
                printWriter.print(s + '\n');
            printWriter.print('\n');

            // write method names
            for(String s: methodNames)
                printWriter.print(s + '\n');
            printWriter.print('\n');

            // write parameter names
            for(String s: argNames)
                printWriter.print(s + '\n');
            printWriter.print('\n');

            // write local variable names
            for(String s: varNames)
                printWriter.print(s + '\n');
            printWriter.print('\n');

            // write comments in the method
            for(String s: methodComments)
                printWriter.print(s + '\n');

            printWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

}
