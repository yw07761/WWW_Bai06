package servlet;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import untils.EntityManagerFactoryUtil;

import java.io.IOException;
import java.util.List;

import entities.DeTai;
import daoImplement.DanhSachDeTaiQuanLy;
/**
 * Servlet implementation class QuanLyFormServlet
 */
public class QuanLyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DanhSachDeTaiQuanLy danhSachDeTaiQuanLy;
	private EntityManager entityManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	@Override
	public void init() throws ServletException {
		this.entityManager = new EntityManagerFactoryUtil().getEntityManager();
		this.danhSachDeTaiQuanLy = new DanhSachDeTaiQuanLy(entityManager);
	}

	public QuanLyFormServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("CAll DOGETT");
		List<DeTai> deTaiList = danhSachDeTaiQuanLy.layTatCaDeTai();
		System.out.println(deTaiList);

		request.setAttribute("deTaiList", deTaiList);

		request.getRequestDispatcher("/QuanLiForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maDT = request.getParameter("maDT");
		if (maDT != null) {
			int maDeTai = Integer.parseInt(maDT);

			danhSachDeTaiQuanLy.xoaDeTai(maDeTai);
		}

		response.sendRedirect("QuanLyFormServlet");
	}

	@Override
	public void destroy() {
		if (entityManager != null) {
			entityManager.close();
		}
	}

}