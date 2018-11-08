package com.enlink.de.pemca.service;

import com.enlink.de.pemca.entity.Certificate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 11:21 2018/9/5
 */
public interface CertificateService {
    int addCer(MultipartFile file,Certificate certificate);
    int deleteCer(String id);
    int updateCer(Certificate certificate);
    Certificate queryOne(String id);
    List<Certificate> queryAll();
}
