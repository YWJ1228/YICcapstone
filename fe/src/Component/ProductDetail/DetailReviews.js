import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './DetailReviews.module.css';

// id를 마스킹 해주는 함수
function userMasking(user) {
    var maskedStart = user.substr(0, 3);
    const maskedLength = user.length - (3 + user.length / 3);
    // 마스킹 길이가 2보다 작을 경우 2로 설정
    if (maskedLength < 2) { maskedLength = 2; }
    var maskedLast = user.substr(-maskedLength,)

    maskedStart += '*'.repeat(maskedLength);
    maskedStart += maskedLast;
    return maskedStart;
}

export default function DetailReviews(props) {


    const reviewArr = (props.reviews).map((review) => {
        return (
            <div className={classes['review-wrapper']}>
                <Row>
                    <Col>
                        <Row className={classes.userid}>{userMasking(review.id)}</Row>
                        <Row className={classes.review}>{review.text}</Row>
                        <Row className={classes['upload-date']}>Upload :  {review.uploadDate}</Row>
                    </Col>
                </Row>
            </div>
        );
    })
    return (
        <>

            <Container>
                <Row>
                    <Col><div className={classes.title}>{props.title}</div></Col>
                </Row>
                <Row>
                    <Col>
                        {reviewArr}
                    </Col>
                </Row>
            </Container>
        </>
    );
}