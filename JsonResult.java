package com.example.springproject.json;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult implements Serializable {
    /**
     * 状态码 0 表示成功
     */
    private Integer code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 描述
     */
    private String msg;

    public JsonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功，不传⼊数据
     *
     * @return
     */
    public static JsonResult buildSuccess() {
        return new JsonResult(200, null, null);
    }

    /**
     * 成功，传⼊数据
     *
     * @param data
     * @return
     */
    public static JsonResult buildSuccess(Object data) {
        return new JsonResult(200, null, data);
    }

    public static JsonResult buildSuccess(String msg, Object data) {
        return new JsonResult(200, msg, data);
    }

    /**
     * 失败，传⼊描述信息
     *
     * @param msg
     * @return
     */
    public static JsonResult fail(String msg) {
        return new JsonResult(400, msg, null);
    }

    public static JsonResult fail(String msg, Object data) {
        return new JsonResult(400, msg, data);
    }

    public static JsonResult fail(int code, String msg, Object data) {
        return new JsonResult(code, msg, data);
    }


}
