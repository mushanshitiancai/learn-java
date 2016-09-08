package nodetest;

import lombok.Data;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by mazhibin on 16/9/6
 */
@Data
@Entity(value = "node",noClassnameStored = true)
public class AllNode {

    @Property(value = "name")
    String name;

    AllDetail detail;

//    @Embedded(value = "fileDetail")
//    FileDetail fileDetail;
//
//    @Embedded(value = "folderDetail")
//    FolderDetail folderDetail;
//
//    @PreLoad
//    void preLoad(DBObject obj){
//        String name = (String) obj.get("name");
//        if(name.startsWith("file")){
//            Object detail = obj.removeField("detail");
//            obj.put("fileDetail",detail);
//        }
//    }
//
//    @PreSave
//    void preSave(DBObject obj){
//
//    }


}
