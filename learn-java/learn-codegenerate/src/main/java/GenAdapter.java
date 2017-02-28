import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.Type;
import com.sun.codemodel.JMod;
import com.sun.codemodel.internal.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import util.GeneratorUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by mazhibin on 16/12/13
 * <p>
 * 生成通讯录adapter的代码生成器
 */
public class GenAdapter {
    private static String moduleInputPathStr = "/Users/mazhibin/project/fs/fs-organization/fs-organization-api/src/main/java/com/facishare/organization/api/model";
    private static String moduleOutputPathStr = "/Users/mazhibin/project/fs/fs-organization/fs-organization-adapter-api/src/main/java/com/facishare/organization/adapter/api/model/biz";
//    private static String moduleOutputPathStr = "/Users/mazhibin/project/java/learn-java/learn-java/learn-codegenerate/out";

    private static String moduleInputPackage = "com.facishare.organization.api.model";
    private static String moduleOutputPackage = "com.facishare.organization.adapter.api.model.biz";

    private static Path moduleInputPath = Paths.get(moduleInputPathStr);
    private static Path moduleOutputPath = Paths.get(moduleOutputPathStr);

    private static String serviceDefinePathStr = "/Users/mazhibin/project/fs/fs-organization/fs-organization-api/src/main/java/com/facishare/organization/api/service";
    private static String serviceInputPathStr = "/Users/mazhibin/project/fs/fs-organization/fs-organization-adapter-api/src/main/java/com/facishare/organization/adapter/api/service/biz";
    private static String serviceOutputPathStr = "/Users/mazhibin/project/fs/fs-organization/fs-organization-adapter/src/main/java";

    private static Path serviceDefinePath = Paths.get(serviceDefinePathStr);
    private static Path serviceInputPath = Paths.get(serviceInputPathStr);
    private static Path serviceOutputPath = Paths.get(serviceOutputPathStr);

    private static String serviceInputPackage = "com.facishare.organization.api.service";
    private static String serviceOutputPackage = "com.facishare.organization.adapter.dubbo.biz";

    public static void main(String[] args) throws IOException {
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

        dealModule();
        dealService();
    }

    public static void dealModule() throws IOException {
        // 遍历api模块中的module
        Files.walkFileTree(moduleInputPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                String nameWithoutExtension = com.google.common.io.Files.getNameWithoutExtension(file.toString());
                if (nameWithoutExtension.endsWith("Arg")) {
                    gen(file.toFile(), GenAdapter::genArgConverter);
                } else if (nameWithoutExtension.endsWith("Result")) {
                    gen(file.toFile(), GenAdapter::genResultConverter);
                } else {
                    gen(file.toFile(), GenAdapter::genTypeConverter);
                }

                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void dealService() throws IOException {
        System.out.println("复制serviceDefinePath中的接口到serviceInputPath中");
        // 复制serviceDefinePath中的接口到serviceInputPath中
        Files.walkFileTree(serviceDefinePath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.getFileName().toString().startsWith("OrganizationCacheService"))
                    return FileVisitResult.CONTINUE;

                String content = FileUtils.readFileToString(file.toFile(), Charset.defaultCharset());

                content = content.replace("package com.facishare.organization.api.service;", "package com.facishare.organization.adapter.api.service.biz;");
                content = content.replace("import com.facishare.organization.api.model", "import com.facishare.organization.adapter.api.model.biz");

                Path dest = serviceInputPath.resolve(file.getFileName());
                FileUtils.write(dest.toFile(), content, Charset.defaultCharset());
                return FileVisitResult.CONTINUE;
            }
        });

        System.out.println("遍历api模块中的service");
        // 遍历api模块中的service
        Files.walkFileTree(serviceInputPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().contains("OrganizationCacheService.java")) return FileVisitResult.CONTINUE;

