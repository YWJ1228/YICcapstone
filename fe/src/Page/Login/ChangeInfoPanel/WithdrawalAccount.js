import { useState } from "react";
import { getCookies, removeCookies } from "../../../Component/Cookies/LoginCookie";
import { Button, Form } from "react-bootstrap/";
import { API } from "../../../Config/APIConfig";
import { useNavigate } from "react-router-dom";

import axios from "axios";
import classes from "./ChangePanel.module.css";
export default function WithdrawalAccount() {
  const homeNavigate = useNavigate();
  const [validSubmit, setValidSubmit] = useState();
  function userWithdrawalClickHandler(event) {
    event.preventDefault();
    axios
      .delete(
        `${API.DELETE_USER}`,
        {
          headers: {
            Authorization: `Bearer ${getCookies("accessToken")}`,
          },
          data : {checkPassword: event.target.curPwd.value.trim()}
        }
      )
      .then(function (response) {
        removeCookies("accessToken");
        homeNavigate("/");
      })
      .catch((err) => {
        err.response.data.message !== undefined ? setValidSubmit(err.response.data.message) : setValidSubmit(err.response.data.detail);
      });
  }
  return (
    <div className = {classes.wrapper}>
      <Form onSubmit = {userWithdrawalClickHandler}>
        <Form.Group className="mb-3" controlId="formCheckPwd">
          <Form.Label>현재 비밀번호 확인</Form.Label>
          <Form.Control type="password" name="curPwd" />
          <Form.Text className = {classes.warning}>{validSubmit}</Form.Text>
        </Form.Group>
        <Button type="submit" className={classes["submit"]}>
          회원 탈퇴
        </Button>
      </Form>
      </div>
  );
}
