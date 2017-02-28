package util;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.UnknownType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mazhibin on 16/12/14
 */
public class GeneratorUtil {

    public static Map<String, String> getTypeFullName(String path) throws IOException {
        Map<String, String> fullNameMap = new HashMap<>();

        Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String nameWithoutExtension = com.google.common.io.Files.getNameWithoutExtension(file.toString());
                String content = FileUtils.readFileToString(file.toFile(), "utf-8");
                Matcher matcher = Pattern.compile("package ([\\w.]+);").matcher(content);
                if (matcher.find()) {
                    String packageName = matcher.group(1);

                    fullNameMap.put(nameWithoutExtension, packageName + "." + nameWithoutExtension);
                }

                return FileVisitResult.CONTINUE;
            }
        });
        
        return fullNameMap;
    }

    public static boolean isClass(TypeDeclaration<?> typeDeclaration) {
        if (typeDeclaration instanceof ClassOrInterfaceDeclaration) {
            if (((ClassOrInterfaceDeclaration) typeDeclaration).isInterface()) return false;
        }
        return true;
    }

    public static boolean isInterface(TypeDeclaration<?> typeDeclaration) {
        if (typeDeclaration instanceof ClassOrInterfaceDeclaration) {
            if (((ClassOrInterfaceDeclaration) typeDeclaration).isInterface()) return true;
        }
        return false;
    }

    public static boolean isEnum(TypeDeclaration<?> typeDeclaration) {
        if (typeDeclaration instanceof EnumDeclaration) {
            return true;
        }
        return false;
    }

    // type<innerType>
    public static ClassOrInterfaceType getGenericObjectType(String type, Type<?> innerType) {
        NodeList<Type<?>> typeArguments = new NodeList<Type<?>>();
        typeArguments.add(innerType);
        return new ClassOrInterfaceType(null, null, new SimpleName(type), typeArguments);
    }

    // aClass name = new aClass();
    public static void genCreateObjectAndAssign(CompilationUnit cu, BlockStmt block, ClassOrInterfaceType type, String name) {
        VariableDeclaratorId id = new VariableDeclaratorId(name);
        ObjectCreationExpr objectCreationExpr = new ObjectCreationExpr(null, type, new NodeList<>());
        VariableDeclarator result = new VariableDeclarator(id, objectCreationExpr);
        VariableDeclarationExpr variableDeclarationExpr = new VariableDeclarationExpr(type, result);
        block.addStatement(variableDeclarationExpr);
    }

    // List<T> name = new ArrayList<T>();
    public static void genCreateListAndAssign(CompilationUnit cu, BlockStmt block, Type<?> innerType, String name) {
        VariableDeclaratorId id = new VariableDeclaratorId(name);

        NodeList<Type<?>> typeArguments = new NodeList<Type<?>>() {{
            add(innerType);
        }};
        ClassOrInterfaceType listType = new ClassOrInterfaceType(null, null, new SimpleName("List"), typeArguments);
        ClassOrInterfaceType arrayListType = new ClassOrInterfaceType(null, null, new SimpleName("ArrayList"), typeArguments);

        ObjectCreationExpr objectCreationExpr = new ObjectCreationExpr(null, arrayListType, new NodeList<>());
        VariableDeclarator result = new VariableDeclarator(id, objectCreationExpr);
        VariableDeclarationExpr variableDeclarationExpr = new VariableDeclarationExpr(listType, result);
        block.addStatement(variableDeclarationExpr);

        cu.addImport("java.util.List");
        cu.addImport("java.util.ArrayList");
    }

    public static MethodDeclaration genPublicStaticMethodDeclaration(String name, Type<?> resultType, Type<?> argType, String argName) {
        EnumSet<Modifier> modifiers = EnumSet.of(Modifier.PUBLIC, Modifier.STATIC);
        NodeList<Parameter> parameters = new NodeList<>();
        if (argType != null) {
            parameters.add(new Parameter(argType, argName));
        }
        MethodDeclaration methodDeclaration = new MethodDeclaration(modifiers, resultType, name, parameters);

        BlockStmt block = new BlockStmt();
        methodDeclaration.setBody(block);

        return methodDeclaration;
    }

    public static MethodDeclaration genPublicMethodDeclaration(String name, Type<?> resultType, Type<?> argType, String argName) {
        EnumSet<Modifier> modifiers = EnumSet.of(Modifier.PUBLIC);
        NodeList<Parameter> parameters = new NodeList<>();
        if (argType != null) {
            parameters.add(new Parameter(argType, argName));
        }
        MethodDeclaration methodDeclaration = new MethodDeclaration(modifiers, resultType, name, parameters);

        BlockStmt block = new BlockStmt();
        methodDeclaration.setBody(block);

        return methodDeclaration;
    }

    //public static List<SimpleDepartment> fromAPIList(List<com.facishare.organization.api.model.department.SimpleDepartment> dto) {
    //  return dto.stream().map(SimpleDepartment::fromAPI).collect(Collectors.toList());
    //}
    public static MethodDeclaration genFromAPIListMethod(ClassOrInterfaceType selfType, ClassOrInterfaceType fullDTOType) {
        // public static List<Department> fromAPIList(List<com.facishare.organization.api.model.department.Department> dto) {
        MethodDeclaration fromAPIListMethod = GeneratorUtil.genPublicStaticMethodDeclaration("fromAPIList", GeneratorUtil.getGenericObjectType("List", selfType), GeneratorUtil.getGenericObjectType("List", fullDTOType), "dto");
        BlockStmt fromAPIListMethodBlock = fromAPIListMethod.getBody().get();

        //  return dto.stream().map(Department::fromAPI).collect(Collectors.toList());
        MethodCallExpr streamCall = new MethodCallExpr(new NameExpr("dto"), "stream");
        NodeList<Expression> mapArguments = new NodeList<>();
        mapArguments.add(new MethodReferenceExpr(null, new NameExpr(selfType.getName()), null, "fromAPI"));
        MethodCallExpr mapCall = new MethodCallExpr(streamCall, new SimpleName("map"), mapArguments);
        MethodCallExpr toListCall = new MethodCallExpr(new NameExpr("Collectors"), "toList");
        NodeList<Expression> collectArguments = new NodeList<>();
        collectArguments.add(toListCall);
        MethodCallExpr collectCall = new MethodCallExpr(mapCall, new SimpleName("collect"), collectArguments);

        ReturnStmt returnStmt = new ReturnStmt(collectCall);
        fromAPIListMethodBlock.addStatement(returnStmt);

        return fromAPIListMethod;
    }


