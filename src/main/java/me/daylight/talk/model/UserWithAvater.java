package me.daylight.talk.model;

public class UserWithAvater {
    private Integer id;

    private String phone;

    private String name;

    private String nicname;

    private Integer gender;

    private String signature;

    private String address;

    private Long updatetime;

    private byte[] headImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNicname() {
        return nicname;
    }

    public void setNicname(String nicname) {
        this.nicname = nicname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public byte[] getHeadImage() {
        return headImage;
    }

    public void setHeadImage(byte[] headImage) {
        this.headImage = headImage;
    }

    public void setUser(User user){
        this.id=user.getId();
        this.phone=user.getPhone();
        this.name=user.getName();
        this.nicname=user.getNicname();
        this.gender=user.getGender();
        this.signature=user.getSignature();
        this.address=user.getAddress();
        this.updatetime=user.getUpdatetime();
    }
}