import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import Button from 'react-bootstrap/Button';
import classes from './BannerCard.module.css';

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
                        <Col xs={3}>
                            <Button className={classes.price}>{props.price}</Button>
                            </Col>
                            <Col>
                                <div className = {classes.sale}>{props.salesDescription} </div>
                            </Col>
                    </Row>
                </Col>
            </Row>
        </Container>
    );
};