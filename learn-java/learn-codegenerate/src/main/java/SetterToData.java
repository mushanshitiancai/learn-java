import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mazhibin on 16/12/20
 */
public class SetterToData {

    public static void main(String[] args) throws IOException {
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

        String[] inputPathStr = {"/Users/mazhibin/project/fs/fs-organization/fs-organization-api/src/main/java/com/facishare/organization/api/event",
        "/Users/mazhibin/project/fs/fs-organization/fs-organization-api/src/main/java/com/facishare/organization/api/model"};

        for (String s : inputPathStr) {
            Files.walkFileTree(Paths.get(s), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    backToData(file.toFile());
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    public static void backToData(File file) throws IOException {
        CompilationUnit cu = JavaParser.parse(file);
        TypeDeclaration<?> type = cu.getType(0);
        if (type instanceof ClassOrInterfaceDeclaration) {
            System.out.println(file);
            ClassOrInterfaceDeclaration mainType = (ClassOrInterfaceDeclaration) type;
            if(mainType.isInterface()) return;

            if (mainType.getAnnotationByName("Data") == null) {
                cu.addImport("lombok.Data");
                mainType.addAnnotation("Data");
            }
            if(mainType.getFields().size() != 0) {
                if (mainType.getAnnotationByName("NoArgsConstructor") == null) {
                    cu.addImport("lombok.NoArgsConstructor");
                    mainType.addAnnotation("NoArgsConstructor");
                }
                if (mainType.getAnnotationByName("AllArgsConstructor") == null) {
                    cu.addImport("lombok.AllArgsConstructor");
                    mainType.addAnnotation("AllArgsConstructor");
                }
            }
            
            List<BodyDeclaration<?>> delList = new ArrayList<>();
            NodeList<BodyDeclaration<?>> members = mainType.getMembers();
            for (BodyDeclaration<?> member : members) {
                if (member instanceof ConstructorDeclaration) {
                    ConstructorDeclaration constructorDeclaration = (ConstructorDeclaration) member;
                    int paramCount = constructorDeclaration.getParameters().size();
                    if(paramCount == 0 || paramCount == mainType.getFields().size()) {
                        delList.add(member);
                    }
                }
                if(member instanceof MethodDeclaration){
                    String methodName = ((MethodDeclaration) member).getNameAsString();
                    if(methodName.startsWith("set") || methodName.startsWith("get") || methodName.startsWith("is") || methodName.equals("toString") || methodName.equals("hashCode") || methodName.equals("equals")){
                        delList.add(member);
                    }
                }
            }
            members.removeAll(delList);

            FileUtils.write(file, cu.toString(), Charset.defaultCharset());
        }
    }


}
