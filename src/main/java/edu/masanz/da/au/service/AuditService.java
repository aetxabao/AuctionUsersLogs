package edu.masanz.da.au.service;

import edu.masanz.da.au.dao.AuditDao;
import edu.masanz.da.au.dto.AuditRecord;
import edu.masanz.da.au.dto.UserAuditRecords;
import edu.masanz.da.au.dto.Usuario;

import java.util.List;
import java.util.Map;

public class AuditService {

    private static AuditDao auditDao = new AuditDao();

    public static void addAuditRecord(AuditRecord record) {
        auditDao.addAuditRecord(record);
    }

    public static List<AuditRecord> getUserLoginRecords() { return auditDao.getUserLoginRecords(); }
    public static List<UserAuditRecords> getIlegalUserAuditRecords() {
        return auditDao.getIlegalUserAuditRecords();
    }

    public static UserAuditRecords getUserAuditRecords(String selectedUsername) {
        return auditDao.getUserAuditRecords(selectedUsername);
    }

    public static Map<String, List<AuditRecord>> getIlegalUserAuditRecordsMap() {
        return auditDao.getIlegalUserAuditRecordsMap();
    }

}
