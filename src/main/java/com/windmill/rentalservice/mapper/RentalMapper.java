package com.windmill.rentalservice.mapper;

import com.windmill.rentalservice.dto.RentalDto;
import com.windmill.rentalservice.model.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    public RentalDto toDto(Rental rental) {
        if (rental == null) {
            return null;
        }

        RentalDto rentalDto = new RentalDto();
        rentalDto.setRentalId(rental.getRentalId());
        rentalDto.setCustomerId(rental.getCustomerId());
        rentalDto.setToolId(rental.getToolId());
        rentalDto.setCustomerId(rental.getCustomerId());
        rentalDto.setRentalDays(rental.getRentalDays());
        rentalDto.setDiscountPercent(rental.getDiscountPercent());
        rentalDto.setCheckoutDate(rental.getCheckoutDate());
        rentalDto.setDueDate(rental.getDueDate());
        rentalDto.setChargeDays(rental.getChargeDays());
        rentalDto.setPreDiscountCharge(rental.getPreDiscountCharge());
        rentalDto.setDiscountAmount(rental.getDiscountAmount());
        rentalDto.setFinalCharge(rental.getFinalCharge());

        return rentalDto;
    }


    public Rental toEntity(RentalDto rentalDto) {
        if (rentalDto == null) {
            return null;
        }

        Rental rental = new Rental();
        rental.setRentalId(0L);
        rental.setRentalId(rentalDto.getCustomerId());
        rental.setToolId(rentalDto.getToolId());
        rental.setCustomerId(rentalDto.getCustomerId());
        rental.setRentalDays(rentalDto.getRentalDays());
        rental.setDiscountPercent(rentalDto.getDiscountPercent());
        rental.setCheckoutDate(rentalDto.getCheckoutDate());
        rental.setDueDate(rentalDto.getDueDate());
        rental.setChargeDays(rentalDto.getChargeDays());
        rental.setPreDiscountCharge(rentalDto.getPreDiscountCharge());
        rental.setDiscountAmount(rentalDto.getDiscountAmount());
        rental.setFinalCharge(rentalDto.getFinalCharge());

        return rental;
    }

}
