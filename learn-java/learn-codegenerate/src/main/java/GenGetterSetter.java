import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.imports.ImportDeclaration;
import com.github.javaparser.ast.imports.SingleTypeImportDeclaration;
import org.apache.commons.io.FileUtils;
import util.GeneratorUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mazhibin on 16/12/15
 */
public class GenGetterSetter {

    public static void main(String[] args) throws IOException {
        test();
//        genMethod();
        
    }
    
    public static void genMethod() throws IOException {
        String inputPathStr = "/Users/mazhibin/project/fs/fs-organization/fs-organization-adapter-api/src/main/java/com/facishare/organization/adapter/api/model/biz/asterisk/arg";

        Files.walkFileTree(Paths.get(inputPathStr) , new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                CompilationUnit cu = JavaParser.parse(file);

                TypeDeclaration<?> type = cu.getType(0);
                if(type instanceof ClassOrInterfaceDeclaration){
                    ClassOrInterfaceDeclaration mainType = (ClassOrInterfaceDeclaration)type;
                    List<String> delAnnotationNameList = new ArrayList<String>(){{add("Data");add("AllArgsConstructor");add("NoArgsConstructor");}};

                    // remove annotation
                    NodeList<AnnotationExpr> annotations = mainType.getAnnotations();
                    List<AnnotationExpr> delAnnotationList = new ArrayList<>();
                    for (AnnotationExpr annotation : annotations) {
                        if(delAnnotationNameList.contains(annotation.getNameAsString())){
                            delAnnotationList.add(annotation);
                        }
                    }
                    annotations.removeAll(delAnnotationList);
                    
                    // remove import
                    List<ImportDeclaration> delImportList = new ArrayList<>();
                    NodeList<ImportDeclaration> imports = cu.getImports();
                    for (ImportDeclaration anImport : imports) {
                        if(anImport instanceof SingleTypeImportDeclaration){
                            if(((SingleTypeImportDeclaration) anImport).getType().getNameAsString().startsWith("lombok")){
                                delImportList.add(anImport);
                            }
                        }
                    }
                    imports.removeAll(delImportList);
                }

                System.out.println(cu.toString());
                return FileVisitResult.CONTINUE;
            }
        });
    }
    
    public static void test() throws IOException {
        String inputPathStr = "/Users/mazhibin/project/fs/fs-organization/fs-organization-api/src/main/java/com/facishare/organization/api/model/employee/arg/BatchUpdateEmployeeStatusArg.java";

        String content = FileUtils.readFileToString(new File(inputPathStr), Charset.defaultCharset());
        CompilationUnit cu = JavaParser.parse(content);

        ClassOrInterfaceDeclaration mainType = (ClassOrInterfaceDeclaration)cu.getType(0);
        List<FieldDeclaration> fields = mainType.getFields();

        for (FieldDeclaration field : fields) {
            VariableDeclarator variableDeclarator = field.getVariables().get(0);
            String getter = GeneratorUtil.genGetter(variableDeclarator.getIdentifier().getNameAsString(), variableDeclarator.getType().toString());

            System.out.println("arg." + getter + "();");
        }

        System.out.println();

        for (FieldDeclaration field : fields) {
            VariableDeclarator variableDeclarator = field.getVariables().get(0);
            String setter = GeneratorUtil.genSetter(variableDeclarator.getIdentifier().getNameAsString(), variableDeclarator.getType().toString());

            System.out.println("arg." + setter + "();");
        }
    }
}
