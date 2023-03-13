package com.study.studyspring.controller;

import com.study.studyspring.domain.Member;
import com.study.studyspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {

    //new로 생성하지 않고 DI로 쓰는 이유
    //MemberController말고 다른 controller에서도 MemberService를 가져다 쓸 쑤 있는데,
    //그럴 때 마다 new로 객체를 생성하면 여러 개의 객체가 생성되고 그렇게 쓸 필요가 없기 때문이다.
    //밑에 DI코딩이 Spring container에 객체를 딱 하나 등록하고 그걸 쓸 수 있도록 하는 방법이다.
    private final MemberService memberService;

    //Autowired로 Spring이 Spring container에 있는 memberservice로 자동으로 연결을 시켜준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/login")
    public String loginform(){return "members/login";}

    @PostMapping("/members/login")
    public String login(MemberForm form, HttpSession session, Model model){
        Member member = new Member();
        member.setLoginId(form.getLoginId());
        member.setPwd(form.getPwd());

        Optional<String> rs = memberService.findOne(member.getLoginId(), member.getPwd());
        if(rs.isPresent()){
            session.setAttribute("sessions", rs.get());
            return "main";
        }else{
            model.addAttribute("message", "wrong");
            return "members/login";
        }

    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form, Model model){
        Member member = new Member();
        member.setName(form.getName());
        member.setLoginId(form.getLoginId());
        member.setPwd(form.getPwd());

        long rs = memberService.join(member);
        System.out.println(rs);
        if(rs == -1){
            model.addAttribute("data", "중복된 이름입니다.");
            return "members/createMemberForm";
        }
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList.html";
    }

    @GetMapping("/members/myPage")
    public String myPage(HttpSession session) {
        session.getAttribute("session");
        return "members/myPage.html";
    }

    @PostMapping("/members/upload")
    public String upload(MemberForm form) throws Exception{
        memberService.imgsave(form.getFilepath());
        return "redirect:/members/myPage";
    }
}
