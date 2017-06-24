package org.liufeng.weixin.util.trans;

/**
 * Created by Administrator on 2017/6/1.
 */
public class Status {
    private Integer showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBody showapi_res_body;

    public Integer getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(Integer showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBody getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBody showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }
}
