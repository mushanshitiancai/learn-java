package nodetest;

import com.mongodb.DBObject;
import lombok.Data;
import org.mongodb.morphia.annotations.*;

/**
 * Created by mazhibin on 16/9/6
 */
@Data
@Entity(value = "node",noClassnameStored = true)
public class Node {

    @Property(value = "name")
    String name;

    @Embedded(value = "fileDetail")
    FileDetail fileDetail;

    @Embedded(value = "folderDetail")
    FolderDetail folderDetail;

    @PreLoad
    void preLoad(DBObject obj){
        String name = (String) obj.get("name");
        if(name.startsWith("file")){
            Object detail = obj.removeField("detail");
            obj.put("fileDetail",detail);
        }else{
            Object detail = obj.removeField("detail");
            obj.put("folderDetail",detail);
        }
    }

    @PreSave
    void preSave(DBObject obj){
        String name = (String) obj.get("name");
        if(name.startsWith("file")){
            Object detail = obj.removeField("fileDetail");
            obj.put("detail",detail);
        }else{
            Object detail = obj.removeField("folderDetail");
            obj.put("detail",detail);
        }
    }


}
