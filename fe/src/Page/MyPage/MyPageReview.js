import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';

import Stack from 'react-bootstrap/Stack';

import classes from './MyPageReview.module.css';

export default function MyPageReview(props) {
    function reviewRequestClickHandler(event){
        console.log("리뷰 전송");
    }
    const requiredReviewBooks = (props.reviews).map((book,idx) => {
        return (
            <Row key = {idx}>
                <Col xs="auto" ><img src={book.image} className={classes.image} /></Col>
                <Col xs={8}>
                    <Row className={classes.title}>{book.title}</Row>
                    <Row className={classes.author}>{book.author}</Row>
                    <Row>
                        <Stack direction="horizontal" gap={3}>
                            <textarea className={classes['review-text']} placeholder='리뷰를 입력하세요'></textarea>
                            <Button className={classes['review-btn']} onClick = {reviewRequestClickHandler}>작성</Button>
                        </Stack>
                    </Row>
                </Col>
                {/* for padding */}

            </Row>
        );
    })
    return (
        <Container>
            {requiredReviewBooks}
        </Container>
    );
}