package org.mashup.takoyaki.service;

import lombok.extern.slf4j.Slf4j;
import org.mashup.takoyaki.common.exception.BadRequestException;
import org.mashup.takoyaki.common.exception.UserNotFoundException;
import org.mashup.takoyaki.common.util.S3Util;
import org.mashup.takoyaki.dto.ReportDto;
import org.mashup.takoyaki.entity.FoodTruckImage;
import org.mashup.takoyaki.entity.Region;
import org.mashup.takoyaki.entity.Report;
import org.mashup.takoyaki.entity.User;
import org.mashup.takoyaki.repository.FoodTruckImageRepository;
import org.mashup.takoyaki.repository.RegionRepository;
import org.mashup.takoyaki.repository.ReportRepository;
import org.mashup.takoyaki.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService{

    private ReportRepository reportRepository;
    private UserRepository userRepository;
    private FoodTruckImageRepository foodTruckImageRepository;
    private RegionRepository regionRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, UserRepository userRepository,
                             FoodTruckImageRepository foodTruckImageRepository, RegionRepository regionRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.foodTruckImageRepository = foodTruckImageRepository;
        this.regionRepository = regionRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void report(String accessToken, MultipartFile[] photoFiles, ReportDto reportDto) {
        Optional<User> userByAccessToken = userRepository.findByAccessToken_Token(accessToken);
        User user = userByAccessToken.orElseThrow(UserNotFoundException::new);

        Optional<Region> regionByRegionType = regionRepository.findByRegionType(reportDto.getRegionName());
        Region region = regionByRegionType.orElseThrow(BadRequestException::new);

        Report report = saveReport(reportDto, user, region);
        saveFoodTruckImage(photoFiles, report);
    }

    private void saveFoodTruckImage(MultipartFile[] photoFiles, Report report) {
        if(photoFiles.length != 0) {

            for (MultipartFile file : photoFiles) {
                String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                String fileUrl = S3Util.uploadFile(String.format("trucks/%s/%s%s", report.getId(), LocalDateTime.now(), extension), file);

                FoodTruckImage foodTruckImage = new FoodTruckImage();
                foodTruckImage.setReportId(report);
                foodTruckImage.setTruckImageUrl(fileUrl);
                foodTruckImageRepository.save(foodTruckImage);

                log.info("등록된 사진 정보 : {}", foodTruckImage);
            }

        }
    }

    private Report saveReport(ReportDto reportDto, User user, Region region) {
        Report report = new Report();
        report.setUserId(user);
        report.setTruckName(reportDto.getTruckName());
        report.setDescription(reportDto.getDescription());
        report.setExpirationDate(reportDto.getExpirationDate());
        report.setRegionId(region);
        report.setLongitude(reportDto.getLongitude());
        report.setLatitude(reportDto.getLatitude());
        report.setReportedAt(LocalDateTime.now());
        reportRepository.save(report);
        return report;
    }
}
