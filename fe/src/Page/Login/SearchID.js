import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './SearchID.module.css';

import axios from 'axios';

// ######################### API #####################
const postSearchIDAPI = "http:localhost:8080/api/find/id";

export default function SearchID() {
    function submitHandler(event) {
        event.preventDefault();
        axios.post(postSearchIDAPI, {
            name: event.target.name.value,
            birth : event.target.birth.value
        }).then(function (res) {
            console.log(res);
            // 일치하는 회원의 list
        }).catch(function (err) {
            console.log(err);
            // 404 존재하지 않는 회원
        });
    }
    return (
        <div>
            <Container className={classes.logo}><Row><Col >아이디 찾기</Col></Row></Container>
            <Card className={classes.card}>
                <Card.Body>
                    <Form onSubmit={submitHandler}>
                        <Form.Group className="mb-3" controlId="formName">
                            <Form.Label>이름</Form.Label>
                            <Form.Control type="text" name = "name" placeholder="홍길동" />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formBirth">
                            <Form.Label>생일</Form.Label>
                            <Form.Control type="number" name = "birth" placeholder="생년월일 6자리 (ex.010101)" />
                        </Form.Group>
                        <Button className={classes['login_btn']} variant="primary" type="submit">
                            아이디 찾기
                        </Button>
                    </Form>
                </Card.Body>
            </Card>
        </div>
    );
}