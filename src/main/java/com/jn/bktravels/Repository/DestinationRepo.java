package com.jn.bktravels.Repository;

import com.jn.bktravels.Model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DestinationRepo extends JpaRepository<Destination, Integer> {

    @Query("SELECT d FROM Destination d WHERE " +
            "(LOWER(d.destinationName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(d.destinationDescription) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "(CAST(d.destinationPrice AS string) LIKE CONCAT('%', :query, '%')))")
    List<Destination> searchByQuery(String query);
        }
