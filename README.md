# WWW_Bai06
SQL Database

CREATE TABLE GIANGVIEN (

    MAGV VARCHAR(20) PRIMARY KEY,
    
    TENGV VARCHAR(100) NOT NULL,
    
    LINHVUCNGHIENCUU VARCHAR(250) NOT NULL,
    
    SODIENTHOAI VARCHAR(50) NOT NULL
    
);


CREATE TABLE DETAI (

    MADT VARCHAR(20) PRIMARY KEY,
    
    TENDT VARCHAR(100) NOT NULL,
    
    NAMDANGKY INT NOT NULL,
    
    MOTADETAI TEXT NOT NULL,
    
    MAGV VARCHAR(20),
    
    FOREIGN KEY (MAGV) REFERENCES GIANGVIEN(MAGV) ON DELETE CASCADE
    
);
