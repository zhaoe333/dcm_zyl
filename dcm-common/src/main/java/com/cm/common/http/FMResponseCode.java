package com.cm.common.http;

/**
 * 响应码
 * 200为正常响应
 * 1XX为业务响应码
 * 3XX为通用响应码
 * 4XX为系统异常响应码
 *
 * @author yunlu
 */
public enum FMResponseCode {
    //系统操作
    normal("200", "Successful.", "操作成功"),
    syserror("400", "System is busy!", "系统繁忙"),

    //用户相关
    accountnotexist("101", "User does not exist.", "用户不存在"),
    accountremoved("102", "User has been disabled.", "用户已停用"),
    passworderror("103", "Account or password error.", "账号或密码错误"),
    noroleforaccount("104", "User has no role.", "用户没有任何的角色"),
    propertyUsed("105", "This value has been taken.", "该值已被占用"),
    infonotmatch("106", "Information do not match", "信息不匹配"),
    choiceUserError("107", "Please select the user", "请选择用户"),
//    fiveresult("108", "已经有五条记录", "已经有五条记录"),
//    hasNoId("108", "has no user id!", "没有用户id"),
//    isBlacked("109", "has added black list", "用户被加入黑名单，请联系管理员"),

    //操作相关
    paramserror("301", "Requested parameter error!", "请求参数错误"),
    tokenerror("302", "No Token!", "无token"),
    tokenfail("303", "Token validation failed!", "token验证失败"),
    adminError("304", "permission restrictions!", "超级管理员权限限制"),
    resourceError("305", "No permission", "没有该功能权限"),
    invalidlink("306", "Invalid link", "无效的链接"),

    // 经销商相关
    invalidId("310", "Invalid ID", "ID不存在"),
    multiRules("320", "Please choose a dealer with the same rules", "所选经销商包含多个规则"),
    repeatSubmitDuringApproval("321", "Do not repeat operation during approval", "重复提交申请"),

    // 审批相关,
    processAlreadyStarted("330", "Process already started", "已有流程发起"),
    notRepeatOperation("331","Do not repeat opration during approval","在审批期间不可重复提交"),
    invalidProcessStatus("332", "Invalid process status", "无效的审批状态"),

    //文件相关
    filetimeout("316", "File was invalid!", "文件已失效"),

    repeatsubmit("888888", "repeat data", "操作频繁，请稍后再试"),
    ttttt("999999", "not allowed word!", "放到最后方便");


    private String code;
    private String msg;
    private String cnMsg;

    private FMResponseCode(String code, String msg, String cnMsg) {
        this.code = code;
        this.msg = msg;
        this.cnMsg = cnMsg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getCnMsg() {
        return this.cnMsg;
    }

    public String toString() {
        return this.code;
    }
}
