package entities;

import java.util.ArrayList;
import java.util.List;

public class GiangVienList {
    private static final List<GiangVien> ds = new ArrayList<>();

    static {
        initData();
    }

    public static List<GiangVien> queryGiangVien() {
        System.out.println(ds);
        return ds;
    }

    private static void initData() {
        System.out.println("Initializing data...");

        // Tạo giảng viên mẫu
        GiangVien gv1 = new GiangVien();
        gv1.setMaGV("GV001");
        gv1.setTenGV("Nguyễn Văn A");
        gv1.setLinhVuc("Công nghệ thông tin");
        gv1.setLienKet("0123456789");

        GiangVien gv2 = new GiangVien();
        gv2.setMaGV("GV002");
        gv2.setTenGV("Trần Thị B");
        gv2.setLinhVuc("Toán học");
        gv2.setLienKet("0987654321");

        // Thêm giảng viên vào danh sách
        ds.add(gv1);
        ds.add(gv2);

        // In ra danh sách giảng viên để kiểm tra
        System.out.println("Danh sách giảng viên: " + ds);
    }
}
