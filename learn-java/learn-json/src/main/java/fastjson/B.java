package fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by mazhibin on 17/4/7
 */
@Data
public class B {
    @JSONField(name = "EmployeeId")
    private int employeeId;

    @JSONField(name = "IsPauseLogin")
    private boolean isPauseLogin;

    @JSONField(name = "TimeStamp")
    private long timeStamp;

}