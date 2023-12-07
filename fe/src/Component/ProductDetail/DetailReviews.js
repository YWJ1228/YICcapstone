import { Container, Row, Col, Button } from "react-bootstrap";
import { API } from "../../Config/APIConfig";
import { getCookies } from "../Cookies/LoginCookie";
import classes from "./DetailReviews.module.css";
import axios from 'axios';

export default function DetailReviews(props) {
  const currentPage = props.curReviewPage;
  const pageCnt = props.totalPages;
  const setCurrentPage = props.setCurPage;
  const type = props.type;
  const prdId = props.id;
  function changeReview(){

  }
  function deleteReview(){
    axios.delete(`${type === 'book' ? API.DELETE_REVIEW_EBOOK : API.DELETE_REVIEW_VOICE}${prdId}`,{
      headers : {Authorization : `Bearer ${getCookies('accessToken')}`}
    })
    .then((res)=>{
      alert('삭제 성공')
    }).catch((err)=>{
      // console.log(err)
    })
  }
  const reviewArr = props.reviews.map((review) => {
    return (
      <div className={classes["review-wrapper"]} key={review.nickName}>
        <Row>
          <Col>
            <Row className={classes.userid}>
              <Col>{review.nickName}</Col>
            </Row>
            <Row className={classes.review}>
              <Col>{review.content}</Col>
            </Row>
            <Row className={classes["upload-date"]}>
              <Col>Upload : {review.time}</Col>
            </Row>
          </Col>
        </Row>
      </div>
    );
  });
  const buttonArr = Array.from({ length: pageCnt }, (v, i) => i + 1).map((pageNum, idx) => {
    return (
      <Button
        key={idx}
        className={pageNum === currentPage + 1 ? classes["page-button-focus"] : classes["page-button"]}
        onClick={() => {
          setCurrentPage(pageNum);
        }}
      >
        {pageNum}
      </Button>
    );
  });
  return (
    <>
      <Container>
        <Row>
          <Col>
            <div className={classes.title}>{props.title}</div>
          </Col>
        </Row>
        <Row>
          <Col>{props.reviews.length === 0 ? <div className={classes["no-review"]}>책을 구매하고 리뷰를 작성해주세요!</div> : reviewArr}</Col>
        </Row>
        <Row>
          <Col className={classes["button-wrapper"]}>{buttonArr}</Col>
        </Row>
      </Container>
    </>
  );
}
