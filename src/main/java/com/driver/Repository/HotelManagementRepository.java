package com.driver.Repository;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HotelManagementRepository {
    Map<String, Hotel> hoteldb = new HashMap<>();
    Map<String, User> Userdb = new HashMap<>();
    Map<Integer, Booking> Bookingdb = new HashMap<>();

    public String  savehotel(Hotel hotel) {
        if(hoteldb.containsKey(hotel.getHotelName()))
        {
            return "FAILURE";
        }
        else
        {
            hoteldb.put(hotel.getHotelName(),hotel);
            return "SUCCESS";
        }

    }

    public void saveuser(User user) {
        Userdb.put(user.getName(), user);
    }

    public void booking(Booking booking)
    {
        Bookingdb.put(booking.getBookingAadharCard(),booking);
    }


    public Hotel update(List<Facility> newFacilities, String hotelName) {
        Hotel hotel = hoteldb.get(hotelName);
        if(hotel.getFacilities()==newFacilities) {

        } else hotel.setFacilities(newFacilities);

        hoteldb.put(hotelName,hotel);
        return hoteldb.get(hotelName);
    }

    public int  Bookinginfo(Integer aadharCard) {
        int count = 0;
       for( int ele: Bookingdb.keySet())
       {
           if(ele==aadharCard) count++;
       }
       return  count;
    }

    public Hotel getHotelWithMostFacilities() {
        int data = 0;
        Hotel hotels = null;
        for(String ele: hoteldb.keySet())
        {
            Hotel hotel = hoteldb.get(ele);
            List<Facility> facilities = hotel.getFacilities();
            if(facilities.size()>data)
            {
                data = facilities.size();
                hotels = hotel;
            }
            else if(facilities.size()==data)
            {
                int com = hotel.getHotelName().compareTo(hotels.getHotelName());
                if(com>0) hotels  = hotel;
            }
        }
        return hotels;
    }
}
