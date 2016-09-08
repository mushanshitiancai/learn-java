import com.google.protobuf.InvalidProtocolBufferException;
import io.protostuff.JsonIOUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * Created by mazhibin on 16/9/2
 */
public class ProtoStuff {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Person p = new Person("mushan",20);

        LinkedBuffer buffer = LinkedBuffer.allocate();
        Schema<Person> schema = RuntimeSchema.getSchema(Person.class);

        byte[] protobuf = ProtobufIOUtil.toByteArray(p, schema, buffer);
        System.out.println(protobuf.length);

        Person person = schema.newMessage();
        ProtobufIOUtil.mergeFrom(protobuf,person,schema);
        System.out.println(person);

//        In.Person person1 = In.Person.parseFrom(protobuf);
//        System.out.println(person1);

//        buffer.clear();
//        byte[] protostuff = ProtostuffIOUtil.toByteArray(p, schema, buffer);
//        System.out.println(protostuff.length);
//
//        Person person2 = schema.newMessage();
//        ProtobufIOUtil.mergeFrom(protostuff,person2,schema);
//        System.out.println(person);

        buffer.clear();
        byte[] bytes = JsonIOUtil.toByteArray(p, schema, true, buffer);
        System.out.println(bytes.length);
        System.out.println(new String(bytes));
    }
}
