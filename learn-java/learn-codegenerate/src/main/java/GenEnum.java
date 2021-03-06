import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mazhibin on 16/12/5
 */
public class GenEnum {

    public static void main(String[] args) throws IOException {
        String inputFilePathStr = "/Users/mazhibin/project/java/learn-java/learn-java/learn-codegenerate/src/main/resources/InvitePersonStatus.java";
        String input = FileUtils.readFileToString(new File(inputFilePathStr), "utf-8");


        Pattern enumNamePattern = Pattern.compile("enum +(\\w+)");
        Pattern enumItemPattern = Pattern.compile("(\\w+)\\((\\d+).*?\\),");
        StringBuffer result = new StringBuffer();

        Matcher matcher = enumNamePattern.matcher(input);
        if (!matcher.find())
            throw new RuntimeException("没找到对应的enumName");

        String enumName = matcher.group(1);
        result.append(String.format("public static %s valueOf(int tag) {\n", enumName));
        result.append("    switch(tag) {\n");

        matcher = enumItemPattern.matcher(input);
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);

            result.append(String.format("        case %s: return %s;\n", value, key));
        }
        result.append("        default: throw new IllegalArgumentException(\"").append(enumName).append(" not fount type= \" + tag );\n");
        result.append("    }\n");
        result.append("}\n");

        System.out.println(result);
    }
}
