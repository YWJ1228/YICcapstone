package com.example.YICcapstone.domain.member.service;

import com.example.YICcapstone.domain.member.dto.MemberSignUpDto;
import com.example.YICcapstone.domain.member.dto.UpdateNicknameDto;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.global.util.security.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean signUp(MemberSignUpDto memberSignUpDto) { // 회원가입 서비스
        Member newMember = memberSignUpDto.toEntity(); // 회원가입 요청으로 보낸 정보를 DB에 저장할 수 있도록 엔티티화
        newMember.addRole(); // 회원가입 요청을 통한 회원 등록은 무조건 USER 등급의 역할 부여. ADMIN 등급은 별도의 경로로 설정하도록 구현
        newMember.encodePassword(passwordEncoder); // 회원가입 요청을 통한 회원 정보 중에서 비밀번호 정보는 암호화하여 DB에 저장하기 위함

        if(memberRepository.findByUsername(memberSignUpDto.username()).isPresent()) { // 위의 코드를 통해 회원정보를 DB에 저장할 준비를 마쳤으나, 아이디(이메일) 중복 시 X
            return false;
        }
        if(memberRepository.findByNickname(memberSignUpDto.nickname()).isPresent()) { // 위의 코드를 통해 회원정보를 DB에 저장할 준비를 마쳤으나, 닉네임 중복 시 X
            return false;
        }

        memberRepository.save(newMember); // 회원가입 요청으로 온 회원 정보를 DB에 저장

        return true;
    }

    @Override
    public Boolean dupUsername(String username) {  // 회원가입 중, 아이디(이메일) 중복 체크 서비스
        return memberRepository.existsByUsername(username);
    }

    @Override
    public Boolean dupNickname(String nickname) { // 회원가입 중, 닉네임 중복 체크 서비스
        return memberRepository.existsByNickname(nickname);
    }

    @Override
    public String updateNickname(UpdateNicknameDto updateNicknameDto) { // 닉네임 변경 서비스
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername()).orElse(null); // 접속중인 사용자의 DB 불러옴

        if(member == null) { // 사용자가 DB에 존재하지 않으면 null 리턴
            return null;
        }

        updateNicknameDto.nickname().ifPresent(member::updateNickname);

        return updateNicknameDto.nickname().orElse(null); // 닉네임 변경 성공 시, 바뀐 닉네임 리턴
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
    */

    /*
    public Boolean findPw(MemberFindPwDto memberFindPwDto) { // 비밀번호 찾기 서비스
        String name = memberFindPwDto.name();
        String username = memberFindPwDto.username();

        Member target = memberRepository.findByUsername(username).orElse(null);
        if(target == null) return false;
        if(!target.getName().equals(name)) return false;

        return true;
    }
    */

    /*
    public Member updatePassword(Long id, UpdatePassword updatePassword) { // 비밀번호 변경 서비스
        Member target = memberRepository.findById(id).orElse(null);

        if(target == null) { return null; }

        target.updatePassword(updatePassword.password());
        Member updated = memberRepository.save(target);
        return updated;
    }
    */

    /*
    public Member deteleMember(Long id) { // 회원 삭제 서비스
        Member target = memberRepository.findById(id).orElse(null);
        if(target == null) return null;

        memberRepository.delete(target);
        return target;
    }
     */
}
