package com.windmill.rentalservice.repository;

import com.windmill.rentalservice.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByToolId(Long toolId);
    List<Rental> findByCustomerId(Long customerId);
    List<Rental> findByCheckoutDate(LocalDate checkoutDate);
    List<Rental> findByDueDate(LocalDate checkoutDate);

    @Query("SELECT r FROM Rental r JOIN Tool t ON r.toolId = t.toolId WHERE t.toolCode = :toolCode")
    List<Rental> findRentalsByToolCode(@Param("toolCode") String toolCode);

    @Query("SELECT r FROM Rental r JOIN Customer c ON r.customerId = c.customerId WHERE c.name = :name")
    List<Rental> findRentalsByCustomerName(@Param("name") String name);

    @Query("SELECT r FROM Rental r JOIN Customer c ON r.customerId = c.customerId WHERE c.contact = :contact")
    List<Rental> findRentalsByCustomerContact(@Param("contact") String contact);

    Optional<Rental> findRentalByRentalId(Long rentalId);
}
