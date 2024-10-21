package servlet;

import jakarta.persistence.EntityManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.EntityManagerFactoryUtil;

import java.io.IOException;
import java.util.List;

import dao.GiangVienDAO;
import dao.DeTaiDAO;
import daoImplement.DanhSachDeTaiQuanLy;
import daoImplement.GiangVienDAOIMPL;
import entities.DeTai;
import entities.GiangVien;

/**
 * Servlet implementation class DanhSachDeTaiServlet
 */
@WebServlet("/DanhSachDeTaiServlet")
public class DanhSachDeTaiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntityManagerFactoryUtil entityManageFactory;
    private DeTaiDAO deTaiDAO;
    private GiangVienDAO giangVienDAO;

   

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DanhSachDeTaiServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.entityManageFactory = new EntityManagerFactoryUtil();
        this.deTaiDAO = new DanhSachDeTaiQuanLy(this.entityManageFactory.getEntityManager()); // Đảm bảo sử dụng đúng DAO
        this.giangVienDAO = new GiangVienDAOIMPL(this.entityManageFactory.getEntityManager());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String maGVStr = request.getParameter("magv");
        int maGV = 0;
        if (maGVStr != null && !maGVStr.isEmpty()) {
            try {
                maGV = Integer.parseInt(maGVStr);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mã không hợp lệ");
                return;
            }
        }

        List<GiangVien> giangVienList = giangVienDAO.layTatCaGiangVien(); // Sửa phương thức

        List<DeTai> deTaiList;
        if (maGV > 0) {
            deTaiList = deTaiDAO.layDeTaiTheoGiangVien(maGV); // Sửa phương thức
        } else {
            deTaiList = deTaiDAO.layTatCaDeTai(); // Sửa phương thức
        }

        request.setAttribute("giangVienList", giangVienList);
        request.setAttribute("deTaiList", deTaiList); // Sửa tên biến

        RequestDispatcher dispatcher = request.getRequestDispatcher("/DanhSachDeTai.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Xử lý phương thức POST tương tự như doGet
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        if (entityManageFactory != null) {
            entityManageFactory.close(); // Đóng EntityManagerFactory
        }
    }
    
    
}
