package com.example.copmprob.cotroller;

import com.example.copmprob.exceptions.ThisApartmentHasOwnerException;
import com.example.copmprob.model.dto.ApartmentEnterDto;
import com.example.copmprob.model.dto.ApartmentFreeTenantDto;
import com.example.copmprob.model.dto.UserLoginDto;
import com.example.copmprob.service.ApartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ApartmentController {
    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("/my-apartment")
    public String myApartment(Model model){
        if (apartmentService.hasThisUserApartments()){
            return "ownerAfterRegistration";
        }

        model.addAttribute("apartments",apartmentService.findAllApartmentsOfUser());

        return "my-home";
    }

    @GetMapping("/add-new-apartment-in-my-list")
    public String addApartmentInMyList(){
            return "ownerAfterRegistration";
    }

    @GetMapping("/give-apartment-for-rent/{apartmentNumber}")
    public String giveApartmentForRent(@PathVariable String apartmentNumber){
        apartmentService.giveApartmentForRent(apartmentNumber);
            return "home";
    }

    @GetMapping("/back-apartment/{apartmentNumber}")
    public String backApartment(@PathVariable String apartmentNumber){
        apartmentService.backApartment(apartmentNumber);
            return "home";
    }


    @PostMapping("/apartment-number-enter")
    public String enterApartmentNumber(@Valid ApartmentEnterDto apartmentEnterDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("apartmentEnterDto", apartmentEnterDto);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.apartmentEnterDto"
                            , bindingResult);

            return "redirect:my-apartment";
        }

        if (apartmentService.hasThisApartmentNumber(apartmentEnterDto)){
            if (apartmentService.hasThisApartmentOwner(apartmentEnterDto)){
                return "has-owner";
            }
             return "home";
        }
        return "ownerAfterRegistration";
    }

    @GetMapping("/all-free-apartment")
    public String freeApartment(Model model){
        if (apartmentService.hasThisTenantApartments()){
            model.addAttribute("apartment",apartmentService.findMyRentHome());
            return "my-rent-home";
        }

        model.addAttribute("freeApartments",apartmentService.findAllFreeApartments());

        return "all-free-apartments";
    }

    @GetMapping("/rentApartment/{apartmentNumber}")
    public String rentApartment(@PathVariable String apartmentNumber){
        apartmentService.rentApartment(apartmentNumber);
        return "home";
    }

    @GetMapping("/leaveApartment/{apartmentNumber}")
    public String leaveApartment(@PathVariable String apartmentNumber){
        apartmentService.leaveApartment(apartmentNumber);
        return "home";
    }

    @ModelAttribute
    public ApartmentEnterDto apartmentEnterDto(){
        return new ApartmentEnterDto();
    }

    @ModelAttribute
    public ApartmentFreeTenantDto apartmentFreeTenantDto(){
        return new ApartmentFreeTenantDto();
    }

}
