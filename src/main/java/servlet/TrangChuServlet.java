package servlet;

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
import daoImplement.DanhSachDeTaiQuanLy; // Giả sử bạn có lớp này
import entities.DeTai;
import entities.GiangVien;

/**
 * Servlet implementation class GiangVienServlet
 */
@WebServlet("/DeTaiServlet")
public class TrangChuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntityManagerFactoryUtil entityManagerFactory;
    private DanhSachDeTaiQuanLy dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.entityManagerFactory = new EntityManagerFactoryUtil();
        this.dao = new DanhSachDeTaiQuanLy(this.entityManagerFactory.getEntityManager());
    }

    public TrangChuServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<DeTai> list = dao.layTatCaDeTai();
        if (!list.isEmpty()) {
            request.setAttribute("dsDeTai", list);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/TrangChu.jsp");
            dispatcher.forward(request, response);
        } else {
            List<DeTai> deTaiList = dao.layTatCaDeTai(); // Thay đổi để lấy danh sách giảng viên

            request.setAttribute("dsDeTai", deTaiList); // Thay vì lấy lại danh sách đề tài
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/TrangChu.jsp");
            dispatcher.forward(request, response);
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
