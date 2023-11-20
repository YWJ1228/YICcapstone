import { useState } from "react";
import { API } from "../../Config/APIConfig";

import axios from "axios";
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import NavigationBar from "../../Component/NavigationBar/NavigationBar";

import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import classes from "./SearchPwd.module.css";
import { useNavigate } from "react-router-dom";

export default function SearchPwd() {
  const navigate = useNavigate();
  const [afterEmail, setAfterEmail] = useState(0);
  const [userEmail, setUserEmail] = useState();
  const [verifyNumber, setVerifyNumber] = useState();
  function emailSubmitHandler(event) {
    event.preventDefault();
    axios
      .post(`${API.FIND_PWD}`, {
        username: event.target.email.value.trim(),
        name: event.target.name.value.trim(),
      })
      .then(function (res) {
        setAfterEmail(1);
        setUserEmail(event.target.email.value.trim());
        setVerifyNumber(res.data);
        Array.from(event.target.elements).forEach((input) => {
          if (input.type === "email" || input.type === 'text') {
            input.value = "";
          }
        });
      })
      .catch(function (err) {
        console.log(err);
      });
  }
  function changePwdHandler(event) {
    event.preventDefault();
    if (event.target.verify.value.trim() === verifyNumber) {
      axios
        .patch(`${API.FIND_PWD}`, {
          username: userEmail,
          changePassword: event.target.password.value.trim(),
        })
        .then((res) => {
          alert(res.data);
          navigate('/login');
        })
        .catch((err) => {
          console.log(err);
        });
    }
    else{
      alert("인증번호가 틀렸습니다");
    }
  }

  return (
    <div>
      <NavigationBar img_src="logo.png" />
      <div style={{ width: "100%", height: "6rem" }} />
      <Container className={classes.logo}>
        <Row>{afterEmail ? <Col> 비밀번호 변경</Col> : <Col>비밀번호 찾기</Col>}</Row>
      </Container>
      <Card className={classes.card}>
        <Card.Body>
          <Form onSubmit={afterEmail ? changePwdHandler : emailSubmitHandler}>
            {afterEmail ? (
              <>
                <Form.Group className="mb-3" controlId="formVerify">
                  <Form.Label>인증번호</Form.Label>
                  <Form.Control type="text" name="verify" placeholder="AAAAAA" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formPwd">
                  <Form.Label>변경할 비밀번호</Form.Label>
                  <Form.Control type="password" name="password" placeholder="" />
                </Form.Group>
              </>
            ) : (
              <>
                <Form.Group className="mb-3" controlId="formEmail">
                  <Form.Label>아이디</Form.Label>
                  <Form.Control type="email" name="email" placeholder="HongilDong@example.com" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formName">
                  <Form.Label>이름</Form.Label>
                  <Form.Control type="text" name="name" placeholder="홍길동" />
                </Form.Group>
              </>
            )}

            {afterEmail ? (
              <Button className={classes["login_btn"]} variant="primary" type="submit">
                비밀번호 변경
              </Button>
            ) : (
              <Button className={classes["login_btn"]} variant="primary" type="submit">
                비밀번호 찾기
              </Button>
            )}
          </Form>
        </Card.Body>
      </Card>
    </div>
  );
}
