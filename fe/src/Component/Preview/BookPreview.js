import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './BookPreview.module.css';



export default function BookPreview(props) {
    const imageArr = (props.images).map((image) => {
        return <Col><img src = {image} className = {classes.image}/></Col>;
    });
    return (
        <Container>
            <Row className={classes.title}>이달의 책</Row>
            <Row>
                <Col className={classes['sub-title']}>
                    이 달의 가장 인기있는 책을 만나보세요
                </Col>
                <Col xs={1}>
                    <a href={props.link} className={classes.more}>&lt;더보기</a>
                </Col>
            </Row>
            <Row >
                {imageArr}
            </Row>
        </Container>

    );
};