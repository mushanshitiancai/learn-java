package com.mushan.rabbit.demo1.learn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mazhibin on 16/12/5
 */
public class GenEnum {

    public static void main(String[] args) {
        String inputFilePathStr = "";
        
        
        Pattern enumNamePattern = Pattern.compile("enum (\\w+)");
        Pattern enumItemPattern = Pattern.compile("(\\w+)\\((\\d+)\\),");
        StringBuffer result = new StringBuffer();

        Matcher matcher = enumNamePattern.matcher(input);
        if(matcher.find()){
            String enumName = matcher.group(1);
            result.append(String.format("public static %s valueOf(int tag) {\n",enumName));
        }
        result.append("    switch(tag) {\n");

        matcher = enumItemPattern.matcher(input);
        while(matcher.find()){
            String key = matcher.group(1);
            String value = matcher.group(2);

            result.append(String.format("        case %s: return %s;\n",value,key));
        }
        result.append("        default: return null;\n");
        result.append("    }\n");
        result.append("}\n");

        System.out.println(result);
    }
    
    public static String input = "package com.facishare.organization.provider.dao.entity.type;\n" + "\n" + "/**\n" + " * Created by chenxb on 16-10-31.\n" + " */\n" + "public enum FunctionNo implements EnumTypeBase {\n" + "    /**\n" + "     * 公告管理员\n" + "     */\n" + "    PublishAnnouncement(1),\n" + "    /**\n" + "     * 话题管理员\n" + "     */\n" + "    TopicManagement(2),\n" + "    /**\n" + "     * 财务审批管理员\n" + "     */\n" + "    AllApproveManagement(3),\n" + "    /**\n" + "     * 人事审批管理员\n" + "     */\n" + "    HRApproveManagement(4),\n" + "    /**\n" + "     * 指令管理员\n" + "     */\n" + "    FeedWorkManagement(5),\n" + "    /**\n" + "     * 日志管理员\n" + "     */\n" + "    FeedPlanManagement(6),\n" + "    /**\n" + "     * 定位管理员\n" + "     */\n" + "    LocationManagement(7),\n" + "    /**\n" + "     * 促销宝\n" + "     */\n" + "    MarketingAdmin(21),\n" + "    /**\n" + "     * 客户管理员\n" + "     */\n" + "    FCustomerManagement(31),\n" + "    /**\n" + "     * 报数系统管理员\n" + "     */\n" + "    DataReportingManagement(41),\n" + "    /**\n" + "     * 代理商助手系统管理员\n" + "     */\n" + "    CrossAgentAssistantManagement(42),\n" + "    /**\n" + "     * 百川系统管理员\n" + "     */\n" + "    BaichuanManagement(43),\n" + "    /**\n" + "     * 表格管理员\n" + "     */\n" + "    WorkOrderManagement(44),\n" + "    /**\n" + "     * 对话记录查看权限\n" + "     */\n" + "    SearchHistoricalSessionsPermission(45),\n" + "    /**\n" + "     * 微营销管理员\n" + "     */\n" + "    VYXManagement(46),\n" + "    /**\n" + "     * 部门管理员\n" + "     */\n" + "    CircleAndEmployeeManagement(47),\n" + "    /**\n" + "     * 观察员\n" + "     */\n" + "    Observer(48),\n" + "    /**\n" + "     * 外勤签到管理员\n" + "     */\n" + "    CRMLBSManagement(49),\n" + "    /**\n" + "     * 考勤签到管理员\n" + "     */\n" + "    AttendanceManagement(50),\n" + "    /**\n" + "     * 系统管理员\n" + "     */\n" + "    SystemManagement(99),\n" + "    ;\n" + "\n" + "    private int type;\n" + "\n" + "    public static FunctionNo getMobileStatus(int type) {\n" + "        switch (type) {\n" + "            case 1:\n" + "                return PublishAnnouncement;\n" + "            case 2:\n" + "                return TopicManagement;\n" + "            case 3:\n" + "                return AllApproveManagement;\n" + "            case 4:\n" + "                return HRApproveManagement;\n" + "            default:\n" + "                return null;\n" + "        }\n" + "    }\n" + "\n" + "    FunctionNo(int type) {\n" + "        this.type = type;\n" + "    }\n" + "\n" + "    @Override\n" + "    public int getValue() {\n" + "        return type;\n" + "    }\n" + "\n" + "    @Override\n" + "    public boolean equalToValue(int value) {\n" + "        return type == value;\n" + "    }\n" + "}\n";
}
