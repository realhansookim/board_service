package com.realhansookim.notice_board.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import com.realhansookim.notice_board.vo.admin.AdminAddVO;
import com.realhansookim.notice_board.vo.admin.AdminLoginVO;

import jakarta.servlet.http.HttpSession;

import com.realhansookim.notice_board.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
  private final AdminService aService;

  @GetMapping("/add")
  public String getaddAdmin(){
    return "/admin/add";
  }

  @PostMapping("/add")
    public String postAddAdmin(AdminAddVO data, Model model){
      Map<String,Object> map = aService.addAdmin(data);
      if((boolean)map.get("status")){
        return "redirect:/";
      }
      model.addAttribute("inputdata", data);
      model.addAttribute("message", map.get("message"));
      return "/admin/add";
    }

    @GetMapping("/login")
    public String getLogin(){
      return "/index";
    }

    @PostMapping("/login")
    public String postAdminLogin(AdminLoginVO login, HttpSession session, Model model){
      Map<String,Object> map = aService.loginAdmin(login);
      if((boolean)map.get("status")){
        session.setAttribute("loginUser", map.get("login"));
        return "redirect:/";
      }
      else{
        model.addAttribute("message", map.get("message"));
        return "/index";
      }
    }
    @GetMapping("/logout")
    public String getLogout(HttpSession session){
      session.invalidate();
      return "redirect:/";
    }
  }

