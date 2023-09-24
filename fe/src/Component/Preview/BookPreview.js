import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './BookPreview.module.css';

/**
 * Redirect 구현 필요해보임
 * API로 해당 img에 대한 api를 쏴서
 * BookDetailPage에 해당 이미지의 제품 상세페이지라 랜더링 되도록
 * 코드를 짜야함.
 * 
 * 일단은 더미코드로 작동하게 구현할 예정
 * 
 * 백엔드와의 연동 필요!!
 * 
 */

export default function BookPreview(props) {
    const imageArr = (props.images).map((img) => {
        return (
            <Col key={img.id}>
                {/* 이 부분은 redirect 구현이 필요해보임 */}
                <a href="/bookDetail">
                    <Row><img src={img.image} className={classes.image} /></Row>
                </a>
                <Row className={classes.name}><div>{img.name}</div></Row>

            </Col>);
    });
    return (
        <Container>
            <Row className={classes.title}>{props.title}</Row>
            <Row>
                <Col className={classes['sub-title']}>{props.subtitle}</Col>
                <Col xs={1} className={classes['more-wrapper']}>
                    <a href={props.link} className={classes.more}>&lt;더보기</a>
                </Col>
            </Row>
            <Row >
                {imageArr}
            </Row>
        </Container>

    );
};