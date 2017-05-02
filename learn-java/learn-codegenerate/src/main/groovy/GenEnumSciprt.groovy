import java.util.regex.Matcher
import java.util.regex.Pattern

def filePath = _editor.getVirtualFile().getPath()
def input = new File(filePath).text

Pattern enumNamePattern = Pattern.compile("enum +(\\w+)")
Pattern enumItemPattern = Pattern.compile("(\\w+)\\((\\d+).*?\\),")
StringBuffer result = new StringBuffer()

Matcher matcher = enumNamePattern.matcher(input)
if (!matcher.find())
    throw new RuntimeException("没找到对应的enumName")

String enumName = matcher.group(1)
result.append(String.format("public static %s valueOf(int tag) {\n", enumName))
result.append("    switch(tag) {\n")

matcher = enumItemPattern.matcher(input)
while (matcher.find()) {
    String key = matcher.group(1)
    String value = matcher.group(2)

    result.append(String.format("        case %s: return %s;\n", value, key))
}
result.append("        default: throw new IllegalArgumentException(\"").append(enumName).append(" not fount type= \" + tag );\n")
result.append("    }\n")
result.append("}\n")

result