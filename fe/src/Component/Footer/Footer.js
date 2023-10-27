import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './Footer.module.css';
export default function Footer() {
    return (
        <>
            <div className={classes.wrapper}>
                <Container>
                    <Row><Col className={classes.title}>최애의 보이스</Col></Row>
                    <Row>
                        <Col className = {classes.info}>
                            <Row className={classes['sub-title']}>
                                <Col md = "auto">만든 이 : </Col>
                            </Row>
                            <Row className={classes.maker} >
                                <Col  md = "auto" >유우준</Col>
                                <Col md = "auto">학번 : 12181636</Col>
                                <Col md = "auto" >e-mail : dnwns0913@naver.com</Col>
                            </Row>
                            <Row className={classes.maker} >
                                <Col  md = "auto">임태훈</Col>
                                <Col md = "auto">학번 : 12181671</Col>
                                <Col md = "auto">e-mail : xogns9647@naver.com</Col>
                            </Row>
                            <Row className={classes.maker} >
                                <Col  md = "auto">최준성</Col>
                                <Col md = "auto">학번 : 12181697</Col>
                                <Col md = "auto">e-mail : castlechoi@naver.com</Col>
                            </Row>
                        </Col>
                    </Row>

                </Container>
            </div>
        </>
    );
}