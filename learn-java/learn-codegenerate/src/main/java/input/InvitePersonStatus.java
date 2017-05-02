package input;

/**
 * Created by chenxiaobin on 2017/3/29.
 */
public enum InvitePersonStatus {
    PENDING_REVIEW(1, "待审核"),
    AGREE(2, "同意"),
    REFUSE(3, "拒绝"),
    FREE_AUDIT_CREATE(4, "无需审批已创建"),
    FREE_AUDIT_STOP(5, "无需审批已停用"),;

    private int code;
    private String explain;

    public int getCode() {
        return code;
    }

    InvitePersonStatus(int code, String explain) {
        this.code = code;
        this.explain = explain;

    }

    public static InvitePersonStatus valueOf(int tag) {
        switch (tag) {
            case 1:
                return PENDING_REVIEW;
            case 2:
                return AGREE;
            case 3:
                return REFUSE;
            case 4:
                return FREE_AUDIT_CREATE;
            case 5:
                return FREE_AUDIT_STOP;
            default:
                throw new IllegalArgumentException("InvitePersonStatus not fount type= " + tag);
        }
    }

}
