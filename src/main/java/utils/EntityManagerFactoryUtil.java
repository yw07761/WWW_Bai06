package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtil {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public EntityManagerFactoryUtil() {
        entityManagerFactory = Persistence.createEntityManagerFactory("quanlygiangviendetai");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public boolean testConnection() {
        try {
            if (entityManager.isOpen()) {
                System.out.println("Kết nối tới cơ sở dữ liệu thành công!"); // Log thành công
                return true; // Kết nối đang mở
            } else {
                entityManager = entityManagerFactory.createEntityManager(); // Tạo lại EntityManager
                System.out.println("Kết nối tới cơ sở dữ liệu thành công!"); // Log thành công
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kết nối tới cơ sở dữ liệu không thành công!"); // Log không thành công
            return false; // Kết nối không thành công
        }
    }

    public void close() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
