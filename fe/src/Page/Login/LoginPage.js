import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';


import classes from './LoginPage.module.css';

export default function LoginPage() {

    return (
        <div className={classes.wrapper}>
            <Container className={classes.logo}><Row><Col >최애의 보이스</Col></Row></Container>
            <Card className={classes.card}>
                <Card.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>아이디</Form.Label>
                            <Form.Control type="text" placeholder="아이디"/>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formBasicPassword">
                            <Form.Label>비밀번호</Form.Label>
                            <Form.Control type="password" placeholder="비밀번호" />
                        </Form.Group>
                        <Button className={classes['login_btn']} variant="primary" type="submit">
                            로그인
                        </Button>
                    </Form>
                </Card.Body>
            </Card>
            <Container className={classes['option-wrapper']}>
                <Row>
                    <Col><a href="/search-pwd" className={classes.option}>비밀번호 찾기</a></Col>
                    <Col><a href="/search-id" className={classes.option}>아이디 찾기</a></Col>
                    <Col><a href="/register" className={classes.option}>회원가입</a></Col>
                </Row>
            </Container>
        </div>
    );
};

