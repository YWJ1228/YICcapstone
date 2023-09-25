import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import Button from 'react-bootstrap/Button';

import classes from './DetailBanner.module.css';
export default function DetailBanner(props) {
    return (
        <Container >
            <Row>
                <Col xs={1}></Col>
                <Col xs={4}><img src={props.book.image} className={classes.image} /></Col>
                <Col xs={1}></Col>
                {/* // 책 배너와 TTS 배너 */}
                {props.type === "book" ?
                    <Col>
                        <Row className={classes.title}>{props.book.title}</Row>
                        <Row className={classes.detail}>저자 : {props.book.author}</Row>
                        <Row className={classes.detail}>페이지 : {props.book.numPages}</Row>
                        <Row className={classes.detail}>좋아요 : {props.book.numLikes}</Row>
                        <Row className={classes.detail}>업데이트 날짜 : {props.book.updatedDate}</Row>
                        <Row>
                            <Button className={classes['price-button']}>{props.book.price} 원</Button>
                        </Row>
                    </Col> :
                    <Col>
                        <Row className={classes.title}>
                            {props.book.name}

                        </Row>

                        <Row className={classes.detail}>{props.book.job} ( {props.book.jobDescription} )</Row>
                        <Row className={classes.detail}>평점 : {props.book.rating}</Row>
                        <Row className={classes.detail}>업데이트 날짜 : {props.book.updatedDate}</Row>
                        <Row className={classes['price-wrapper']}>
                            <Button className={classes['price-button']}>{props.book.price} 원</Button>
                        </Row>
                        <Row className={classes['demo-wrapper']}><Button className={classes['demo-button']}>데모 듣기</Button></Row>
                    </Col>
                }
            </Row>
        </Container>
    );
};