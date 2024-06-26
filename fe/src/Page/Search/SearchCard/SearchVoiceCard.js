import { useState } from "react";
import { Button, Row, Col, Container } from "react-bootstrap";
import { categoryDict } from "../../../Config/Config";
import { API } from "../../../Config/APIConfig";
import { getCookies } from "../../../Component/Cookies/LoginCookie";
import axios from 'axios';
import AlertModal from "../../../Component/Modal/AlertModal/AlertModal";
import StarRateRoundedIcon from "@mui/icons-material/StarRateRounded";
import StarBorderRoundedIcon from "@mui/icons-material/StarBorderRounded";
import ShoppingCartOutlinedIcon from "@mui/icons-material/ShoppingCartOutlined";
import IconButton from "@mui/material/IconButton";

import classes from "./SearchVoiceCard.module.css";
export default function SearchVoiceCard(props) {
    const [message, setMessage] = useState("장바구니에 담았습니다");
    const [show, setShow] = useState(false);
    const showModal = () => {
      setShow(true);
    };
    const hideModal = () => {
      setShow(false);
    };
    function addCartHandler() {
        axios.post(`${API.ADD_VOICEITEM_CART}/${props.prd.id}`,{},{
          headers: {
            Authorization: `Bearer ${getCookies("accessToken")}`,
          },
        })
        .then((res)=>{
          setMessage(res.data);
          showModal();
        }).catch((err)=>{
          setMessage((err.response.status === 403 ? '로그인이 필요합니다!':err.response.data.message));
          showModal();
        });
      }
    return (
        <>
            <AlertModal value={{ show, message }} func={hideModal} />
            <Row className={classes.wrapper} md={12} xs={12}>
                <Col md={3}>
                    <a href={`/voiceDetail/${props.prd.id}`}>
                        <img src={props.prd.imageUrl} className={classes.image} />
                    </a>
                </Col>
                <Col md={5}>
                    <Row>
                        <a href={`/voiceDetail/${props.prd.id}`} className={classes.title}>
                            [{categoryDict[props.prd.category]}] {props.prd.celebrityName}
                        </a>
                    </Row>
                    <Row className={classes.price}>
                        <Col>
                            <b>{props.prd.price}</b>
                            <span style={{ "font-size": "0.8rem" }}>원</span>
                        </Col>
                    </Row>
                    <Row className={classes.rating}>
                        <Col>
                            조회수 : <span className={classes["rating-num"]}>{props.prd.viewCount}</span>
                        </Col>{" "}
                    </Row>
                    <Row className={classes.rating}>
                        <Col>
                            구매 횟수 : <span className={classes["rating-num"]}>{props.prd.purchaseCount}</span>
                        </Col>{" "}
                    </Row>
                </Col>
                <Col md={1} className={classes["cart-button-wrapper"]}>
                    <IconButton aria-label="add to shopping cart" className={classes["cart-icon"]} onClick = {addCartHandler}>
                        <ShoppingCartOutlinedIcon>add_circle</ShoppingCartOutlinedIcon>
                    </IconButton>
                </Col>
            </Row>
            <Row>
                <Col md={9}>
                    <hr></hr>
                </Col>
            </Row>
        </>
    );
}
