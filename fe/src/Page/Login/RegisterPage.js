import { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { API } from "../../Config/APIConfig";

import NavigationBar from "../../Component/NavigationBar/NavigationBar";
import axios from "axios";

import classes from "./RegisterPage.module.css";
import StyleChangeRef from "../../Ref/StyleChangeRef";

import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import ToggleButton from "react-bootstrap/ToggleButton";
import ToggleButtonGroup from "react-bootstrap/ToggleButtonGroup";

import FormGroup from "react-bootstrap/esm/FormGroup";

export default function RegisterPage() {
  const myRef = useRef([]); // css의 변경을 위한 코드
  const navigate = useNavigate(); // 로그인 성공 후에 넘어갈 navigate를 위한 코드

  // ######################################################
  // ################ 데이터를 저장한 State ################
  // ######################################################

  const [userForm, setUserForm] = useState({
    // 서버로 보내는 데이터
    username: "defaultUser",
    password: "defaultPassword",
    name: "defaultName",
    nickname: "defaultNickName",
    birth: "defaultBirth",
    sex: 0,
  });
  const [guideMessage, setGuideMessage] = useState({
    // 가이드 문구
    guideEmailText: "",
    guidePwdText: "숫자, 알파벳과 특수문자를 섞어서 8~12자로 작성해주세요",
    guideEqualPwdText: "",
  });

  const [validForm, setValidForm] = useState({
    // 회원가입 유효성 판단
    verifyText: false, // 서버로부터 받은 인증번호를 저장할 state
    verifyNickname: false, // 닉네임 인증 여부
    verifyEqualPwd: false, // 비밀번호 일치 여부
    verifyPwd: false, // 비밀번호 규칙 적합 여부
  });

  function formChangeHandler(event) {
    // 회원가입 폼의 값이 바뀔 때마다 update
    const { value, name: inputValue } = event.target;
    setUserForm({
      ...userForm,
      [inputValue]: event.target.value.trim(),
    });
  }
  function genderClickHandler(value) {
    setUserForm({
      ...userForm,
      sex: value,
    });
  }

  function validEmailHandler() {
    // 인증번호 확인 & 이메일 보내기 api 호출
    axios
      .get(`${API.VERIFY_EMAIL}/${userForm.username}`)
      .then(function (response) {
        setValidForm({ ...validForm, verifyText: response.data }); // 정답 인증번호 저장
        setGuideMessage({ ...guideMessage, guideEmailText: "" });
      })
      .catch(function (error) {
        console.log(error);
      });
  }
  function validNicknameHandler() {
    // 닉네임 중복확인 api 호출
    axios
      .get(`${API.CHECK_NICKNAME}/${userForm.nickname}`)
      .then(function (response) {
        console.log("valid nickname");
        setValidForm({ ...validForm, verifyNickname: true });
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  // form submit handler
  function submitHandler(event) {
    event.preventDefault();
    // 회원가입 요청 유효성 검사
    if (
      validForm.verifyText === event.target.verifyText.value &&
      validForm.verifyNickname &&
      validForm.verifyPwd &&
      validForm.verifyEqualPwd
    ) {
      console.log("Login Succeeded");
      axios
        .post(`${API.REGISTER_USER}`, userForm)
        .then(function (response) {
          // 로그인 성공
          navigate("/login");
        })
        .catch(function (error) {
          console.log(error);
        });
    }
    const conditions = [
      event.target.username.value !== "", // 이메일
      validForm.verifyText === event.target.verifyText.value, // 인증번호
      validForm.verifyPwd, // 비밀번호
      validForm.verifyEqualPwd, // 비밀번호 확인
      event.target.name.value !== "", // 이름
      validForm.verifyNickname, // 닉네임
      event.target.birth.value !== "", // 생년월일
    ];
    conditions.map((cond, index) => {
      // 유효성 검사 후 invalid field는 디자인 변경
      StyleChangeRef(myRef, index, cond);
    });
  }

  function pwdChangeHanlder(event) {
    // 비밀번호 규칙 확인
    setUserForm({
      // 비밀번호 state 설정
      ...userForm,
      password: event.target.value.trim(),
    });

    // 비밀번호 규칙 확인
    const regex = /(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,20}/;
    const pwdString = event.target.value.trim();
    setValidForm({ ...validForm, verifyPwd: false });
    if (pwdString.length === 0) {
      setGuideMessage({
        ...guideMessage,
        guidePwdText: "숫자와 알파벳을 섞어서 8~12자로 작성해주세요",
      });
    } else if (!(pwdString.length < 21 && pwdString.length >= 8)) {
      setGuideMessage({
        ...guideMessage,
        guidePwdText: "비밀번호 길이는 8~20자입니다",
      });
    } else if (!regex.test(pwdString)) {
      setGuideMessage({
        ...guideMessage,
        guidePwdText: "숫자와 알파벳을 혼합하여 작성해주세요",
      });
    } else {
      setGuideMessage({ ...guideMessage, guidePwdText: "확인" });
      setValidForm({ ...validForm, verifyPwd: true });
    }
  }
  // 비밀번호 확인
  function equalPwdChangeHandler(event) {
    if (userForm.password === event.target.value.trim()) {
      setGuideMessage({ ...guideMessage, guideEqualPwdText: "일치합니다" });
      setValidForm({ ...validForm, verifyEqualPwd: true });
    } else {
      setGuideMessage({
        ...guideMessage,
        guideEqualPwdText: "비밀번호가 다릅니다",
      });
      setValidForm({ ...validForm, verifyEqualPwd: false });
    }
  }

  return (
    <>
      <NavigationBar img_src="logo.png" />
      <div style={{ width: "100%", height: "6rem" }} />
      <div className={classes.wrapper}>
        <Form onSubmit={submitHandler}>
          <Form.Group className="mb-3" controlId="formGridCheckEmail">
            <Form.Label>아이디 생성</Form.Label>
            <Form.Control
              type="email"
              name="username"
              onChange={formChangeHandler}
              placeholder="이메일"
              className={classes.inputBox}
              ref={(el) => (myRef.current[0] = el)}
            />
            <Form.Text>{guideMessage.guideEmailText}</Form.Text>
          </Form.Group>

          <Form.Group className="mb-3" controlId="formVerify">
            <Row className="mb-3">
              <Col>
                <Form.Control
                  type="text"
                  placeholder="인증번호"
                  name="verifyText"
                  className={classes.inputBox}
                  ref={(el) => (myRef.current[1] = el)}
                />
              </Col>
              <Col xs={3} className={classes["input-wrapper"]}>
                <Button
                  className={classes["verify-button"]}
                  type="button"
                  onClick={validEmailHandler}
                >
                  이메일발송
                </Button>
              </Col>
            </Row>
          </Form.Group>

          <Form.Group className="mb-3" controlId="formPwd">
            <Form.Control
              type="password"
              name="password"
              onChange={pwdChangeHanlder}
              placeholder="비밀번호"
              className={classes.inputBox}
              ref={(el) => (myRef.current[2] = el)}
            />
            <Form.Text>{guideMessage.guidePwdText}</Form.Text>
          </Form.Group>
          <Form.Group className="mb-3" controlId="formCheckPwd">
            <Form.Control
              type="password"
              name="checkPassword"
              onChange={equalPwdChangeHandler}
              placeholder="비밀번호 확인"
              className={classes.inputBox}
              ref={(el) => (myRef.current[3] = el)}
            />
            <Form.Text>{guideMessage.guideEqualPwdText}</Form.Text>
          </Form.Group>

          <Form.Group className="mb-3" controlId="formGridName">
            <Row>
              <Form.Label>개인정보</Form.Label>
              <Col>
                <Form.Control
                  name="name"
                  onChange={formChangeHandler}
                  placeholder="성명"
                  className={classes.inputBox}
                  ref={(el) => (myRef.current[4] = el)}
                />
              </Col>

              <Col xs={3} className={classes["input-wrapper"]}>
                <ToggleButtonGroup type="radio" name="options" defaultValue={1}>
                  <ToggleButton
                    className={
                      userForm.sex ? classes.gender : classes["gender-focus"]
                    }
                    id="tbg-radio-1"
                    value={1}
                    onClick={() => genderClickHandler(0)}
                  >
                    남성
                  </ToggleButton>
                  <ToggleButton
                    className={
                      userForm.sex ? classes["gender-focus"] : classes.gender
                    }
                    id="tbg-radio-2"
                    value={2}
                    onClick={() => genderClickHandler(1)}
                  >
                    여성
                  </ToggleButton>
                </ToggleButtonGroup>
              </Col>
            </Row>
          </Form.Group>
          <Form.Group className="mb-3" controlId="formGridNickname">
            <Row>
              <Col>
                <Form.Control
                  type="text"
                  name="nickname"
                  onChange={formChangeHandler}
                  placeholder="닉네임"
                  className={classes.inputBox}
                  ref={(el) => (myRef.current[5] = el)}
                />
              </Col>
              <Col xs={3} className={classes["input-wrapper"]}>
                <Button
                  className={classes["verify-button"]}
                  type="button"
                  name="verifyNickname"
                  onClick={validNicknameHandler}
                >
                  중복확인
                </Button>
              </Col>
            </Row>
          </Form.Group>
          <FormGroup>
            <Row className="mb-3">
              <Form.Group as={Col} controlId="formBirth">
                <Form.Control
                  type="number"
                  name="birth"
                  onChange={formChangeHandler}
                  placeholder="생년월일"
                  className={classes.inputBox}
                  ref={(el) => (myRef.current[6] = el)}
                />
              </Form.Group>
            </Row>
          </FormGroup>

          <Button type="submit" className={classes["submit"]}>
            회원가입 완료
          </Button>
        </Form>
        {/* <SexBtn/> */}
      </div>
    </>
  );
}
