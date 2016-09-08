import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

/**
 * Created by mazhibin on 16/9/7
 */
public class MainTest {

    @Test
    public void tableTest(){
        Table<String,String,String> table = HashBasedTable.create();

        table.put("A","1","A1");
        table.put("A","2","A2");
        table.put("B","1","B1");

        System.out.println(table);
    }
}
