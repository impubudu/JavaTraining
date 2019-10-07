package com.example.emsui.model;

public class AssignTask {
    private Integer id;

    private Integer eid;

    private Integer pid;

    private String tid;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "AssignTask{" +
                "id=" + id +
                ", eid=" + eid +
                ", pid=" + pid +
                ", tid='" + tid + '\'' +
                '}';
    }
}
