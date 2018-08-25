package org.mashup.takoyaki.controller;

import lombok.extern.slf4j.Slf4j;
import org.mashup.takoyaki.common.exception.BadRequestException;
import org.mashup.takoyaki.dto.ReportDto;
import org.mashup.takoyaki.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping(value = "/v1/truck")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public void reportTruck(@RequestHeader("Access-Token") String accessToken,
                                                @RequestPart("photo_files")MultipartFile[] photoFiles,
                                                @RequestPart ReportDto report,
                                                HttpServletResponse response) {

        log.info("요청 받은 트럭 정보 : {}", report.toString());

        if (photoFiles.length > 3) {
            throw new BadRequestException();
        }

        if (LocalDate.now().isAfter(report.getExpirationDate())) {
            throw new BadRequestException();
        }
        reportService.report(accessToken, photoFiles, report);
        response.setStatus(HttpStatus.OK.value());
    }
}