package daoImplement;

import java.util.List;

import dao.GiangVienDAO;
import entities.GiangVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class GiangVienDAOIMPL implements GiangVienDAO{
	private EntityManager entityManager;

    public GiangVienDAOIMPL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<GiangVien> layTatCaGiangVien() {
        String query = "SELECT d FROM GiangVien d";
        List<GiangVien> result = entityManager.createQuery(query, GiangVien.class).getResultList();
       
        return result;
    }

    public void themGiangVien(GiangVien giangVienMoi) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(giangVienMoi);
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
               }
    }
}