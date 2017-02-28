import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * Created by mazhibin on 16/12/15
 * 生成VO的那些Tag
 */
public class GenAnnotation {

    private static String inputPathStr = "/Users/mazhibin/project/fs/fs-organization/fs-organization-biz/src/main/java/com/facishare/organization/biz/vo/web/type";
    private static String outputPathStr = inputPathStr;
//    private static String outputPathStr = "/Users/mazhibin/project/java/learn-java/learn-java/learn-codegenerate/out";

    private static Path inputPath = Paths.get(inputPathStr);
    private static Path outputPath = Paths.get(outputPathStr);

    public static void main(String[] args) throws IOException {

        Files.walkFileTree(inputPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                CompilationUnit cu = JavaParser.parse(file.toFile());

                ClassOrInterfaceDeclaration mainType = (ClassOrInterfaceDeclaration) cu.getType(0);
                List<FieldDeclaration> fields = mainType.getFields();
                
                int i = 1;
                for (FieldDeclaration field : fields) {
                    final int index = i;
                    String fieldName = field.getVariable(0).getIdentifier().getNameAsString();

                    NormalAnnotationExpr tag = field.addAnnotation("Tag");
                    tag.setPairs(new NodeList<MemberValuePair>(){{add(new MemberValuePair("value",new IntegerLiteralExpr(Integer.toString(index))));}});

                    NormalAnnotationExpr jsonField = field.addAnnotation("JSONField");
                    jsonField.setPairs(new NodeList<MemberValuePair>(){{add(new MemberValuePair("name",new StringLiteralExpr(fieldName)));}});

                    NormalAnnotationExpr serializedName = field.addAnnotation("SerializedName");
                    serializedName.setPairs(new NodeList<MemberValuePair>(){{add(new MemberValuePair("value",new StringLiteralExpr(fieldName)));}});

                    i++;
                }

                cu.addImport("com.alibaba.fastjson.annotation.JSONField");
                cu.addImport("com.google.gson.annotations.SerializedName");
                cu.addImport("io.protostuff.Tag");

                File outputFile = new File(file.toFile().getAbsolutePath().replace(inputPathStr, outputPathStr));
                FileUtils.write(outputFile, cu.toString(), Charset.defaultCharset());

                return FileVisitResult.CONTINUE;
            }
        });
    }
}