                System.out.println(file);
                try {
                    genServiceImplement(file.toFile());
                } catch (JClassAlreadyExistsException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void genServiceImplement(File file) throws IOException, JClassAlreadyExistsException, ClassNotFoundException {
        Map<String, String> typeFullName = GeneratorUtil.getTypeFullName(moduleOutputPathStr);

        CompilationUnit cu = JavaParser.parse(file);

        ClassOrInterfaceDeclaration mainType = (ClassOrInterfaceDeclaration) cu.getType(0);
        List<MethodDeclaration> methods = mainType.getMethods();

        JCodeModel cm = new JCodeModel();
        String fullImplClassName = serviceOutputPackage + "." + mainType.getNameAsString();
        JDefinedClass jDefinedClass = cm._class(fullImplClassName + "Impl");
        jDefinedClass._implements(cm.ref("com.facishare.organization.adapter.api.service.biz." + mainType.getNameAsString()));
//        jDefinedClass.annotate(cm.ref("org.springframework.stereotype.Service"));

        JFieldVar field = jDefinedClass.field(JMod.PRIVATE, cm.parseType(serviceInputPackage + "." + mainType.getNameAsString()), lowerFirstLetter(mainType.getNameAsString()));
        field.annotate(cm.ref("org.springframework.beans.factory.annotation.Autowired"));

        for (MethodDeclaration method : methods) {
            JClass returnType = cm.ref(typeFullName.get(method.getType().toString()));
            JMethod methodImpl = jDefinedClass.method(JMod.PUBLIC, returnType, method.getNameAsString());
            methodImpl.param(cm.parseType(typeFullName.get(method.getParameter(0).getType().toString())), "arg");

            // return GetALevelUpdateV3Result.fromAPI(aLevelV3Service.getALevelUpdate(arg.toAPI()));
            JBlock body = methodImpl.body();
            JInvocation fromAPI = returnType.staticInvoke("fromAPI");
            JInvocation invokeMethod = field.invoke(method.getNameAsString());
            invokeMethod.arg(methodImpl.params().get(0).invoke("toAPI"));
            fromAPI.arg(invokeMethod);
            body._return(fromAPI);
        }

        cm.build(serviceOutputPath.toFile());
    }

    public static void gen(File file, Consumer<CompilationUnit> dealGen) throws IOException {
        CompilationUnit cu = JavaParser.parse(file);

        dealGen.accept(cu);

        String code = cu.toString();
        code = code.replaceAll("import " + moduleInputPackage, "import " + moduleOutputPackage);
        code = code.replaceAll("package " + moduleInputPackage, "package " + moduleOutputPackage);

        String outputPath = file.getAbsolutePath().replace(moduleInputPathStr, moduleOutputPathStr);
        FileUtils.write(new File(outputPath), code, Charset.defaultCharset());
    }

    public static void genArgConverter(CompilationUnit cu) {
        TypeDeclaration<?> mainType = cu.getType(0);
        if (mainType == null) return;
        if (GeneratorUtil.isInterface(mainType)) return;

        ClassOrInterfaceType selfType = new ClassOrInterfaceType(mainType.getNameAsString());
        String fullDTOTypeName = cu.getPackage().get().getNameAsString() + "." + mainType.getNameAsString();
        ClassOrInterfaceType fullDTOType = new ClassOrInterfaceType(fullDTOTypeName);

        EnumSet<Modifier> modifiers = EnumSet.of(Modifier.PUBLIC);
        MethodDeclaration methodDeclaration = new MethodDeclaration(modifiers, fullDTOType, "toAPI");

        BlockStmt block = new BlockStmt();
        methodDeclaration.setBody(block);

        // 声明DTO
        VariableDeclarator dto = new VariableDeclarator(new VariableDeclaratorId("dto"), new ObjectCreationExpr(null, fullDTOType, new NodeList<>()));
        VariableDeclarationExpr variableDeclarationExpr = new VariableDeclarationExpr(fullDTOType, dto);
        block.addStatement(variableDeclarationExpr);

        // 执行赋值
        List<FieldDeclaration> fields = mainType.getFields();
        for (FieldDeclaration field : fields) {
            VariableDeclarator variable = field.getVariable(0);
            if (variable != null) {
                genFieldAssign(FieldType.Arg, cu, block, variable, "dto", "this");
            }
        }

        // 返回DTO
        ReturnStmt returnStmt = new ReturnStmt(new NameExpr("dto"));
        block.addStatement(returnStmt);

        mainType.addMember(methodDeclaration);
    }

    public static void genResultConverter(CompilationUnit cu) {
        TypeDeclaration<?> mainType = cu.getType(0);
        if (mainType == null) return;
        if (!(mainType instanceof ClassOrInterfaceDeclaration)) return;

        ClassOrInterfaceDeclaration mainClassOrInterfaceType = (ClassOrInterfaceDeclaration) mainType;
        if (mainClassOrInterfaceType.isInterface()) return;

        ClassOrInterfaceType selfType = new ClassOrInterfaceType(mainType.getNameAsString());
        String fullDTOTypeName = cu.getPackage().get().getNameAsString() + "." + mainType.getNameAsString();
        ClassOrInterfaceType fullDTOType = new ClassOrInterfaceType(fullDTOTypeName);

        //public static Department fromAPI(com.facishare.organization.api.model.department.Department dto)
        EnumSet<Modifier> modifiers = EnumSet.of(Modifier.PUBLIC, Modifier.STATIC);
        NodeList<Parameter> parameters = new NodeList<>();
        parameters.add(new Parameter(fullDTOType, "dto"));
        MethodDeclaration methodDeclaration = new MethodDeclaration(modifiers, selfType, "fromAPI", parameters);

        BlockStmt block = new BlockStmt();
        methodDeclaration.setBody(block);

        // 如果dto为null，返回null
        BlockStmt ifBlock = new BlockStmt();
        ifBlock.addStatement(new ReturnStmt(new NameExpr("null")));
        IfStmt ifStmt = new IfStmt(new BinaryExpr(new NameExpr("dto"), new NameExpr("null"), BinaryExpr.Operator.EQUALS), ifBlock, null);
        block.addStatement(ifStmt);

        // 声明result
        GeneratorUtil.genCreateObjectAndAssign(cu, block, selfType, "result");

        // 执行赋值
        List<FieldDeclaration> fields = mainType.getFields();
        for (FieldDeclaration field : fields) {
            VariableDeclarator variable = field.getVariable(0);
            if (variable != null) {
                genFieldAssign(FieldType.Result, cu, block, variable, "result", "dto");
            }
        }

        // 返回DTO
        ReturnStmt returnStmt = new ReturnStmt(new NameExpr("result"));
        block.addStatement(returnStmt);

        mainType.addMember(methodDeclaration);
    }

    public static void genTypeConverter(CompilationUnit cu) {
        TypeDeclaration<?> mainClassType = cu.getType(0);
        if (mainClassType == null) return;

        if (GeneratorUtil.isInterface(mainClassType)) return;

        //如果是枚举
        if (GeneratorUtil.isEnum(mainClassType)) {
            genEnumConverter(cu);
            return;
        }

        ClassOrInterfaceType selfType = new ClassOrInterfaceType(mainClassType.getNameAsString());
        String fullDTOTypeName = cu.getPackage().get().getNameAsString() + "." + mainClassType.getNameAsString();
        ClassOrInterfaceType fullDTOType = new ClassOrInterfaceType(fullDTOTypeName);

        // public static Department fromAPI(com.facishare.organization.api.model.department.Department dto)
        EnumSet<Modifier> modifiers = EnumSet.of(Modifier.PUBLIC, Modifier.STATIC);
        NodeList<Parameter> parameters = new NodeList<>();
        parameters.add(new Parameter(fullDTOType, "dto"));
        MethodDeclaration methodDeclaration = new MethodDeclaration(modifiers, selfType, "fromAPI", parameters);

        BlockStmt block = new BlockStmt();
        methodDeclaration.setBody(block);

        // 如果dto为null，返回null
        BlockStmt ifBlock = new BlockStmt();
        ifBlock.addStatement(new ReturnStmt(new NameExpr("null")));
        IfStmt ifStmt = new IfStmt(new BinaryExpr(new NameExpr("dto"), new NameExpr("null"), BinaryExpr.Operator.EQUALS), ifBlock, null);
        block.addStatement(ifStmt);

        // 声明result
        GeneratorUtil.genCreateObjectAndAssign(cu, block, selfType, "result");

        // 执行赋值
        List<FieldDeclaration> fields = mainClassType.getFields();
        for (FieldDeclaration field : fields) {
            VariableDeclarator variable = field.getVariable(0);
            if (variable != null) {
                genFieldAssign(FieldType.Type_FromAPI, cu, block, variable, "result", "dto");
            }
        }

        // 返回DTO
        ReturnStmt returnStmt = new ReturnStmt(new NameExpr("result"));
        block.addStatement(returnStmt);

        // fromAPIList
        MethodDeclaration fromAPIListMethod = GeneratorUtil.genFromAPIListMethod(selfType, fullDTOType);

        // toAPI     public com.facishare.organization.api.model.departmentmember.EmployeeMainDepartment toAPI(){
        EnumSet<Modifier> toAPImodifiers = EnumSet.of(Modifier.PUBLIC, Modifier.STATIC);
        MethodDeclaration toAPIMethod = new MethodDeclaration(toAPImodifiers, fullDTOType, "toAPI");
        toAPIMethod.addParameter(selfType, "arg");
        BlockStmt toAPIBody = new BlockStmt();
        toAPIMethod.setBody(toAPIBody);

        // 声明result
        GeneratorUtil.genCreateObjectAndAssign(cu, toAPIBody, fullDTOType, "result");

        // 执行赋值
        fields = mainClassType.getFields();
        for (FieldDeclaration field : fields) {
            VariableDeclarator variable = field.getVariable(0);
            if (variable != null) {
                genFieldAssign(FieldType.Type_ToAPI, cu, toAPIBody, variable, "result", "arg");
            }
        }

        // 返回DTO
        returnStmt = new ReturnStmt(new NameExpr("result"));
        toAPIBody.addStatement(returnStmt);

        // toAPIList
        MethodDeclaration toAPIListMethod = GeneratorUtil.genToAPIListMethod(selfType, fullDTOType);

        // add import 
        cu.addImport("java.util.stream.Collectors");
        cu.addImport("java.util.List");

        mainClassType.addMember(methodDeclaration);
        mainClassType.addMember(fromAPIListMethod);
        mainClassType.addMember(toAPIMethod);
        mainClassType.addMember(toAPIListMethod);
    }


    public static void genEnumConverter(CompilationUnit cu) {
        EnumDeclaration mainEnumType = (EnumDeclaration) cu.getType(0);
        if (mainEnumType == null) return;

        ClassOrInterfaceType selfType = new ClassOrInterfaceType(mainEnumType.getNameAsString());
        String fullDTOTypeName = cu.getPackage().get().getNameAsString() + "." + mainEnumType.getNameAsString();
        ClassOrInterfaceType fullDTOType = new ClassOrInterfaceType(fullDTOTypeName);

        // fromAPI
        MethodDeclaration fromAPIMethod = GeneratorUtil.genPublicStaticMethodDeclaration("fromAPI", selfType, fullDTOType, "dto");
        BlockStmt fromAPIListMethodBlock = fromAPIMethod.getBody().get();

        NodeList<SwitchEntryStmt> nodeList = new NodeList<>();
        NodeList<EnumConstantDeclaration> entries = mainEnumType.getEntries();
        for (EnumConstantDeclaration entry : entries) {
            String name = entry.getNameAsString();
            NodeList<Statement> swtichEntryStmtList = new NodeList<>();
            swtichEntryStmtList.add(new ReturnStmt(new NameExpr(name)));
            SwitchEntryStmt switchEntryStmt = new SwitchEntryStmt(new NameExpr(name), swtichEntryStmtList);
            nodeList.add(switchEntryStmt);
        }

        SwitchStmt switchStmt = new SwitchStmt(null, new NameExpr("dto"), nodeList);
        fromAPIListMethodBlock.addStatement(switchStmt);
        fromAPIListMethodBlock.addStatement(new ReturnStmt("null"));

        // fromAPIList
        MethodDeclaration fromAPIListMethod = GeneratorUtil.genFromAPIListMethod(selfType, fullDTOType);

        // toAPI
        MethodDeclaration toAPIMethod = GeneratorUtil.genPublicStaticMethodDeclaration("toAPI", fullDTOType, selfType, "arg");
        BlockStmt toAPIListMethodBlock = toAPIMethod.getBody().get();

        // toAPIList
        MethodDeclaration toAPIListMethod = GeneratorUtil.genToAPIListMethod(selfType, fullDTOType);

        nodeList = new NodeList<>();
        entries = mainEnumType.getEntries();
        for (EnumConstantDeclaration entry : entries) {
            String name = entry.getNameAsString();
            NodeList<Statement> swtichEntryStmtList = new NodeList<>();
            swtichEntryStmtList.add(new ReturnStmt(new NameExpr(cu.getPackage().get().getNameAsString() + "." + mainEnumType.getNameAsString() + "." + name)));
            SwitchEntryStmt switchEntryStmt = new SwitchEntryStmt(new NameExpr(name), swtichEntryStmtList);
            nodeList.add(switchEntryStmt);
        }

        switchStmt = new SwitchStmt(null, new NameExpr("arg"), nodeList);
        toAPIListMethodBlock.addStatement(switchStmt);
        toAPIListMethodBlock.addStatement(new ReturnStmt("null"));

        mainEnumType.addMember(fromAPIMethod);
        mainEnumType.addMember(fromAPIListMethod);
        mainEnumType.addMember(toAPIMethod);
        mainEnumType.addMember(toAPIListMethod);

        // add import 
        cu.addImport("java.util.stream.Collectors");
        cu.addImport("java.util.List");
    }

    public static String genSetter(String field, String type) {
        if (type.equals("boolean")) {
            field = field.replaceFirst("^is", "");
        }
        return "set" + StringUtils.capitalize(field);
    }

    public static String genGetter(String field, String type) {
        if (type.equals("boolean")) {
            if (field.startsWith("is")) return field;
            else return "is" + StringUtils.capitalize(field);
        }
        return "get" + StringUtils.capitalize(field);
    }

    public static String lowerFirstLetter(String str) {
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    public static boolean isPrimitiveWrapType(String type) {
        String[] primitiveWrap = {"Integer", "String", "Double", "Float"};
        return Arrays.binarySearch(primitiveWrap, type) >= 0;
    }

    // left.setXX(dto.getXX())
    // left.setXX(InnerType.fromAPIList(dto.getXX()))

    enum FieldType {
        Arg, Result, Type_FromAPI, Type_ToAPI
    }

    public static void genFieldAssign(FieldType fieldType, CompilationUnit cu, BlockStmt block, VariableDeclarator variable, String left, String right) {
        Type type = variable.getType();
        NameExpr leftExpr = new NameExpr(left);
        NameExpr rightExpr = new NameExpr(right);
        String rightGetter = genGetter(variable.getIdentifier().getNameAsString(), type.toString());

        String fromTo = "";
        if (fieldType == FieldType.Arg || fieldType == FieldType.Type_ToAPI) {
            fromTo = "to";
        } else {
            fromTo = "from";
        }

        // right.getXX()
        MethodCallExpr getCallExpr = new MethodCallExpr(rightExpr, rightGetter);
        NodeList<Expression> rightGetAsNodeList = new NodeList<>();
        rightGetAsNodeList.add(getCallExpr);

        NodeList<Expression> arguments = new NodeList<>();
        if (type instanceof PrimitiveType || type.toString().equals("Boolean") || type.toString().equals("Integer") || type.toString().equals("Character")) {
            // get子句
            MethodCallExpr getCall = new MethodCallExpr(rightExpr, rightGetter);
            arguments.add(getCall);
        } else if (type instanceof ClassOrInterfaceType) {
            ClassOrInterfaceType classOrInterfaceType = (ClassOrInterfaceType) type;
            String typeNameStr = classOrInterfaceType.getNameAsString();

            if (isPrimitiveWrapType(typeNameStr)) {
                // get子句
                MethodCallExpr getCall = new MethodCallExpr(rightExpr, rightGetter);
                arguments.add(getCall);
            } else if (typeNameStr.equals("List") || typeNameStr.equals("Collection")) {
                if (classOrInterfaceType.getTypeArguments().isPresent()) {
                    ClassOrInterfaceType innerType = (ClassOrInterfaceType) classOrInterfaceType.getTypeArguments().get().get(0);
                    if (isPrimitiveWrapType(innerType.getNameAsString())) {
                        NameExpr tempListExpr = new NameExpr(variable.getIdentifier().getNameAsString() + "Temp");
                        GeneratorUtil.genCreateListAndAssign(cu, block, innerType, tempListExpr.getNameAsString());


                        MethodCallExpr addAllExpr = new MethodCallExpr(tempListExpr, new SimpleName("addAll"), rightGetAsNodeList);
                        BlockStmt ifBlock = new BlockStmt();
                        ifBlock.addStatement(addAllExpr);
                        IfStmt ifStmt = new IfStmt(new BinaryExpr(getCallExpr, new NameExpr("null"), BinaryExpr.Operator.NOT_EQUALS), ifBlock, null);
                        block.addStatement(ifStmt);
                        arguments.add(tempListExpr);
                    } else {
                        // left.setXX(InnerType.fromAPIList(dto.getXX()))
                        String prefix = "to";
                        if (fieldType == FieldType.Type_FromAPI || fieldType == FieldType.Result) {
                            prefix = "from";
                        }

                        MethodCallExpr fromAPIListExpr = new MethodCallExpr(new NameExpr(innerType.getNameAsString()), new SimpleName(prefix + "APIList"), rightGetAsNodeList);
                        arguments.add(fromAPIListExpr);
                    }
                }
            } else if (typeNameStr.equals("Map")) {
                if (classOrInterfaceType.getTypeArguments().isPresent()) {
                    NodeList<Type<?>> types = classOrInterfaceType.getTypeArguments().get();
                    ClassOrInterfaceType leftType = (ClassOrInterfaceType) types.get(0);
                    ClassOrInterfaceType rightType = (ClassOrInterfaceType) types.get(1);

                    MethodCallExpr mapConverter = GeneratorUtil.getMapConverter(cu, block, new MethodCallExpr(rightExpr, rightGetter), classOrInterfaceType, leftType, rightType);
                    arguments.add(mapConverter);
                }
            } else {
                TypeExpr typeExpr = new TypeExpr(null, type);
                MethodCallExpr fromAPIExpr = new MethodCallExpr(typeExpr, new SimpleName(fromTo + "API"), rightGetAsNodeList);
                arguments.add(fromAPIExpr);
            }
        }

        // set子句
        MethodCallExpr setCall = new MethodCallExpr(leftExpr, new SimpleName(genSetter(variable.getIdentifier().getNameAsString(), type.toString())), arguments);
        block.addStatement(setCall);
    }
}
