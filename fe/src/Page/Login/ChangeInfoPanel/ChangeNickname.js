import { useState, useEffect } from "react";
import { getCookies } from "../../../Component/Cookies/LoginCookie";
import { Form, Button } from "react-bootstrap/";
import { API } from "../../../Config/APIConfig";

import axios from "axios";
import classes from "./ChangePanel.module.css";
export default function ChangeNickname() {
  const [submitValid, setSubmitValid] = useState("");
  const [currentNickname, setCurrentNickname] = useState();
  useEffect(() => {
    axios
      .get(`${API.USER_INFO}`, {
        headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
      })
      .then((res) => {
        setCurrentNickname(res.data.nickname);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [currentNickname]);
  function nicknameSubmitHandler(event) {
    event.preventDefault();
    axios
      .patch(
        `${API.CHANGE_NICKNAME}`, // url
        { nickname: event.target.nickname.value.trim() }, // data
        {
          headers: {
            Authorization: `Bearer ${getCookies("accessToken")}`,
          },
        }
      )
      .then(function () {
        alert("닉네임 변경 완료!");
        setSubmitValid("");
        event.target.nickname.value = "";
        setCurrentNickname(event.target.nickname.value.trim());
      })
      .catch(function (error) {
        (error.response.data.detail === undefined ? setSubmitValid(error.response.data.message) : setSubmitValid(error.response.data.detail));
      });
  }
  return (
    <div className={classes.wrapper}>
      <Form onSubmit={nicknameSubmitHandler}>
        <Form.Group className="mb-3" controlId="formGridName">
          <Form.Label>닉네임</Form.Label>
          <Form.Control type="text" name="nickname" placeholder={`현재 닉네임 : ${currentNickname}`} />
          <Form.Text className={classes.warning}>{submitValid}</Form.Text>
          <Button type="submit" className={classes["submit"]}>
            닉네임 변경
          </Button>
        </Form.Group>
      </Form>
    </div>
  );
}
