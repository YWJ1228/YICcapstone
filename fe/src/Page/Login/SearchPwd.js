import React, { useEffect, useState } from 'react';
import { API } from '../../Config/APIConfig';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './SearchPwd.module.css';
import FormGroup from 'react-bootstrap/esm/FormGroup';

export default function SearchPwd() {
    const navigate = useNavigate();
    const [ansVerify, setAnsVerify] = useState(null);
    const [form, setForm] = useState({
        email: "default",
        name: "default"
    });
    function emailChangeHandler(event) {
        setForm({
            ...form,
            email: event.target.value.trim()
        })
    }
    function nameChangeHandler(event) {
        setForm({ ...form, name: event.target.value.trim() })
    }
    function verifyClickHandler(event) {
        event.preventDefault();
        axios.post(`${API.FIND_PWD}`, {
            username: form.email,
            name: form.name
        }).then(function (res) {
            setAnsVerify(res.data);
        }).catch(function (err) {
            console.log(err);
            // 400 정규표현식 틀림
            // 404 존재하지 않는 회원
        });
    }

    function submitHandler(event) {
        event.preventDefault();
        if (ansVerify === event.target.verifyNumber.value.trim()) {
            // 이메일 인증 확인
            navigate('/change-pwd');
        }
        else {
            alert("인증번호를 전송하였습니다.");
        }

    }

    return (
        <div >
            <Container className={classes.logo}><Row><Col >비밀번호 찾기</Col></Row></Container>
            <Card className={classes.card}>
                <Card.Body>
                    <Form onSubmit={submitHandler}>
                        <Form.Group className="mb-3" controlId="formEmail">
                            <Form.Label>아이디</Form.Label>
                            <Form.Control type="email" name="email" placeholder="HongilDong@example.com" onChange={emailChangeHandler} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formName">
                            <Form.Label>이름</Form.Label>
                            <Row>
                                <Col><Form.Control type="text" name="name" placeholder="홍길동" onChange={nameChangeHandler} /></Col>
                                <Col>
                                    <Button variant="primary" type="button" onClick={verifyClickHandler} >
                                        인증번호 전송
                                    </Button>
                                </Col>
                            </Row>

                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formVerifyNumber">
                            <Form.Label>인증번호</Form.Label>
                            <Form.Control type="text" name="verifyNumber" placeholder="ABC123" />
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