//    public static List<com.facishare.organization.api.model.departmentmember.EmployeeMainDepartment> toAPIList(List<EmployeeMainDepartment> args){
//        return args.stream().map(EmployeeMainDepartment::toAPI).collect(Collectors.toList());
//    }
    public static MethodDeclaration genToAPIListMethod(ClassOrInterfaceType selfType, ClassOrInterfaceType fullDTOType) {
        // public static List<Department> fromAPIList(List<com.facishare.organization.api.model.department.Department> dto) {
        MethodDeclaration toAPIListMethod = GeneratorUtil.genPublicStaticMethodDeclaration("toAPIList", GeneratorUtil.getGenericObjectType("List", fullDTOType), GeneratorUtil.getGenericObjectType("List", selfType), "args");
        BlockStmt fromAPIListMethodBlock = toAPIListMethod.getBody().get();

        //  return dto.stream().map(Department::fromAPI).collect(Collectors.toList());
        MethodCallExpr streamCall = new MethodCallExpr(new NameExpr("args"), "stream");
        NodeList<Expression> mapArguments = new NodeList<>();
        mapArguments.add(new MethodReferenceExpr(null, new NameExpr(selfType.getName()), null, "toAPI"));
        MethodCallExpr mapCall = new MethodCallExpr(streamCall, new SimpleName("map"), mapArguments);
        MethodCallExpr toListCall = new MethodCallExpr(new NameExpr("Collectors"), "toList");
        NodeList<Expression> collectArguments = new NodeList<>();
        collectArguments.add(toListCall);
        MethodCallExpr collectCall = new MethodCallExpr(mapCall, new SimpleName("collect"), collectArguments);

        ReturnStmt returnStmt = new ReturnStmt(collectCall);
        fromAPIListMethodBlock.addStatement(returnStmt);

        return toAPIListMethod;
    }
    
    
    

    // Map<Integer, List<SimpleDepartment>> collect = dto.getDepartmentMap().entrySet().stream().collect(Collectors.toMap(o -> o.getKey(), o -> SimpleDepartment.fromAPIList(o.getValue())));
    // result.setDepartmentMap(dto.getDepartmentMap().entrySet().stream().collect(Collectors.toMap(o -> o.getKey(), o -> SimpleDepartment.fromAPI(o.getValue()))));
    public static MethodCallExpr getMapConverter(CompilationUnit cu, BlockStmt block, Expression getExpression, ClassOrInterfaceType type, ClassOrInterfaceType leftType, ClassOrInterfaceType rightType) {
//        VariableDeclaratorId id = new VariableDeclaratorId(name);
//
//        NodeList<Type<?>> typeArguments = new NodeList<Type<?>>() {{
//            add(leftType);
//            add(rightType);
//        }};
//        ClassOrInterfaceType listType = new ClassOrInterfaceType(null, null, new SimpleName("Map"), typeArguments);
//        ClassOrInterfaceType arrayListType = new ClassOrInterfaceType(null, null, new SimpleName("HashMap"), typeArguments);
//
//        ObjectCreationExpr objectCreationExpr = new ObjectCreationExpr(null, arrayListType, new NodeList<>());
//        VariableDeclarator result = new VariableDeclarator(id, objectCreationExpr);
//        VariableDeclarationExpr variableDeclarationExpr = new VariableDeclarationExpr(listType, result);
//        block.addStatement(variableDeclarationExpr);

        NodeList<Expression> arguments = new NodeList<>();

        // o -> o.getKey()
        arguments.add(new LambdaExpr(null, new NodeList<Parameter>() {{
            add(new Parameter(new UnknownType(), "o"));
        }}, new ExpressionStmt(new MethodCallExpr(new NameExpr("o"), "getKey")), false));

        if (rightType.getNameAsString().equals("List")) {
            // o -> SimpleDepartment.fromAPIList(o.getValue())
            NodeList<Type<?>> types = rightType.getTypeArguments().get();
            ClassOrInterfaceType innerType = (ClassOrInterfaceType) types.get(0);

            arguments.add(new LambdaExpr(null, new NodeList<Parameter>() {{
                add(new Parameter(new UnknownType(), "o"));
            }}, new ExpressionStmt(new MethodCallExpr(new NameExpr(innerType.getNameAsString()), new SimpleName("fromAPIList"), new NodeList<Expression>() {{
                add(new MethodCallExpr(new NameExpr("o"), "getValue"));
            }})), false));
        } else {
            // o -> SimpleDepartment.fromAPI(o.getValue())
            arguments.add(new LambdaExpr(null, new NodeList<Parameter>() {{
                add(new Parameter(new UnknownType(), "o"));
            }}, new ExpressionStmt(new MethodCallExpr(new NameExpr(rightType.getNameAsString()), new SimpleName("fromAPI"), new NodeList<Expression>() {{
                add(new MethodCallExpr(new NameExpr("o"), "getValue"));
            }})), false));
        }


        MethodCallExpr mapConverterCallExpr = new MethodCallExpr(new MethodCallExpr(new MethodCallExpr(getExpression, "entrySet"), "stream"), new SimpleName("collect"), new NodeList<Expression>() {{
            add(new MethodCallExpr(new NameExpr("Collectors"), new SimpleName("toMap"), arguments));
        }});

        cu.addImport("java.util.Map");
        cu.addImport("java.util.HashMap");
        cu.addImport("java.util.stream.Collectors");

        return mapConverterCallExpr;
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
}
