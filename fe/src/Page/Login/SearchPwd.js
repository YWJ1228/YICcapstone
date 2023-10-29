import { useEffect } from 'react';
import { API } from '../../Config/APIConfig';

import axios from 'axios';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './SearchPwd.module.css';

export default function SearchPwd() {
    function submitHandler(event) {
        event.preventDefault();
        axios.post(`${API.FIND_PWD}`, {
            usename: event.target.email.value.trim(),
            name: event.target.name.value.trim()
        }).then(function (res) {
            console.log(res);
        }).catch(function (err) {
            console.log(err);
            // 400 정규표현식 틀림
            // 404 존재하지 않는 회원
        });
    }

return (
    <div >
        <Container className={classes.logo}><Row><Col >비밀번호 찾기</Col></Row></Container>
        <Card className={classes.card}>
            <Card.Body>
                <Form onSubmit={submitHandler}>
                    <Form.Group className="mb-3" controlId="formEmail">
                        <Form.Label>아이디</Form.Label>
                        <Form.Control type="email" name="email" placeholder="HongilDong@example.com" />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formName">
                        <Form.Label>이름</Form.Label>
                        <Form.Control type="text" name="name" placeholder="홍길동" />
                    </Form.Group>

                    <Button className={classes['login_btn']} variant="primary" type="submit">
                        비밀번호 찾기
                    </Button>
                </Form>
            </Card.Body>
        </Card>
    </div>
);
}