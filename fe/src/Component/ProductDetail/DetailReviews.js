import {Container,Row,Col,Button} from 'react-bootstrap';

import classes from './DetailReviews.module.css';


export default function DetailReviews(props) {
    const currentPage = props.curReviewPage;
    const pageCnt = props.totalPages;
    const setCurrentPage = props.setCurPage;
    const reviewArr = (props.reviews).map((review) => {
        return (
            <div className={classes['review-wrapper']} key = {review.userName}>
                <Row>
                    <Col>
                        <Row className={classes.userid}>{review.userName}</Row>
                        <Row className={classes.review}>{review.content}</Row>
                        <Row className={classes['upload-date']}>Upload :  {review.time}</Row>
                    </Col>
                </Row>
            </div>
        );
    })
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
                    <Col><div className={classes.title}>{props.title}</div></Col>
                </Row>
                <Row>
                    <Col>
                        {props.reviews.length === 0  ?  <div className = {classes['no-review']}>책을 구매하고 리뷰를 작성해주세요!</div>:reviewArr}
                    </Col>
                </Row>
                <Row>
                    <Col className = {classes['button-wrapper']}>{buttonArr}</Col>
                </Row>
            </Container>
        </>
    );
}