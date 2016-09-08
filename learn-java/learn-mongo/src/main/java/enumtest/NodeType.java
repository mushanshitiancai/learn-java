package enumtest;

import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

/**
 * Created by mazhibin on 16/9/7
 */
public enum NodeType implements EntityTypeFieldEnum<NodeType>{
    Folder(1), File(2);

    private int value;

    NodeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean equalToValue(int value) {
        return this.value == value;
    }

//    public static NodeType fromValue(int value){
//        switch (value){
//            case 1:return Folder;
//            case 2:return File;
//            default: throw new IllegalArgumentException("illegal NodeType value: "+value);
//        }
//    }

    public static class Converter extends TypeConverter{

        @Override
        public Object decode(final Class targetClass, final Object fromDBObject, final MappedField optionalExtraInfo) {
            if (fromDBObject == null) {
                return null;
            }

            return TypeBase.fromValue(targetClass,Integer.valueOf(fromDBObject.toString()));
        }

        @Override
        public Object encode(final Object value, final MappedField optionalExtraInfo) {
            if (value == null) {
                return null;
            }

            return ((NodeType) value).getValue();
        }

        @Override
        protected boolean isSupported(final Class c, final MappedField optionalExtraInfo) {
            return c.isEnum();
        }

    }
}
