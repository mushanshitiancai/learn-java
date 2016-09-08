package enumtest;

/**
 * Created by mazhibin on 16/9/7
 */
public class TypeBase {

    public static <T extends Enum<T>> T fromValue(Class<T> enumType,int value){
        T[] enumConstants = enumType.getEnumConstants();
        for (T enumConstant : enumConstants) {
            if(enumConstant instanceof EntityTypeFieldEnum){
                EntityTypeFieldEnum type = (EntityTypeFieldEnum)enumConstant;
                if(type.equalToValue(value)){
                    return enumConstant;
                }
            }
        }
        throw new IllegalArgumentException();
    }
}
