import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by mazhibin on 16/12/13
 */
public class JavaParserTest {

    public static void main(String[] args) throws FileNotFoundException {
//        test();
//        testVisitMethod();
//        testVisitMethodAndModify();
        testTypeSolver();
    }

    public static void test() throws FileNotFoundException {
        FileInputStream in = new FileInputStream("/Users/mazhibin/project/java/learn-java/learn-java/learn-codegenerate/src/main/java/CodeModelTest.java");
        CompilationUnit parse = JavaParser.parse(in);

        System.out.println(parse);
    }

    public static void testVisitMethod() throws FileNotFoundException {
        FileInputStream in = new FileInputStream("/Users/mazhibin/project/java/learn-java/learn-java/learn-codegenerate/src/main/java/CodeModelTest.java");
        CompilationUnit parse = JavaParser.parse(in);

        new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(MethodDeclaration n, Void arg) {
                System.out.println(n.getName());
                super.visit(n, arg);
            }
        }.visit(parse, null);
    }

    public static void testVisitMethodAndModify() throws FileNotFoundException {
        FileInputStream in = new FileInputStream("/Users/mazhibin/project/java/learn-java/learn-java/learn-codegenerate/src/main/java/CodeModelTest.java");
        CompilationUnit parse = JavaParser.parse(in);

        new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(MethodDeclaration n, Void arg) {
                n.setName(n.getNameAsString().toUpperCase());
                n.addParameter(int.class, "hello");
                super.visit(n, arg);
            }
        }.visit(parse, null);

        System.out.println(parse);
    }
    
    public static void testTypeSolver() throws FileNotFoundException {
        FileInputStream in = new FileInputStream("/Users/mazhibin/project/java/learn-java/learn-java/learn-codegenerate/src/main/java/CodeModelTest.java");
        CompilationUnit parse = JavaParser.parse(in);

        CombinedTypeSolver combinedTypeSolver = new CombinedTypeSolver();
//        combinedTypeSolver.add(new JreTypeSolver());
//        combinedTypeSolver.add(new JavaParserTypeSolver(new File("src/test/resources/javaparser_src/proper_source")));
//        combinedTypeSolver.add(new JavaParserTypeSolver(new File("src/test/resources/javaparser_src/generated")));

        TypeDeclaration<?> type = parse.getType(0);
        List<FieldDeclaration> fields = type.getFields();
        for (FieldDeclaration field : fields) {
//            Type type1 = JavaParserFacade.get(combinedTypeSolver).getType(field);
            System.out.println(field);
        }
    }
}
