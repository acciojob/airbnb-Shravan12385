package com.driver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repositiory.AirBnbrepo;

@Service
public class AirBnbService {


    AirBnbrepo airbnbrepo=new AirBnbrepo();

    public String addHotel(Hotel hotel) {
        // TODO Auto-generated method stub

        if(hotel==null || hotel.getHotelName()==null)
            return "FAILURE";

        return airbnbrepo.addHotel(hotel);
    }

    public Integer addUser(User user) {
        // TODO Auto-generated method stub
        return airbnbrepo.addUser(user);
    }

    public String getHotelWithMostFacilities() {
        // TODO Auto-generated method stub
        return airbnbrepo.getHotelWithMostFacilities();
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        // TODO Auto-generated method stub
        return airbnbrepo.updateFacilities(newFacilities,hotelName);
    }

    public int bookARoom(Booking booking) {
        // TODO Auto-generated method stub


        return airbnbrepo.bookARoom(booking);
    }

    public int getBookings(Integer aadharCard) {
        // TODO Auto-generated method stub

        int ans = airbnbrepo.getBookings(aadharCard);
        return ans;
    }

}