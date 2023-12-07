import { Container, Row, Col, Button, Stack } from "react-bootstrap";
import { useState } from "react";
import { API } from "../../Config/APIConfig";
import { getCookies } from "../../Component/Cookies/LoginCookie";
import axios from "axios";

import StarRateRoundedIcon from "@mui/icons-material/StarRateRounded";
import StarBorderRoundedIcon from "@mui/icons-material/StarBorderRounded";

import classes from "./MyPageReview.module.css";

export default function MyPageReview(props) {
  const renderFunc = props.renderFunc;
  const renderVal = props.renderVal;
  const [rateState, setRateState] = useState(0);
  const idList = props.reviews.map((book) => {
    return book.purchaseId;
  });
  const voiceIdList = props.voiceReview.map((voice)=>{
    return voice.purchaseId;
  })
  function reviewRequestClickHandler(event) {
    event.preventDefault();
    axios
      .post(
        `${API.REVIEW_EBOOK}`,
        {
          purchaseId: event.target.btn.value,
          content: event.target.review.value.trim(),
          grade: rateState,
        },
        {
          headers: {
            Authorization: `Bearer ${getCookies("accessToken")}`,
          },
        }
      )
      .then((res) => {
        alert('리뷰가 작성되었습니다!');
        renderFunc(renderVal + 1);
      })
      .catch((err) => {
        // console.log(err);
      });
  }
  function reviewRequestVoiceClickHandler(event) {
    event.preventDefault();
    axios
      .post(
        `${API.REVIEW_VOICE}`,
        {
          purchaseId: event.target.btn.value,
          content: event.target.review.value.trim()
        },
        {
          headers: {
            Authorization: `Bearer ${getCookies("accessToken")}`,
          },
        }
      )
      .then((res) => {
        alert('리뷰가 작성되었습니다!');
        renderFunc(renderVal + 1);
      })
      .catch((err) => {
        // console.log(err);
      });
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
                <Row>
                  <Col>
                    <Stack direction="horizontal" gap={3}>
                      {rateState > 0 ? (
                        <StarRateRoundedIcon
                          onClick={() => {
                            setRateState(1);
                          }}
                          className={classes.stars}
                        />
                      ) : (
                        <StarBorderRoundedIcon
                          onClick={() => {
                            setRateState(1);
                          }}
                          className={classes.stars}
                        />
                      )}
                      {rateState > 1 ? (
                        <StarRateRoundedIcon
                          onClick={() => {
                            setRateState(2);
                          }}
                          className={classes.stars}
                        />
                      ) : (
                        <StarBorderRoundedIcon
                          onClick={() => {
                            setRateState(2);
                          }}
                          className={classes.stars}
                        />
                      )}
                      {rateState > 2 ? (
                        <StarRateRoundedIcon
                          onClick={() => {
                            setRateState(3);
                          }}
                          className={classes.stars}
                        />
                      ) : (
                        <StarBorderRoundedIcon
                          onClick={() => {
                            setRateState(3);
                          }}
                          className={classes.stars}
                        />
                      )}
                      {rateState > 3 ? (
                        <StarRateRoundedIcon
                          onClick={() => {
                            setRateState(4);
                          }}
                          className={classes.stars}
                        />
                      ) : (
                        <StarBorderRoundedIcon
                          onClick={() => {
                            setRateState(4);
                          }}
                          className={classes.stars}
                        />
                      )}
                      {rateState > 4 ? (
                        <StarRateRoundedIcon
                          onClick={() => {
                            setRateState(5);
                          }}
                          className={classes.stars}
                        />
                      ) : (
                        <StarBorderRoundedIcon
                          onClick={() => {
                            setRateState(5);
                          }}
                          className={classes.stars}
                        />
                      )}
                    </Stack>
                  </Col>
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
  const requiredReviewVoices = props.voiceReview.map((voice, idx) => {
    return (
      <Row key={idx}>
        <Col xs="auto">
          <img src={voice.voiceModel.imageUrl} className={classes.image} />
        </Col>
        <Col xs={8}>
          <Row className={classes.title}>{voice.voiceModel.celebrityName}</Row>
          <Row>
            <Col>
              <form onSubmit={reviewRequestVoiceClickHandler}>
                <Row>
                  <textarea className={classes["review-text"]} name="review" placeholder="리뷰를 입력하세요"></textarea>
                </Row>
                <Row>
                  <Col className={classes["btn-wrapper"]}>
                    <Button type="submit" name="btn" className={classes["review-btn"]} value={voiceIdList[idx]}>
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
  return <Container>{requiredReviewBooks.length !== 0 ? requiredReviewBooks : <div className={classes["no-item"]}>리뷰를 작성할 상품이 없습니다</div>}
  {requiredReviewVoices.length!== 0 ? requiredReviewVoices : <div className={classes["no-item"]}>리뷰를 작성할 상품이 없습니다</div>}</Container>;
}
