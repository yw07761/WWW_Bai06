package servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import daoImplement.GiangVienDAOIMPL;
import daoImplement.DanhSachDeTaiQuanLy;
import entities.GiangVien;
import entities.GiangVienList;
import entities.DeTai;

/**
 * Servlet implementation class DeTaiFormServlet
 */
@WebServlet("/DeTaiFormServlet")
public class DeTaiFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntityManagerFactory emf;
    private Validator validator;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeTaiFormServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("quanlygiangviendetai");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("DeTaiFormServlet doGet called");

        // Lấy danh sách giảng viên
        List<GiangVien> giangVienList = GiangVienList.queryGiangVien(); // Đảm bảo gọi phương thức đúng

        // In ra danh sách để kiểm tra
        System.out.println("Danh sách giảng viên trong servlet: " + giangVienList);

        request.setAttribute("giangVienList", giangVienList);
        request.getRequestDispatcher("/DeTaiForm.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Các biến cần thiết
        String maDTStr = request.getParameter("maDT");
        String tenDTStr = request.getParameter("tenDT");
        String namDangKy = request.getParameter("namDangKy");
        String moTa = request.getParameter("moTa");
        String maGV = request.getParameter("giangVien");

        DeTai deTai = new DeTai();
        deTai.setMaDT(maDTStr);
        deTai.setTenDT(tenDTStr);
        deTai.setNamDangKy(namDangKy);
        deTai.setMoTa(moTa);

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        try {
            GiangVien giangVien = em.find(GiangVien.class, maGV);
            if (giangVien == null) {
                request.setAttribute("errorMessage", "Giảng viên không tồn tại.");
                request.getRequestDispatcher("/DeTaiForm.jsp").forward(request, response);
                return;
            }

            deTai.setGiangVien(giangVien);

            Set<ConstraintViolation<DeTai>> violations = validator.validate(deTai);
            if (!violations.isEmpty()) {
                StringBuilder errorMessage = new StringBuilder("Dữ liệu nhập không hợp lệ:<br>");
                for (ConstraintViolation<DeTai> violation : violations) {
                    errorMessage.append(violation.getMessage()).append("<br>");
                }
                request.setAttribute("errorMessage", errorMessage.toString());
                request.getRequestDispatcher("/DeTaiForm.jsp").forward(request, response);
                return;
            }

            transaction.begin();
            DanhSachDeTaiQuanLy deTaiDao = new DanhSachDeTaiQuanLy(em);
            deTaiDao.themDeTai(deTai); // Gọi phương thức thêm đề tài
            transaction.commit();

            request.setAttribute("quanlygiangviendetai", deTai);
            request.getRequestDispatcher("/DanhSachDeTaiServlet").forward(request, response);

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            request.setAttribute("errorMessage", "Có lỗi xảy ra khi thêm đề tài.");
            request.getRequestDispatcher("/DeTaiForm.jsp").forward(request, response);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }


} 