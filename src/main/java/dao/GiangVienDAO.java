package dao;

import java.util.List;

import entities.GiangVien;

public interface GiangVienDAO {
	 public List<GiangVien> layTatCaGiangVien();

	 public void themGiangVien(GiangVien GiangVienMoi);
}