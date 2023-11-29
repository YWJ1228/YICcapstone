import { useState } from "react";
import { getCookies } from "../../../Component/Cookies/LoginCookie";
import { Form, Button } from "react-bootstrap/";
import { API } from "../../../Config/APIConfig";

import axios from "axios";
import classes from "./ChangePanel.module.css";
export default function ChangePassword() {
  const [validForm, setValidForm] = useState({
    // 회원가입 유효성 판단
    verifyEqualPwd: false, // 비밀번호 일치 여부
    verifyPwd: false, // 비밀번호 규칙 적합 여부
    verifySubmit: "",
  });
  const [changeForm, setChangeForm] = useState({
    password: "",
  });
  const [guideMessage, setGuideMessage] = useState({
    // 가이드 문구
    guidePwdText: "숫자와 알파벳을 섞어서 8~12자로 작성해주세요",
    guideEqualPwdText: "",
  });
  function passwordSubmitHandler(event) {
    event.preventDefault();
    if (event.target.curPwd.value.trim() === event.target.newPwd.value.trim()) {
      alert("변경할 비밀번호가 현재 비밀번호와 동일합니다!");
    } else {
      axios
        .patch(
          `${API.CHANGE_PWD}`,
          {
            checkPassword: event.target.curPwd.value,
            changePassword: changeForm.password,
          },
          {
            headers: {
              Authorization: `Bearer ${getCookies("accessToken")}`,
            },
          }
        )
        .then((res) => {
          alert("비밀번호 변경 완료!");
          setValidForm({ verifyEqualPwd: false, verifyPwd: false, verifySubmit: "" });
          setChangeForm({ password: "" });
          setGuideMessage({ guidePwdText: "숫자와 알파벳을 섞어서 8~12자로 작성해주세요", guideEqualPwdText: "" });
          Array.from(event.target.elements).forEach((input) => {
            if (input.type === "password") {
              input.value = "";
            }
          });
        })
        .catch((err) => {
          console.log(err);
          err.response.data.message === undefined ? setValidForm({ ...validForm, verifySubmit: err.response.data.detail }) : setValidForm({ ...validForm, verifySubmit: err.response.data.message });
        });
    }
  }
  function pwdChangeHanlder(event) {
    // 비밀번호 규칙 확인
    setChangeForm({
      // 비밀번호 state 설정
      ...changeForm,
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
    if (changeForm.password === event.target.value.trim()) {
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
    <div className={classes.wrapper}>
      <Form onSubmit={passwordSubmitHandler}>
        <Form.Group className="mb-3" controlId="formCheckPwd">
          <Form.Label>현재 비밀번호 확인</Form.Label>
          <Form.Control type="password" name="curPwd" />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formPwd">
          <Form.Label>새 비밀번호</Form.Label>
          <Form.Control type="password" onChange={pwdChangeHanlder} name="newPwd" />
          <Form.Text>{guideMessage.guidePwdText}</Form.Text>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formCheckPwd">
          <Form.Label>새 비밀번호 확인</Form.Label>
          <Form.Control type="password" onChange={equalPwdChangeHandler} />
          <Form.Text>{guideMessage.guideEqualPwdText}</Form.Text>
        </Form.Group>
        <Form.Group className="mb-3" controlId="message">
          <Button type="submit" className={classes["submit"]}>
            비밀번호 변경
          </Button>
          <Form.Text className={classes.warning}>{validForm.verifySubmit}</Form.Text>
        </Form.Group>
      </Form>
    </div>
  );
}
