package com.jn.bktravels.Repository;


import com.jn.bktravels.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {


    List<Booking> findBookingByUserUsername(String userName);

    @Modifying
    @Query("UPDATE Booking b SET b.status = :status WHERE b.id = :id")
    void updateBookingStatus(@Param("id") Long id, @Param("status") String status);


}
