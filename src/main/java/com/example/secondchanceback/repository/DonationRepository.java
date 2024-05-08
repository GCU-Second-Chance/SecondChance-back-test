package com.example.secondchanceback.repository;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.example.secondchanceback.entity.DonationEntity;
import com.example.secondchanceback.entity.UserEntity;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @PackageName : com.example.secondchanceback.repository
 * @FileName : DonationRepository
 * @Author : noglass_gongdae
 * @Date : 2024-05-04
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@Qualifier("donationRepository")
public interface DonationRepository extends JpaRepository<DonationEntity, Long> {

    Optional<DonationEntity> findById(Long id);

}
