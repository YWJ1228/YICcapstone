import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';

import Stack from 'react-bootstrap/Stack';

import classes from './MyPageReview.module.css';

export default function MyPageReview() {
    const dummyList = [
        { imgPath: "./logo192.png", title: "어린왕자", author: "생텍쥐페리" },
        { imgPath: "./logo192.png", title: "어린왕자", author: "생텍쥐페리" },
        { imgPath: "./logo192.png", title: "어린왕자", author: "생텍쥐페리" }];
    const requiredReviewBooks = dummyList.map((book) => {
        return (
            <Row>
                <Col xs="auto" ><img src={book.imgPath} className={classes.image} /></Col>
                <Col xs={8}>
                    <Row className={classes.title}>{book.title}</Row>
                    <Row className={classes.author}>{book.author}</Row>
                    <Row>
                        <Stack direction="horizontal" gap={3}>
                            <textarea className={classes['review-text']} placeholder='리뷰를 입력하세요'></textarea>
                            <Button className={classes['review-btn']}>작성</Button>
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