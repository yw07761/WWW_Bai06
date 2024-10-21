package daoImplement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.DeTaiDAO;
import entities.DeTai;
import entities.GiangVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;



public class DanhSachDeTaiQuanLy implements DeTaiDAO {
    private EntityManager entityManager;

    public DanhSachDeTaiQuanLy(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public List<DeTai> layTatCaDeTai() {
        String query = "SELECT t FROM DeTai t";  
        return entityManager.createQuery(query, DeTai.class).getResultList();
    }

    public List<DeTai> layDeTaiTheoGiangVien(int maDT) {
        String query = "SELECT t FROM DeTai t WHERE t.DeTai.maDT = :maDT";
        return entityManager.createQuery(query, DeTai.class)
                            .setParameter("maDT", maDT)
                            .getResultList();
    }

   
    public void themDeTai(DeTai deTai) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            // Use merge() instead of persist() to handle detached entities
            entityManager.merge(deTai);
            transaction.commit();
            System.out.println("Thêm tin tức thành công!"); // Optional: log success message
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Thêm tin tức không thành công: " + e.getMessage()); // Optional: log error message
        }
    }


    
    public void xoaDeTai(int maDT) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            DeTai deTai = entityManager.find(DeTai.class, maDT);
            if (deTai != null) {
                entityManager.remove(deTai);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    
    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
    }
   
}