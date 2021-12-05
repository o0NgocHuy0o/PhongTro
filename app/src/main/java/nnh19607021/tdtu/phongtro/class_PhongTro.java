package nnh19607021.tdtu.phongtro;

import java.util.Date;

public class class_PhongTro {
    private String id;
    private String SoPhong;
    private boolean PhongTrong;
    private int GiaTro;
    private class_NguoiTro nguoitro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSoPhong() {
        return SoPhong;
    }

    public void setSoPhong(String soPhong) {
        SoPhong = soPhong;
    }

    public boolean isPhongTrong() {
        return PhongTrong;
    }

    public void setPhongTrong(boolean phongTrong) {
        PhongTrong = phongTrong;
    }

    public int getGiaTro() {
        return GiaTro;
    }

    public void setGiaTro(int giaTro) {
        GiaTro = giaTro;
    }

    public class_NguoiTro getNguoitro() {
        return nguoitro;
    }

    public void setNguoitro(class_NguoiTro nguoitro) {
        this.nguoitro = nguoitro;
    }
}
