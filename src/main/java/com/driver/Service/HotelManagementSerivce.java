package com.driver.Service;

import com.driver.Repository.HotelManagementRepository;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelManagementSerivce {
    @Autowired
    HotelManagementRepository hotelManagementRepository;
    public String  addHotel(Hotel hotel) {
        return hotelManagementRepository.savehotel(hotel);
    }

    public Hotel putHotel(List<Facility> newFacilities, String hotelName) {
        return hotelManagementRepository.update(newFacilities,hotelName);

    }

    public int  getBooking(Integer aadharCard) {
        return hotelManagementRepository.Bookinginfo(aadharCard);


    }

    public Hotel getHotelWithMostFacilities() {

        return hotelManagementRepository.getHotelWithMostFacilities();
    }

    public void addUser(User user) {
        hotelManagementRepository.saveuser(user);
    }
}
