import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { API } from "../../Config/APIConfig";
import { getCookies,removeCookies } from "../../Component/Cookies/LoginCookie";

import axios from "axios";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import AccountCircleRoundedIcon from '@mui/icons-material/AccountCircleRounded';

import classes from "./MyPageBanner.module.css";

export default function MyPageBanner() {
  const [userName, setUserName] = useState("oo");
  const navigate = useNavigate();

  useEffect(() => {
    axios.all([axios.get(`${API.USER_INFO}`,{
      headers : {
        Authorization : `Bearer ${getCookies('accessToken')}`
      }
    })]).then(
      axios.spread((res1) => {
        setUserName(res1.data.givenName);
      })
    ).catch((err)=>{
      if(err.response.status === 403){
        alert('로그인이 만료되었습니다!');
        removeCookies('accessToken');
        navigate('/');
      }
    });
  }, []);

  return (
    <>
      <Container>
        <Row>
          <Col xs={4}>
    <AccountCircleRoundedIcon className = {classes.image} sx={{fontSize : 120}}/>
          </Col>
          <Col>
            <Row className={classes.greeting}>{userName}님 반갑습니다!</Row>
            <Row>
              <Button href="/changeinfo" className={classes["changeInfo-btn"]}>
                개인정보수정
              </Button>
              <Button href="/player" className={classes["changeInfo-btn"]}>
                오디오북 재생
              </Button>
            </Row>
          </Col>
        </Row>
      </Container>
      <div className={classes.divider}></div>
    </>
  );
}
