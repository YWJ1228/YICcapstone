import { useEffect, useState } from 'react';
import { API } from '../../Config/APIConfig';

import axios from 'axios';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './SearchPwd.module.css';
import FormGroup from 'react-bootstrap/esm/FormGroup';

export default function ChangePwd() {
    function submitHandler(event){
        event.preventDefault();
        if(event.target.pwd.value.trim() === event.target.pwdSame.value.trim()){
            axios.patch(`${API.FIND_PWD}`,{
                username : event.target.email.value.trim(),
                changePassword : event.target.pwd.value.trim()
            }).then((res)=>{
                console.log(res);
            }).catch((err)=>{console.log(err);})
        }
        else{
            alert("비밀번호가 일치하지 않습니다");
        }
        
    }
    return (
        <div >
            <Container className={classes.logo}><Row><Col >비밀번호 변경</Col></Row></Container>
            <Card className={classes.card}>
                <Card.Body>
                    <Form onSubmit={submitHandler}>
                    <Form.Group className="mb-3" controlId="formEmail">
                            <Form.Label>아이디</Form.Label>
                            <Form.Control type="emil" name="email" />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formPwd">
                            <Form.Label>비밀번호</Form.Label>
                            <Form.Control type="password" name="pwd" />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formSamePwd">
                            <Form.Label>비밀번호 확인</Form.Label>
                            <Form.Control type="password" name="pwdSame" />
                        </Form.Group>


                        <Button className={classes['login_btn']} variant="primary" type="submit">
                            비밀번호 변경
                        </Button>
                    </Form>
                </Card.Body>
            </Card>
        </div>
    );
}