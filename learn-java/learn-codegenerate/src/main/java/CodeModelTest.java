import com.sun.codemodel.internal.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by mazhibin on 16/12/13
 */
public class CodeModelTest {
    
    public static int i;
    public static List<Integer> list;
    public static CodeModelTest test;

    public CodeModelTest() {
    }

    public CodeModelTest(int a) {
    }

    public static void main(String[] args) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        File destDir = new File("/Users/mazhibin/project/java/learn-java/learn-java/learn-codegenerate/src/main/java");
        
        JCodeModel cm = new JCodeModel();
        JDefinedClass jDefinedClass = cm._class("test.example");

        JFieldVar testField = jDefinedClass.field(JMod.PUBLIC, JClassAlreadyExistsException.class, "testField");
        testField.annotate(Override.class);

        JMethod exampleMethod = jDefinedClass.method(JMod.PUBLIC, cm.VOID, "exampleMethod");
        JBlock body = exampleMethod.body();
        body.decl(cm.parseType("String"), "testVar");

        cm.build(destDir);
    }
}
