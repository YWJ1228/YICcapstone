import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './BookPreview.module.css';



export default function BookPreview(props) {
    const imageArr = (props.images).map((img) => {
        return (
            <Col key={img.id}>
                <Row><a href = {img.link}><img src={img.image} className={classes.image} /></a></Row>
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