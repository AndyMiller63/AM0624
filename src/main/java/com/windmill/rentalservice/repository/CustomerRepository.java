package com.windmill.rentalservice.repository;

import com.windmill.rentalservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Find a customer by name
    List<Customer> findByName(String name);

    // Find customers by contact
    List<Customer> findByContact(String contact);

    // Find customers by name containing a substring (case insensitive)
    List<Customer> findByNameContainingIgnoreCase(String name);

    // Find customers with a custom query
    @Query("SELECT c FROM Customer c WHERE c.contact = :contact")
    List<Customer> findCustomersByContact(@Param("contact") String contact);

    Optional<Customer> findCustomerByCustomerId(Long customerId);
    // Find a customer by name and contact

    // Custom query to find customers whose name starts with a given prefix
    //@Query("SELECT c FROM Customer c WHERE c.name LIKE :prefix%")
    //List<Customer> findByNameStartingWith(@Param("prefix") String prefix);
}
