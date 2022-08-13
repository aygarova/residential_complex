package com.example.copmprob.cotroller;

import com.example.copmprob.model.dto.UserDto;
import com.example.copmprob.model.dto.UserLoginDto;
import com.example.copmprob.model.dto.UserWithApartmentDto;
import com.example.copmprob.model.entity.Users;
import com.example.copmprob.model.enums.RoleEnum;
import com.example.copmprob.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserDto userDto,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userDto.getPassword()
                .equals(userDto.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userDto", userDto);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userDto"
                            , bindingResult);

            return "redirect:register";
        }

        userService.registerUser(modelMapper.map(userDto, UserDto.class));


        return "redirect:login";
    }


    @GetMapping("/login")
    public String loginUser(Model model){
        if (!model.containsAttribute("isFound")){
            model.addAttribute("isFound", true);
        }
        return "login";
    }


    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginDto userLoginDto,BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginDto"
                            , bindingResult);

            return "redirect:login";
        }

        if (!userService.loginUser(userLoginDto)){
                redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
                redirectAttributes.addFlashAttribute("isFound", false);
                return "redirect:login";
        }
        return "home";
    }


    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();

        return "redirect:/";
    }

    @GetMapping("/owner")
    public String news(){
        return "ownerAfterRegistration";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/all-owners")
    public String allOwners(Model model){
        model.addAttribute("owners",userService.findAll(RoleEnum.OWNER));

        return "all-owners";
    }

    @GetMapping("/all-tenants")
    public String allTenants(Model model){
        model.addAttribute("tenants",userService.findAll(RoleEnum.TENANT));

        return "all-tenants";
    }

    @ModelAttribute
    public UserDto userRegisterDto(){
        return new UserDto();
    }
    @ModelAttribute
    public UserLoginDto userLoginDto(){
        return new UserLoginDto();
    }

    @ModelAttribute
    public UserWithApartmentDto allOwnersDto(){
        return new UserWithApartmentDto();
    }

}
