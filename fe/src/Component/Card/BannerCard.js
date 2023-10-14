import { Link } from 'react-router-dom';

import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './BannerCard.module.css';
// 홈페이지 Carousel에 들어가는 배너 카드
export default function BannerCard(props) {
    return (
        <Container className={classes.wrapper}>
            <Row>
                <Col xs={4}>
                    <img src={props.imagePath} />
                </Col>
                <Col>
                    <Row className={classes.title}>{props.title}</Row>
                    <Row className={classes.description}>{props.description}</Row>
                    <Row>
                        <Link to={props.link}>
                            <button className={classes.detail}>자세히 보기</button>
                        </Link>
                    </Row>
                </Col>
            </Row>
        </Container>
    );
};