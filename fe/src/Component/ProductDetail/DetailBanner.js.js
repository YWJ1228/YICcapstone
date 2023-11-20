import axios from "axios";
import { useState } from "react";
import { API } from "../../Config/APIConfig";
import { Container, Row, Col, Button } from "react-bootstrap";

import { onClickPayment } from "../../Page/Credit/Credit";

import classes from "./DetailBanner.module.css";
import { getCookies } from "../Cookies/LoginCookie";
import { HeartButton } from "../Button/HeartButton";
import { useNavigate } from "react-router-dom";
import AlertModal from "../Modal/AlertModal/AlertModal";


export default function DetailBanner(props) {
    const navigateHome = useNavigate();
  const creditInfo = {
    productId: "defaultId", // 주문번호
    price: 0, // 결제금액
    name: "productName", // 주문명
    buyer_name: "name", // 구매자 이름
    buyer_tel: "phone", // 구매자 전화번호
    buyer_email: "email@email.com", // 구매자 이메일
    buyer_addr: "address", // 구매자 주소
    buyer_postcode: "01181",
  };
  function creditClickHandler() {
    axios
      .get(`${props.type === "book" ? API.LOAD_EBOOK : API.LOAD_VOICE}/${props.book.id}`, { headers: { Authorization: getCookies("accessToken") } })
      .then((response) => {
        onClickPayment({
          ...creditInfo,
          price: response.data.price,
          name: props.type === "book" ? response.data.ebookName : response.data.celebrityName,
          productId: response.data.id,
        });
      })
      .catch((err) => {
        console.log(err);
      });
  }
  const [message, setMessage] = useState('로그인이 필요합니다!');
  const [show, setShow] = useState(false);
  const showModal = () => {setShow(true)};
  const hideModal = () => {setShow(false)};
  return (
    <Container>
        <AlertModal value = {{show, message}} func = {hideModal} navigateType = 'login'/>
      <Row>
        <Col xs={1}></Col>
        <Col xs={4}>
          <img src={props.book.image} className={props.type === 'book' ? classes.image : classes['voice-image']} />
        </Col>
        <Col xs={1}></Col>
        {/* // 책 배너와 TTS 배너 */}
        {props.type === "book" ? (
          <Col>
            <Row className={classes.title}>{props.book.title}</Row>
            <Row className={classes.detail}>저자 : {props.book.author}</Row>
            <Row className={classes.detail}>페이지 : {props.book.numPages}</Row>
            <Row className={classes.detail}>평점 : {props.book.numLikes}</Row>
            <Row className={classes.detail}>업로드 날짜 : {props.book.updatedDate}</Row>
            <Row>
              <Button
                className={classes["price-button"]}
                onClick={() => {
                  getCookies("accessToken") === undefined ?showModal() : creditClickHandler();
                }}
              >
                {props.book.price} 원
              </Button>
            </Row>
          </Col>
        ) : (
          <Col>
            <Row className={classes.title}>
              <Col xs="auto">{props.book.name}</Col>
              <Col>
                <HeartButton id={props.book.id} />
              </Col>
            </Row>
            <Row className={classes.detail}>
              {props.book.job} ( {props.book.jobDescription} )
            </Row>
            <Row className={classes.detail}>좋아요 : {props.book.rating}</Row>
            <Row className={classes.detail}>업로드 날짜 : {props.book.updatedDate}</Row>
            <Row className={classes["price-wrapper"]}>
              <Button
                className={classes["price-button"]}
                onClick={() => {
                  getCookies("accessToken") === undefined ? showModal() : creditClickHandler();
                }}
              >
                {props.book.price} 원
              </Button>
            </Row>
            <Row className={classes["demo-wrapper"]}>
              <Button className={classes["demo-button"]} onClick={props.demoFn}>
                데모 듣기
              </Button>
            </Row>
          </Col>
        )}
      </Row>
    </Container>
  );
}
