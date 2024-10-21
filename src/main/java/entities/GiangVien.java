package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "GIANGVIEN")
public class GiangVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assuming auto-increment for maGV
    @Column(name = "MAGV") // Ensure the name matches the database column
    private String maGV;

    @Column(name = "TENGV")
    @NotEmpty(message = "Tiêu đề không được để trống.")
    private String tenGV;

    @Column(name = "LINHVUCNGHIENCUU")
    @NotEmpty(message = "Nội dung không được để trống.")
    @Size(max = 250, message = "Nội dung không được quá 255 ký tự.")
    private String linhVuc;

    @Column(name = "SODIENTHOAI")
    @NotEmpty(message = "Số điện thoại không hợp lệ")
    @Size(max = 50, message = "Nội dung không được quá 50 ký tự.")    
    private String lienKet;

    // Một giảng viên có nhiều đề tài
    @OneToMany(mappedBy = "giangVien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DeTai> deTaiList;

    // Getters and setters
    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public String getLinhVuc() {
        return linhVuc;
    }

    public void setLinhVuc(String linhVuc) {
        this.linhVuc = linhVuc;
    }

    public String getLienKet() {
        return lienKet;
    }

    public void setLienKet(String lienKet) {
        this.lienKet = lienKet;
    }

    public List<DeTai> getDeTaiList() {
        return deTaiList;
    }

    public void setDeTaiList(List<DeTai> deTaiList) {
        this.deTaiList = deTaiList;
    }
}
