import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './BookPreview.module.css';

/**
    이달의 책
    이달의 가장 인기 있는 책을 만나보세요

 */

export default function BookPreview(props) {
    // 각 상품 카드에 대한 코드
    const imageArr = (props.images).map((img) => {
        return (
            <Col key={img.id}>
                <a href={"/bookDetail/" + img.id}>
                    <Row><img src={img.image} className={classes.image} /></Row>
                </a>
                <Row className={classes.name}><div>{img.name}</div></Row>
                <Row className={classes.author}><div>{img.author}</div></Row>
            </Col>);
    });
    return (
        <Container>
            <Row className={classes.title}>{props.title}</Row>
            <Row>
                {imageArr}
            </Row>
        </Container>

    );
};