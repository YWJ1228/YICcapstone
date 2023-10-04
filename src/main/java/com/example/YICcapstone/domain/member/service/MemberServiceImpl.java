package com.example.YICcapstone.domain.member.service;

import com.example.YICcapstone.domain.member.dto.MemberInfoDto;
import com.example.YICcapstone.domain.member.dto.MemberSignUpDto;
import com.example.YICcapstone.domain.member.dto.UpdateNicknameDto;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.exception.MemberNicknameDuplicatedException;
import com.example.YICcapstone.domain.member.exception.MemberUsernameDuplicatedException;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.global.util.security.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    //private EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(MemberSignUpDto memberSignUpDto) { // 회원가입 서비스
        Member newMember = memberSignUpDto.toEntity(); // 회원가입 요청으로 보낸 정보를 DB에 저장할 수 있도록 엔티티화
        newMember.addRole(); // 회원가입 요청을 통한 회원 등록은 무조건 USER 등급의 역할 부여. ADMIN 등급은 별도의 경로로 설정하도록 구현
        newMember.encodePassword(passwordEncoder); // 회원가입 요청을 통한 회원 정보 중에서 비밀번호 정보는 암호화하여 DB에 저장하기 위함

        if(memberRepository.existsByUsername(memberSignUpDto.username())) { // 아이디(이메일) 중복 시, 409 conflict 에러
            throw new MemberUsernameDuplicatedException();
        }
        if(memberRepository.existsByNickname(memberSignUpDto.nickname())) { // 닉네임 중복 시, 409 conflict 에러
            throw new MemberNicknameDuplicatedException();
        }

        memberRepository.save(newMember); // 회원가입 요청으로 온 회원 정보를 DB에 저장
    }

    @Override
    public void dupUsername(String username) {  // 회원가입 중, 아이디(이메일) 중복 체크 서비스
        if(memberRepository.existsByUsername(username)) { // 아이디(이메일) 중복 시, 409 conflict 에러
            throw new MemberUsernameDuplicatedException();
        }
    }

    @Override
    public void dupNickname(String nickname) { // 회원가입 중, 닉네임 중복 체크 서비스
        if(memberRepository.existsByNickname(nickname)) { // 아이디(이메일) 중복 시, 409 conflict 에러
            throw new MemberNicknameDuplicatedException();
        }
    }

    @Override
    public void updateNickname(UpdateNicknameDto updateNicknameDto) throws Exception{ // 로그인 중, 닉네임 변경 서비스
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new Exception("회원이 존재하지 않습니다!"));

        updateNicknameDto.nickname().ifPresent(member::updateNickname);
    }

    @Override
    public void updatePassword(String checkPassword, String ChangePassword) throws Exception { // 로그인 중, 비밀번호 변경 서비스
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new Exception("회원이 존재하지 않습니다!"));

        if(!member.matchPassword(passwordEncoder, checkPassword)) {
            throw new Exception("비밀번호가 일치하지 않습니다!");
        }

        member.updatePassword(passwordEncoder, ChangePassword);
    }

    public void withdraw(String checkPassword) throws Exception { // 로그인 중, 회원 탈퇴 서비스
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new Exception("회원이 존재하지 않습니다!"));

        if(!member.matchPassword(passwordEncoder, checkPassword)) {
            throw new Exception("비밀번호가 일치하지 않습니다!");
        }

        memberRepository.delete(member);
    }

    public List<Member> index() { // 회원가입 되어 있는 모든 사용자 불러오기
        return memberRepository.findAll();
    }


    /*
    public String findId(MemberFindIdDto memberFindIdDto) { // 아이디 찾기 서비스
        String name = memberFindIdDto.name();
        String birth = memberFindIdDto.birth();

        List<Member> userList = memberRepository.findByName(name);
        for(int i = 0; i < userList.size(); i++) {
            Member member = userList.get(i);
            if(member.getBirth().equals(birth)) {
                return member.getUsername();
            }
        }

        return null;
    }

    public Boolean findPw(MemberFindPwDto memberFindPwDto) { // 비밀번호 찾기 서비스
        String name = memberFindPwDto.name();
        String username = memberFindPwDto.username();

        Member target = memberRepository.findByUsername(username).orElse(null);
        if(target == null) return false;
        if(!target.getName().equals(name)) return false;

        return true;
    }

    public Member updatePassword(Long id, UpdatePassword updatePassword) { // 비밀번호 변경 서비스
        Member target = memberRepository.findById(id).orElse(null);

        if(target == null) { return null; }

        target.updatePassword(updatePassword.password());
        Member updated = memberRepository.save(target);
        return updated;
    }
*/
}
