import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { getCookies, setCookies } from "../../Component/Cookies/LoginCookie";

import { API } from "../../Config/APIConfig";

import NavigationBar from "../../Component/NavigationBar/NavigationBar";
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import axios from "axios";

import classes from "./LoginPage.module.css";

export default function LoginPage(props) {
  const navigateHome = useNavigate();
  // 로그인 json 형식
  const [loginForm, setLoginForm] = useState({
    username: "defaultUser",
    password: "defaultPassword",
    loginSuccess: true,
  });

  function formChangeHandler(event) {
    const { value, name: inputValue } = event.target;
    setLoginForm({
      ...loginForm,
      [inputValue]: event.target.value.trim(),
    });
  }

  function submitHandler(event) {
    event.preventDefault();
    // api로 데이터 전송
    if (loginForm.password !== "") {
      axios
        .post(`${API.LOGIN_USER}`, loginForm)
        .then(function (response) {
          setCookies("accessToken", response.headers.getAuthorization());
          navigateHome("/");
        })
        .catch(function (error) {
          // console.log(error);
          setLoginForm({ ...loginForm, loginSuccess: false });
        });
    } else if (loginForm.password === "") {
      alert("비밀번호를 입력해주세요");
    }
  }
  return (
    <>
      <NavigationBar img_src="logo.png" />
      <div style={{ width: "100%", height: "6rem" }} />
      <div className={classes.wrapper}>
        <Container className={classes.logo}>
          <Row>
            <Col>최애의 보이스</Col>
          </Row>
        </Container>
        <Card className={classes.card}>
          <Card.Body>
            <Form onSubmit={submitHandler}>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>아이디</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="아이디"
                  name="username"
                  onChange={formChangeHandler}
                />
              </Form.Group>

              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>비밀번호</Form.Label>
                <Form.Control
                  type="password"
                  placeholder="비밀번호"
                  name="password"
                  onChange={formChangeHandler}
                />
                {!loginForm.loginSuccess && (
                  <div className={classes["login-guide"]}>
                    일치하는 회원정보가 없습니다
                  </div>
                )}
              </Form.Group>

              <Button
                className={classes["login_btn"]}
                variant="primary"
                type="submit"
              >
                로그인
              </Button>
            </Form>
          </Card.Body>
        </Card>
        <Container className={classes["option-wrapper"]}>
          <Row>
            <Col>
              <a href="/search-pwd" className={classes.option}>
                비밀번호 찾기
              </a>
            </Col>
            <Col>
              <a href="/search-id" className={classes.option}>
                아이디 찾기
              </a>
            </Col>
            <Col>
              <a href="/register" className={classes.option}>
                회원가입
              </a>
            </Col>
          </Row>
        </Container>
      </div>
    </>
  );
}
