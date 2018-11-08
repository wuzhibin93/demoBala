package com.enlink.de.pemca.dao;

import com.enlink.de.pemca.entity.Certificate;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 10:35 2018/9/5
 */

public interface CertificateDao {
    int addCer(Certificate certificate);
    int deleteCer(String cerId);
    int updateCer(Certificate certificate);
    Certificate queryOnr(String cerId);
    List<Certificate> queryAll();
}
