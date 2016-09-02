import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * Created by mazhibin on 16/9/1
 */
public class Hello {
    public static void main(String[] args) {
        test3();
    }

    public static void test1(){
        ST hello = new ST("Hello <name>");
        hello.add("name","World");
        System.out.println(hello.render());
    }

    public static void test2(){
        STGroup stGroup = new STGroup();
        stGroup.defineTemplate("thing","name","<name>");
        stGroup.defineTemplate("say","name","Hello <thing(name)>");
        ST st = stGroup.getInstanceOf("say");
        st.add("name","World");
        System.out.println(st.render());
    }

    public static void test3(){
        STGroupFile stGroupFile = new STGroupFile(Hello.class.getResource("/one.stg").getPath());
        ST st = stGroupFile.getInstanceOf("say");
        st.add("name","mushan");
        st.add("name","willing");
        System.out.println(st.render());

        st = stGroupFile.getInstanceOf("introduction");
        st.addAggr("person.{name,age}","mushan",18);
        System.out.println(st.render());
    }
}
