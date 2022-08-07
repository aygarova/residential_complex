package com.example.copmprob.service;

import com.example.copmprob.exceptions.ExceptionMessages;
import com.example.copmprob.exceptions.WrongActionException;
import com.example.copmprob.model.dto.ApartmentEnterDto;
import com.example.copmprob.model.dto.ApartmentFreeTenantDto;
import com.example.copmprob.model.dto.ApartmentViewDto;
import com.example.copmprob.model.entity.Apartment;
import com.example.copmprob.model.entity.Users;
import com.example.copmprob.model.enums.StatusEnum;
import com.example.copmprob.repository.ApartmentRepository;
import com.example.copmprob.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ApartmentService {
    public final UserRepository userRepository;

    public final ApartmentRepository apartmentRepository;

    public final ModelMapper modelMapper;

    public ApartmentService(UserRepository userRepository, ApartmentRepository apartmentRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.apartmentRepository = apartmentRepository;
        this.modelMapper = modelMapper;
    }

    public boolean hasThisUserApartments(){
        Users user = findCurrentUser();
        return user.getApartments().isEmpty();
    }

    public Users findCurrentUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName() ;
        return userRepository.getUsersByUsername(username);
    }

    public boolean hasThisApartmentNumber(ApartmentEnterDto apartmentEnterDto){
        Apartment apartment = apartmentRepository.findApartmentByApartmentNumber(apartmentEnterDto.getApartmentNumber());
        if (apartment == null){
            throw new WrongActionException(ExceptionMessages.APARTMENT_NOT_FOUND_EXCEPTION);
        }
        return true;
    }

    public boolean hasThisApartmentOwner(ApartmentEnterDto apartmentEnterDto)  {
        Users user = findCurrentUser();
        Apartment apartment = apartmentRepository.findApartmentByApartmentNumber(apartmentEnterDto.getApartmentNumber());
        if (apartment.getOwner() == null){
            apartment.setOwner(user);
            apartment.setStatus(StatusEnum.Occupied);
            apartmentRepository.save(apartment);
            return false;
        }
        return true;
    }

    public List<ApartmentViewDto> findAllApartmentsOfUser() {
        Users user = findCurrentUser();
        List<Apartment> apartments = apartmentRepository.findApartmentByOwnerId(user.getId());
        List<ApartmentViewDto> apartmentViewDtos = new ArrayList<>();
        for (Apartment apartment: apartments) {
            ApartmentViewDto apartmentViewDto = modelMapper.map(apartment,ApartmentViewDto.class);
            if (apartment.getTenant() != null){
                apartmentViewDto.setTenant(apartment.getTenant().getFirstName() + " " + apartment.getTenant().getLastName());

            }
            apartmentViewDtos.add(apartmentViewDto);
        }
        return apartmentViewDtos;

    }

    public boolean giveApartmentForRent(String apartmentNumber) {
        Users user = findCurrentUser();
        boolean flag = false;
        Apartment apartment = apartmentRepository.findApartmentByApartmentNumber(apartmentNumber);
        if (user.getApartments().contains(apartment) && apartment.getTenant() == null){
            apartment.setStatus(StatusEnum.FREE);
            apartmentRepository.save(apartment);
            flag = true;
        }
        return flag;
    }

    public void backApartment(String apartmentNumber) {
        Apartment apartment = apartmentRepository.findApartmentByApartmentNumber(apartmentNumber);
        apartment.setStatus(StatusEnum.Occupied);
        apartmentRepository.save(apartment);
    }
    public boolean hasThisTenantApartments() {
        Users user = findCurrentUser();
        return user.getRentApartments() != null;
    }


    public List<ApartmentFreeTenantDto> findAllFreeApartments() {
        List<Apartment> apartments = apartmentRepository.findApartmentByStatus(StatusEnum.FREE);
        List<ApartmentFreeTenantDto> apartmentFreeTenantDtos = new ArrayList<>();

        for (Apartment apartment: apartments) {

            apartmentFreeTenantDtos.add(convertApartmentTenantEntityToDto(apartment));
        }

        return apartmentFreeTenantDtos;

    }

    public void rentApartment(String apartmentNumber) {
        Users user = findCurrentUser();
        Apartment apartment = apartmentRepository.findApartmentByApartmentNumber(apartmentNumber);
        apartment.setTenant(user);
        apartment.setStatus(StatusEnum.Occupied);

        apartmentRepository.save(apartment);
    }


    public void leaveApartment(String apartmentNumber) {
        Apartment apartment = apartmentRepository.findApartmentByApartmentNumber(apartmentNumber);
        apartment.setTenant(null);
        apartment.setStatus(StatusEnum.FREE);

        apartmentRepository.save(apartment);
    }
    public ApartmentFreeTenantDto findMyRentHome() {
        Users user = findCurrentUser();
        Apartment apartment =  apartmentRepository.findApartmentByTenantId(user.getId());
        return convertApartmentTenantEntityToDto(apartment);
    }

    public ApartmentFreeTenantDto convertApartmentTenantEntityToDto(Apartment apartment){
        ApartmentFreeTenantDto apartmentFreeTenantDto = new ApartmentFreeTenantDto();
        String ownerName = apartment.getOwner().getFirstName() +" " + apartment.getOwner().getLastName();
        String ownerEmail = apartment.getOwner().getEmail();
        String ownerPhone = apartment.getOwner().getPhoneNumber();
        apartmentFreeTenantDto.setApartmentNumber(apartment.getApartmentNumber());
        apartmentFreeTenantDto.setArea(apartment.getArea());
        apartmentFreeTenantDto.setFloor(apartment.getFloor());
        apartmentFreeTenantDto.setApartmentType(apartment.getApartmentType().name());
        apartmentFreeTenantDto.setStatus(apartment.getStatus().name());
        apartmentFreeTenantDto.setOwnerName(ownerName);
        apartmentFreeTenantDto.setOwnerEmail(ownerEmail);
        apartmentFreeTenantDto.setOwnerPhone(ownerPhone);

        return apartmentFreeTenantDto;
    }

}
