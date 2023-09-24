import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import Button from 'react-bootstrap/Button';

import classes from './BookDetailBanner.module.css';
export default function BookDetailBanner(props) {
    return (
        <Container >
            <Row>
                <Col><img src={props.book.image} className={classes.image} /></Col>
                <Col>
                    <Row className={classes.title}>{props.book.title}</Row>
                    <Row className={classes.detail}>저자 : {props.book.author}</Row>
                    <Row className={classes.detail}>페이지 : {props.book.numPages}</Row>
                    <Row className={classes.detail}>좋아요 : {props.book.numLikes}</Row>
                    <Row className={classes.detail}>업데이트 날짜 : {props.book.updatedDate}</Row>
                    <Row className={classes.price}><Button>{props.book.price} 원</Button></Row>
                </Col>
            </Row>
        </Container>
    );
};