package com.example.secondchanceback.service.Impl;

import com.example.secondchanceback.dto.UserDto;
import com.example.secondchanceback.entity.DonationEntity;
import com.example.secondchanceback.entity.UserEntity;
import com.example.secondchanceback.repository.DonationRepository;
import com.example.secondchanceback.repository.UserRepository;
import com.example.secondchanceback.service.FeatureService;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @PackageName : com.example.secondchanceback.service.Impl
 * @FileName : FeatureServiceImpl
 * @Author : noglass_gongdae
 * @Date : 2024-05-04
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@Service
public class FeatureServiceImpl implements FeatureService {

    private final Logger LOGGER = LoggerFactory.getLogger(FeatureServiceImpl.class);

    @Qualifier("userRepository")
    private final UserRepository userRepository;
    @Qualifier("donationRepository")
    private final DonationRepository donationRepository;

    public FeatureServiceImpl(UserRepository userRepository, DonationRepository donationRepository){
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
    }

    @Override
    @Transactional
    public UserEntity donationUserUpdate(UserDto userDto) {
        Optional<UserEntity> userEntity = findUser(userDto);
        LOGGER.info("findUser : {}", userEntity);
        return userEntity.map(entity -> {
            LOGGER.info("entity : {}", entity);
            entity.setSharing(entity.getSharing() + 1);
            return updateSharing(entity);
        }).orElse(null);
    }

    @Override
    @Transactional
    public ResponseEntity<DonationEntity> donationAmountUpdate() {
        ResponseEntity<DonationEntity> responseEntity;

        Optional<DonationEntity> donationEntity = findDonation(1L);
        responseEntity = donationEntity.map(entity -> {
            entity.setAmount(entity.getAmount() + 1);
            return new ResponseEntity<>(updateDonation(entity), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        return responseEntity;
    }
    @Override
    @Transactional
    public ResponseEntity<UserEntity> updateTakeaway(UserDto userDto) {
        LOGGER.info("updateTakeaway(), userDto : {}", userDto);
        ResponseEntity<UserEntity> responseEntity;
        Optional<UserEntity> userEntity = findUser(userDto);

        responseEntity = userEntity.map(entity -> {
            LOGGER.info("entity : {}", entity);
            entity.setTakeaway(userDto.getTakeaway());
            return new ResponseEntity<>(updateUserTakeaway(entity), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        return responseEntity;
    }

    private Optional<UserEntity> findUser(UserDto userDto){
        LOGGER.info("findUser(), userDto : {} ", userDto);
        return userRepository.findById(userDto.getId());
    }
    private Optional<DonationEntity> findDonation(Long id){
        LOGGER.info("findDonation, id : {} ", id);
        return donationRepository.findById(id);
    }
    private UserEntity updateSharing(UserEntity userEntity){
        LOGGER.info("updateSharing, userEntity : {} ", userEntity);
        return userRepository.save(userEntity);
    }
    private DonationEntity updateDonation(DonationEntity donationEntity){
        LOGGER.info("updateDonation, donationEntity : {} ", donationEntity);
        return donationRepository.save(donationEntity);
    }
    private UserEntity updateUserTakeaway(UserEntity userEntity){
        LOGGER.info("updateUserTakeaway(), userEntity : {}", userEntity);
        return userRepository.save(userEntity);
    }
}
