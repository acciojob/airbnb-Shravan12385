package com.driver.repositiory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;


@Repository
public class AirBnbrepo {

    private HashMap<Integer,User> userDb = new HashMap<>();

    private HashMap<String,Hotel> hotelDb = new HashMap<>();

    private HashMap<String,Booking> bookingDb = new HashMap<>();

    private HashMap<Integer,Integer> userADb = new HashMap<>();

    private HashMap<Integer,Set<String>> fac = new HashMap<>();



    public String addHotel(Hotel hotel) {
        // TODO Auto-generated method stub

        if(hotelDb.containsKey(hotel.getHotelName()))
            return "FAILURE";
        hotelDb.put(hotel.getHotelName(),hotel);
        int num = hotel.getFacilities().size();


        if(fac.containsKey(num)) {
            Set<String> s = fac.get(num);
            s.add(hotel.getHotelName());
            fac.put(num, s);
        }
        else {
            Set<String> s = new HashSet<>();
            s.add(hotel.getHotelName());
            fac.put(num, s);
        }


        return "SUCCESS";
    }

    public Integer addUser(User user) {
        // TODO Auto-generated method stub
        userDb.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
    }

    public int bookARoom(Booking booking) {
        // TODO Auto-generated method stub
        String id = String.valueOf(UUID.randomUUID());

        int avb = hotelDb.get(booking.getHotelName()).getAvailableRooms();
        if(booking.getNoOfRooms()>avb)
            return -1;
        Hotel h= hotelDb.get(booking.getHotelName());


        int amount = booking.getNoOfRooms()*(hotelDb.get(booking.getHotelName()).getPricePerNight());
        Booking b = new Booking(id,booking.getBookingAadharCard(),booking.getNoOfRooms(),booking.getBookingPersonName(),booking.getHotelName());

        h.setAvailableRooms(avb-booking.getNoOfRooms());

        hotelDb.put(h.getHotelName(), h);
        bookingDb.put(id,b);
        userADb.put(booking.getBookingAadharCard(),userADb.getOrDefault(booking.getBookingAadharCard(),0)+1);

        return amount;
    }

    public int getBookings(Integer aadharCard) {
        // TODO Auto-generated method stub
        return userADb.get(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        // TODO Auto-generated method stub

        Hotel h = hotelDb.get(hotelName);
        List<Facility> l = h.getFacilities();
        int num1 = l.size();
        for(int i=0;i<newFacilities.size();i++) {
            if(l.contains(newFacilities.get(i))==false)
                l.add(newFacilities.get(i));
        }
        int num2 = l.size();

        Set<String> r = fac.get(num1);
        r.remove(hotelName);
        if(fac.containsKey(num2)) {
            Set<String> s = fac.get(num2);
            s.add(hotelName);
            fac.put(num2, s);
        }
        else {
            Set<String> s = new HashSet<>();
            s.add(hotelName);
            fac.put(num2, s);
        }


        h.setFacilities(l);
        hotelDb.put(hotelName, h);

        return h;
    }

    public String getHotelWithMostFacilities() {
        // TODO Auto-generated method stub

        String ans = "";
        for(int i=1;i<7;i++) {
            if(fac.containsKey(i)) {
                ans = fac.get(i).stream().findFirst().get();
            }
        }
        return ans;
    }


}