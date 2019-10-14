package com.impubudu.emscloud.commons.model.employee;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AssignedOperation {

    @EmbeddedId
    private OperationCompositeKey operationCompositeKey;

    @Embeddable
    public static class OperationCompositeKey implements Serializable {
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

    public OperationCompositeKey getOperationCompositeKey() {
        return operationCompositeKey;
    }

    public void setOperationCompositeKey(OperationCompositeKey operationCompositeKey) {
        this.operationCompositeKey = operationCompositeKey;
    }
}