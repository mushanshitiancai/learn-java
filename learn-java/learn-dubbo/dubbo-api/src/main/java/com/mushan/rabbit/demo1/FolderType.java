package com.mushan.rabbit.demo1;

/**
 * Created by mazhibin on 16/9/6
 */
public enum FolderType {
    CompanyFolder(1),
    PersonalFolder(2),
    SharedFolder(3),
    UNRECOGNIZED(-1);

    private int value;

    private FolderType(int n) {
        this.value = n;
    }

    public int getNumber() {
        if (value == -1) {
            throw new java.lang.IllegalStateException(
                    "Can't get the number of an unknown enum value.");
        }
        return value;
    }

    public static final FolderType valueOf(int tag) {
        switch(tag) {
            case 1: return CompanyFolder;
            case 2: return PersonalFolder;
            case 3: return SharedFolder;
            default: return UNRECOGNIZED;
        }
    }
}
