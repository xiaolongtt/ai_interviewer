package com.xiaolintt.result;

import com.xiaolintt.enums.ResponseStatusEnum;
import lombok.Data;

import java.util.Map;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/15 10:49
 * @注释 通用JSON返回结果类
 */
@Data
public class GraceJSONResult {

    // 响应业务状态码
    private Integer status;

    // 响应消息
    private String msg;

    // 是否成功
    private Boolean success;

    // 响应数据，可以是Object，也可以是List或Map等
    private Object data;


    public static GraceJSONResult Success(){
        GraceJSONResult graceJSONResult=new GraceJSONResult();
        graceJSONResult.setStatus(200);
        graceJSONResult.setSuccess(true);
        graceJSONResult.setMsg("操作成功");
        return graceJSONResult;
    }

    public static GraceJSONResult Success(Object data){
        GraceJSONResult graceJSONResult=new GraceJSONResult();
        graceJSONResult.setStatus(200);
        graceJSONResult.setSuccess(true);
        graceJSONResult.setMsg("操作成功");
        graceJSONResult.setData(data);
        return graceJSONResult;
    }

    public static GraceJSONResult Error(){
        GraceJSONResult graceJSONResult=new GraceJSONResult();
        graceJSONResult.setStatus(400);
        graceJSONResult.setSuccess(false);
        graceJSONResult.setMsg("操作失败");
        return graceJSONResult;
    }

    public static GraceJSONResult ErrorMsg(String msg){
        GraceJSONResult graceJSONResult=new GraceJSONResult();
        graceJSONResult.setStatus(400);
        graceJSONResult.setSuccess(false);
        graceJSONResult.setMsg(msg);
        return graceJSONResult;
    }

    /**
     * 错误返回，map中包含了多条错误信息，可以用于表单或BO对象的校验，把错误统一的全部返回出去
     * @param map
     * @return
     */
    public static GraceJSONResult ErrorMap(Map map){
        GraceJSONResult graceJSONResult=new GraceJSONResult();
        graceJSONResult.setStatus(400);
        graceJSONResult.setSuccess(false);
        graceJSONResult.setMsg("操作失败");
        graceJSONResult.setData(map);
        return graceJSONResult;
    }

    public static GraceJSONResult exception(ResponseStatusEnum responseStatus) {
        GraceJSONResult graceJSONResult=new GraceJSONResult();
        graceJSONResult.setStatus(responseStatus.status());
        graceJSONResult.setSuccess(responseStatus.success());
        graceJSONResult.setMsg(responseStatus.msg());
        return graceJSONResult;
    }
}
