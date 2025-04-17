package edu.masanz.da.au.dao;

import edu.masanz.da.au.dto.AuditRecord;
import edu.masanz.da.au.dto.UserAuditRecords;
import edu.masanz.da.au.dto.Usuario;
import edu.masanz.da.au.service.AuctionService;

import java.util.*;

public class AuditDao {

    private Map<String, List<AuditRecord>> mapaAuditRecords;

    public AuditDao() {
        mapaAuditRecords = new HashMap<>();
    }

    public void addAuditRecord(AuditRecord record) {
        List<AuditRecord> records = mapaAuditRecords.get(record.getUsername());
        if (records == null) {
            records = new ArrayList<>();
            mapaAuditRecords.put(record.getUsername(), records);
        }
        records.add(record);
    }

    public List<AuditRecord> getUserLoginRecords() {
        List<AuditRecord> lista = new ArrayList<>();
        for (List<AuditRecord> marList : mapaAuditRecords.values()) {
            for (AuditRecord auditRecord : marList) {
                if (auditRecord.getPath().equals("/autenticar")) {
                    lista.add(auditRecord);
                }
            }
        }
        lista.sort((o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp()));
        return lista;
    }

    public List<UserAuditRecords> getIlegalUserAuditRecords() {
        List<UserAuditRecords> lista = new ArrayList<>();
        for (Map.Entry<String, List<AuditRecord>> entry : mapaAuditRecords.entrySet()) {
            UserAuditRecords uar = new UserAuditRecords(entry.getKey());
            uar.setAuditRecords(new ArrayList<>());
            Iterator<AuditRecord> it = entry.getValue().iterator();
            while (it.hasNext()) {
                AuditRecord ar = it.next();
                if (!ar.getPath().equals("/autenticar") && !ar.isLegal()) {
                    uar.getAuditRecords().add(ar);
                }
            }
            if (uar.getAuditRecords().size() > 0) {
                Collections.sort(uar.getAuditRecords(), (o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp()));
                lista.add(uar);
            }
        }
        Collections.sort(lista);//Alfabéticamente pero ANONYMOUS al final
        return lista;
    }

    public Map<String, List<AuditRecord>> getIlegalUserAuditRecordsMap() {
        Map<String, List<AuditRecord>> mapa = new HashMap<>();
        for (Map.Entry<String, List<AuditRecord>> entry : mapaAuditRecords.entrySet()) {
            String username = entry.getKey();
            Iterator<AuditRecord> it = entry.getValue().iterator();
            List<AuditRecord> lista = new ArrayList<>();
            while (it.hasNext()) {
                AuditRecord ar = it.next();
                if (!ar.getPath().equals("/autenticar") && !ar.isLegal()) {
                    lista.add(ar);
                }
            }
            if (lista.size() > 0) {
                mapa.put(username, lista);
            }
        }
        return mapa;
    }

    public UserAuditRecords getUserAuditRecords(String selectedUsername) {
        UserAuditRecords uar = new UserAuditRecords(selectedUsername);
        List<AuditRecord> lista = mapaAuditRecords.get(selectedUsername);
        if (lista == null) {
            lista = new ArrayList<>();
        }
        uar.setAuditRecords(lista);
        Collections.sort(lista, (o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp()));
        return uar;
    }

}
