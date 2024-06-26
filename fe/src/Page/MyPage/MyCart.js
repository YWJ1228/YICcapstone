import { useState, useEffect } from "react";
import { getCookies } from "../../Component/Cookies/LoginCookie";
import { API } from "../../Config/APIConfig";
import { onClickPayment } from "../Credit/Credit";
import axios from "axios";
import { Row, Col, Container, Button, Stack } from "react-bootstrap";
import classes from "./MyCart.module.css";
import { createChainedFunction } from "@mui/material";
import AlertModal from "../../Component/Modal/AlertModal/AlertModal";
import AlertModal2 from "../../Component/Modal/AlertModal/AlertModal2";
export default function MyCart(props) {
  const [selectedPrd, setSelectedPrd] = useState({
    id: -1,
    type: "book",
  });
  const [creditInfo, setCreditInfo] = useState({
    productId: "defaultId", // 주문번호
    price: 0, // 결제금액
    name: "", // 주문명
    buyer_name: "name", // 구매자 이름
    buyer_tel: "phone", // 구매자 전화번호
    buyer_email: "email@email.com", // 구매자 이메일
    buyer_addr: "address", // 구매자 주소
    buyer_postcode: "01181",
  });
  const [message, setMessage] = useState("결제할 상품이 없습니다!");
  const [show, setShow] = useState(false);
  const showModal = () => {
    setShow(true);
  };
  const hideModal = () => {
    setShow(false);
  };
  const [message2, setMessage2] = useState("정말로 삭제하시겠습니까?");
  const [show2, setShow2] = useState(false);
  const showModal2 = () => {
    if (selectedPrd.id === -1) {
      setMessage("선택된 상품이 없습니다!");
      setShow(true);
    } else {
      setShow2(true);
    }
  };
  const hideModal2 = () => {
    setShow2(false);
  };
  let totalPrice = 0;
  const renderFunc = props.renderFunc;
  const renderVal = props.renderVal;
  const ebookCart = props.cartEbooks;
  const voiceCart = props.cartVoices;
  const renderEbooks =
    ebookCart !== undefined &&
    ebookCart.map((book, idx) => {
      totalPrice += book.price;
      return (
        <div
          key={idx}
          onClick={() => {
            setSelectedPrd({ id: book.id, type: "book" });
          }}
          className={selectedPrd.id === book.id && selectedPrd.type === "book" ? classes["selected-item"] : classes["card-wrapper"]}
        >
          <img src={book.imageUrl} className={classes["book-image"]} />
          <div className={classes["book-name"]}>{book.ebookName}</div>
        </div>
      );
    });
  const renderVoices =
    voiceCart !== undefined &&
    voiceCart.map((voice, idx) => {
      totalPrice += voice.price;
      return (
        <div
          key={idx}
          onClick={() => {
            setSelectedPrd({ id: voice.id, type: "voice" });
          }}
          className={selectedPrd.id === voice.id && selectedPrd.type === "voice" ? classes["selected-item"] : classes["card-wrapper"]}
        >
          <img src={voice.imageUrl} className={classes["voice-image"]} />
          <div className={classes["book-name"]}>{voice.celebrityName}</div>
        </div>
      );
    });
  function deletePrdFromCart() {
    axios
      .delete(`${selectedPrd.type === "book" ? API.DELETE_EBOOKITEM_CART : API.DELETE_VOICEITEM_CART}/${selectedPrd.id}`, {
        headers: {
          Authorization: `Bearer ${getCookies("accessToken")}`,
        },
        data: {},
      })
      .then((res) => {})
      .catch((err) => {
        // console.log(err);
      });
    renderFunc(renderVal + 1);
  }
  function buyProducts() {
    if (ebookCart.length + voiceCart.length === 0) {
      showModal();
    } else {
      ebookCart.map((book) => {
        creditInfo.price += book.price;
      });
      voiceCart.map((voice) => {
        creditInfo.price += voice.price;
      });
      if (ebookCart.length !== 0) {
        creditInfo.name = ebookCart[0].ebookName;
      } else {
        creditInfo.name = voiceCart[0].celebrityName;
      }
      const prdName = `${ebookCart.length + voiceCart.length > 1 ? " 외 " + (ebookCart.length + voiceCart.length - 1) + "건" : ""}`;
      onClickPayment({ ...creditInfo, name: creditInfo.name + prdName });
    }
  }
  return (
    <Container>
      <AlertModal value={{ show, message }} func={hideModal} />
      <AlertModal2 value={{ show2, message2,selectedPrd }} func={hideModal2} activeFunc={deletePrdFromCart} initSelected = {setSelectedPrd}/>
      <Row>
        <Col className={classes.title}>E-Book</Col>
      </Row>
      <Row>
        <Stack direction="horizontal" className={classes.stack}>
          {ebookCart.length !== 0 ? renderEbooks : <div className={classes["no-item"]}>장바구니에 상품이 없습니다</div>}
        </Stack>
      </Row>
      <Row>
        <Col className={classes.title}>TTS</Col>
      </Row>
      <Row>
        <Stack direction="horizontal" className={classes.stack}>
          {voiceCart.length !== 0 ? renderVoices : <div className={classes["no-item"]}>장바구니에 상품이 없습니다</div>}
        </Stack>
      </Row>
      <Row>
        <Col className={classes["price-guide"]}>
          총 주문 금액 : <span style={{ color: "red" }}>{totalPrice}</span>원
        </Col>
      </Row>
      <Row>
        <Col className={classes["button-wrapper"]}>
          <Button className={classes.btn} onClick={showModal2}>
            선택 삭제
          </Button>
        </Col>
        <Col className={classes["button-wrapper"]}>
          <Button className={classes.btn} onClick={buyProducts}>
            모두 구매
          </Button>
        </Col>
      </Row>
    </Container>
  );
}
