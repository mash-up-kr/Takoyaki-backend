package org.mashup.takoyaki.service;

import org.mashup.takoyaki.dto.ReportDto;
import org.springframework.web.multipart.MultipartFile;

public interface ReportService {

    void report(String accessToken, MultipartFile[] photoFiles, ReportDto reportDto);

}
