import { Container, Row, Col, Button, Stack } from "react-bootstrap";
import { useState } from "react";
import { API } from "../../Config/APIConfig";
import {getCookies}from "../../Component/Cookies/LoginCookie";
import axios from "axios";

import classes from "./MyPageReview.module.css";

export default function MyPageReview(props) {
  const idList = props.reviews.map((book) => {
    return book.id;
  });
  function reviewRequestClickHandler(event) {
    event.preventDefault();
    axios.post(
      `${API.REVIEW_EBOOK}`,
      {
        purchaseId: event.target.btn.value,
        content: event.target.review.value.trim(),
        grade: 5,
      },
      {
        headers: {
          Authorization: `Bearer ${getCookies("accessToken")}`,
        },
      }
    ).then((res)=>{
      console.log(res);
    }).catch((err)=>{
      console.log(err);
    })
  }
  const requiredReviewBooks = props.reviews.map((book, idx) => {
    return (
      <Row key={idx}>
        <Col xs="auto">
          <img src={book.image} className={classes.image} />
        </Col>
        <Col xs={8}>
          <Row className={classes.title}>{book.name}</Row>
          <Row className={classes.author}>{book.author}</Row>
          <Row>
            <Col>
              <form onSubmit={reviewRequestClickHandler}>
                <Row>
                  <textarea className={classes["review-text"]} name="review" placeholder="리뷰를 입력하세요"></textarea>
                </Row>
                <Row >
                  <Col className={classes["btn-wrapper"]}>
                  <Button type="submit" name="btn" className={classes["review-btn"]} value={idList[idx]}>
                    작성
                  </Button>
                  </Col>
                </Row>
              </form>
            </Col>
          </Row>
        </Col>
      </Row>
    );
  });
  return <Container>{props.reviews.length !== 0 ? requiredReviewBooks : <div className = {classes['no-item']}>리뷰를 작성할 상품이 없습니다</div>}</Container>;
}
