package org.demo.enums;


public enum CrdFrontStatEnum {

    SUCCESS(1, "操作成功"),
    INNER_ERROR(0, "系统异常"),
    UPDATE_FAIL(-1, "修改信息失败"),
    INSERT_FAIL(-2, "添加信息失败");

    private int state;

    private String stateInfo;

    CrdFrontStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static CrdFrontStatEnum stateOf(int index) {
        for (CrdFrontStatEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

}
