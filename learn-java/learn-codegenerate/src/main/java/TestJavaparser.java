
/**
 * Created by chenxb on 16-12-14.
 */
public class TestJavaparser {


    public TestJavaparser() throws Exception {
    }
    

//    public void test3() throws Exception {
//        String pathname = "/home/chenxb/workspace/fs-organization/fs-organization-api/src/main/java/com/facishare/organization/api/event";
////        createJavaFile(new File(pathname));
//        Files.walkFileTree(Paths.get(pathname), new SimpleFileVisitor<Path>() {
//            @Override
//            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                File fileVisitor = file.toFile();
//                createJavaFile(fileVisitor);
//                return super.visitFile(file, attrs);
//            }
//        });
//    }
//
//    private void createJavaFile(File file) throws IOException {
//        FileInputStream in = new FileInputStream(file);
//        createJava(in, file);
//    }
//
//    private CompilationUnit createJava(FileInputStream in, File file) throws IOException {
//        CompilationUnit cu = JavaParser.parse(in);
//        for (TypeDeclaration<?> typeDeclaration : cu.getTypes()) {
//            if (!(typeDeclaration instanceof ClassOrInterfaceDeclaration)) {
//                return cu;
//            }
//            ClassOrInterfaceDeclaration classOrInterfaceDeclaration = (ClassOrInterfaceDeclaration) typeDeclaration;
//            if (classOrInterfaceDeclaration.isInterface()) {
//                return cu;
//            }
//
//            createConstructor(classOrInterfaceDeclaration);
//            removeAnnotations(classOrInterfaceDeclaration);
//            removeImports(cu);
//        }
//
//        FileOutputStream out = new FileOutputStream(file);
//        out.write(cu.toString().getBytes());
//        return cu;
//    }
//
//    private void removeImports(CompilationUnit cu) {
//        List<ImportDeclaration> imports = cu.getImports();
//        List<ImportDeclaration> removeImports = new ArrayList<>();
//        for (ImportDeclaration importDeclaration : imports) {
//            SingleTypeImportDeclaration singleImport = (SingleTypeImportDeclaration) importDeclaration;
//            if (singleImport.getType().toString().startsWith("lombok."))
//                removeImports.add(importDeclaration);
//        }
//        imports.removeAll(removeImports);
//    }
//
//    private void removeAnnotations(ClassOrInterfaceDeclaration classOrInterfaceDeclaration) {
//        NodeList<AnnotationExpr> annotations = classOrInterfaceDeclaration.getAnnotations();
//        List<AnnotationExpr> removeAnns = new ArrayList<>();
//        for (AnnotationExpr annotationExpr : annotations) {
//            String annName = annotationExpr.getName().asString();
//            if (annName.equals("Data")) {
//                removeAnns.add(annotationExpr);
//                createGetSetToString(classOrInterfaceDeclaration);
//                continue;
//            }
//
//            if (annName.equals("AllArgsConstructor") || annName.equals("NoArgsConstructor")) {
//                removeAnns.add(annotationExpr);
//            }
//        }
//        annotations.removeAll(removeAnns);
//    }
//
//    private void createConstructor(ClassOrInterfaceDeclaration classOrInterfaceDeclaration) {
//        List<FieldDeclaration> fields = classOrInterfaceDeclaration.getFields();
//        int fieldSize = fields.size();
////        if (fieldSize < 1 || fieldSize > 4)
////            return;
//
//        boolean allArgsConstructor = true;
//        boolean noArgsConstructor = true;
//        NodeList<BodyDeclaration<?>> members = classOrInterfaceDeclaration.getMembers();
//        for (BodyDeclaration<?> member : members) {
//            if (member instanceof ConstructorDeclaration) {
//                ConstructorDeclaration constructorDeclaration = (ConstructorDeclaration) member;
//                int size = constructorDeclaration.getParameters().size();
//                if (size == 0)
//                    noArgsConstructor = false;
//
//                if (size == fieldSize)
//                    allArgsConstructor = false;
//            }
//        }
//
//        if (noArgsConstructor) {
//            classOrInterfaceDeclaration.addConstructor(Modifier.PUBLIC);
//        }
//
//        if (allArgsConstructor && fieldSize != 0) {
//            ConstructorDeclaration constructor = classOrInterfaceDeclaration.addConstructor(Modifier.PUBLIC);
//            BlockStmt body = new BlockStmt();
//            for (FieldDeclaration field : fields) {
//                VariableDeclarator variableDeclarator = field.getVariables().get(0);
//                constructor.addParameter(variableDeclarator.getType(), variableDeclarator.getNameAsString());
//                NameExpr tager = new NameExpr("this." + variableDeclarator.getNameAsString());
//                NameExpr value = new NameExpr(variableDeclarator.getNameAsString());
//                body.addStatement(new AssignExpr(tager, value, AssignExpr.Operator.ASSIGN));
//            }
//            constructor.setBody(body);
//        }
//    }
//
//    private void createGetSetToString(ClassOrInterfaceDeclaration classOrInterfaceDeclaration) {
//        StringBuilder sb = new StringBuilder("\"" + classOrInterfaceDeclaration.getNameAsString() + "{\" +");
//        for (FieldDeclaration fieldDeclaration : classOrInterfaceDeclaration.getFields()) {
//            createGetter(fieldDeclaration);
//
//            createSetter(fieldDeclaration);
//
//            VariableDeclarator variableDeclarator = fieldDeclaration.getVariables().get(0);
//            sb.append(" \"").append(variableDeclarator).append("\" + ").append(variableDeclarator).append(" +");
//        }
//        sb.append(" \"}\"");
//
//        MethodDeclaration toString = classOrInterfaceDeclaration.addMethod("toString", Modifier.PUBLIC);
//        toString.setType(String.class);
//        BlockStmt body = new BlockStmt();
//        ReturnStmt statement = new ReturnStmt(sb.toString());
//        body.addStatement(statement);
//
//        toString.setBody(body);
//    }
//
//    private MethodDeclaration createSetter(FieldDeclaration fieldDeclaration) {
//        if (fieldDeclaration.getVariables().size() != 1)
//            throw new IllegalStateException("You can use this only when the field declares only 1 variable name");
//        ClassOrInterfaceDeclaration parentClass = fieldDeclaration.getAncestorOfType(ClassOrInterfaceDeclaration.class);
//        EnumDeclaration parentEnum = fieldDeclaration.getAncestorOfType(EnumDeclaration.class);
//        if ((parentClass == null && parentEnum == null) || (parentClass != null && parentClass.isInterface()))
//            throw new IllegalStateException(
//                    "You can use this only when the field is attached to a class or an enum");
//
//        VariableDeclarator variable = fieldDeclaration.getVariable(0);
//        String fieldName = variable.getNameAsString();
//        String fieldNameUpper = fieldName.toUpperCase().substring(0, 1) + fieldName.substring(1, fieldName.length());
//
//        final MethodDeclaration setter;
//        String methodName = "set" + fieldNameUpper;
//        VariableDeclarator variableDeclarator = fieldDeclaration.getVariables().get(0);
//        String nameAsString = variableDeclarator.getNameAsString();
//        if (variableDeclarator.getType().toString().equals("boolean") && nameAsString.startsWith("is")) {
//            methodName = "set" + nameAsString.substring(2);
//        }
//
//        if (parentClass != null)
//            setter = parentClass.addMethod(methodName, PUBLIC);
//        else
//            setter = parentEnum.addMethod(methodName, PUBLIC);
//        setter.setType(VOID_TYPE);
//        setter.getParameters().add(new Parameter(variable.getType(), fieldName));
//        BlockStmt blockStmt2 = new BlockStmt();
//        setter.setBody(blockStmt2);
//        blockStmt2.addStatement(new AssignExpr(new NameExpr("this." + fieldName), new NameExpr(fieldName), AssignExpr.Operator.ASSIGN));
//        return setter;
//    }
//
//    private MethodDeclaration createGetter(FieldDeclaration fieldDeclaration) {
//        if (fieldDeclaration.getVariables().size() != 1)
//            throw new IllegalStateException("You can use this only when the field declares only 1 variable name");
//        ClassOrInterfaceDeclaration parentClass = fieldDeclaration.getAncestorOfType(ClassOrInterfaceDeclaration.class);
//        EnumDeclaration parentEnum = fieldDeclaration.getAncestorOfType(EnumDeclaration.class);
//        if ((parentClass == null && parentEnum == null) || (parentClass != null && parentClass.isInterface()))
//            throw new IllegalStateException(
//                    "You can use this only when the field is attached to a class or an enum");
//
//        VariableDeclarator variable = fieldDeclaration.getVariable(0);
//        String fieldName = variable.getNameAsString();
//        String fieldNameUpper = fieldName.toUpperCase().substring(0, 1) + fieldName.substring(1, fieldName.length());
//        final MethodDeclaration getter;
//        String getPrefix = "get";
//
//        VariableDeclarator variableDeclarator = fieldDeclaration.getVariables().get(0);
//
//        String methodName = getPrefix + fieldNameUpper;
//        if (variableDeclarator.getType().toString().equals("boolean")) {
//            if (variableDeclarator.getNameAsString().startsWith("is")) {
//                methodName = variableDeclarator.getNameAsString();
//            } else {
//                getPrefix = "is";
//                methodName = getPrefix + fieldNameUpper;
//            }
//        }
//
//        if (parentClass != null)
//            getter = parentClass.addMethod(methodName, PUBLIC);
//        else
//            getter = parentEnum.addMethod(methodName, PUBLIC);
//        getter.setType(variable.getType());
//        BlockStmt blockStmt = new BlockStmt();
//        getter.setBody(blockStmt);
//        blockStmt.addStatement(new ReturnStmt(fieldName));
//        return getter;
//    }
}
