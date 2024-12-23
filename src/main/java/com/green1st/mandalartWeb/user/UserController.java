package com.green1st.mandalartWeb.user;

import com.green1st.mandalartWeb.common.model.ResultResponse;
import com.green1st.mandalartWeb.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@Tag(name = "회원", description = "sign-in / sign-out")
public class UserController {
    private final UserService userService;
    private final UserMessage userMessage;
    private final UserSignUpReq userSignUpReq;


    //이메일 중복체크
    @GetMapping("email")
    @Operation(summary = "이메일 중복체크")
    public ResultResponse<Integer> emailChk(@ParameterObject @ModelAttribute DuplicateEmailReq p){
        DuplicateEmailRes res = userService.emailChk(p.getUserId());

        return ResultResponse.<Integer>builder()
                .statusCode(res.getCheck() == 1 ? "200" : "400")
                .resultMsg(res.getMessage())
                .resultData(res.getCheck())
                .build();
    }

    //닉네임 중복체크
    @GetMapping("nickName")
    @Operation(summary = "닉네임 중복체크")
    public ResultResponse<Integer> nickNameChk(@ParameterObject @ModelAttribute DuplicateNickNameReq p) {
        DuplicateNickNameRes res = userService.nickNameChk(p.getNickName());

        return ResultResponse.<Integer>builder()
                .statusCode(res.getCheck() == 1 ? "200" : "400")
                .resultMsg(res.getMessage())
                .resultData(res.getCheck())
                .build();
    }


    @PostMapping("signUp")
    @Operation(summary = "회원가입")
    public ResultResponse<Integer> signUpUser(@RequestPart(required = false) MultipartFile pic
                                              , @RequestParam UserSignUpReq p){
        int result = userService.postSignUp(pic, p);

        return ResultResponse.<Integer>builder()
                .statusCode(result == 1 ? "200" : "400")
                .resultMsg(userMessage.getMessage())
                .resultData(result)
                .build();
    }

    @PostMapping("signIn")
    @Operation(summary = "로그인")
    public ResultResponse<UserSignInRes> signInUser(@RequestBody UserSignInReq p) {

        UserSignInRes res = userService.postSignIn(p);

        return ResultResponse.<UserSignInRes>builder()
                .statusCode(res != null ? "200" : "400")
                .resultMsg(res.getMessage())
                .resultData(res)
                .build();
    }

    @GetMapping()
    @Operation(summary = "유저정보조회")
    public ResultResponse<UserInfoGetRes> getUserInfo(@ParameterObject @ModelAttribute UserInfoGetReq p){

        UserInfoGetRes res = userService.getUserInfo(p);

        return ResultResponse.<UserInfoGetRes>builder()
                .statusCode(res != null ? "200" : "400")
                .resultMsg("조회완료")
                .resultData(res)
                .build();
    }

    @PatchMapping
    @Operation(summary = "유저정보수정")
    public ResultResponse<Integer> patchUser(@RequestBody UserUpdateReq p) {
        int result = userService.patchUser(p);

        return ResultResponse.<Integer>builder()
                .statusCode(result == 1 ? "200" : "400")
                .resultMsg(userMessage.getMessage())
                .resultData(result)
                .build();
    }

    @DeleteMapping
    @Operation(summary = "회원탈퇴")
    public ResultResponse<Integer> deleteUser(@ParameterObject @ModelAttribute UserDeleteReq p) {
        int result = userService.deleteUser(p);
        UserDeleteRes res = new UserDeleteRes();
        return ResultResponse.<Integer>builder()
                .statusCode(result == 1 ? "200" : "400")
                .resultMsg(res.getMessage())
                .build();
    }


}
