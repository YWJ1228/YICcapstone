import axios from "axios";
import { useState } from "react";
import { API } from "../../Config/APIConfig";
import { Container, Row, Col, Button } from "react-bootstrap";

import { onClickPayment } from "../../Page/Credit/Credit";

import classes from "./DetailBanner.module.css";
import { getCookies } from "../Cookies/LoginCookie";
import { KeepButton } from "../Button/KeepButton";
import { useNavigate } from "react-router-dom";
import AlertModal from "../Modal/AlertModal/AlertModal";
import ShoppingCartOutlinedIcon from "@mui/icons-material/ShoppingCartOutlined";
import IconButton from "@mui/material/IconButton";
import { LikeButton } from "../Button/LikeButton";

export default function DetailBanner(props) {
  const prdId = props.prdId;
  const navigateHome = useNavigate();
  const [message, setMessage] = useState("로그인이 필요합니다!");
  const [show, setShow] = useState(false);
  const showModal = () => {
    setShow(true);
  };
  const hideModal = () => {
    setShow(false);
  };
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
  function addCartHandler() {
    axios
      .post(
        `${props.type === "book" ? API.ADD_EBOOKITEM_CART : API.ADD_VOICEITEM_CART}/${prdId}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${getCookies("accessToken")}`,
          },
        }
      )
      .then((res) => {
        setMessage(res.data);
        showModal();
      })
      .catch((err) => {
        if (err.response.status === 403) {
          setMessage("로그인이 필요합니다!");
        } else {
          setMessage(err.response.data.message);
        }
        showModal();
      });
  }
  function creditClickHandler() {
    if (props.book.price !== 0) {
      setMessage("로그인이 필요합니다!");
      axios
        .get(`${props.type === "book" ? API.LOAD_EBOOK : API.LOAD_VOICE}/${prdId}`, { headers: { Authorization: getCookies("accessToken") } })
        .then((response) => {
          onClickPayment({
            ...creditInfo,
            price: response.data.price,
            name: props.type === "book" ? response.data.ebookName : response.data.celebrityName,
            productId: response.data.id,
          });
        })
        .catch((err) => {
          // console.log(err);
        });
    } else {
      // 무료 구매 추가
      axios
        .post(
          `${props.type === "book" ? API.PURCHASE_EBOOK : API.PURCHASE_VOICE}`,
          {
            itemId: prdId,
            orderId: 0,
            paymentMethod: "CreditCard",
            price: 0,
          },
          {
            headers: {
              Authorization: `Bearer ${getCookies("accessToken")}`,
            },
          }
        )
        .then((res) => {
          setMessage("구매가 완료되었습니다!");
          showModal();
        })
        .catch((err) => {
          alert(err.response.data.message);
        });
    }
  }
  return (
    <Container>
      <AlertModal value={{ show, message }} func={hideModal} navigateType={message === "로그인이 필요합니다!" && "login"} />
      <Row>
        <Col xs={1}></Col>
        <Col xs={4}>
          <img src={props.book.image} className={props.type === "book" ? classes.image : classes["voice-image"]} />
        </Col>
        <Col xs={1}></Col>
        {/* // 책 배너와 TTS 배너 */}
        {props.type === "book" ? (
          <Col>
            <Row className={classes.title}>
              <Col xs="auto">{props.book.title}</Col>
              <Col>
                <KeepButton id={prdId} type="book" />
              </Col>
              <Col>
                <IconButton aria-label="add to shopping cart"className={props.book.price === 0 ? classes['disabled-cart-icon'] : classes["cart-icon"]}  onClick={addCartHandler} disabled={props.book.price === 0}>
                  <ShoppingCartOutlinedIcon />
                </IconButton>
              </Col>
            </Row>
            <Row className={classes.detail}>저자 : {props.book.author}</Row>
            <Row className={classes.detail}>페이지 : {props.book.numPages}</Row>
            <Row className={classes.detail}>평점 : {props.book.numLikes}</Row>
            <Row className={classes.detail}>업로드 날짜 : {props.book.updatedDate}</Row>
            <Row>
              <Button
                className={classes["price-button"]}
                onClick={() => {
                  getCookies("accessToken") === undefined ? showModal() : creditClickHandler();
                }}
              >
                {props.book.price === 0 ? "무료" : `${props.book.price}원`}
              </Button>
            </Row>
          </Col>
        ) : (
          <Col>
            <Row className={classes.title}>
              <Col xs="auto">{props.book.name}</Col>
              <Col>
                <KeepButton id={prdId} type="voice" />
              </Col>
              <Col>
                <LikeButton id = {prdId}/>
              </Col>
              <Col>
                <IconButton aria-label="add to shopping cart" className={props.book.price === 0 ? classes['disabled-cart-icon'] : classes["cart-icon"]} onClick={addCartHandler} disabled={props.book.price === 0}>
                  <ShoppingCartOutlinedIcon />
                </IconButton>
              </Col>
            </Row>
            <Row className={classes.detail}>
              {props.book.job}
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
                {props.book.price === 0 ? "무료" : `${props.book.price}원`}
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
