package com.renshihan.settlement.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum ProjectStatusEnum {
    //募资状态：0-准备募资；1-募资中（正在募资中）；2-募资已满（代表额度用完）；3-募资结束（代表募资时间结束）
    READY(0, 1,"准备募资"),
    PROCESS(1,0, "募资中"),
    FULL(2, 2,"募资已满"),
    END(3,3, "募资结束");
    private Integer code;
    private Integer viewLevel;
    private String message;

    ProjectStatusEnum(int code,int viewLevel, String message) {
        this.code = code;
        this.viewLevel=viewLevel;
        this.message = message;
    }

    public static boolean isFinishStatus(Integer status){
        return END.getCode()==status||FULL.getCode()==status;
    }

    public static ProjectStatusEnum getMaxViewLevelEnum(List<Integer> projectStatus){
        return projectStatus.stream().map(status->{
            for (ProjectStatusEnum value : ProjectStatusEnum.values()) {
                if(value.getCode()==status){
                    return value;
                }
            }
            return null;
        }).reduce((e1,e2)->{
            if(e1.getViewLevel()<e2.getViewLevel()){
                return e1;
            }
            return e2;
        }).get();
    }



}
