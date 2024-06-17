package com.windmill.rentalservice.repository;

import com.windmill.rentalservice.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

}
