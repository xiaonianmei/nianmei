package org.liufeng.weixin.util.trans;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */
public class ShowapiResBody {
    private Integer ret_code;
    private Boolean flag;
    private List<Trans> expressList;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getRet_code() {
        return ret_code;
    }

    public void setRet_code(Integer ret_code) {
        this.ret_code = ret_code;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public List<Trans> getExpressList() {
        return expressList;
    }

    public void setExpressList(List<Trans> expressList) {
        this.expressList = expressList;
    }
}
