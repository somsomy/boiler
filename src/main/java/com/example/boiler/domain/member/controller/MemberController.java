package com.example.boiler.domain.member.controller;

import com.example.boiler.domain.member.dto.MemberDto;
import com.example.boiler.domain.member.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Api(tags = "Member")
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/member")
  public ResponseEntity<?> createMember(@RequestBody MemberDto memberDto) {
    log.info("memberDto = {}", memberDto);
    memberService.save(memberDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
