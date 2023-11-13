import { useState, useEffect } from "react";

import { API } from "../../Config/APIConfig";
import { getCookies } from "../../Component/Cookies/LoginCookie";

import axios from "axios";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";

import Dropdown from "react-bootstrap/Dropdown";

import classes from "./MyPageBanner.module.css";

export default function MyPageBanner(props) {
  const [purchasedTTS, setPurchasedTTS] = useState([{ name: "default" }]);
  const [selectedTTS, setSelectedTTS] = useState(0);
  const [userName, setUserName] = useState("oo");

  useEffect(() => {
    axios
      .all([
        axios.get(`${API.USER_INFO}`, { headers: { Authorization: `Bearer ${getCookies("accessToken")}` } }),
        axios.get(`${API.LOAD_MYPAGE_VOICELIST}`, { headers: { Authorization: `Bearer ${getCookies("accessToken")}` } }),
      ])
      .then(
        axios.spread((res1, res2) => {
          setUserName(res1.data.givenName);
          const resData2 = res2.data.content.map((voice) => ({
            name: voice.celebrityName,
          }));
          setPurchasedTTS(resData2);
        })
      );
  }, []);

  const dropItem = purchasedTTS.map((item, idx) => {
    return (
      <Dropdown.Item eventKey={idx} className={classes["dropdown-item"]} key={idx}>
        {item.name}
      </Dropdown.Item>
    );
  });

  return (
    <>
      <Container>
        <Row>
          <Col xs={4}>
            <img src="/logo192.png" className={classes.image} />
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
            <Row className={classes["selected-text"]}>적용중인 TTS</Row>
            <Row className={classes["dropdown-wrapper"]}>
              <Dropdown align="end" onSelect={setSelectedTTS}>
                <Button className={classes["dropdown-content"]} disabled>
                  {purchasedTTS[selectedTTS].name}
                </Button>
                <Dropdown.Toggle split className={classes["dropdown-btn"]}></Dropdown.Toggle>
                <Dropdown.Menu className={classes["dropdown-menu"]}>{dropItem}</Dropdown.Menu>
              </Dropdown>
            </Row>
          </Col>
        </Row>
      </Container>
      <div className={classes.divider}></div>
    </>
  );
}
