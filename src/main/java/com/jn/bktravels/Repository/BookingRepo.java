package com.jn.bktravels.Repository;


import com.jn.bktravels.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {


}
