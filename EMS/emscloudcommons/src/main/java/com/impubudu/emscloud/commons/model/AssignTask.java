package com.impubudu.emscloud.commons.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AssignTask {

    @EmbeddedId
    private ComposeKey composeKey;

    @Embeddable
    public static class ComposeKey implements Serializable {
        private Integer eid;

        private Integer pid;

        private Integer tid;

        public Integer getEid() {
            return eid;
        }

        public void setEid(Integer eid) {
            this.eid = eid;
        }

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }

        public Integer getTid() {
            return tid;
        }

        public void setTid(Integer tid) {
            this.tid = tid;
        }
    }

    public ComposeKey getComposeKey() {
        return composeKey;
    }

    public void setComposeKey(ComposeKey composeKey) {
        this.composeKey = composeKey;
    }
}