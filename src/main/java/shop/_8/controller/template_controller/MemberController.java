package shop._8.controller.template_controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop._8.dto.entity.LoginDto;
import shop._8.dto.entity.MemberSaveDto;
import shop._8.entity.Member;
import shop._8.service.MemberService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/register")
    public String registerForm() {
        return "member/memberRegisterForm";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute MemberSaveDto dto, Model model) {
        log.info("dto = {}", dto.toString());
        try {
            Member savedMember = memberService.save(dto);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("redirectUrl", "/member/register");
            return "message"; // fail page
        }
        model.addAttribute("message", "회원가입 되었습니다.");
        model.addAttribute("redirectUrl", "/");
        return "message"; // success page
    }

    @GetMapping("/login")
    public String login() {
        return "member/memberFormPage";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto dto, Model model) {
        log.info("dto = {}", dto.toString());
        try {
            Member member = memberService.findByEmail(dto.getEmail());
            authenticatePassword(member, dto.getPassword());
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("redirectUrl", "/member/login");
            return "message"; // fail page
        }
        model.addAttribute("message", "로그인 되었습니다.");
        model.addAttribute("redirectUrl", "/");
        return "message"; // success page
    }

    private void authenticatePassword(Member member, String password) throws IllegalArgumentException {
        if (!member.getPassword().equals(password)) {
            throw new IllegalArgumentException("password가 잘못되었습니다.");
        }
    }
}