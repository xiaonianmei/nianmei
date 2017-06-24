package org.liufeng.course.bean;

/**
 * Created by Administrator on 2017/5/25.
 */
public class Address {
      private Integer id;
      private String name;
    private String telephone;
    private String province;
    private String address;
    private Integer isor;
    private Integer appUid;
    private String city;
    private String conty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIsor() {
        return isor;
    }

    public void setIsor(Integer isor) {
        this.isor = isor;
    }

    public Integer getAppUid() {
        return appUid;
    }

    public void setAppUid(Integer appUid) {
        this.appUid = appUid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getConty() {
        return conty;
    }

    public void setConty(String conty) {
        this.conty = conty;
    }
}
