package edu.masanz.da.au.dto;

import java.util.List;

import static edu.masanz.da.au.conf.Ctes.USER_ANONYMOUS;

public class UserAuditRecords implements Comparable<UserAuditRecords> {

    private String username;

    private List<AuditRecord> auditRecords;

    public UserAuditRecords(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public List<AuditRecord> getAuditRecords() {
        return auditRecords;
    }

    public void setAuditRecords(List<AuditRecord> auditRecords) {
        this.auditRecords = auditRecords;
    }

    @Override
    public int compareTo(UserAuditRecords other) {
        if (other.username  == USER_ANONYMOUS) {
            return -1;
        }
        return this.username.compareTo(other.username);
    }

}
